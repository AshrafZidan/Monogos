package Controllers;

import Model.storeTransaction;
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

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateStoreContoller implements Initializable  {



        @FXML
        private JFXTextField supplierName;

        @FXML
        private JFXTextField supplierPhone;

        @FXML
        private JFXButton supplierUpdate;

        @FXML
        private JFXTextField supplierAddress;

        public static String storeId;


        @FXML
        void storeUpdateAction(ActionEvent event) {


            if (supplierName.getText().trim().isEmpty()
                    || supplierAddress.getText().trim().isEmpty()

                    ) {

                dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


            } else {
                BasicDBObject basicDBObject = new BasicDBObject();
                basicDBObject.put("name", supplierName.getText());
                basicDBObject.put("location", supplierAddress.getText());


                BasicDBObject basicDBObjectUpdated = storeTransaction.updateStore(storeId, basicDBObject);
                if (basicDBObjectUpdated != null) {
                    dialog dd = new dialog(Alert.AlertType.CONFIRMATION, "تم", "تم التعديل");


                }

            }


        }

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // TODO

            // get supplierBy Id
            DBObject dbObject = storeTransaction.SelectEmployeeById(storeId);
            // set values
            this.supplierAddress.setText(dbObject.get("location").toString());
            this.supplierName.setText(dbObject.get("name").toString());

        }


    }


