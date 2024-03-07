package com.ph.bittelasia.mesh.tv.libFragment.control.model;

public class Slide {

    private String path;
    private String name;
    private String content;

    public Slide(Builder builder){
        this.path = builder.path;
        this.name = builder.name;
        this.content = builder.content;
    }

    @Override
    public String toString() {
        return "Slide{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public static class Builder{

        private String path;
        private String name;
        private String content;

        public Builder path(String path){
            this.path = path;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Slide buildSlide(){
            return new Slide(this);
        }

    }
}
