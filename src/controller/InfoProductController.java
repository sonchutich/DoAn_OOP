/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dba.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Product;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author demon
 */
public class InfoProductController {

    @FXML
    private Button btn_back;
    @FXML
    private TextField txt_MaPN;
    @FXML
    private TextField NgayNhapFld;
    @FXML
    private TextField txt_GioNhap;
    @FXML
    private TextField txt_Malo;
    @FXML
    private TextField txt_maHH;
    @FXML
    private TextField txt_tenHH;
    @FXML
    private TextField txt_DVT;
    @FXML
    private TextField txt_giaNhap;
    @FXML
    private TextField txt_giaBan;
    @FXML
    private TextField txt_soLuong;
    @FXML
    private TextField HSDFld;
    @FXML
    private TextField txt_NCC;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_delete;

    String query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Product product = null;

    ObservableList<Product> ProductList = FXCollections.observableArrayList();
    Connection con = ConnectDB.getConnect();

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    

    public void setProduct(Product product) {
        
        DateFormat dateFormat = null;
        DateFormat timeFormat = null;
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String NgayNhap = dateFormat.format(product.getNgayNhap());
        String HSD = dateFormat.format(product.getHSD());
        String GioNhap = timeFormat.format(product.getGioNhap());
        
        txt_MaPN.setText(String.valueOf(product.getMaPN()));
        NgayNhapFld.setText(NgayNhap);
        txt_Malo.setText(product.getMaLo());
        txt_maHH.setText(product.getMaMH());
        txt_tenHH.setText(product.getTen());
        txt_DVT.setText(product.getDVT());
        txt_soLuong.setText(String.valueOf(product.getSoLuong()));
        txt_giaNhap.setText(String.valueOf(product.getGiaNhap()));
        txt_giaBan.setText(String.valueOf(product.getGiaBan()));
        HSDFld.setText(HSD);
        txt_GioNhap.setText(GioNhap);
        txt_NCC.setText(product.getNCC());

    }
    public void edit(ActionEvent e){
        
    }
//    private int executeQuery(String query) {
//        int ERROR = -1;
//        int rowEffected = 0;
//        Statement st;
//        try {
//            st = con.createStatement();
//            rowEffected = st.executeUpdate(query);
//        } catch (Exception ex) {
//            rowEffected = ERROR;
//            ex.printStackTrace();
//        }
//        return rowEffected;
//    }
}
