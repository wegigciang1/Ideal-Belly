package rpl_ceria.ideal.belly.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rpl_ceria.ideal.belly.db.UserDAO;
import rpl_ceria.ideal.belly.model.User;
import rpl_ceria.ideal.belly.model.UserSession;

/**
 * FXML Controller class
 *
 * @author nathania
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField email;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, ParseException {
        String emailUser = email.getText();
        String passwordUser = password.getText();
        
        User user = UserDAO.searchUser(emailUser, passwordUser);
        
        if(user != null) {
            System.out.println("Login Berhasil");
            
            UserSession.setUserSession(user);
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(scene);
            window.show();
            
        } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Login Failed");
              alert.setHeaderText("Login Gagal");
              alert.setContentText("Username atau Password Salah");
              alert.showAndWait();
        }
        
    }
    
    @FXML
    private void handleSignUpLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Sign Up");
        
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/SignUpStyles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void handleForgotPasswordLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Forgot Password");
        
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/ForgotPassword.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/ForgotPasswordStyles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}