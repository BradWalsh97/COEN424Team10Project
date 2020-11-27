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
        System.out.println("Adding recipe to cookbook now");
        Query query = new org.springframework.data.mongodb.core.query.Query();
        query.addCriteria(Criteria.where("username").is(user).and("cookBooks").elemMatch(Criteria.where("id").is(cookBookId)));
        Update update = new Update();
        update.addToSet("cookBooks.$.recipeIds", recipe);
        mongoOperations.findAndModify(query, update, Account.class);
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
    public void removeRecipeFromCookBook(String user, int cookBookId, int recipeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user)
                .and("cookBooks").elemMatch(Criteria.where("id").is(cookBookId)
                        .and("recipeIds").is(recipeId)));
        System.out.println(query.getQueryObject().toString());
        Update update = new Update();
        update.pull("cookBooks.$.recipeIds", recipeId);
        System.out.println(update.getUpdateObject().toString());
        FindAndModifyOptions options = FindAndModifyOptions.options();
        options.returnNew(true);
        mongoOperations.findAndModify(query, update, options, Account.class);
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

    @Override
    public boolean cookBookContainsRecipeWithId(String user, int cookBookId, int recipeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user)
                .and("cookBooks").elemMatch(Criteria.where("id").is(cookBookId)
                        .and("recipeIds").is(recipeId)));
        query.limit(1);
        return mongoOperations.exists(query, Account.class);
    }


    @Override
    public void updateUserEmail(String user, String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user));
        Update update = new Update();
        update.set("email", email);
        mongoOperations.findAndModify(query, update, Account.class);
    }

    @Override
    public void updateUserPassword(String user, String password) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user));
        Update update = new Update();
        update.set("password", password);
        mongoOperations.findAndModify(query, update, Account.class);
    }

    @Override
    public boolean userExistsWithUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        query.limit(1);
        return mongoOperations.exists(query, Account.class);

    }
}
