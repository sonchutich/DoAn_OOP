/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dba.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Product;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
        dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        
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
        String MaLo = txt_Malo.getText();
        String MaMH = txt_maHH.getText();
        int SL = Integer.valueOf(txt_soLuong.getText());
        String DVT = txt_DVT.getText();
        int GiaNhap = Integer.valueOf(txt_giaNhap.getText());
        int GiaBan = Integer.valueOf(txt_giaBan.getText());
        String HSD = HSDFld.getText();
        String NCC = txt_NCC.getText();
        try {
            query = "update LoHang\n" +
            "set MaMH = '"+MaMH+"',SoLuong = "+SL+",DVT = N'"+DVT+"',NCC = N'"+NCC+"',\n" +
            "GiaNhap = "+GiaNhap+", GiaBan = "+GiaBan+", HSD = '"+HSD+"'\n" +
            "where MaLo = '"+MaLo+"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Done");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InfoProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
