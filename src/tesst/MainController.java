package tesst;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController {

    @FXML
    private Button btnBanHang;

    @FXML
    private Button btnBaoCao;

    @FXML
    private Button btnKhoHang;
    @FXML
    private AnchorPane rootPane;

    @FXML
    void BanHang(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BanHang.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Bán Hàng");
        stage.setScene(new Scene(root1));  
        stage.show();
    }
     @FXML
    void KhoHang(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KhoHang.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Kho Hàng");
        stage.setScene(new Scene(root1));  
        stage.show();
    }
}
