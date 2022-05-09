package tesst;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLDocumentController {

    @FXML
    private Button btnDangNhap;

    @FXML
    private Button btnThoat;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    void login(ActionEvent event) throws IOException {

//        if(txtUserName.getText().equals("")){
//            JOptionPane.showMessageDialog(this,"Vui lòng điền Username");
//        }else if(txtPassword.getText().equals("")){
//            JOptionPane.showMessageDialog("Vui lòng điền Password");
//        }
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Main.fxml"));
        rootPane.getChildren().setAll(pane);
        
        Stage modal_stage = new Stage();
        modal_stage.setScene(new Scene(pane));
        modal_stage.setTitle("modal");
    }

}
