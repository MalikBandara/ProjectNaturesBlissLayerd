package lk.ijse.controller.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.bo.EmployeeBO;
import lk.ijse.bo.GuestBO;
import lk.ijse.bo.RoomBO;
import lk.ijse.bo.VehicleBO;
import lk.ijse.bo.custom.BOFactory;
import lk.ijse.bo.custom.BOTypes;
import lk.ijse.calender.Main;
import lk.ijse.dao.GuestDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.db.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Dash2Controller implements Initializable {


    @FXML
    private JFXButton btncal;

    @FXML
    private AnchorPane pane3;


    @FXML
    private PieChart pieChartt;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private AnchorPane pane4;


    @FXML
    private AnchorPane Dpane1;

    @FXML
    private AnchorPane Dpane2;

    @FXML
    private AnchorPane Dpane3;

    @FXML
    private AnchorPane Dpane4;

    @FXML
    private NumberAxis xaxist;

    @FXML
    private CategoryAxis yaxist;

    @FXML
    private Label lblEmployeecount;

    @FXML
    private Label lblRoomcount;

    @FXML
    private Label lblguestCount;

    @FXML
    private Label lblvehicleCount;

    private int GuestCount;

    private int vehicleCount;

    private int roomCount;

    private int empCount;


    GuestBO guestBO = (GuestBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.GUEST);
    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.VEHICLE);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.EMPLOYEE);
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.ROOM);

    private void setEmpCount(int empCount) {
        lblEmployeecount.setText(String.valueOf(empCount));
    }

    private int getEmpCount() throws SQLException {

        return employeeBO.getEmpCount();

    }

    private void setRoomCount(int roomCount) {

        lblRoomcount.setText(String.valueOf(roomCount));
    }

    private int getRoomCount() throws SQLException {

        return roomBO.getRoomsCount();


    }

    private void setVehicleCount(int vehicleCount) {
        lblvehicleCount.setText(String.valueOf(vehicleCount));
    }

    private int getVehicleCount() throws SQLException {
        return vehicleBO.getVehicleCount();

    }

    private void setCustomerCount(int GuestCount) {

        lblguestCount.setText(String.valueOf(GuestCount));
    }

    private int getGuestCount() throws SQLException {

        return guestBO.getGuestCount();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniLineChart();
        iniPieChart();
        try {
            GuestCount = getGuestCount();
            vehicleCount = getVehicleCount();
            roomCount = getRoomCount();
            empCount = getEmpCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setCustomerCount(GuestCount);
        setVehicleCount(vehicleCount);
        setRoomCount(roomCount);
        setEmpCount(empCount);

    }
    private void iniLineChart(){
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Mondays",8));
        series.getData().add(new XYChart.Data("Tuesday",12));
        series.getData().add(new XYChart.Data("Wednesday",16));
        series.getData().add(new XYChart.Data("Thursday",4));
        series.getData().add(new XYChart.Data("Friday",12));
        series.getData().add(new XYChart.Data("Saturday",10));
        series.getData().add(new XYChart.Data("Sunday",5));
        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        series.getNode().setStyle("-fx-stroke: #FFFF");
    }

    private void iniPieChart(){
        ObservableList<PieChart.Data> PiechartDate = FXCollections.observableArrayList(
                new PieChart.Data("Sri Lanka" , 15),
                new PieChart.Data("China" , 5),
                new PieChart.Data("Australia" , 35),
                new PieChart.Data("Russia" , 10),
                new PieChart.Data("Finland" , 35)

        );
        pieChartt.setData(PiechartDate);
    }

    public void btncalOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/calculator.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setTitle("Calculator");

        ((main.MainWindowController)loader.getController()).init(stage);
        stage.show();
    }

    public void CalenderOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Calender.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Calender!");
        stage.setScene(scene);
        stage.show();

    }
}
