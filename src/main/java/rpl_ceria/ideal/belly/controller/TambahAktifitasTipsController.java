package rpl_ceria.ideal.belly.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rpl_ceria.ideal.belly.db.DaftarAktifitasDAO;
import rpl_ceria.ideal.belly.model.DaftarAktifitas;
import rpl_ceria.ideal.belly.model.User;
import rpl_ceria.ideal.belly.model.UserSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class TambahAktifitasTipsController {
    @FXML
    private TextField kalori;
    @FXML
    private TextArea deskripsi;
    @FXML
    private TextField nama_aktifitas;
    @FXML
    private TextField path_img;
    @FXML
    private Button save;
    @FXML
    private Button browse;
        
    @FXML
    private void handleHomeLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Home");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root= (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/HomeStyles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        HomeController home=loader.getController();
//        home.tambah(userTamp);
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void handleEditProfileLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Edit Profile");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EditProfile.fxml"));
        Parent root= (Parent) loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

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
    private void handleAktifitasTipsLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Tips");
        //Bagian Tips HyperLink
        try{
            User userNow = UserSession.getUserSession();
            System.out.println(userNow.getStatus());
            if("admin".equals(userNow.getStatus())){
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/AktifitasTipsAdmin.fxml"));
                Parent root= (Parent) loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/TipsStyles.css");
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            else{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/AktifitasTipsUser.fxml"));
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

    @FXML
    private void handleTambahAktifitasButtonAction(ActionEvent event) {
        try{
            if(nama_aktifitas.getText().isEmpty() || kalori.getText().isEmpty() || path_img.getText().isEmpty() || deskripsi.getText().isEmpty()){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Registration");
                alert.setHeaderText("Wajib Mengisi Semua Data");
                alert.setContentText("Data Tidak Boleh Kosong");
                alert.showAndWait();
                throw new IOException();
            }

            String popupmessage = "";
            boolean flag = false;
            if(!Pattern.matches("[a-zA-Z ]+", nama_aktifitas.getText())){
                popupmessage = "Nama aktifitas harus huruf";
                flag = true;
            }
            if(!Pattern.matches("[0-9]+[\\.0-9]+", kalori.getText())){
                if(flag){
                    popupmessage = popupmessage + "\nKalori harus angka";
                }
                else{
                    popupmessage = "Kalori harus angka";
                }
//                flag = true;
//            }
//            else{
//                flag = false;
            }
            //path_img masih dalam perbaikan
//            if(Pattern.matches("[a-zA-Z]+", path_img.getText()) || Pattern.matches("[0-9]+", path_img.getText())){
//                if(flag){
//                    popupmessage = popupmessage + "\nPassword harus angka & huruf min 8 karakter";
//                }
//                else{
//                    popupmessage = "Password harus angka & huruf min 8 karakter";
//                }
//                flag = true;
//            }
            if(!popupmessage.equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Pengisian Tidak Benar");
                alert.setContentText(popupmessage);
                alert.showAndWait();
                throw new IOException();
            }

            DaftarAktifitas newaktifitas = new DaftarAktifitas();
            newaktifitas.setAktifitas(nama_aktifitas.getText());
            newaktifitas.setKalori_terbakar(Double.parseDouble(kalori.getText()));
            newaktifitas.setPath_img(path_img.getText());
            newaktifitas.setDeskripsi(deskripsi.getText());
            DaftarAktifitas daftarAktifitas = DaftarAktifitasDAO.searchAktifitasByName(newaktifitas.getAktifitas());
            if (daftarAktifitas == null) {
                DaftarAktifitasDAO.addAktifitas(newaktifitas);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Data Success");
                alert.setHeaderText("Data Berhasil");
                alert.setContentText("Aktifitas Berhasil Ditambahkan");
                alert.showAndWait();
                System.out.println("Data Aktifitas Berhasil");

                //CHECK HERE
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/TambahMakananTips.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/TambahMakananTipsStyles.css");
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Add Data");
                alert.setHeaderText("Gagal Menambah Data");
                alert.setContentText("Aktifitas gagal untuk dibuat");
                alert.showAndWait();
            }
        }
        catch(IOException | ClassNotFoundException | NumberFormatException | SQLException e){
            System.out.println("Error di: " + e);
        }
    }
}
