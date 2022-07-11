/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dba.ConnectDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Product;

/**
 * FXML Controller class
 *
 * @author demon
 */
public class SanPhamController implements Initializable {

    @FXML
    private TextField txt_Search_DVT;
    @FXML
    private TextField txt_Search_MaMH;
    @FXML
    private TextField txt_GiaBD;
    @FXML
    private TextField txt_GiaKT;
    @FXML
    private Button btn_refresh;
    @FXML
    private TextField txt_MaHH;
    @FXML
    private TextField txt_TenHH;
    @FXML
    private TextField txt_SL;
    @FXML
    private TextField txt_GiaBan;
    @FXML
    private TextField txt_DVT;
    @FXML
    private TableView<Product> tb_Sanpham;
    @FXML
    private TableColumn<Product, Integer> stt_col;
    @FXML
    private TableColumn<Product, String> MaHH_col;
    @FXML
    private TableColumn<Product, String> TenHH_col;
    @FXML
    private TableColumn<Product, String> DVT_col;
    @FXML
    private TableColumn<Product, String> SL_col;
    @FXML
    private TableColumn<Product, String> GiaBan_Col;
    @FXML
    private TableColumn<Product, String> Note_col;
    @FXML
    private TextField txt_Status;
    String query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Product product = null;

    ObservableList<Product> ProductList = FXCollections.observableArrayList();
    Connection con = ConnectDB.getConnect();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTable();
    }

    private void loadTable() {

        refreshTable();
        stt_col.setCellValueFactory(new PropertyValueFactory<>("STT"));
        MaHH_col.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
        TenHH_col.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        DVT_col.setCellValueFactory(new PropertyValueFactory<>("DVT"));
        SL_col.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
        GiaBan_Col.setCellValueFactory(new PropertyValueFactory<>("GiaBan"));

        tb_Sanpham.setItems(ProductList);
    }

    private void refreshTable() {
        try {
            ProductList.clear();

            query = "select ROW_NUMBER() OVER (ORDER BY a.MaMH) AS [STT],a.MaMH,b.TenMH, a.DVT, SUM(a.SoLuong) as SoLuong,  a.GiaBan from LoHang a, MatHang b where a.MaMH = b.MaMH group by a.MaMH, b.TenMH, a.GiaBan, a.DVT;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductList.add(new Product(
                        rs.getInt("STT"),
                        rs.getString("MaMH"),
                        rs.getString("TenMH"),
                        rs.getString("DVT"),
                        rs.getInt("SoLuong"),
                        rs.getInt("GiaBan")));
                tb_Sanpham.setItems(ProductList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setInfo(MouseEvent event){
        Product clickedProduct = tb_Sanpham.getSelectionModel().getSelectedItem();
        txt_MaHH.setText(clickedProduct.getMaMH());
        txt_TenHH.setText(clickedProduct.getTen());
        txt_DVT.setText(clickedProduct.getDVT());
        txt_SL.setText(String.valueOf(clickedProduct.getSoLuong()));
        txt_GiaBan.setText(String.valueOf(clickedProduct.getGiaBan()));
        int SL = clickedProduct.getSoLuong();
        if(SL <= 100){
            txt_Status.setText("Sắp Hết");
            txt_Status.setStyle("-fx-text-fill: red;");
        }else{
            txt_Status.setText("Ready");
            txt_Status.setStyle("-fx-text-fill: green;");
        }
    }
    public void TimKiemSP(ActionEvent e){
        String MaMH = txt_Search_MaMH.getText();
        ProductList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY a.MaMH) AS [STT],a.MaMH,b.TenMH, a.DVT, SUM(a.SoLuong) as SoLuong,  a.GiaBan from LoHang a, MatHang b where a.MaMH = b.MaMH and a.MaMH = '"+MaMH+"' group by a.MaMH, b.TenMH, a.GiaBan, a.DVT;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductList.add(new Product(
                        rs.getInt("STT"),
                        rs.getString("MaMH"),
                        rs.getString("TenMH"),
                        rs.getString("DVT"),
                        rs.getInt("SoLuong"),
                        rs.getInt("GiaBan")));
                tb_Sanpham.setItems(ProductList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void filterDVT(ActionEvent e){
        String DVT = txt_Search_DVT.getText();
        ProductList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY a.MaMH) AS [STT],a.MaMH,b.TenMH, a.DVT, SUM(a.SoLuong) as SoLuong,  a.GiaBan from LoHang a, MatHang b where a.MaMH = b.MaMH and a.DVT = '"+DVT+"' group by a.MaMH, b.TenMH, a.GiaBan, a.DVT;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductList.add(new Product(
                        rs.getInt("STT"),
                        rs.getString("MaMH"),
                        rs.getString("TenMH"),
                        rs.getString("DVT"),
                        rs.getInt("SoLuong"),
                        rs.getInt("GiaBan")));
                tb_Sanpham.setItems(ProductList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void filterPrice(ActionEvent e){
        String GiaBD = txt_GiaBD.getText();
        String GiaKT = txt_GiaKT.getText();
        ProductList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY a.MaMH) AS [STT],a.MaMH,b.TenMH, a.DVT, SUM(a.SoLuong) as SoLuong,  a.GiaBan \n" +
            "from LoHang a, MatHang b where a.MaMH = b.MaMH and a.GiaBan between "+GiaBD+" and "+GiaKT+" \n" +
            "group by a.MaMH, b.TenMH, a.GiaBan, a.DVT;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductList.add(new Product(
                        rs.getInt("STT"),
                        rs.getString("MaMH"),
                        rs.getString("TenMH"),
                        rs.getString("DVT"),
                        rs.getInt("SoLuong"),
                        rs.getInt("GiaBan")));
                tb_Sanpham.setItems(ProductList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void refresh(ActionEvent e){
        txt_Search_MaMH.clear();
        txt_Search_DVT.clear();
        txt_GiaBD.clear();
        txt_GiaKT.clear();
        refreshTable();
    }
}
