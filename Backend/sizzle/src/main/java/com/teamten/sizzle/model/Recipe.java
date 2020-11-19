package com.teamten.sizzle.model;

import org.springframework.data.annotation.Id;

public class Recipe {
    private String title;
    private String summary;
    private String instructions;
    private String image;
    @Id
    private int id;

    public Recipe(String title, String summary, String instructions, String image) {
        this.title = title;
        this.summary = summary;
        this.instructions = instructions;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", instructions='" + instructions + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
