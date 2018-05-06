/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.productTransaction;
import Model.supplierTransaction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mongodb.DBObject;
import dialogs.dialog;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import loadFxml.MainUpdateProduct;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class ProductsController implements Initializable {
    ObservableList<productTable> productTable_data = FXCollections.observableArrayList();


    @FXML
    private JFXButton addNew;


    @FXML
    private TreeTableView<productTable> tableProduct;

    @FXML
    private TreeTableColumn<productTable, String> proName;

    @FXML
    private TreeTableColumn<productTable, String> proModel;

    @FXML
    private TreeTableColumn<productTable, Double> proAmount;

    @FXML
    private TreeTableColumn<productTable, Double> proBuyPrice;

    @FXML
    private TreeTableColumn<productTable, Double> proProfit;

    @FXML
    private TreeTableColumn<productTable, Double> proSellPrice;

    @FXML
    private TreeTableColumn<productTable, String> proArriveDate;

    @FXML
    private TreeTableColumn<productTable, String> proSuppName;


    @FXML
    void updateProductAction(ActionEvent event) {

//        get selection First
        RecursiveTreeItem selectedItem = (RecursiveTreeItem) tableProduct.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // get selected Value
            productTable supplierTable = (productTable) selectedItem.getValue();

            MainUpdateProduct mainUpdateProduct = new MainUpdateProduct(supplierTable.getId());


        } else {
            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "اختر المنتج للتعديل");

        }

    }

    @FXML
    void addNewAction(ActionEvent event) {

        try {

            System.out.println("Action Button ");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/createNewProduct.fxml"));
            Scene scene = new Scene(root);

            Stage home = new Stage();

            home.setTitle("Create New Product");
            home.setScene(scene);
            scene.setFill(Color.TRANSPARENT); //Makes scene background transparent
            home.setResizable(false);

            home.initModality(Modality.APPLICATION_MODAL);

            home.showAndWait();


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


        // init table
        proName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<productTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<productTable, String> param) {
                return param.getValue().getValue().name;
            }

        });
        proModel.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<productTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<productTable, String> param) {
                return param.getValue().getValue().model;
            }

        });
        proArriveDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<productTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<productTable, String> param) {
                return param.getValue().getValue().date;
            }

        });
        proSuppName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<productTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<productTable, String> param) {
                return param.getValue().getValue().suppName;
            }

        });

        proAmount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<productTable, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TreeTableColumn.CellDataFeatures<productTable, Double> param) {
                return param.getValue().getValue().amount.asObject();
            }

        });
        proBuyPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<productTable, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TreeTableColumn.CellDataFeatures<productTable, Double> param) {
                return param.getValue().getValue().buyPrice.asObject();
            }

        });
        proSellPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<productTable, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TreeTableColumn.CellDataFeatures<productTable, Double> param) {
                return param.getValue().getValue().sellPrice.asObject();
            }

        });
        proProfit.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<productTable, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TreeTableColumn.CellDataFeatures<productTable, Double> param) {
                return param.getValue().getValue().profit.asObject();
            }

        });


        List<DBObject> listProducts = productTransaction.SelectAllSuppliers();
        listProducts.stream().forEach(dbObject -> {

            String supplierId = dbObject.get("supplierId").toString();

            DBObject dbObject1 = supplierTransaction.SelectSupplierById(supplierId);

            productTable_data.add(new productTable(dbObject.get("_id").toString(),
                    dbObject.get("name").toString(),
                    dbObject.get("model").toString(),
                    dbObject.get("date").toString(),
                    Double.parseDouble(dbObject.get("amount").toString()),
                    Double.parseDouble(dbObject.get("buyPrice").toString()),
                    Double.parseDouble(dbObject.get("profit").toString()),
                    Double.parseDouble(dbObject.get("sellPrice").toString()),
                    dbObject1.get("name").toString()
            ));

        });


        final TreeItem<productTable> root = new RecursiveTreeItem<productTable>(productTable_data, RecursiveTreeObject::getChildren);
