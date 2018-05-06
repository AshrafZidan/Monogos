package loadFxml;

import Controllers.UpdateEmployeeController;
import Controllers.UpdateStoreContoller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainUpdateStores {
    public MainUpdateStores(String id) {

        try {

            UpdateStoreContoller.storeId = id;
            Stage home = new Stage();

            Parent p = FXMLLoader.load(getClass().getResource("/fxml/updateStore.fxml"));

            Scene scene = new Scene(p);

            home.setTitle("Update Store");

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
