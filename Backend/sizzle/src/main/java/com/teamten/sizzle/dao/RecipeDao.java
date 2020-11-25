package com.teamten.sizzle.dao;

import com.teamten.sizzle.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Enumerated;
import java.util.List;

@Repository
public interface RecipeDao extends MongoRepository<Recipe, Long> {

    @Enumerated
    @Query(value = "{}", fields = "{'id' : 1}", sort = "{'_id' :-1}" )
    List<Recipe> findIds();
}
