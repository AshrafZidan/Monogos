/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Model.supplierTransaction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import dialogs.dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class UpdateSupplierController implements Initializable {


    @FXML
    private JFXTextField supplierName;

    @FXML
    private JFXTextField supplierPhone;

    @FXML
    private JFXButton supplierUpdate;

    @FXML
    private JFXTextField supplierAddress;

    public static String supplierId;


    @FXML
    void supplierUpdateAction(ActionEvent event) {


        if (supplierName.getText().trim().isEmpty()
                || supplierAddress.getText().trim().isEmpty()
                || supplierPhone.getText().trim().isEmpty()
                ) {

            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


        } else {
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("name", supplierName.getText());
            basicDBObject.put("address", supplierAddress.getText());
            basicDBObject.put("phone", supplierPhone.getText());

            BasicDBObject basicDBObjectUpdated = supplierTransaction.updateSupplier(supplierId, basicDBObject);
            if (basicDBObjectUpdated != null) {
                dialog dd = new dialog(Alert.AlertType.CONFIRMATION, "تم", "تم التعديل");


            }

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // get supplierBy Id
        DBObject dbObject = supplierTransaction.SelectSupplierById(supplierId);
        // set values
        this.supplierAddress.setText(dbObject.get("address").toString());
        this.supplierName.setText(dbObject.get("name").toString());
        this.supplierPhone.setText(dbObject.get("phone").toString());

    }


}
