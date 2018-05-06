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
        private JFXTextField StoreName;

        @FXML
        private JFXTextField StoreAddress;

        @FXML
        private  JFXButton updateBtn;

        public static String storeId;


        @FXML
        void storeUpdateAction(ActionEvent event) {


            if (StoreName.getText().trim().isEmpty()
                    || StoreAddress.getText().trim().isEmpty()

                    ) {

                dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


            } else {
                BasicDBObject basicDBObject = new BasicDBObject();
                basicDBObject.put("name", StoreName.getText());
                basicDBObject.put("location", StoreAddress.getText());


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
            this.StoreName.setText(dbObject.get("location").toString());
            this.StoreAddress.setText(dbObject.get("name").toString());

        }


    }


