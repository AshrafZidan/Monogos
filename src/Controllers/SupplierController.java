/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.supplierTransaction;
import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import dialogs.dialog;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import loadFxml.MainUpdateSupplier;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class SupplierController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private JFXButton addNewBtn;
    @FXML
    private JFXButton updateBtn;

    @FXML
    private TreeTableView<SupplierController.supplierTable> supplierTable;

    @FXML
    private TreeTableColumn<SupplierController.supplierTable, String> supplierTable_name;

    @FXML
    private TreeTableColumn<SupplierController.supplierTable, String> supplierTable_phone;

    @FXML
    private TreeTableColumn<SupplierController.supplierTable, String> supplierTable_address;

    ObservableList<SupplierController.supplierTable> supplierTable_data = FXCollections.observableArrayList();

    @FXML
    private JFXButton refreshTable;

    @FXML
    private JFXButton deleteSupplier;


    @FXML
    void deleteSupplierAction(ActionEvent event) {


        // check Selection
        RecursiveTreeItem selectedItem = (RecursiveTreeItem) supplierTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            supplierTable supplierTableSelected = (SupplierController.supplierTable) selectedItem.getValue();

            BasicDBObject basicDBObject = supplierTransaction.deleteSupplier(supplierTableSelected.id.get());

            if (basicDBObject != null) {

                // delete from table
                boolean t = supplierTable_data.remove(supplierTableSelected);
                final TreeItem<supplierTable> root = new RecursiveTreeItem<supplierTable>(supplierTable_data, RecursiveTreeObject::getChildren);
                supplierTable.setRoot(root);
                if (!t) {
                    dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح المورد من الجدول");

                }


            } else {
                dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح المورد من الداتابيز ");

            }


        } else {
            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "اختر المورد للمسح");


        }
    }

    @FXML
    void refreshTableAction(ActionEvent event) {


        //clear table data
        supplierTable_data.clear();

        // Select All Suppliers

        List<DBObject> dbObjects = supplierTransaction.SelectAllSuppliers();
        dbObjects.stream().forEach(ee -> {

            supplierTable_data.add(new supplierTable(ee.get("_id").toString(), ee.get("name").toString(), ee.get("phone").toString(), ee.get("address").toString()));


        });

        final TreeItem<supplierTable> root = new RecursiveTreeItem<supplierTable>(supplierTable_data, RecursiveTreeObject::getChildren);
        supplierTable.setRoot(root);


    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // table column initlaize
        supplierTable_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<supplierTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<supplierTable, String> param) {
                return param.getValue().getValue().name;
            }

        });


        supplierTable_phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<supplierTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<supplierTable, String> param) {
                return param.getValue().getValue().phone;
            }

        });
        supplierTable_address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<supplierTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<supplierTable, String> param) {
                return param.getValue().getValue().address;
            }

        });


        // Select All Suppliers

        List<DBObject> dbObjects = supplierTransaction.SelectAllSuppliers();
        dbObjects.stream().forEach(ee -> {

            supplierTable_data.add(new supplierTable(ee.get("_id").toString(), ee.get("name").toString(), ee.get("phone").toString(), ee.get("address").toString()));


        });


        final TreeItem<supplierTable> root = new RecursiveTreeItem<supplierTable>(supplierTable_data, RecursiveTreeObject::getChildren);
//        tableview.getColumns().setAll(NaklTable_date, NaklTable_bolisa, NaklTable_carNum, NaklTable_weight, NaklTable_nawlon, NaklTable_ohda, NaklTable_agz, NaklTable_added, NaklTable_mezan, NaklTable_discount, NaklTable_office, NaklTable_clear, NaklTable_type, NaklTable_notes);
        supplierTable.setRoot(root);
        supplierTable.setShowRoot(false);


    }

    @FXML
    private void LoadAddNewPage(ActionEvent e) {
        try {
            Stage home = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateNewStor.fxml"));
            Scene scene = new Scene(root);

            home.setTitle("Create New Store");

            home.setScene(scene);
            home.setResizable(false);

            scene.setFill(Color.TRANSPARENT); //Makes scene background transparent

            home.initModality(Modality.APPLICATION_MODAL);

            home.showAndWait();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    @FXML
    private void updatePage(ActionEvent e) {


        // check Selection


        RecursiveTreeItem selectedItem = (RecursiveTreeItem) supplierTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            // get selected Value
            supplierTable supplierTable = (SupplierController.supplierTable) selectedItem.getValue();

            MainUpdateSupplier mainUpdateSupplier = new MainUpdateSupplier(supplierTable.id.get());


        } else {
            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "اختر المورد للتعديل");

        }


    }

    class supplierTable extends RecursiveTreeObject<supplierTable> {
        SimpleStringProperty id;
        SimpleStringProperty name;
        SimpleStringProperty phone;
        SimpleStringProperty address;

        public supplierTable(String id, String name, String phone, String address) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.phone = new SimpleStringProperty(phone);
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
    }


}
