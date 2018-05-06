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
    private JFXTextField EmployeeName;

    @FXML
    private JFXTextField EmployeePhone;

    @FXML
    private JFXButton addEmployee;

    @FXML
    private JFXTextField EmployeeAddress;

    @FXML
    private JFXTextField EmployeeEmail;
    @FXML
    private JFXComboBox storeComBox;

    List<String> _idlist;





    @FXML
    void addEmployee(ActionEvent event) {

        if (EmployeeName.getText().trim().isEmpty()
                || EmployeePhone.getText().trim().isEmpty()
                || EmployeeAddress.getText().trim().isEmpty()
                || EmployeeEmail.getText().trim().isEmpty()
                || storeComBox.getValue().toString().isEmpty()

                ) {

            dialog Warning = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


        } else {


            BasicDBObject object = employeeTransaction.insertEmployee(getObjId() , EmployeeName.getText().toLowerCase(), EmployeePhone.getText(), EmployeeAddress.getText() , EmployeeEmail.getText());
            if (object != null) {
                dialog Done = new dialog(Alert.AlertType.CONFIRMATION, "تم", "تم حفظ العميل");


            }

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<DBObject> AllStores =   storeTransaction.selectAllStores();


        List<String> Namelist = new ArrayList<String>();
        _idlist = new ArrayList<String>();



        //Iteatre overThem
        AllStores.stream().forEach(dbObject ->{

            Namelist.add((String) dbObject.get("name"));
            _idlist.add((String) dbObject.get("_id").toString());

        });

        System.out.println(_idlist);
        ObservableList<String> options =
                FXCollections.observableArrayList(Namelist);





        storeComBox.setItems(options);

    }

    //to get selected store id

    public String getObjId() {

        List<String> objID =  _idlist;
        int index = storeComBox.getSelectionModel().getSelectedIndex();
        String Store_id =  objID.get(index).toString();
        System.out.println(Store_id);

        return Store_id;

    }

}
