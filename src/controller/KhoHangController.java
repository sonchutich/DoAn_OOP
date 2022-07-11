/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;


import dba.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Product;

public class KhoHangController implements Initializable {

    @FXML
    private TextField txt_timkiem;
    @FXML
    private MenuButton btn_TimKiem;
    @FXML
    private TableView<Product> Product_tb;
    @FXML
    private TableColumn<Product, String> PN_col;
    @FXML
    private TableColumn<Product, String> Lo_col;
    @FXML
    private TableColumn<Product, String> ma_col;
    @FXML
    private TableColumn<Product, String> ten_col;
    @FXML
    private TableColumn<Product, String> DVT_col;
    @FXML
    private TableColumn<Product, String> SL_col;
    @FXML
    private TableColumn<Product, String> GiaNhap_col;
    @FXML
    private TableColumn<Product, String> GiaBan_col;
    @FXML
    private TableColumn<Product, String> NhayNhap_col;
    @FXML
    private TableColumn<Product, String> GioNhap_col;
    @FXML
    private TableColumn<Product, String> HSD_col;
    @FXML
    private TableColumn<Product, String> NCC_col;
    
    
    @FXML
    private TextField txt_MaPN;
    @FXML
    private TextField txt_MaLo;
    @FXML
    private TextField txt_GioNhap;
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
    private DatePicker HSDFld;
    @FXML
    private DatePicker NgayNhapFld;
    @FXML
    private TextField txt_NCC;
    @FXML
    private Button btn_NhapKho;
    @FXML
    private Button btn_Delete;
    @FXML
    private Button btn_Edit;

    
    String query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Product product = null;
    
