/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class ProductsController implements Initializable {


    @FXML
    private JFXButton addNew;

    @FXML
    void addNewAction(ActionEvent event) {

        try {

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
    }

}
