package com.example.elefante_afnc;

public class Articulo {
    private String title;
    private String subtitle;
    private int image;
    private String content;

    public Articulo(String title, String subtitle, int image, String content) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.content = content;
    }

    //getter y setter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
