package loadFxml;

import Controllers.EmployeeController;
import Controllers.UpdateEmployeeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainUpdateEmployee {

    public MainUpdateEmployee(String id) {

        try {

            UpdateEmployeeController.employeeId = id;
            Stage home = new Stage();

            Parent p = FXMLLoader.load(getClass().getResource("/fxml/updateEmployee.fxml"));
;
            Scene scene = new Scene(p);

            home.setTitle("Update Employee");

            home.setScene(scene);
            home.setResizable(false);

            scene.setFill(Color.TRANSPARENT); //Makes scene background transparent

            home.initModality(Modality.APPLICATION_MODAL);

            home.showAndWait();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }




}