//        tableview.getColumns().setAll(NaklTable_date, NaklTable_bolisa, NaklTable_carNum, NaklTable_weight, NaklTable_nawlon, NaklTable_ohda, NaklTable_agz, NaklTable_added, NaklTable_mezan, NaklTable_discount, NaklTable_office, NaklTable_clear, NaklTable_type, NaklTable_notes);
        tableProduct.setRoot(root);
        tableProduct.setShowRoot(false);

    }

    @FXML
    void refreshTableAction(ActionEvent event) {
        productTable_data.clear();

        List<DBObject> listProducts = productTransaction.SelectAllSuppliers();
        listProducts.stream().forEach(dbObject -> {

            String supplierId = dbObject.get("supplierId").toString();

            DBObject dbObject1 = supplierTransaction.SelectSupplierById(supplierId);

            productTable_data.add(new productTable(dbObject.get("_id").toString(),
                    dbObject.get("name").toString(),
                    dbObject.get("model").toString(),
                    dbObject.get("date").toString(),
                    Double.parseDouble(dbObject.get("buyPrice").toString()),
                    Double.parseDouble(dbObject.get("profit").toString()),
                    Double.parseDouble(dbObject.get("sellPrice").toString()),
                    Double.parseDouble(dbObject.get("amount").toString()),
                    dbObject1.get("name").toString()
            ));

        });


        final TreeItem<productTable> root = new RecursiveTreeItem<productTable>(productTable_data, RecursiveTreeObject::getChildren);
//        tableview.getColumns().setAll(NaklTable_date, NaklTable_bolisa, NaklTable_carNum, NaklTable_weight, NaklTable_nawlon, NaklTable_ohda, NaklTable_agz, NaklTable_added, NaklTable_mezan, NaklTable_discount, NaklTable_office, NaklTable_clear, NaklTable_type, NaklTable_notes);
        tableProduct.setRoot(root);

    }

    class productTable extends RecursiveTreeObject<productTable> {
        SimpleStringProperty id;
        SimpleStringProperty name;
        SimpleStringProperty model;
        SimpleStringProperty date;
        SimpleDoubleProperty amount;
        SimpleStringProperty suppName;
        SimpleDoubleProperty buyPrice;
        SimpleDoubleProperty profit;
        SimpleDoubleProperty sellPrice;

        public productTable(String id, String name, String model, String date, Double amount, Double buyPrice, Double profit, Double sellPrice, String suppName) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.model = new SimpleStringProperty(model);
            this.date = new SimpleStringProperty(date);
            this.suppName = new SimpleStringProperty(suppName);

            this.amount = new SimpleDoubleProperty(amount);
            this.buyPrice = new SimpleDoubleProperty(buyPrice);
            this.profit = new SimpleDoubleProperty(profit);
            this.sellPrice = new SimpleDoubleProperty(sellPrice);

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

        public String getModel() {
            return model.get();
        }

        public SimpleStringProperty modelProperty() {
            return model;
        }

        public void setModel(String model) {
            this.model.set(model);
        }

        public String getDate() {
            return date.get();
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public double getAmount() {
            return amount.get();
        }

        public SimpleDoubleProperty amountProperty() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount.set(amount);
        }

        public String getSuppName() {
            return suppName.get();
        }

        public SimpleStringProperty suppNameProperty() {
            return suppName;
        }

        public void setSuppName(String suppName) {
            this.suppName.set(suppName);
        }

        public double getBuyPrice() {
            return buyPrice.get();
        }

        public SimpleDoubleProperty buyPriceProperty() {
            return buyPrice;
        }

        public void setBuyPrice(double buyPrice) {
            this.buyPrice.set(buyPrice);
        }

        public double getProfit() {
            return profit.get();
        }

        public SimpleDoubleProperty profitProperty() {
            return profit;
        }

        public void setProfit(double profit) {
            this.profit.set(profit);
        }

        public double getSellPrice() {
            return sellPrice.get();
        }

        public SimpleDoubleProperty sellPriceProperty() {
            return sellPrice;
        }

        public void setSellPrice(double sellPrice) {
            this.sellPrice.set(sellPrice);
        }
    }


}
