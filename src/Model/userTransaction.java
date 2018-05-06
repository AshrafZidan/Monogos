package Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.List;

public class userTransaction {

    static DBCollection collection = MongoConnection.getDatabae().getCollection("users");

    // select All
    public static List<DBObject> SelectAll() {
        BasicDBObject searchQuery = new BasicDBObject();
        DBCursor cursor = collection.find();

        return cursor.toArray();


    }

    public  static  BasicDBObject insertUser(String Name , String Address , String Email ,String phone , String pass , String role){


        BasicDBObject document = new BasicDBObject();
        document.put("name", Name);
        document.put("phone", phone);
        document.put("address", Address);
        document.put("email", Email);
        document.put("pass", pass);
        document.put("role", role);

        collection.insert(document);
        return document;
    }

    // select ByID
    public static DBObject SelectbyID(String id) {
        BasicDBObject searchQuery1 = new BasicDBObject();

        searchQuery1.put("_id", new ObjectId(id));
        DBObject one = collection.findOne(searchQuery1);

        return one;


    }
    public static BasicDBObject updateUser(String id, BasicDBObject objectUpdated) {


        BasicDBObject update_query = new BasicDBObject();

        update_query .put("_id", new ObjectId(id));


        BasicDBObject newInfo = new BasicDBObject();
        newInfo.put("$set", objectUpdated);

        collection.update(update_query, newInfo);

        return newInfo;


    }

}
