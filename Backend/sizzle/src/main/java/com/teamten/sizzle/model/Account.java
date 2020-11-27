package com.teamten.sizzle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    @Id
    private String username;
//    @ManyToMany
    private List<CookBook> cookBooks;
}
