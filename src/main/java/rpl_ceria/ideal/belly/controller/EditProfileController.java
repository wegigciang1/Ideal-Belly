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
import javafx.scene.control.Alert;
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
public class EditProfileController implements Initializable {

    /**
     * Initializes the controller class.
     *
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

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/LoginStyles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void handleEditProfileButtonAction(ActionEvent event) throws IOException {

        try {
            if (email.getText().isEmpty() || password.getText().isEmpty() || nama.getText().isEmpty() || tinggi_badan.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Data masih kosong");
                alert.setContentText("Data harus diisi semua");
                alert.showAndWait();
                throw new IOException();
            }

            String popupmessage = "";
            boolean flag = false;
            if (!Pattern.matches("[a-zA-Z ]+", nama.getText())) {
                popupmessage = "Nama harus huruf";
                flag = true;
            }
            if (!email.getText().contains("@") || !email.getText().contains(".")) {
                if (flag) {
                    popupmessage = popupmessage + "\nEmail tidak valid";
                } else {
                    popupmessage = "Email tidak valid";
                }
                flag = true;
            }
            if (Pattern.matches("[a-zA-Z]+", password.getText()) || Pattern.matches("[0-9]+", password.getText())
                    || password.getText().length() < 8) {
                if (flag) {
                    popupmessage = popupmessage + "\nPassword harus angka & huruf min 8 karakter";
                } else {
                    popupmessage = "Password harus angka & huruf min 8 karakter";
                }
                flag = true;
            }
            if (!Pattern.matches("[0-9]+[\\.0-9]+", tinggi_badan.getText())) {
                if (flag) {
                    popupmessage = popupmessage + "\nTinggi badan harus angka";
                } else {
                    popupmessage = "Tinggi badan harus angka";
                }
                flag = true;
            }

            if (popupmessage != "") {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Pengisian Tidak Benar");
                alert.setContentText(popupmessage);
                alert.showAndWait();
                throw new IOException();
            }

            User userUpdate = UserDAO.searchUserByEmail(email.getText());
            userUpdate.setEmail(email.getText());
            userUpdate.setPassword(password.getText());
            userUpdate.setNama(nama.getText());
            userUpdate.setTinggi_badan(Double.parseDouble(tinggi_badan.getText()));
            if (userUpdate != null) {
                UserDAO.updateUser(userUpdate.getId(), userUpdate);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input Success");
                alert.setHeaderText("Data Changes");
                alert.setContentText("Data Berhasil Diperbarui");
                alert.showAndWait();
                System.out.println("Akun Berhasil Diperbarui");

                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Edit Gagal");
                alert.setHeaderText("Error");
                alert.setContentText("Maaf, Edit Profile Gagal");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    private void handleHomeLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Home");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void handleTipsLinkAction(ActionEvent event) throws Exception {
        System.out.println("Request Tips");
        
        try{  
            User userNow = UserSession.getUserSession();
            if("admin".equals(userNow.getStatus())){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/TipsAdmin.fxml"));
            Parent root= (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/TipsStyles.css");
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            }
            else{
              FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/TipsUser.fxml"));
            Parent root= (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/TipsStyles.css");
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();  
            }
        }
        catch(IOException e){
            System.out.println("Error Terjadi: " + e);
            throw e;
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        User userNow = UserSession.getUserSession();
        email.setText(userNow.getEmail());
        nama.setText(userNow.getNama());
        password.setText(userNow.getPassword());
        retypePass.setText(userNow.getPassword());
        tinggi_badan.setText(String.valueOf(userNow.getTinggi_badan()));
    }

}
