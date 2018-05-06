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
public class CreateNewSupplierController implements Initializable {


    @FXML
    private JFXTextField supplierName;

    @FXML
    private JFXTextField supplierPhone;

    @FXML
    private JFXButton addSupplier;

    @FXML
    private JFXTextField supplierAddress;

    @FXML
    void addSupplierAction(ActionEvent event) {

        if (supplierName.getText().trim().isEmpty()
                || supplierAddress.getText().trim().isEmpty()
                || supplierPhone.getText().trim().isEmpty()
                ) {

            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


        } else {


            BasicDBObject object = supplierTransaction.insertSupplier(supplierName.getText(), supplierPhone.getText(), supplierAddress.getText());
            if (object != null) {
                resetFields();
                dialog dd = new dialog(Alert.AlertType.CONFIRMATION, "تم", "تم حفظ العميل");


            }

        }


    }

    private void resetFields() {

        this.supplierAddress.setText("");
        this.supplierPhone.setText("");
        this.supplierAddress.setText("");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
