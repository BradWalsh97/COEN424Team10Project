package com.teamten.sizzle.dao;

import com.teamten.sizzle.model.Account;
import com.teamten.sizzle.model.CookBook;
import com.teamten.sizzle.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomAccountDao implements CustomAccountDaoInterface {
    private final MongoOperations mongoOperations;

    @Autowired
    public CustomAccountDao(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void addNewRecipeToUser(String user, int cookBookId, Recipe recipe) {
        Query query = new org.springframework.data.mongodb.core.query.Query();
        query.addCriteria(Criteria.where("username").is(user).and("cookBooks").elemMatch(Criteria.where("id").is(cookBookId)));
        Update update = new Update();
        update.addToSet("recipeIds", recipe.getId());
        mongoOperations.findAndModify(query, update, Account.class);
    }

    @Override
    public void removeRecipeFromUserAccount() {

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
        update.pull("cookBooks", Criteria.where("id").is(cookBookId));
        FindAndModifyOptions options = FindAndModifyOptions.options();
        mongoOperations.findAndModify(query, update, options, Account.class);
    }
}
