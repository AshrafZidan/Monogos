/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.employeeTransaction;

import Model.storeTransaction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import dialogs.dialog;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import loadFxml.MainUpdateEmployee;

import static Model.employeeTransaction.selectAllEmployees;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */

public class EmployeeController implements Initializable {

    @FXML
    private JFXTreeTableView treeView;

    @FXML
    private JFXButton addNewBtn;
    @FXML
    private JFXButton updateBtn;

    @FXML
    private  JFXButton refresh;

    @FXML
    private  JFXButton deletBtn , searchBtn;

    @FXML
    private JFXTextField searchBox;

    @FXML
    private HBox hbox1 , lastHbox;


    @FXML
    private TreeTableView<EmployeeController.EmployeeTable> employeeTable;

    @FXML
    private TreeTableColumn<EmployeeController.EmployeeTable, String> employeeTable_name;

    @FXML
    private TreeTableColumn<EmployeeController.EmployeeTable, String> employeeTable_store;

    @FXML
    private TreeTableColumn<EmployeeController.EmployeeTable, String> employeeTable_phone;

    @FXML
    private TreeTableColumn<EmployeeController.EmployeeTable, String> employeeTable_address;

    @FXML
    private TreeTableColumn<EmployeeController.EmployeeTable, String> employeeTable_email;




    ObservableList<EmployeeController.EmployeeTable> employeeTable_data = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


        // --------------- set size ---------------------------
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        hbox1.setPrefWidth(primaryScreenBounds.getWidth() - 180);
        employeeTable.setPrefWidth(primaryScreenBounds.getWidth() - 185);

        lastHbox.setLayoutY(primaryScreenBounds.getHeight() - 75);
//        hbox4.setLayoutY(table.getPrefHeight());
        employeeTable.setPrefHeight(primaryScreenBounds.getHeight() - 200);

        employeeTable_name.setPrefWidth(employeeTable.getPrefWidth() / 5 );
        employeeTable_email.setPrefWidth(employeeTable.getPrefWidth() / 5 );
        employeeTable_phone.setPrefWidth(employeeTable.getPrefWidth() / 5 );
        employeeTable_store.setPrefWidth(employeeTable.getPrefWidth() / 5 );
        employeeTable_address.setPrefWidth(employeeTable.getPrefWidth() / 5 );

        // table column initlaizton

        employeeTable_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<EmployeeTable , String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EmployeeTable, String> param) {
                return param.getValue().getValue().name;
            }

        });

        employeeTable_phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< EmployeeTable, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EmployeeTable, String> param) {
                return param.getValue().getValue().phone;
            }

        });

        employeeTable_address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< EmployeeTable, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EmployeeTable, String> param) {
                return param.getValue().getValue().address;
            }

        });

        employeeTable_email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< EmployeeTable, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EmployeeTable, String> param) {
                return param.getValue().getValue().email;
            }

        });

        employeeTable_store.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< EmployeeTable, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EmployeeTable, String> param) {
                return param.getValue().getValue().store_id;
            }

        });

