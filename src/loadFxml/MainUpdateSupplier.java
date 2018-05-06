package loadFxml;

import Controllers.UpdateSupplierController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by ahmed mar3y on 19/04/2018.
 */
public class MainUpdateSupplier {

    public MainUpdateSupplier(String id) {

        try {
            UpdateSupplierController.supplierId = id;
            Stage home = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent p = fxmlLoader.load(getClass().getResource("/fxml/updateSupplier.fxml"));
//                UpdateSupplierController updateSupplierController = (UpdateSupplierController) fxmlLoader.getController();
//                updateSupplierController.supplierId=supplierTable.id.get();
//                updateSupplierController.name=supplierTable.name.get();

//                System.out.println(supplierTable.id.get());
//                System.out.println(supplierTable.name.get());
//                System.out.println(supplierTable.phone.get());
//                System.out.println(supplierTable.address.get());

//                updateSupplierController.setValues(supplierTable.id.get(), supplierTable.name.get(), supplierTable.phone.get(), supplierTable.address.get());
//                Parent root = FXMLLoader.load(getClass().getResource("/fxml/updateSupplier.fxml"));
            Scene scene = new Scene(p);

            home.setTitle("Update Supplier");

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
