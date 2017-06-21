package com.swpuiot.ws.lib.src.lib;

import com.swpuiot.ws.lib.src.lib.callback.MessageCallBack;
import com.swpuiot.ws.lib.src.lib.constant.Config;
import com.swpuiot.ws.lib.src.lib.exception.MqttClientInitException;
import com.swpuiot.ws.lib.src.lib.utils.JsonUtils;
import com.swpuiot.ws.lib.src.lib.utils.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Main class
 * Created by wuhaojie on 17-5-25.
 */
public class Connector<T> {

    /**
     * Singleton instance
     */
    private static volatile Connector DEFAULT_CONNECTOR;

    private String user;
    private String password;
    private int connectionTimeout;
    private int keepAliveInterval;
    private String serverURI;
    private String clientId;
    private Class<T> mMessageClassType;
    private String clientTopic;

    private MqttClient mMqttClient;
    private MqttConnectOptions mOptions;


    private CopyOnWriteArrayList<MessageCallBack<T>> mMessageCallBacks = new CopyOnWriteArrayList<>();
    private boolean mRetrySendMessage = true;


    private Connector() {
    }

    public static <T> Connector<T> defaultConnector(Class<T> tClass, String clientTopic) {
        if (DEFAULT_CONNECTOR == null) {
            synchronized (Connector.class) {
                if (DEFAULT_CONNECTOR == null) {
                    DEFAULT_CONNECTOR = new Builder<T>()
                            .setMessageClassType(tClass)
                            .setClientTopic(clientTopic)
                            .build();
                }
            }
        }
        return DEFAULT_CONNECTOR;
    }


    /**
     * lib.Connector init function.
     * Call this before use it, you could call this when your application start.
     */
    // Couldn't put it in constructor
    public void init() {
        // Mqtt 配置
        mOptions = new MqttConnectOptions();
        mOptions.setCleanSession(true);  // 是否每次连接后清除 session
        mOptions.setUserName(user);
        mOptions.setPassword(password.toCharArray());
        mOptions.setConnectionTimeout(connectionTimeout);   // 设置超时 单位秒
        mOptions.setKeepAliveInterval(keepAliveInterval);   // 心跳时间 每隔 1.5*time 秒发送消息判断客户是否在线
        mOptions.setAutomaticReconnect(true);

        // 创建远程的客户端
        try {
            mMqttClient = new MqttClient(serverURI, clientId, new MemoryPersistence());
        } catch (MqttException e) {
            throw new MqttClientInitException();
        }
        mMqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                reConnect();
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                T t = JsonUtils.fromJson(mqttMessage.toString(), mMessageClassType);
                if (mMessageCallBacks != null && !mMessageCallBacks.isEmpty())
                    mMessageCallBacks.forEach(messageCallBack -> messageCallBack.onNewMessage(t));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
        try {
            connect();
            Log.d("Connect to " + serverURI + " succeed");
        } catch (MqttException e) {
            Log.d("Connect failed. " + e.getMessage());
            reConnect();
        }
        Looper.prepare(message -> {
            try {
                send(message.topic, message.msg);
            } catch (MqttException e) {
                MessageQueue.put(new MessageQueue.QMessage(message.topic, message.msg), 2000);
            }
        });
        Looper.loop();

    }

    private void connect() throws MqttException {
        mMqttClient.connect(mOptions);
        mMqttClient.subscribe(clientTopic, Config.QOS);
    }

    /**
     * A lib.callback for new message arrived.
     *
     * @param callBack lib.callback function
     */
    public void receiveMessage(MessageCallBack<T> callBack) {
        mMessageCallBacks.add(callBack);
    }

    /**
     * Function for send a instance message.
     *
     * @param topic   the topicId of remote client
     * @param message the message you want to send, must be correct type which you set before
     */
    public void sendMessage(String topic, T message) {
        String s = JsonUtils.toJson(message);
        sendMessage(topic, s);
    }

    /**
     * Function for send a json string message
     *
     * @param topic the topicId of remote client
     * @param s     the json string message you want to send
     */
    public void sendMessage(String topic, String s) {
        try {
            send(topic, s);
        } catch (MqttException e) {
            Log.d("Send message failed." + e.getMessage());
            if (mRetrySendMessage) {
                MessageQueue.QMessage qmsg = new MessageQueue.QMessage(topic, s);
                MessageQueue.put(qmsg);
                Log.d("Resend " + qmsg);
            }
        }
    }

    private void send(String topic, String s) throws MqttException {
        MqttTopic mqttTopic = mMqttClient.getTopic(topic);
        mqttTopic.publish(s.getBytes(), Config.QOS, false).waitForCompletion();
    }


    private void reConnect() {
        if (mMqttClient == null) throw new MqttClientInitException();
        try {
            connect();
            Log.d("Reconnect succeed.");
        } catch (MqttException e) {
            Log.d("Reconnect failed. " + e.getMessage() + ". retry later");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                Log.e(e1);
            }
            reConnect();
        }
    }


    public static class Builder<T> {

        private String user = "admin";
        private String password = "password";
        private int connectionTimeout = 10;
        private int keepAliveInterval = 10;
        private String serverURI = "tcp://0.0.0.0:61613";
        private String clientId = "client#" + System.currentTimeMillis();
        private Class<T> mMessageClassType = null;
        private String clientTopic = "mqtt/client";

        public Builder() {
        }

        public Builder<T> setUser(String user) {
            this.user = user;
            return this;
        }

        public Builder<T> setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder<T> setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public Builder<T> setKeepAliveInterval(int keepAliveInterval) {
            this.keepAliveInterval = keepAliveInterval;
            return this;
        }

        public Builder<T> setServerURI(String serverURI) {
            this.serverURI = serverURI;
            return this;
        }

        public Builder<T> setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder<T> setMessageClassType(Class<T> messageClassType) {
            mMessageClassType = messageClassType;
            return this;
        }

        public Builder<T> setClientTopic(String clientTopic) {
            this.clientTopic = clientTopic;
            return this;
        }

        private void apply(Connector<T> connector) {
            connector.user = this.user;
            connector.password = this.password;
            connector.connectionTimeout = this.connectionTimeout;
            connector.keepAliveInterval = this.keepAliveInterval;
            connector.serverURI = this.serverURI;
            connector.clientId = this.clientId;
            connector.mMessageClassType = this.mMessageClassType;
            connector.clientTopic = this.clientTopic;
        }

        public Connector<T> build() {
            Connector<T> connector = new Connector<>();
            apply(connector);
            return connector;
        }

    }


}
