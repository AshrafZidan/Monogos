/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import loadFxml.MainUpdateProduct;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class ProductsController implements Initializable {


    @FXML
    private JFXButton addNew;


    @FXML
    private TreeTableView<?> tableProduct;

    @FXML
    private TreeTableColumn<?, ?> proName;

    @FXML
    private TreeTableColumn<?, ?> proModel;

    @FXML
    private TreeTableColumn<?, ?> proAmount;

    @FXML
    private TreeTableColumn<?, ?> proBuyPrice;

    @FXML
    private TreeTableColumn<?, ?> proProfit;

    @FXML
    private TreeTableColumn<?, ?> proSellPrice;

    @FXML
    private TreeTableColumn<?, ?> proArriveDate;

    @FXML
    private TreeTableColumn<?, ?> proSuppName;


    @FXML
    void updateProductAction(ActionEvent event) {

//        get selection First


        MainUpdateProduct mainUpdateProduct = new MainUpdateProduct("");
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
