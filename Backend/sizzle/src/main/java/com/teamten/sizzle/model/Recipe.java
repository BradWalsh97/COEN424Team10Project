package com.teamten.sizzle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private String title;
    private String summary;
    private String instructions;
    private String image;
    @Id
    private int id;
    private boolean isPublic;
    private boolean isFromCookbook;

    public Recipe(int id, String title, String summary, String instructions, String image, boolean isFromCookbook, boolean isPublic) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.instructions = instructions;
        this.image = image;
        this.isFromCookbook = isFromCookbook;
        this.isPublic = isPublic;
    }
}
