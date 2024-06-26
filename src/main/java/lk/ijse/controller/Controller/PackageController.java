package lk.ijse.controller.Controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bo.PackageBO;
import lk.ijse.bo.custom.BOFactory;
import lk.ijse.bo.custom.BOTypes;
import lk.ijse.dao.PackageDAO;
import lk.ijse.dto.PackageDTO;
import lk.ijse.dto.tm.PackageTm;
import lk.ijse.dao.impl.PackageDaoImpl;
import lk.ijse.entity.Package;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PackageController {


    @FXML
    private Button pacclear;

    @FXML
    private Button packasave;

    @FXML
    private Button packback;

    @FXML
    private Button packdelete;

    @FXML
    private Button packupdate;
    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> coldes;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableView<PackageTm> tblPackage;

    @FXML
    private TextField txtDescription;

    @FXML
    private Label txtPackage;

    @FXML
    private TextField txtPackageId;

    @FXML
    private TextField txtPackageName;

    @FXML
    private TextField txtPrice;


    PackageBO packageBO = (PackageBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.PACKAGE);

    public void initialize(){
        setCellValueFactory();
        loadAllPackage();
        generateNewPackageId();
    }

    private void loadAllPackage() {
        ObservableList<PackageTm> obList = FXCollections.observableArrayList();

        try {
            List<PackageDTO> PackageList = packageBO.getAllpackages();
            for(PackageDTO Package : PackageList){
                PackageTm cusTm = new PackageTm(
                        Package.getPackageId(),
                        Package.getName(),
                        Package.getType(),
                        Package.getPrice()
                );
                obList.add(cusTm);
            }
            tblPackage.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void generateNewPackageId() {
        try {
            String lastId = packageBO.getLastPackageId();
            String newId = generateNextPackageId(lastId);
            txtPackageId.setText(newId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate package ID: " + e.getMessage()).show();
        }
    }

    private String generateNextPackageId(String lastId) {
        if (lastId == null || lastId.isEmpty()) {
            return "P001";
        }


        int num = Integer.parseInt(lastId.substring(1)); // Assuming "P" prefix

        // Incrementing the numeric part
        num++;


        String paddedNum = String.format("%03d", num);

        return "P" + paddedNum;
    }


    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("packageId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    @FXML
    void backBtnOnAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBord.fxml"));
            Parent Loginpanel = loader.load();

            Stage stage = (Stage) packback.getScene().getWindow();
            stage.setTitle("Login");

            // Animate the exit of the welcome page
            Timeline exitTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(packback.translateXProperty(), -packback.getWidth(), Interpolator.EASE_BOTH))
            );
            exitTimeline.setOnFinished(e -> {
                // Animate the entrance of the service page
                double sceneWidth = stage.getScene().getWidth(); // or any other suitable width
                Loginpanel.translateXProperty().set(sceneWidth);
                stage.getScene().setRoot(Loginpanel);
                Timeline entranceTimeline = new Timeline(
                        new KeyFrame(Duration.seconds(1),
                                new KeyValue(Loginpanel.translateXProperty(), 0, Interpolator.EASE_BOTH))
                );
                entranceTimeline.play();
            });
            exitTimeline.play();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void clearBtnOnAction(ActionEvent event) {
                clearFields();
    }
    private void clearFields() {
        txtPackageId.setText("");
        txtPackageName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String packageId = txtPackageId.getText();

        if (isValied()){
            try {
                boolean isDeleted = packageBO.deletePackage(packageId);
                if(isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Package deleted!").show();
                    loadAllPackage();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }



    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String packageId = txtPackageId.getText();
        String packageName = txtPackageName.getText();
        String description = txtDescription.getText();
        String priceText = txtPrice.getText();

        if (isValied()){
            if (packageId.isEmpty() || packageName.isEmpty() || description.isEmpty() || priceText.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Empty Fields! Try again").show();
                return;
            }

            double price = Double.parseDouble(priceText);

            PackageDTO packagee = new PackageDTO(packageId, packageName, description, price);
            try {
                boolean isSaved = packageBO.savePackage(packagee);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Package saved successfully!").show();
                    loadAllPackage();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }



    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        String packageId = txtPackageId.getText();
        String packageName = txtPackageName.getText();
        String description = txtDescription.getText();
        String priceText = txtPrice.getText();

        if (isValied()){
            if (packageId.isEmpty() || packageName.isEmpty() || description.isEmpty() || priceText.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Empty Fields! Try again").show();
                return;
            }

            double price = Double.parseDouble(priceText);

            PackageDTO packagee = new PackageDTO(packageId, packageName, description, price);
            try {
                boolean isUpdated = packageBO.updatePackage(packagee);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Package updated successfully!").show();
                    loadAllPackage();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }



    }


    @FXML
    void txtSearchOnAction(ActionEvent event) {
        try {
            String packageID = txtPackageId.getText();

            PackageDTO pkg = packageBO.searchPackageById(packageID);
            if (pkg != null) {
                txtPackageId.setText(pkg.getPackageId());
                txtPackageName.setText(pkg.getName());
                txtDescription.setText(pkg.getType());
                txtPrice.setText(String.valueOf(pkg.getPrice()));
            } else {
                new Alert(Alert.AlertType.ERROR, "Package is not found!").show();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching for the package.").show();
        }
    }


    public void TypeOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.LETTERS_AND_NUMBERS,txtDescription);


    }

    public void priceOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtPrice);


    }

    public void NameOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtPackageName);




    }

    public void IdOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PackageID,txtPackageId);

    }

    public boolean isValied(){
        if (! Regex.setTextColor(TextFields.LETTERS_AND_NUMBERS,txtDescription)) return false;
        if (!Regex.setTextColor(TextFields.DOUBLE,txtPrice)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtPackageName)) return false;
        if (!Regex.setTextColor(TextFields.PackageID,txtPackageId)) return false;


        return true;
    }
}
