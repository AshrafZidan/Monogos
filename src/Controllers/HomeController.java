/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private VBox Menu;

    @FXML
    private Pane homepage;


    Pane holderPane;

    @FXML
    private Text welcome;


    @FXML
    private JFXButton supplier , employee ,products , stores , setting ;


    Object ppanel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {


        try {
            createPage();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setNode(Node node) {
        homepage.getChildren().clear();
        homepage.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));

        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);

        ft.setAutoReverse(false);
        ft.play();
    }

    private void createPage() throws IOException {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        try {

            FXMLLoader loader = new FXMLLoader();
//            loader.setController(new EmployeeController());
            loader.setLocation(getClass().getResource("/fxml/Employee.fxml"));
            holderPane = loader.load();


            holderPane.setPrefWidth(primaryScreenBounds.getWidth() - Menu.getPrefWidth());
            holderPane.setPrefHeight(primaryScreenBounds.getHeight() );
            System.out.println("Holder : " + holderPane.getPrefWidth());
            setNode(holderPane);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    @FXML
    void MainButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == employee && ppanel != employee) {
//            setting.getStyleClass().remove("activeOne");
//            nakl.getStyleClass().add("activeOne");
//
//            money.getStyleClass().remove("activeOne");
//            accounts.getStyleClass().remove("activeOne");
//
//            kashfHesab.getStyleClass().remove("activeOne");

            ppanel = event.getSource();
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/fxml/Employee.fxml"));
            holderPane = loader.load();


            setNode(holderPane);
        }
        if (event.getSource() == supplier && ppanel != supplier) {
            setting.getStyleClass().remove("activeOne");
//            kashfHesab.getStyleClass().add("activeOne");
//
//            money.getStyleClass().remove("activeOne");
//            accounts.getStyleClass().remove("activeOne");
//
//            nakl.getStyleClass().remove("activeOne");
            ppanel = event.getSource();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/supplier.fxml"));
            holderPane = loader.load();


            setNode(holderPane);
        }
        if (event.getSource() == products && ppanel != products) {
            setting.getStyleClass().remove("activeOne");
//            accounts.getStyleClass().add("activeOne");
//
//            money.getStyleClass().remove("activeOne");
//            accounts.getStyleClass().remove("activeOne");
//            kashfHesab.getStyleClass().remove("activeOne");
//            nakl.getStyleClass().remove("activeOne");

            ppanel = event.getSource();


            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/fxml/products.fxml"));
            holderPane = loader.load();


            setNode(holderPane);
        }
        if (event.getSource() == stores && ppanel != stores) {
            setting.getStyleClass().remove("activeOne");
//            money.getStyleClass().add("activeOne");
//
//            clients.getStyleClass().remove("activeOne");
//            accounts.getStyleClass().remove("activeOne");
//            kashfHesab.getStyleClass().remove("activeOne");
//            nakl.getStyleClass().remove("activeOne");

            ppanel = event.getSource();


            FXMLLoader loader = new FXMLLoader();
//            loader.setController(new moneyController());
            loader.setLocation(getClass().getResource("/Fxml/stores.fxml"));
            holderPane = loader.load();


            setNode(holderPane);

        }
        if (event.getSource() == setting && ppanel != setting) {

            setting.getStyleClass().remove("activeOne");
//            clients.getStyleClass().add("activeOne");
//
//            money.getStyleClass().remove("activeOne");
//            accounts.getStyleClass().remove("activeOne");
//            kashfHesab.getStyleClass().remove("activeOne");
//            nakl.getStyleClass().remove("activeOne");


            ppanel = event.getSource();

            FXMLLoader loader = new FXMLLoader();
//            loader.setController(new ProfileController());
            loader.setLocation(getClass().getResource("/Fxml/Profile.fxml"));
            holderPane = loader.load();

            setNode(holderPane);

        }




    }


    @FXML
    private void SupplierPage(ActionEvent event) throws IOException {
        try {
            homepage = FXMLLoader.load(getClass().getResource("/fxml/supplier.fxml"));
            setNode(homepage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    @FXML
    private void productPage(ActionEvent event) throws IOException {
        try {
            homepage = FXMLLoader.load(getClass().getResource("/fxml/products.fxml"));
            setNode(homepage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    @FXML
    private void usersPage(ActionEvent event) throws IOException {
        try {
            homepage = FXMLLoader.load(getClass().getResource("/fxml/Users.fxml"));
            setNode(homepage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    @FXML
    private void employPage(ActionEvent event) throws IOException {
        try {
            homepage = FXMLLoader.load(getClass().getResource("/fxml/Employee.fxml"));
            setNode(homepage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    @FXML
    private void profilePage(ActionEvent event) throws IOException {
        try {
            homepage = FXMLLoader.load(getClass().getResource("/fxml/Profile.fxml"));
            setNode(homepage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


}
