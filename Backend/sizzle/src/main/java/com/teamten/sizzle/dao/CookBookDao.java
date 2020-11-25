package com.teamten.sizzle.dao;

import com.teamten.sizzle.model.CookBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.persistence.Enumerated;
import java.text.CollationKey;
import java.util.List;

//public interface CookBookDao extends MongoRepository<CookBook, Long> {
//    @Enumerated
//    @Query(value = "{}", fields = "{'id': 1}", sort = "{'_id': -1")
//    List<CollationKey>findIds();
//
//
//}
