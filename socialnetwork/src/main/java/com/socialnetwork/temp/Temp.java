package com.socialnetwork.temp;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.socialnetwork.entity.User;
import com.socialnetwork.logic.UserCreator;

public class Temp {

	private static String MONGODB_HOST = "localhost";
	private static int MONGODB_PORT = 27017;

	public void workWithMongo() {
		MongoClient mongoClient = new MongoClient(MONGODB_HOST, MONGODB_PORT);
		/*
		 * if 'socialnetwork' database not exist -> mongo will create it
		 */
		MongoDatabase database = mongoClient.getDatabase("socialnetwork");
		MongoCollection<Document> usersCollection = database.getCollection("users");
		usersCollection.deleteMany(new Document());
		UserCreator userCreator = new UserCreator(10);// clear each time
		populateCollection(usersCollection, userCreator.getUsers());
		System.out.println(usersCollection.countDocuments());

		usersCollection.find().forEach((Block<Document>) System.out::println);

		mongoClient.close();
	}

	private static void populateCollection(MongoCollection<Document> usersCollection, List<User> users) {
		usersCollection.insertMany(users.stream().map(u -> {
			Document document = new Document();
			document.put("name", u.getName());
			document.put("interests", u.getInterests());
			return document;
		}).collect(Collectors.toList()));
	}
}
