package Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.List;

public class employeeTransaction {

    static DBCollection employeeCollection = MongoConnection.getDatabae().getCollection("employee");

    // insert into db
    public static BasicDBObject insertEmployee( String store_id ,String name, String phone, String address , String email) {

        BasicDBObject Employeedocument = new BasicDBObject();
        Employeedocument.put("store_id",store_id);
        Employeedocument.put("name", name);
        Employeedocument.put("phone", phone);
        Employeedocument.put("address", address);
        Employeedocument.put("email", email);

        Employeedocument.put("available", true);

        employeeCollection.insert(Employeedocument);

        return Employeedocument;


    }


    //select All Employee
    public static List<DBObject> selectAllEmployees(){

          DBCursor EmpCursor = employeeCollection.find();

          return EmpCursor.toArray();
        }


    // update
    public static BasicDBObject updateEmployee(String id , BasicDBObject objectUpdated) {


        BasicDBObject Update_query = new BasicDBObject();

        Update_query.put("_id" , new ObjectId(id));

        BasicDBObject updateObject = new BasicDBObject();

        updateObject.put("$set", objectUpdated);

        employeeCollection.update(Update_query, updateObject);

        return updateObject;


    }



    //select By Id
    public static DBObject SelectEmployeeById(String id) {
        BasicDBObject searchQuery1 = new BasicDBObject();
        searchQuery1.put("_id", new ObjectId(id));

        DBObject one_employee = employeeCollection.findOne(searchQuery1);

        return one_employee;
    }

//    search By Name

    public static DBObject SelectEmployeeByName(String name) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", name);

        DBObject one_employee = employeeCollection.findOne(searchQuery);
        System.out.println(one_employee);


        return one_employee;
    }


    // ------ delete -----------------------
    public static BasicDBObject deleteEmployee(String id) {


        BasicDBObject Query_Delete = new BasicDBObject();
        Query_Delete.put("_id", new ObjectId(id));

        employeeCollection.remove(Query_Delete);
        return Query_Delete;


    }


}
