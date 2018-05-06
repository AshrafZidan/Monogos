/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Model.userTransaction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.BasicDBObject;
import dialogs.dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class CreateNewUserController implements Initializable {

    @FXML
    private JFXTextField UserName;

    @FXML
    private ToggleGroup role;

    @FXML
    private JFXTextField UsereEmail;

    @FXML
    private JFXTextField UserPhone;

    @FXML
    private JFXPasswordField UserePass;

    @FXML
    private JFXTextField UserAddress;

    @FXML
    private JFXButton addUserBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    @FXML
    void addUser(ActionEvent event) {

        if (UserName.getText().trim().isEmpty()
                || UserPhone.getText().trim().isEmpty()
                || UserAddress.getText().trim().isEmpty()
                || UsereEmail.getText().trim().isEmpty()
                || UserePass.getText().trim().isEmpty()
                || role.getSelectedToggle().toString().isEmpty()

                ) {

            dialog Warning = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


        } else {

            String hashed_pass = password.hashPassword(UserePass.getText().toString());
            RadioButton selectedRadioButton = (RadioButton) role.getSelectedToggle();

            BasicDBObject object = userTransaction.insertUser( UserName.getText().toLowerCase(), UserAddress.getText(), UsereEmail.getText(), UserPhone.getText() ,hashed_pass , selectedRadioButton.getText().toString());

            if (object != null) {
                dialog Done = new dialog(Alert.AlertType.CONFIRMATION, "تم", "تم حفظ العميل");


            }

        }


    }


}
