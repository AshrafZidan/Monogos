package Model;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * Created by ahmed mar3y on 19/04/2018.
 */
public class MongoConnection {


    private static DB database;

    public static MongoClient createMongoConnection() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        return mongoClient;

    }

    public static DB getDatabae() {
        if (database == null) {
            database = createMongoConnection().getDB("myMongoDb");
        }
        return database;
    }


}
