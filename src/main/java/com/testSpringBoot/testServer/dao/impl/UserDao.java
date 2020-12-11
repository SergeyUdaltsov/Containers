package com.testSpringBoot.testServer.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.testSpringBoot.testServer.dao.IUserDao;
import com.testSpringBoot.testServer.model.Gender;
import com.testSpringBoot.testServer.model.User;
import com.testSpringBoot.testServer.utils.JsonUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Serhii_Udaltsov on 8/30/2020
 */
@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {

    private List<User> users = new ArrayList<>();

    @Autowired
    public UserDao(MongoTemplate mongoTemplate) {
        super(mongoTemplate, "Users", User.class);
//        try {
//            URI uri = getClass().getClassLoader().getResource("usersList.json").toURI();
//            System.out.println("URI -------------------- " + uri);
//            final Map<String, String> env = new HashMap<>();
//            final String[] array = uri.toString().split(":");
////            final FileSystem fs = FileSystems.newFileSystem(URI.create(array[0]), env);
//            final Path path = Paths.get(uri);
//            System.out.println("Path -------------- " + path.toString());
//            byte[] bytes = Files.readAllBytes(path);
//            String usersJson = new String(bytes);
//            this.users = JsonUtils.parseJson(usersJson, new TypeReference<List<User>>() {
//            });
//        } catch (URISyntaxException | IOException e) {
//            e.printStackTrace();
//        }
//        users.add(new User("sergii.udaltsov@gmail.com", "Sergii", "Sergii + Galya", Gender.MALE, 1601126233));
//        users.add(new User("gudaltsova@gmail.com", "Galyna", "Udaltsova", Gender.FEMALE, 1601126233));
    }

    @Override
    public List<User> getAllUsers() {
        return getAllItems();
    }
}
