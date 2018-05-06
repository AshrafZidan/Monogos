/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.MongoConnection;
import Model.userTransaction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.DBObject;
import com.sun.javaws.progress.Progress;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dialogs.dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import loadFxml.MainHome;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class LoginMainController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private JFXButton login;

    @FXML
    private JFXTextField userName;


    @FXML
    private JFXPasswordField pass;


    @FXML
    AnchorPane loginpage;

    static String UserId;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        MongoConnection.createMongoConnection();


    }


    @FXML
    public void loginFunc(ActionEvent e) throws IOException {


        loginFunction();
//       if (usernameField.getText().isEmpty()) {
//           errorMsg.show("Username is empty !", 1500);
//           return;
//       }
//       if (passwordField.getText().isEmpty()) {
//           errorMsg.show("Password is empty !", 1500);
//           return;
//       }
//       System.out.println("Username : " + usernameField.getText());
//       System.out.println("Password : " + passwordField.getText());
//
//       errorMsg.show("Success !", 2000);

    }


    public void loginFunction() throws IOException {


        String username = userName.getText();
        String password = pass.getText();
        if (username.trim().isEmpty()
                || password.trim().isEmpty()
                ) {

            dialog dd = new dialog(Alert.AlertType.WARNING, "Error", "enter all data");

        } else {

            // if not found eny
            List<DBObject> dbObjects = userTransaction.SelectAll();
            if (dbObjects.isEmpty()) {

                // ad new user

                userTransaction.insertUser("admin", "admin", "admin", "123456", "admin", "admin");

            }
            DBObject dbObject = userTransaction.SelectByNamePass(username, password);
            if (dbObject == null) {
                // not found
                dialog dd = new dialog(Alert.AlertType.WARNING, "Error", "user Not Found ");


            } else {

                UserId = dbObject.get("_id").toString();
                ((Stage) userName.getScene().getWindow()).close();


                MainHome mainHome = new MainHome();
            }


        }

    }
}
