/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.storeTransaction;
import Model.userTransaction;
import com.jfoenix.controls.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import dialogs.dialog;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Zi-D-aN
 */
public class ProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private JFXButton createNewUserBtn;


    @FXML
    private TextField newUserName, newUserAddress, newUserPhone, newUserEmail;

    @FXML
    private JFXButton loadInfoBtn;


    // **************************************

    ObservableList<ProfileController.tableUser> user_data = FXCollections.observableArrayList();
    @FXML
    private JFXButton refreshTable;
    @FXML
    private TreeTableView<ProfileController.tableUser> tableUser;

    @FXML
    private TreeTableColumn<ProfileController.tableUser, String> tableUserName;

    @FXML
    private TreeTableColumn<ProfileController.tableUser, String> tablePhone;

    @FXML
    private TreeTableColumn<ProfileController.tableUser, String> tableRole;

    @FXML
    private TreeTableColumn<ProfileController.tableUser, String> tableAddress;


    @FXML
    private TreeTableColumn tableEmail;

    @FXML
    private JFXButton deleteUser;

    @FXML
    private JFXButton updateUser;

    @FXML
    private JFXButton addUser;


    // *-**************************************
    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane pane1 , pane2 , pane3;

    @FXML
    private Pane mainPane;

    @FXML
    private Label label;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXButton closeBtn;

    @FXML
    private TitledPane pane22;



    @FXML
    private HBox hbox1;


    @FXML
    private HBox hbox2;


    @FXML
    private HBox hbox3;

    @FXML
    private PasswordField oldPass, newPass, confirmPass, newUserPass;

    @FXML
    private ToggleGroup role;

    @FXML
    private HBox hbox4;


    @FXML
    private HBox hbox5;


    @FXML
    private JFXButton savePhone;


    @FXML
    private Pane mainPane2, infoPane, phonPane, tablePane, userPane , pane221;

    @FXML
    private Label label2;

    @FXML
    private JFXButton backup, save;

    @FXML
    TextField UserName, UserAddress, UserPhone, UserEmail;

    @FXML
    void refreshTableAction(ActionEvent event) {
//        resetFields();
        this.updateUser.setDisable(true);
        this.addUser.setDisable(false);
    }


    String recivedPass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UserName.setDisable(true);
        UserAddress.setDisable(true);
        UserPhone.setDisable(true);
        UserEmail.setDisable(true);
        // TODO
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = primaryScreenBounds.getWidth() - 180;

        accordion.setPrefWidth(screenWidth);

        double width = primaryScreenBounds.getWidth() - 200;
        phonPane.setPrefWidth(screenWidth / 2);
        infoPane.setLayoutX(phonPane.getPrefWidth() + 10);

        userPane.setPrefWidth(screenWidth / 2);
        tablePane.setLayoutX(userPane.getPrefWidth() + 10);

        tableUser.setPrefWidth(screenWidth - userPane.getPrefWidth() - 10);
        tableUserName.setPrefWidth(tableUser.getPrefWidth() / 5);
        tablePhone.setPrefWidth(tableUser.getPrefWidth() / 5);
        tableEmail.setPrefWidth(tableUser.getPrefWidth() / 5);
        tableAddress.setPrefWidth(tableUser.getPrefWidth() / 5);
        tableRole.setPrefWidth(tableUser.getPrefWidth() / 5);

        infoPane.setLayoutX(phonPane.getPrefWidth() + 10);
        label.setLayoutX(width / 2);

        label2.setLayoutX((width / 2) - 160);
        logoutBtn.setLayoutX(width / 2);
        backup.setLayoutX((width / 2) - 130);
        closeBtn.setLayoutX(width / 2);
        pane2.setPrefWidth(screenWidth);
        accordion.setExpandedPane(pane1);

        // --------------------------------- init table user ---------------------------
        tableUserName.setCellValueFactory(new Callback <TreeTableColumn.CellDataFeatures<ProfileController.tableUser , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProfileController.tableUser, String> param) {
                return param.getValue().getValue().name;
            }

        });


        tablePhone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProfileController.tableUser, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProfileController.tableUser, String> param) {
                return param.getValue().getValue().phone;
            }

        });
        tableAddress.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProfileController.tableUser, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProfileController.tableUser, String> param) {
                return param.getValue().getValue().address;
            }


        });
        tableEmail.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProfileController.tableUser, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProfileController.tableUser , String> param) {
                return param.getValue().getValue().email;
            }


        });
        tableRole.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProfileController.tableUser, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProfileController.tableUser, String> param) {
                return param.getValue().getValue().role;
            }
        });


        // Select All Suppliers

        List<DBObject> dbObjects = userTransaction.SelectAll();
        dbObjects.stream().forEach(ee ->{
            user_data.add(new ProfileController.tableUser(ee.get("_id").toString(), ee.get("name").toString(), ee.get("phone").toString(), ee.get("address").toString(),ee.get("email").toString(), ee.get("role").toString()));


        });


        final TreeItem<ProfileController.tableUser> root = new RecursiveTreeItem<tableUser>(user_data, RecursiveTreeObject::getChildren);

        tableUser.setRoot(root);
        tableUser.setShowRoot(false);


    }


    @FXML
    void deleteUserAction(ActionEvent e){
        // check Selection
        RecursiveTreeItem selectedItem = (RecursiveTreeItem) tableUser.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            tableUser userTableSelected = (ProfileController.tableUser) selectedItem.getValue();
            BasicDBObject basicDBObject = userTransaction.deleteUser(userTableSelected.id.get());

            if (basicDBObject != null) {
                boolean t =  user_data.remove(userTableSelected);
                final  TreeItem<tableUser>  root = new RecursiveTreeItem <>(user_data , RecursiveTreeObject::getChildren);
                tableUser.setRoot(root);

                if (!t) {
                    dialog warning = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح الموظف  من الجدول");

                }


            } else {
                dialog warning = new dialog(Alert.AlertType.WARNING, "خظأ", "خطأ فى مسح المستخدم من الداتابيز ");

            }


        } else {
            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "اختر المستخدم للمسح");


        }

    }


    @FXML
    void backupAction(ActionEvent e){}

    @FXML
    void addUserAction(ActionEvent event) {

        if (newUserName.getText().trim().isEmpty()
                || newUserAddress.getText().trim().isEmpty()
                || newUserEmail.getText().trim().isEmpty()
                || newUserPhone.getText().trim().isEmpty()
                || newUserPass.getText().trim().isEmpty()
                || role.getSelectedToggle().toString().isEmpty()

                ) {

            dialog Warning = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


        } else {

            String hashed_pass = password.hashPassword(newUserPass.getText().toString());
            RadioButton selectedRadioButton = (RadioButton) role.getSelectedToggle();

            BasicDBObject object = userTransaction.insertUser(newUserName.getText().toLowerCase(), newUserAddress.getText(), newUserEmail.getText(), newUserPhone.getText(), hashed_pass, selectedRadioButton.getText().toString());

            if (object != null) {
                dialog Done = new dialog(Alert.AlertType.CONFIRMATION, "تم", "تم حفظ العميل");


            }

        }


    }


    @FXML
    void saveAction(ActionEvent event) {
        if (UserName.getText().trim().isEmpty()
                || UserAddress.getText().trim().isEmpty()
                || UserPhone.getText().trim().isEmpty()
                || UserEmail.getText().trim().isEmpty()
                || oldPass.getText().trim().isEmpty()
                || newPass.getText().trim().isEmpty()
                || confirmPass.getText().trim().isEmpty()
                ) {

            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "ادخل جميع البيانات");


        } else if (!password.checkPassword(oldPass.getText(), recivedPass)) {
            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "كلمه السر غير صحيحه");

        } else if (!newPass.getText().toString().toLowerCase().equals(confirmPass.getText().toString().toLowerCase())) {
            dialog dd = new dialog(Alert.AlertType.WARNING, "خظأ", "كلمه السر غير مطابقة");
        } else {
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("name", UserName.getText());
            basicDBObject.put("address", UserAddress.getText());
            basicDBObject.put("phone", UserPhone.getText());
            basicDBObject.put("email", UserEmail.getText());

            String newHasedPass = password.hashPassword(newPass.getText().toString());
            basicDBObject.put("pass", newHasedPass);


            BasicDBObject basicDBObjectUpdated = userTransaction.updateUser("5ada0428c466192c246b9179", basicDBObject);
            if (basicDBObjectUpdated != null) {
                dialog dd = new dialog(Alert.AlertType.CONFIRMATION, "تم", "تم التعديل");


            }

        }


    }

    @FXML
    void loadInfoAction(ActionEvent event) {

        //get current  user id  to load his infos
        DBObject userObj = userTransaction.SelectbyID("5ada0428c466192c246b9179");
        UserName.setText(userObj.get("name").toString());
        UserEmail.setText(userObj.get("email").toString());
        UserAddress.setText(userObj.get("address").toString());
        UserPhone.setText(userObj.get("phone").toString());
        recivedPass = userObj.get("pass").toString();

        UserName.setDisable(false);
        UserEmail.setDisable(false);
        UserAddress.setDisable(false);
        UserPhone.setDisable(false);
    }


    @FXML
    void closeAction(ActionEvent event) {
        System.exit(0);
    }

    public void logoutAction(javafx.event.ActionEvent actionEvent) throws IOException {

        ((Stage) this.closeBtn.getScene().getWindow()).close();
        MainStart mainStart = new MainStart();

    }


    class tableUser extends RecursiveTreeObject<tableUser> {
        SimpleStringProperty id;
        SimpleStringProperty name;
        SimpleStringProperty phone;
        SimpleStringProperty address;
        SimpleStringProperty email;
        SimpleStringProperty role;

        public tableUser(String id, String name, String phone, String address,String email, String role) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.phone = new SimpleStringProperty(phone);
            this.address = new SimpleStringProperty(address);
            this.email = new SimpleStringProperty(email);
            this.role = new SimpleStringProperty(role);

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

        public String getPhone() {
            return phone.get();
        }

        public SimpleStringProperty phoneProperty() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone.set(phone);
        }

        public String getAddress() {
            return address.get();
        }

        public SimpleStringProperty addressProperty() {
            return address;
        }

        public void setAddress(String address) {
            this.address.set(address);
        }


        public String getRole() {
            return role.get();
        }

        public SimpleStringProperty roleProperty() {
            return role;
        }

        public void setRole(String role) {
            this.role.set(role);
        }


        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emalProperty() {
            return email;
        }

        public void setEmail(String email) {
            this.email.set(email);
        }
    }


}