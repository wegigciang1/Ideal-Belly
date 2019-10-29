/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import rpl_ceria.ideal.belly.db.UserDAO;
import rpl_ceria.ideal.belly.model.User;

/**
 * FXML Controller class
 *
 * @author nathania
 */
public class EditProfileController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField retypePass;
    
    @FXML
    private TextField nama;
    
    @FXML
    private TextField tinggi_badan;
    
    @FXML
    private void handleLogOutLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Log Out");
        
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/LoginStyles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void handleEditProfileButtonAction(ActionEvent event) throws IOException {
        try{
            if(email.getText().isEmpty() || password.getText().isEmpty() || nama.getText().isEmpty() || tinggi_badan.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Data harus diisi semua");
                throw new IOException();
            }
            
            String popupmessage = "";
            boolean flag = false;
            if(!Pattern.matches("[a-zA-Z]+", nama.getText())){
                popupmessage = "Nama harus huruf";
                flag = true;
            }
            if(!email.getText().contains("@") || !email.getText().contains(".")){
                if(flag){
                    popupmessage = popupmessage + "\nEmail tidak valid";
                }
                else{
                    popupmessage = "Email tidak valid";
                }
                flag = true;
            }
            if(Pattern.matches("[a-zA-Z]+", password.getText()) || Pattern.matches("[0-9]+", password.getText()) 
                    || password.getText().length() < 8){
                if(flag){
                    popupmessage = popupmessage + "\nPassword harus angka & huruf min 8 karakter";
                }
                else{
                    popupmessage = "Password harus angka & huruf min 8 karakter";
                }
                flag = true;
            }
            if(!Pattern.matches("[0-9]+", tinggi_badan.getText())){
                if(flag){
                    popupmessage = popupmessage + "\nTinggi badan harus angka";
                }
                else{
                    popupmessage = "Tinggi badan harus angka";
                }
                flag = true;
            }
            
            if(popupmessage != ""){
                JOptionPane.showMessageDialog(null, popupmessage);
                throw new IOException();
            }

            User userUpdate = UserDAO.searchUserByEmail(email.getText()); 
            userUpdate.setEmail(email.getText());
            userUpdate.setPassword(password.getText());
            userUpdate.setNama(nama.getText());
            userUpdate.setTinggi_badan(Double.parseDouble(tinggi_badan.getText()));
            if (userUpdate != null) {
                UserDAO.updateUser(userUpdate.getId(), userUpdate);
                JOptionPane.showMessageDialog(null, "Akun berhasil diedit");
                System.out.println("Edit Profile Done");
                
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, edit profile gagal");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    
    @FXML
    private void handleHomeLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Home");
        
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