//        get All Employess from employee Model

        List<DBObject> AllEmployess_DbObjectes  = employeeTransaction.selectAllEmployees();

     //Iteatre overThem
     AllEmployess_DbObjectes.stream().forEach(dbObject ->{
         employeeTable_data.add(new EmployeeTable(
                 storeTransaction.SelectEmployeeById(dbObject.get("store_id").toString()).get("name").toString(),
                 dbObject.get("_id").toString(),
                 dbObject.get("name").toString(),
                 dbObject.get("phone").toString(),
                 dbObject.get("email").toString(),
                 dbObject.get("address").toString()));

             }
         );


        final TreeItem<EmployeeTable> root = new RecursiveTreeItem<EmployeeTable>(employeeTable_data, RecursiveTreeObject::getChildren);

        employeeTable.setRoot(root);
        employeeTable.setShowRoot(false);

    }

     @FXML
    private  void LoadAddNewPage(ActionEvent e){
        
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateNewEmployee.fxml"));
            Scene scene =  new Scene(root);
            
            Stage home = new Stage();

            home.setTitle("Create New Supplier");
            home.setScene(scene);
            home.show();
            home.setResizable(false);
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    

    @FXML
    private void updatePage(ActionEvent ev) {

        // check Selection
        RecursiveTreeItem selectedItem = (RecursiveTreeItem) employeeTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            EmployeeTable employeeTable = (EmployeeController.EmployeeTable) selectedItem.getValue();
            MainUpdateEmployee updateEmployee = new MainUpdateEmployee(employeeTable.id.get());


        }
        else {
            dialog warning = new dialog(Alert.AlertType.WARNING, "خظأ", "اختر الموظف للتعديل");

        }


    }



    @FXML
    public  void refreshPage(ActionEvent ev){

        employeeTable_data.clear();

//        get All Employess from employee Model
        List<DBObject> AllEmployess_DbObjectes  = employeeTransaction.selectAllEmployees();

        AllEmployess_DbObjectes.stream().forEach(ee -> {
            employeeTable_data.add( new EmployeeTable( storeTransaction.SelectEmployeeById(ee.get("store_id").toString()).get("name").toString() , (ee.get("_id").toString()) , ee.get("name").toString(), ee.get("phone").toString(), ee.get("address").toString() , ee.get("email").toString()) );


        });

        final TreeItem<EmployeeTable> root = new RecursiveTreeItem<EmployeeTable>(employeeTable_data, RecursiveTreeObject::getChildren);
        employeeTable.setRoot(root);

    }


    @FXML
    public  void deleteEmployee(ActionEvent ev){


               // check Selection
        RecursiveTreeItem selectedItem = (RecursiveTreeItem) employeeTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            EmployeeTable employeeTableSelected = (EmployeeController.EmployeeTable) selectedItem.getValue();
            BasicDBObject basicDBObject = employeeTransaction.deleteEmployee(employeeTableSelected.id.get());

            if (basicDBObject != null) {
                boolean t =  employeeTable_data.remove(employeeTableSelected);
                final  TreeItem<EmployeeTable>  root = new RecursiveTreeItem <>(employeeTable_data , RecursiveTreeObject::getChildren);
                employeeTable.setRoot(root);

                if (!t) {
                    dialog warning = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح الموظف  من الجدول");

                }


            } else {
                dialog warning = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح الموظف من الداتابيز ");

            }


        } else {
            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "اختر الموظف للمسح");


        }

    }

    @FXML
    public  void searchByName(ActionEvent ev){
        employeeTable_data.clear();
        String query = searchBox.getText();
        DBObject dbObject = employeeTransaction.SelectEmployeeByName(query);

        if (query.equals("")){
            dialog warning = new dialog(Alert.AlertType.WARNING, "نتيجة", "إدخل اسم موظف للبحث");
        }
        if (dbObject == null){
            dialog warning = new dialog(Alert.AlertType.WARNING, "نتيجة", "لا توجد نتائج مطابقة");
        }
        else {

            employeeTable_data.add( new EmployeeTable( storeTransaction.SelectEmployeeById(dbObject.get("store_id").toString()).get("name").toString(),  (dbObject.get("_id").toString()) , dbObject.get("name").toString(), dbObject.get("phone").toString(), dbObject.get("address").toString() , dbObject.get("email").toString()));



            final TreeItem<EmployeeTable> root = new RecursiveTreeItem<EmployeeTable>(employeeTable_data, RecursiveTreeObject::getChildren);
            employeeTable.setRoot(root);
        }

        }

    class EmployeeTable extends RecursiveTreeObject<EmployeeTable>{

        SimpleStringProperty id;
        SimpleStringProperty store_id;
        SimpleStringProperty name;
        SimpleStringProperty phone;
        SimpleStringProperty address;
        SimpleStringProperty email;


        // create constructor
        public EmployeeTable(String store_id , String id , String name , String phone , String address , String email){

            this.id = new SimpleStringProperty(id);
            this.store_id = new SimpleStringProperty(store_id);
            this.address = new SimpleStringProperty(address);
            this.name = new SimpleStringProperty(name);
            this.phone = new SimpleStringProperty(phone);
            this.email = new SimpleStringProperty(email);

        }

        //setter and getter
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

        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emailProperty() {
            return email;
        }

        public void setEmail(String email) {
            this.email.set(email);
        }


        public String getStore() {
            return store_id.get();
        }

        public SimpleStringProperty store_idProperty() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id.set(store_id);
        }
    }

}