    ObservableList<Product> ProductList = FXCollections.observableArrayList();
    Connection con = ConnectDB.getConnect();
    @FXML
    private DatePicker NgayNhapFld1;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTable();
    }
    public void handleButtonAction(ActionEvent event) throws SQLException {
        
        if(event.getSource() == btn_NhapKho){
            insertRecord();
        }
//        }else if (event.getSource() == btn_Edit){
//            updateRecord();
//        }else if(event.getSource() == btn_Delete){
//            deleteButton();
//        }
    }
    private void refreshTable(){
        try {
            ProductList.clear();
            
            //query = "SELECT LoHang.MaPN,LoHang.MaLo, LoHang.MaMH, MatHang.TenMH, LoHang.DVT, LoHang.SoLuong, LoHang.GiaNhap, LoHang.GiaBan FROM LoHang INNER JOIN MatHang on LoHang.MaMH = MatHang.MaMH";
            query = "SELECT a.MaPN,c.NgayNhap,c.GioNhap,a.MaLo, a.MaMH, b.TenMH, a.DVT, a.SoLuong, a.GiaNhap, a.GiaBan, a.HSD, a.NCC FROM LoHang a, MatHang b, PhieuNhap c where a.MaMH = b.MaMH and a.MaPN = c.MaPN";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                 ProductList.add(new Product(
                         rs.getInt("MaPN"),
                         rs.getString("MaLo"),
                         rs.getString("MaMH"),
                         rs.getString("TenMH"),
                         rs.getString("DVT"),
                         rs.getInt("GiaNhap"),
                         rs.getInt("SoLuong"),
                         rs.getInt("GiaBan"),
                         rs.getDate("NgayNhap"),
                         rs.getTime("GioNhap"),
                         rs.getDate("HSD"),
                         rs.getString("NCC")));
                 Product_tb.setItems(ProductList);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(KhoHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void loadTable() {
        
        refreshTable();
        PN_col.setCellValueFactory(new PropertyValueFactory<>("MaPN"));
        Lo_col.setCellValueFactory(new PropertyValueFactory<>("MaLo"));
        ma_col.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
        ten_col.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        DVT_col.setCellValueFactory(new PropertyValueFactory<>("DVT"));
        SL_col.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
        GiaNhap_col.setCellValueFactory(new PropertyValueFactory<>("GiaNhap"));
        GiaBan_col.setCellValueFactory(new PropertyValueFactory<>("GiaBan"));
        NhayNhap_col.setCellValueFactory(new PropertyValueFactory<>("NgayNhap"));
        GioNhap_col.setCellValueFactory(new PropertyValueFactory<>("GioNhap"));
        HSD_col.setCellValueFactory(new PropertyValueFactory<>("HSD"));
        NCC_col.setCellValueFactory(new PropertyValueFactory<>("NCC"));
        Product_tb.setItems(ProductList);
    }
    

    private void insertRecord() throws SQLException {
        insertPhieuNHap();
        insertMatHang();
        insertLoHang();
        executeQuery(query);
        
        loadTable();
    }
    
    private void insertPhieuNHap() {
        System.out.println("Start method insertPhieuNhap...");
        query = "insert into PhieuNhap (MaPN,NgayNhap,GioNhap) values (" + txt_MaPN.getText() + ",'" + String.valueOf(NgayNhapFld.getValue()) + "','" + txt_GioNhap.getText() + "')";
        int rowAffected = executeQuery(query);
        System.out.println("Finish method insertPhieuNHap, row inserted:" + rowAffected);
    }

    private void insertMatHang() {
        System.out.println("Start method insertMatHang...");
        query = "insert into MatHang (MaMH,TenMH) values ('" + txt_maHH.getText() + "',N'" + txt_tenHH.getText() + "');";
        int rowAffected = executeQuery(query);
        System.out.println("Finish method insertMatHang, row inserted:" + rowAffected);
    }

    private void insertLoHang() {
        System.out.println("Start method insertLoHang...");
        /*query = "insert into LoHang (MaLo,MaPN,MaMH,NCC,SoLuong,HSD,GiaNhap,GiaBan,DVT) values ('" 
                + txt_MaLo.getText() + "'," + txt_MaPN.getText() + ",'" + txt_maHH.getText() + "',N'" 
                + txt_NCC.getText() + "'," + txt_soLuong.getText() + ",'" + String.valueOf(HSDFld.getValue()) + "'," 
                + txt_giaNhap.getText() + "," + txt_giaBan.getText() + ",'" + txt_DVT.getText() + "')";*/
        query = "insert into LoHang (MaLo,MaPN,MaMH,NCC,SoLuong,HSD,GiaNhap,GiaBan,DVT) values ('"
                +txt_MaLo.getText()+"',"+txt_MaPN.getText()+",'"+txt_maHH.getText()+"','"
                +txt_NCC.getText()+"',"+txt_soLuong.getText()+","+ "'"+String.valueOf(HSDFld.getValue())
                +"',"+txt_giaNhap.getText()+","+txt_giaBan.getText()+",'"+txt_DVT.getText()+"')";
        int rowAffected = executeQuery(query);
        System.out.println("Finish method insertLoHang, row inserted:" + rowAffected);
    }
    
    @FXML
    public void Delete(ActionEvent e) throws IOException{
        Product clickedProduct = Product_tb.getSelectionModel().getSelectedItem();
        String MaLo = clickedProduct.getMaLo();
        try {
            query = "delete from LoHang where MaLo = '" + MaLo + "';";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Done");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhoHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
    }


    public void Edit(ActionEvent e) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/infoProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Dashboard");
        InfoProductController controller = fxmlLoader.getController();
        Product clickedProduct = Product_tb.getSelectionModel().getSelectedItem();
        controller.setProduct(clickedProduct);
        stage.setScene(new Scene(root1));
        stage.show();
        
    }
    
    //Ham tra ve ket qua cau lenh
    private int executeQuery(String query) {
        int ERROR = -1;
        int rowEffected = 0;
        Statement st;
        try {
            st = con.createStatement();
            rowEffected = st.executeUpdate(query);
        } catch (SQLException ex) {
            rowEffected = ERROR;
        }
        return rowEffected;
    }
}
