package Controllers;

import Model.productTransaction;
import Model.supplierTransaction;
//import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import dialogs.dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by ahmed mar3y on 06/05/2018.
 */
public class CreateNewProductController implements Initializable {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField model;

    @FXML
    private JFXTextField amount;

    @FXML
    private JFXTextField buyPrice;

    @FXML
    private JFXTextField profit;

    @FXML
    private JFXTextField sellPrice;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox supplierName;

    @FXML
    private JFXButton add;


    List<String> supplierNames;
    List<String> supplierIds;
    List<DBObject> suppliers;

    @FXML
    void addAction(ActionEvent event) {

        String pName = name.getText();
        String pModel = model.getText();
        String pAmount = amount.getText();
        String pBuyPrice = buyPrice.getText();
        String pSellPrice = sellPrice.getText();
        String pProfit = profit.getText();
        LocalDate localDate = date.getValue();
        boolean emptySUpplier = supplierName.getSelectionModel().isEmpty();

        if (
                pName.trim().isEmpty() ||
                        pModel.trim().isEmpty() ||
                        pAmount.trim().isEmpty() ||
                        pBuyPrice.trim().isEmpty() ||
                        pSellPrice.trim().isEmpty() ||
                        pProfit.trim().isEmpty() ||
                        localDate == null ||
                        emptySUpplier

                ) {

            dialog Warning = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");

        } else {


            int suppIndex = supplierName.getSelectionModel().getSelectedIndex();

            String supplierId = supplierIds.get(suppIndex);


            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("name", pName);
            basicDBObject.put("model", pModel);
            basicDBObject.put("amount", pAmount);
            basicDBObject.put("buyPrice", pBuyPrice);
            basicDBObject.put("profit", pProfit);
            basicDBObject.put("sellPrice", pSellPrice);
            basicDBObject.put("date", Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            basicDBObject.put("supplierId", supplierId);

            BasicDBObject basicDBObject1 = productTransaction.insertSupplier(basicDBObject);
            if (basicDBObject1 != null) {

                resetFields();
                dialog dd = new dialog(Alert.AlertType.CONFIRMATION, "Done ! ", "Done create Product");


            }


        }


    }

    private void resetFields() {

        this.supplierName.setValue("");
        this.model.setText("");
        this.amount.setText("");
        this.buyPrice.setText("");
        this.sellPrice.setText("");
        this.profit.setText("");

        // set init date
        String newstring = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        date.setValue(LOCAL_DATE(newstring));

    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
// select all supplier
        suppliers = supplierTransaction.SelectAllSuppliers();
        supplierNames = suppliers.stream().map(dbObject -> dbObject.get("name").toString()).collect(Collectors.toList());
        supplierIds = suppliers.stream().map(dbObject -> dbObject.get("_id").toString()).collect(Collectors.toList());
        supplierName.getItems().setAll(supplierNames);


        // set init date
        String newstring = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        date.setValue(LOCAL_DATE(newstring));


        // validate texts
//        buyPrice.setTextFormatter(Validation.DoubleValidation());
//        sellPrice.setTextFormatter(Validation.DoubleValidation());
//        profit.setTextFormatter(Validation.DoubleValidation());
//        amount.setTextFormatter(Validation.DoubleValidation());


    }
}
