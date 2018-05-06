package Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.ObservableList;
import org.bson.types.ObjectId;

import java.util.List;

public class storeTransaction {

    static DBCollection storesCollection = MongoConnection.getDatabae().getCollection("stores");

    //select All Store
    public static List<DBObject> selectAllStores(){

        DBCursor store = storesCollection.find();

        return store.toArray();
    }
    //select By Id
    public static DBObject SelectEmployeeById(String id) {
        BasicDBObject searchQuery1 = new BasicDBObject();
        searchQuery1.put("_id", new ObjectId(id));

        DBObject one_store = storesCollection.findOne(searchQuery1);

        return one_store;
    }
}
