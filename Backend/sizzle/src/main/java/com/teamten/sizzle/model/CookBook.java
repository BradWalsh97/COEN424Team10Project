package com.teamten.sizzle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CookBook {
    @Id
    private int id;
    private String name;
    private int[] recipeIds;

    @Override
    public String toString() {
        return "CookBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", recipeIds=" + Arrays.toString(recipeIds) +
                '}';
    }
}
