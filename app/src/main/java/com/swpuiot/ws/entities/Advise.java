package com.swpuiot.ws.entities;

/**
 * Created by 羊荣毅_L on 2017/6/20.
 */
public class Advise {
   private int imageId;
   private String title;
   private String content;

    public Advise(int imageId, String title, String content) {
        this.imageId = imageId;
        this.title = title;
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "title:"+title+"\n"
                +"content:"+content;
    }
}
