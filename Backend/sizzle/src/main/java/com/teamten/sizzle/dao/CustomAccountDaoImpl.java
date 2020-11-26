package com.teamten.sizzle.dao;

import com.teamten.sizzle.model.Account;
import com.teamten.sizzle.model.CookBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomAccountDaoImpl implements CustomAccountDao {
    private final MongoOperations mongoOperations;

    @Autowired
    public CustomAccountDaoImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void addNewRecipeToUser(String user, int cookBookId, int recipe) {
        Query query = new org.springframework.data.mongodb.core.query.Query();
        query.addCriteria(Criteria.where("username").is(user).and("cookBooks").elemMatch(Criteria.where("id").is(cookBookId)));
        Update update = new Update();
        update.addToSet("recipeIds", recipe);
        mongoOperations.findAndModify(query, update, Account.class);
    }

    @Override
    public void removeRecipeFromCookBook(String user, int cookBookId, int recipeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user).and("cookBooks").elemMatch(Criteria.where("id").is(cookBookId).and("recipeIds").elemMatch(Criteria.byExample(recipeId))));
        Update update = new Update();
        update.pull("recipeIds",new Query().addCriteria(Criteria.byExample(recipeId)));
        
    }

    @Override
    public void addNewCookBook(String user, CookBook cookBook) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user));
        Update update = new Update();
        update.addToSet("cookBooks", cookBook);
        mongoOperations.findAndModify(query, update, Account.class);
    }


    @Override
    public void removeCookBook(String user, int cookBookId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user).and("cookBooks").elemMatch(Criteria.where("id").is(cookBookId)));
        Update update = new Update();
        update.pull("cookBooks", new Query().addCriteria(Criteria.where("id").is(cookBookId)));
        FindAndModifyOptions options = FindAndModifyOptions.options();
        options.returnNew(true);
        mongoOperations.findAndModify(query, update, options, Account.class);
    }
}
