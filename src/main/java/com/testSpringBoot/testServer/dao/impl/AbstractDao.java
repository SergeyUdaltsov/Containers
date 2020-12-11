package com.testSpringBoot.testServer.dao.impl;

import com.testSpringBoot.testServer.dao.IDao;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 12/9/2020
 */
public class AbstractDao<T> implements IDao {

    private final MongoOperations mongoOperations;
    private String collectionName;
    private Class<T> type;

    public AbstractDao(MongoOperations operations, String collectionName, Class<T> type) {
        this.mongoOperations = operations;
        this.collectionName = collectionName;
        this.type = type;
    }

    @Override
    public List<T> getAllItems() {
        return mongoOperations.findAll(type, collectionName);
    }
}
