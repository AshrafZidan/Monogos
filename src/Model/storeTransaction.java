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
    public  static  BasicDBObject inserٍStore(String Name , String Address  ){


        BasicDBObject document = new BasicDBObject();
        document.put("name", Name);
        document.put("address", Address);

        storesCollection.insert(document);
        return document;
    }

    public static BasicDBObject deleteStore(String id) {


        BasicDBObject Query_Delete = new BasicDBObject();
        Query_Delete.put("_id", new ObjectId(id));

        storesCollection.remove(Query_Delete);
        return Query_Delete;


    }

    public static DBObject SelectStoreByName(String name) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", name);

        DBObject one_employee = storesCollection.findOne(searchQuery);


        return one_employee;
    }

    public static BasicDBObject updateStore(String id , BasicDBObject objectUpdated) {


        BasicDBObject Update_query = new BasicDBObject();

        Update_query.put("_id" , new ObjectId(id));

        BasicDBObject updateObject = new BasicDBObject();

        updateObject.put("$set", objectUpdated);

        storesCollection.update(Update_query, updateObject);

        return updateObject;


    }

}
