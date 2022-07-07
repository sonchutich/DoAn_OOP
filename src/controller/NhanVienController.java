/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author demon
 */
public class NhanVienController implements Initializable {

    @FXML
    private TextField txt_MaNV;
    @FXML
    private TextField txt_TenNV;
    @FXML
    private TextField txt_DiaChi;
    @FXML
    private TextField txt_SDT;
    @FXML
    private Button btn_Them;
    @FXML
    private Button btn_Sua;
    @FXML
    private Button btn_Xoa;
    @FXML
    private PasswordField pass_Old;
    @FXML
    private PasswordField pass_New;
    @FXML
    private PasswordField pass_NewConfirm;
    @FXML
    private Button btn_ChangePass;
    @FXML
    private TableColumn<?, ?> STT_col;
    @FXML
    private TableColumn<?, ?> MaNV_col;
    @FXML
    private TableColumn<?, ?> TenNV_col;
    @FXML
    private TableColumn<?, ?> DiaChi_col;
    @FXML
    private TableColumn<?, ?> SDT_col;
    @FXML
    private TextField txt_MaHD;
    @FXML
    private TextField txt_NgayBD;
    @FXML
    private TextField txt_NgayKT;
    @FXML
    private Button btn_TimKiemMaHD;
    @FXML
    private Button btn_LocTheoNgay;
    @FXML
    private TableColumn<?, ?> Col_STT;
    @FXML
    private TableColumn<?, ?> Col_MaHD;
    @FXML
    private TableColumn<?, ?> Col_NgayLap;
    @FXML
    private TableColumn<?, ?> Col_MaKH;
    @FXML
    private TableColumn<?, ?> Col_TongTien;
    @FXML
    private TableColumn<?, ?> Col1_STT;
    @FXML
    private TableColumn<?, ?> Col1_MaMH;
    @FXML
    private TableColumn<?, ?> Col1_TenMH;
    @FXML
    private TableColumn<?, ?> Col1_SL;
    @FXML
    private TableColumn<?, ?> Col1_ThanhTien;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
