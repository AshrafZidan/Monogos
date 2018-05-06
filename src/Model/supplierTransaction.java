package Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by ahmed mar3y on 19/04/2018.
 */
public class supplierTransaction {

    static DBCollection collection = MongoConnection.getDatabae().getCollection("suppliers");

    // insert into db
    public static BasicDBObject insertSupplier(String name, String phone, String address) {

        BasicDBObject document = new BasicDBObject();
        document.put("name", name);
        document.put("phone", phone);
        document.put("address", address);
        document.put("available", true);

        collection.insert(document);

        return document;


    }

    // select All
    public static List<DBObject> SelectAllSuppliers() {
//        BasicDBObject searchQuery = new BasicDBObject();
        DBCursor cursor = collection.find();

        return cursor.toArray();


    }

    //select By Id
    public static DBObject SelectSupplierById(String id) {
        BasicDBObject searchQuery1 = new BasicDBObject();
        searchQuery1.put("_id", new ObjectId(id));
        DBObject one = collection.findOne(searchQuery1);
        return one;
    }

    // update
    public static BasicDBObject updateSupplier(String id, BasicDBObject objectUpdated) {


        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", objectUpdated);

        collection.update(query, updateObject);

        return updateObject;


    }


    // ------ delete -----------------------
    public static BasicDBObject deleteSupplier(String id) {


        BasicDBObject searchDelete = new BasicDBObject();
        searchDelete.put("_id", new ObjectId(id));
        collection.remove(searchDelete);
        return searchDelete;


    }


}
