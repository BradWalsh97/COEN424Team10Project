package com.teamten.sizzle.dao;

import com.teamten.sizzle.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDao extends MongoRepository<Recipe, Long> {

}
