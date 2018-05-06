/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Model.productTransaction;
import Model.supplierTransaction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mongodb.BasicDBObject;
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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
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
    private DatePicker from;

    @FXML
    private DatePicker to;
    @FXML
    private JFXButton addNew, delete;

    @FXML
    HBox hbox1, lastHbox;
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

    @FXML
    void searchProduct(ActionEvent event) {

        LocalDate fromEmpty = from.getValue();
        LocalDate toEmpty = to.getValue();
        if (fromEmpty != null && toEmpty != null) {
            productTable_data.clear();

            List<DBObject> dbObjects = productTransaction.SelectAllSuppliers();

            dbObjects.stream().filter(dbObject -> {

                if (
                        (((Date) dbObject.get("date")).after(Date.from(fromEmpty.atStartOfDay(ZoneId.systemDefault()).toInstant())) ||
                                ((Date) dbObject.get("date")).compareTo(Date.from(fromEmpty.atStartOfDay(ZoneId.systemDefault()).toInstant())) == 0
                        )

                                &&
                                (((Date) dbObject.get("date")).before(Date.from(toEmpty.atStartOfDay(ZoneId.systemDefault()).toInstant())) ||
                                        ((Date) dbObject.get("date")).compareTo(Date.from(toEmpty.atStartOfDay(ZoneId.systemDefault()).toInstant())) == 0
                                )

                        ) {


                    return true;
                }


                return false;
            }).forEach(dbObject -> {
                String supplierId = dbObject.get("supplierId").toString();

                DBObject dbObject1 = supplierTransaction.SelectSupplierById(supplierId);

                System.out.println("dddddddddddddddddd");

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

        }


    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        hbox1.setPrefWidth(primaryScreenBounds.getWidth() - 180);
        tableProduct.setPrefWidth(primaryScreenBounds.getWidth() - 190);

//        System.out.println(employeeTable.getLayoutX());

        lastHbox.setLayoutY(primaryScreenBounds.getHeight() - 75);
//        hbox4.setLayoutY(table.getPrefHeight());
        tableProduct.setPrefHeight(primaryScreenBounds.getHeight() - 200);

        proBuyPrice.setPrefWidth(tableProduct.getPrefWidth() / 8);
        proProfit.setPrefWidth(tableProduct.getPrefWidth() / 8);
        proSellPrice.setPrefWidth(tableProduct.getPrefWidth() / 8);
        proAmount.setPrefWidth(tableProduct.getPrefWidth() / 8);
        proSuppName.setPrefWidth(tableProduct.getPrefWidth() / 8);
        proArriveDate.setPrefWidth(tableProduct.getPrefWidth() / 8);
        proModel.setPrefWidth(tableProduct.getPrefWidth() / 8);
        proName.setPrefWidth(tableProduct.getPrefWidth() / 8);

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

    }

    @FXML
    void deleteProductAction(ActionEvent event) {


        // check Selection
        RecursiveTreeItem selectedItem = (RecursiveTreeItem) tableProduct.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            productTable supplierTableSelected = (productTable) selectedItem.getValue();

            BasicDBObject basicDBObject = supplierTransaction.deleteSupplier(supplierTableSelected.id.get());

            if (basicDBObject != null) {

                // delete from table
                boolean t = productTable_data.remove(supplierTableSelected);
                final TreeItem<productTable> root = new RecursiveTreeItem<productTable>(productTable_data, RecursiveTreeObject::getChildren);
                tableProduct.setRoot(root);
                if (!t) {
                    dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح المنتج من الجدول");

                }


            } else {
                dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح المنتج من الداتابيز ");

            }


        } else {
            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "اختر المنتج للمسح");


        }


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
