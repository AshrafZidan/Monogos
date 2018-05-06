/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.employeeTransaction;
import Model.storeTransaction;
import Model.userTransaction;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import loadFxml.MainUpdateStores;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class StoresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton addNewBtn , searchBtn , updateBtn ;

    @FXML
    private JFXButton refresh;

    @FXML
    private JFXTextField searchBox;

    @FXML
    private HBox hbox1 , lastHbox;
    @FXML
    private TreeTableView<StoresController.storeTable> storeTable;

    @FXML
    private TreeTableColumn<StoresController.storeTable, String> userTable_name;

    @FXML
    private TreeTableColumn<StoresController.storeTable, String> userTable_loacation;


    ObservableList<StoresController.storeTable>  storeTable_data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // --------------- set size ---------------------------
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        hbox1.setPrefWidth(primaryScreenBounds.getWidth() - 180);
        storeTable.setPrefWidth(primaryScreenBounds.getWidth() - 190);

        lastHbox.setLayoutY(primaryScreenBounds.getHeight() - 75);
        storeTable.setPrefHeight(primaryScreenBounds.getHeight() - 200);

      userTable_name.setPrefWidth(storeTable.getPrefWidth() / 2 );
      userTable_loacation.setPrefWidth(storeTable.getPrefWidth() / 2 );


        // table column initlaize
        userTable_name.setCellValueFactory(new Callback <TreeTableColumn.CellDataFeatures<StoresController.storeTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StoresController.storeTable, String> param) {
                return param.getValue().getValue().name;
            }

        });

        userTable_loacation.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StoresController.storeTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StoresController.storeTable, String> param) {
                return param.getValue().getValue().address;
            }

        });
        // Select All Stores

        List<DBObject> dbObjects = storeTransaction.selectAllStores();
        System.out.println(dbObjects);
       dbObjects.stream().forEach(dbObject -> {
                    storeTable_data.add(new StoresController.storeTable(

                            dbObject.get("_id").toString(),
                            dbObject.get("name").toString(),
                            dbObject.get("location").toString() ));


                }
        );


        final TreeItem<StoresController.storeTable> root = new RecursiveTreeItem<StoresController.storeTable>(storeTable_data, RecursiveTreeObject::getChildren);

        storeTable.setRoot(root);
        storeTable.setShowRoot(false);
    }

    @FXML
    private void refreshPage(ActionEvent e){

        //clear table data
        storeTable_data.clear();



        List<DBObject> dbObjects = userTransaction.SelectAll();

        dbObjects.stream().forEach(ee ->{
            storeTable_data.add(new StoresController.storeTable(ee.get("_id").toString(), ee.get("name").toString(), ee.get("location").toString()));
            });

        final TreeItem<storeTable> root = new RecursiveTreeItem<storeTable>(storeTable_data, RecursiveTreeObject::getChildren);
        storeTable.setRoot(root);



    }

    @FXML
    private void LoadAddNewPage(ActionEvent e){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/createNewStore.fxml"));
            Scene scene = new Scene(root);

            Stage home = new Stage();
            home.setTitle("Create New Store");

            home.setScene(scene);
            home.show();
            home.setResizable(false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @FXML
    public  void searchByName(ActionEvent ev){
        storeTable_data.clear();
        String query = searchBox.getText();
        DBObject dbObject = storeTransaction.SelectStoreByName(query);

        if (query.equals("")){
            dialog warning = new dialog(Alert.AlertType.WARNING, "نتيجة", "إدخل اسم موظف للبحث");
        }
        if (dbObject == null){
            dialog warning = new dialog(Alert.AlertType.WARNING, "نتيجة", "لا توجد نتائج مطابقة");
        }
        else {

            storeTable_data.add( new storeTable( (dbObject.get("_id").toString()) , dbObject.get("name").toString(),  dbObject.get("location").toString() ));



            final TreeItem<storeTable> root = new RecursiveTreeItem<storeTable>(storeTable_data, RecursiveTreeObject::getChildren);
            storeTable.setRoot(root);
        }

    }



    @FXML
    private void deleteStore(ActionEvent ev) {
        // check Selection
        RecursiveTreeItem selectedItem = (RecursiveTreeItem) storeTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            StoresController.storeTable storeTableSelected = (StoresController.storeTable) selectedItem.getValue();
            BasicDBObject basicDBObject = storeTransaction.deleteStore(storeTableSelected.id.get());

            if (basicDBObject != null) {
                boolean t =  storeTable_data.remove(storeTableSelected);
                final  TreeItem<StoresController.storeTable>  root = new RecursiveTreeItem <>(storeTable_data , RecursiveTreeObject::getChildren);
                storeTable.setRoot(root);

                if (!t) {
                    dialog warning = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح الفرع  من الجدول");

                }


            } else {
                dialog warning = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح الفرع من الداتابيز ");

            }


        } else {
            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "اختر الفرع للمسح");


        }


    }
    @FXML
    private void updatePage(ActionEvent ev) {

        // check Selection
        RecursiveTreeItem selectedItem = (RecursiveTreeItem) storeTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            storeTable employeeTable = (StoresController.storeTable) selectedItem.getValue();
            MainUpdateStores updateEmployee = new MainUpdateStores(storeTable.getId());


        }
        else {
            dialog warning = new dialog(Alert.AlertType.WARNING, "خظأ", "اختر الفرع للتعديل");

        }


    }

    class storeTable extends RecursiveTreeObject<storeTable> {
        SimpleStringProperty id;
        SimpleStringProperty name;

        SimpleStringProperty address;


        public storeTable(String id, String name, String address) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.address = new SimpleStringProperty(address);

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




        public String getAddress() {
            return address.get();
        }

        public SimpleStringProperty addressProperty() {
            return address;
        }

        public void setAddress(String address) {
            this.address.set(address);
        }




    }
}








