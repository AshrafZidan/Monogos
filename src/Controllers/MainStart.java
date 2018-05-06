package Controllers;

import Controllers.LoginMainController;
import Model.employeeTransaction;
import Model.storeTransaction;
import Model.supplierTransaction;
import Model.userTransaction;
import com.mongodb.BasicDBObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Created by ahmed mar3y on 19/04/2018.
 */

    public class MainStart {


        public MainStart() throws IOException {

            Stage primaryStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Login.fxml"));

            FXMLLoader loader = new FXMLLoader();
            loader.setController(new LoginMainController());
            loader.setLocation(getClass().getResource("/fxml/loginMain.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);
            // on stage event close
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {

                    Platform.exit();
                    System.exit(0);

                }
            });

            primaryStage.setTitle("Login");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    }



