/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.employeeTransaction;
import Model.storeTransaction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import dialogs.dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class UpdateEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private JFXTextField employeeName;
    @FXML
    private JFXTextField employeeEmail;
    @FXML
    private JFXTextField employeephone;
    @FXML
    private JFXTextField employeeAddress;

    @FXML
    private JFXButton updateBtn;
 @FXML
    private JFXComboBox storeCombox;

    public static String employeeId;

    ArrayList<String> _idlist;


    @FXML
    void employeepdateAction(javafx.event.ActionEvent event) {


        if (employeeName.getText().trim().isEmpty()
                || employeeAddress.getText().trim().isEmpty()
                || employeephone.getText().trim().isEmpty()
                || employeeEmail.getText().trim().isEmpty()
                || storeCombox.getValue().toString().isEmpty()
                ) {

            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


        } else {
            BasicDBObject basicDBObject = new BasicDBObject();

            basicDBObject.put("store_id",getObjId());
            basicDBObject.put("address", employeeAddress.getText());

            basicDBObject.put("name", employeeName.getText());
            basicDBObject.put("email", employeeEmail.getText());
            basicDBObject.put("phone", employeephone.getText());

            BasicDBObject basicDBObjectUpdated = employeeTransaction.updateEmployee(employeeId, basicDBObject);
            if (basicDBObjectUpdated != null) {
                dialog dd = new dialog(Alert.AlertType.CONFIRMATION, "تم", "تم التعديل");


            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        List<DBObject> AllStores =   storeTransaction.selectAllStores();
        List<String> Namelist = new ArrayList<String>();
        _idlist = new ArrayList<String>();



        //Iteatre overThem
        AllStores.stream().forEach(dbObject ->{

            Namelist.add((String) dbObject.get("name"));
            _idlist.add((String) dbObject.get("_id").toString());

        });

        ObservableList<String> options =  FXCollections.observableArrayList(Namelist);

        storeCombox.setItems(options);

////         get supplierBy Id
        DBObject dbObject = employeeTransaction.SelectEmployeeById(employeeId);
        // set values
//        System.out.println(employeeId +" from Updated Control");
        this.employeeAddress.setText(dbObject.get("address").toString());
        this.employeephone.setText(dbObject.get("phone").toString());
        this.employeeEmail.setText(dbObject.get("email").toString());
        this.employeeName.setText(dbObject.get("name").toString());

    }

    //to get selected store id

    public String getObjId() {

        List<String> objID =  _idlist;
        int index = storeCombox.getSelectionModel().getSelectedIndex();
        String Store_id =  objID.get(index).toString();
        System.out.println(Store_id);

        return Store_id;

    }

}
