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
import lk.ijse.bo.EmployeeBO;
import lk.ijse.bo.VehicleBO;
import lk.ijse.bo.custom.BOFactory;
import lk.ijse.bo.custom.BOTypes;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.dto.tm.VehicleTm;
import lk.ijse.dao.impl.EmployeeDaoImpl;
import lk.ijse.entity.Employee;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VehicleFormController {

    @FXML
    private TextField NoOfSeat;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> cmbEmployeeID;

    @FXML
    private TableColumn<?, ?> colempid;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colseat;

    @FXML
    private TableColumn<?, ?> colstatus;

    @FXML
    private TextField status;

    @FXML
    private TableView<VehicleTm> tblVehicle;

    @FXML
    private TextField vid;

    @FXML
    private Button vehback;

    @FXML
    private Button vehdelete;

    @FXML
    private Button vehicleclear;

    @FXML
    private Button vehiclup;

    @FXML
    private Button vehsave;


    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.VEHICLE);

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.EMPLOYEE);

    @FXML
    void backOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBord.fxml"));
            Parent Loginpanel = loader.load();

            Stage stage = (Stage) vehback.getScene().getWindow();
            stage.setTitle("Login");

            // Animate the exit of the welcome page
            Timeline exitTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(vehback.translateXProperty(), -vehback.getWidth(), Interpolator.EASE_BOTH))
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


    public void initialize(){
        getEmployeeId();
        setCellValueFactory();
        loadAllVehicles();
        generateNewVehicleId();



    }

    private String generateNextVehicleId(String lastId) {
        if (lastId == null || lastId.isEmpty()) {
            return "V0001"; // If there are no existing IDs, start from V0001
        }

        String prefix = lastId.substring(0, 1); // Extract the prefix (e.g., "V")
        int sequence = Integer.parseInt(lastId.substring(1)); // Extract the sequence part and convert it to an integer
        sequence++; // Increment the sequence
        String newId = prefix + String.format("%04d", sequence); // Format the new ID with leading zeros if necessary
        return newId;
    }

    private void generateNewVehicleId() {
        try {
            String lastId = vehicleBO.getLastVehicleId();
            String newId = generateNextVehicleId(lastId);
            vid.setText(newId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate vehicle ID: " + e.getMessage()).show();
        }
    }





    private void loadAllVehicles() {
        try {
            ObservableList<VehicleTm> obList = FXCollections.observableArrayList();
            List<VehicleDTO> vehicleList = vehicleBO.getAllVehicles();
            for (VehicleDTO vehicle : vehicleList) {
                obList.add(new VehicleTm(vehicle.getVehicleId(), vehicle.getNumberOfSeats(), vehicle.getStatus(), vehicle.getEmployeeId()));
            }
            tblVehicle.setItems(obList);
            tblVehicle.refresh();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colseat.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        colempid.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
    }


    @FXML
    void clearOnAction(ActionEvent event) {
        clearFields();

    }

    @FXML
    void cmbVehicleOnAction(ActionEvent event) {
        String employeeiD = cmbEmployeeID.getValue();

        try {
            EmployeeDTO Employee = employeeBO.searchEmployeeById(employeeiD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> employeeIdList = employeeBO.getAllEmployeeIds();

            for (String employeeId : employeeIdList) {
                obList.add(employeeId);
            }
            cmbEmployeeID.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void deleteOnAction(ActionEvent event) {
        String vehicleid = vid.getText();

        if (isValied()){
            try {
                boolean isDeleted = vehicleBO.deleteVehicle(vehicleid);
                if(isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Vehicle deleted successfully!").show();
                    loadAllVehicles();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }



    }


    @FXML
    void saveOnAction(ActionEvent event) {
        String vehicleId = vid.getText();
        String status = this.status.getText();
        String numberOfSeats = NoOfSeat.getText();
        String empId = String.valueOf(cmbEmployeeID.getValue());

        if (isValied()){
            if (vehicleId.isEmpty() || status.isEmpty() || numberOfSeats.isEmpty() || empId.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return; // Exit the method if any field is empty
            }

            VehicleDTO vehicle = new VehicleDTO(vehicleId, status, numberOfSeats, empId);
            try {
                boolean isSaved = vehicleBO.saveVehicle(vehicle);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Vehicle saved successfully!").show();
                    loadAllVehicles();

                    loadAllVehicles();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }



    }





    @FXML
    void updateOnAction(ActionEvent event) {
        String vehicleId = vid.getText();
        String status = this.status.getText();
        String numberOfSeats = NoOfSeat.getText();
        String empId = String.valueOf(cmbEmployeeID.getValue());

        if (isValied()){
            // Check if any of the input fields are empty
            if (vehicleId.isEmpty() || status.isEmpty() || numberOfSeats.isEmpty() || empId.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return; // Exit the method if any field is empty
            }

            VehicleDTO vehicle = new VehicleDTO(vehicleId, status, numberOfSeats, empId);
            try {
                boolean isUpdated = vehicleBO.updateVehicle(vehicle);
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Update Successful").show();
                    loadAllVehicles();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }



    }

    public void clearFields(){
        vid.clear();
        status.clear();
        NoOfSeat.clear();
        cmbEmployeeID.setValue(null);
        //cmbEmployeeId.setSelectionModel(null);
    }

    public void VehicleIdOnAction(ActionEvent actionEvent) {
        try {
            String vehicleId = vid.getText();

            VehicleDTO vehicle = vehicleBO.searchVehicleById(vehicleId);
            if (vehicle != null) {
                vid.setText(vehicle.getVehicleId());
                status.setText(vehicle.getStatus());
                NoOfSeat.setText(String.valueOf(vehicle.getNumberOfSeats()));
                // Assuming you have a ComboBox for employee ID
                cmbEmployeeID.setValue(vehicle.getEmployeeId());
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle is not found!").show();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching for the vehicle.").show();
        }
    }

    public void StatusOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.VehicActive,NoOfSeat);


    }

    public void NoOfSeatOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INT,status);

    }

    public void VehicleIdKeyOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.VehicId,vid);

    }

    public boolean isValied(){
        if (! Regex.setTextColor(TextFields.VehicActive,NoOfSeat)) return false;
        if (!Regex.setTextColor(TextFields.INT,status)) return false;
        if (!Regex.setTextColor(TextFields.VehicId,vid)) return false;

        return true;
    }
}
