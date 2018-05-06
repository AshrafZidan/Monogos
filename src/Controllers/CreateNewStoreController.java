package Controllers;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateNewStoreController  implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField StoreName;

    @FXML
    private JFXTextField StoreAddress;

    @FXML
    private JFXButton addStore;


    @FXML
    void addStoreAction(ActionEvent event) {

        if (StoreName.getText().trim().isEmpty()
                || StoreAddress.getText().trim().isEmpty()

                ) {

            dialog Warning = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


        } else {


            BasicDBObject object = storeTransaction.inserٍStore(StoreName.getText().toString(), StoreAddress.getText().toString());
            if (object != null) {
                dialog Done = new dialog(Alert.AlertType.CONFIRMATION, "تم", "تم حفظ العميل");


            }

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }
}
