package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class DashboardController implements Initializable {

    @FXML
    private Button btbBanHang;

    @FXML
    private Button btbDonHang;

    @FXML
    private Button btnBaoCao;

    @FXML
    private Button btnKhachHang;
    
    @FXML
    private Button btnSanPham;
    
    @FXML
    private Button btnKhoHang;

    @FXML
    private Pane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void BanHang(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("view/BanHang.fxml"));
        rootPane.getChildren().setAll(pane);
        
        Stage modal_stage = new Stage();
        modal_stage.setScene(new Scene(pane));
        modal_stage.setTitle("modal");
    }
    @FXML
    void SanPham(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("view/SanPham.fxml"));
        rootPane.getChildren().setAll(pane);
        
        Stage modal_stage = new Stage();
        modal_stage.setScene(new Scene(pane));
        modal_stage.setTitle("modal");
    }
    @FXML
    void KhoHang(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("view/KhoHang.fxml"));
        rootPane.getChildren().setAll(pane);
        
        Stage modal_stage = new Stage();
        modal_stage.setScene(new Scene(pane));
        modal_stage.setTitle("modal");
    }
    @FXML
    void BaoCao(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("view/HoaDon.fxml"));
        rootPane.getChildren().setAll(pane);
        
        Stage modal_stage = new Stage();
        modal_stage.setScene(new Scene(pane));
        modal_stage.setTitle("modal");
    }
    @FXML
    void KhachHang(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("view/KhachHang.fxml"));
        rootPane.getChildren().setAll(pane);
        
        Stage modal_stage = new Stage();
        modal_stage.setScene(new Scene(pane));
        modal_stage.setTitle("modal");
    }
    @FXML
    void Nhanvien(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("view/NhanVien.fxml"));
        rootPane.getChildren().setAll(pane);
        
        Stage modal_stage = new Stage();
        modal_stage.setScene(new Scene(pane));
        modal_stage.setTitle("modal");
    }
}
