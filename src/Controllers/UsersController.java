/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.userTransaction;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mongodb.DBObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class UsersController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton addNewBtn;

    @FXML
    private JFXButton refreshBtn;

    @FXML
    private TreeTableView<UsersController.userTable> userTable;

    @FXML
    private TreeTableColumn<UsersController.userTable, String> userTable_name;

    @FXML
    private TreeTableColumn<UsersController.userTable, String> userTable_phone;

    @FXML
    private TreeTableColumn<UsersController.userTable, String> userTable_address;

    @FXML
    private TreeTableColumn<UsersController.userTable, String>  userTable_email;


    @FXML
    private TreeTableColumn<UsersController.userTable, String> userTable_role;

    ObservableList<UsersController.userTable> userTable_data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // table column initlaize


        userTable_name.setCellValueFactory(new Callback <TreeTableColumn.CellDataFeatures<UsersController.userTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UsersController.userTable, String> param) {
                return param.getValue().getValue().name;
            }

        });


        userTable_phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UsersController.userTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UsersController.userTable, String> param) {
                return param.getValue().getValue().phone;
            }

        });
        userTable_address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UsersController.userTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UsersController.userTable, String> param) {
                return param.getValue().getValue().address;
            }


        });

        userTable_email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UsersController.userTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UsersController.userTable, String> param) {
                return param.getValue().getValue().email;
            }


        });
        userTable_role.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UsersController.userTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UsersController.userTable, String> param) {
                return param.getValue().getValue().role;
            }
        });


        // Select All Suppliers

        List<DBObject> dbObjects = userTransaction.SelectAll();
        dbObjects.stream().forEach(ee ->{
   userTable_data.add(new UsersController.userTable(ee.get("_id").toString(), ee.get("name").toString(), ee.get("phone").toString(), ee.get("address").toString(),ee.get("email").toString(), ee.get("role").toString()));


                });


        final TreeItem<UsersController.userTable> root = new RecursiveTreeItem<userTable>(userTable_data, RecursiveTreeObject::getChildren);
//        tableview.getColumns().setAll(NaklTable_date, NaklTable_bolisa, NaklTable_carNum, NaklTable_weight, NaklTable_nawlon, NaklTable_ohda, NaklTable_agz, NaklTable_added, NaklTable_mezan, NaklTable_discount, NaklTable_office, NaklTable_clear, NaklTable_type, NaklTable_notes);

        userTable.setRoot(root);
        userTable.setShowRoot(false);



    }

    @FXML
    private void refreshPage(ActionEvent e){

        //clear table data
        userTable_data.clear();

//        System.out.println("done role");
        // Select All Suppliers

        List<DBObject> dbObjects = userTransaction.SelectAll();

        dbObjects.stream().forEach(ee ->{
            userTable_data.add(new UsersController.userTable(ee.get("_id").toString(), ee.get("name").toString(), ee.get("phone").toString(), ee.get("address").toString(),ee.get("email").toString(), ee.get("role").toString()));
            });

        final TreeItem<userTable> root = new RecursiveTreeItem<userTable>(userTable_data, RecursiveTreeObject::getChildren);
        userTable.setRoot(root);



    }

    @FXML
    private void loadNewUserPage(ActionEvent e){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/createNewUser.fxml"));
            Scene scene = new Scene(root);

            Stage home = new Stage();
            home.setTitle("Create New USer");

            home.setScene(scene);
            home.show();
            home.setResizable(false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    class userTable extends RecursiveTreeObject<userTable> {
        SimpleStringProperty id;
        SimpleStringProperty name;
        SimpleStringProperty phone;
        SimpleStringProperty address;
        SimpleStringProperty email;
        SimpleStringProperty role;

        public userTable(String id, String name, String phone, String address,String email, String role) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.phone = new SimpleStringProperty(phone);
            this.address = new SimpleStringProperty(address);
            this.email = new SimpleStringProperty(email);
            this.role = new SimpleStringProperty(role);

        }


        public String getId() {
            return id.get();
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getPhone() {
            return phone.get();
        }

        public SimpleStringProperty phoneProperty() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone.set(phone);
        }

        public String getAddress() {
            return address.get();
        }

        public SimpleStringProperty addressProperty() {
            return address;
        }

        public void setAddress(String address) {
            this.address.set(address);
        }


        public String getRole() {
            return role.get();
        }

        public SimpleStringProperty roleProperty() {
            return role;
        }

        public void setRole(String role) {
            this.role.set(role);
        }


        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emalProperty() {
            return email;
        }

        public void setEmail(String email) {
            this.email.set(email);
        }
    }
}








