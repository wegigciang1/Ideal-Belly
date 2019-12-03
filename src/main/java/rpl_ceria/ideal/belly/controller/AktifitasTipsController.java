/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import rpl_ceria.ideal.belly.db.BeratHarianDAO;
import rpl_ceria.ideal.belly.db.DaftarAktifitasDAO;
import rpl_ceria.ideal.belly.model.BeratHarian;
import rpl_ceria.ideal.belly.model.CountUserBody;
import rpl_ceria.ideal.belly.model.DaftarAktifitas;
import rpl_ceria.ideal.belly.model.User;
import rpl_ceria.ideal.belly.model.UserSession;

/**
 * FXML Controller class
 *
 * @author nathania
 */
public class AktifitasTipsController implements Initializable {

    @FXML
    private AnchorPane bilah_kanan;
    @FXML
    private AnchorPane anchor_lvl1;
    @FXML
    private AnchorPane anhcor_lvl2;
    @FXML
    private AnchorPane anchor_lvl3;
    @FXML
    private AnchorPane bilah_atas;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private AnchorPane bilah_kiri;
    
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
    private void handleTipsLinkAction(ActionEvent event) throws Exception {
        System.out.println("Request Tips");
        
        try{  
            User userNow = UserSession.getUserSession();
            System.out.println(userNow.getStatus());
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
    private void handleHomeLinkAction(ActionEvent event) throws IOException {
        
        System.out.println("Request Home");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root= (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/HomeStyles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          //Tabel Aktifitas
        
        try {
            Label title_tipsmakanan = new Label("Rekomendasi Aktifitas");
            title_tipsmakanan.setLayoutX(16.0);
            title_tipsmakanan.setLayoutY(16.0);
            title_tipsmakanan.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
            this.bilah_kanan.getChildren().addAll(title_tipsmakanan);
            
            User userNow = UserSession.getUserSession();
            BeratHarian cek_beratharian = BeratHarianDAO.searchBeratHarianByEmailAndDate(userNow.getEmail(), LocalDate.now(ZoneId.systemDefault()));
            String levelAktifitasFisik = "tidak aktif";
            double userUmur, userBMR, userTEE;
            if(cek_beratharian != null){
                userUmur = Period.between(userNow.getTanggal_lahir(), LocalDate.now(ZoneId.systemDefault())).getYears();
                userBMR = CountUserBody.countBMR(userNow.getTinggi_badan(), cek_beratharian.getBerat_badan(), userUmur, userNow.getJenis_kelamin());
                userTEE = CountUserBody.countTEE(userBMR, levelAktifitasFisik);
            }
            else{
                cek_beratharian = BeratHarianDAO.searchBeratHarianByEmailAndDate(userNow.getEmail(), LocalDate.now(ZoneId.systemDefault()).minusDays(1));
                userUmur = Period.between(userNow.getTanggal_lahir(), LocalDate.now(ZoneId.systemDefault())).getYears();
                userBMR = CountUserBody.countBMR(userNow.getTinggi_badan(), cek_beratharian.getBerat_badan(), userUmur, userNow.getJenis_kelamin());
                userTEE = CountUserBody.countTEE(userBMR, levelAktifitasFisik);
            }
            
            ObservableList<DaftarAktifitas> data;
            data = DaftarAktifitasDAO.searchAktifitass(userTEE);
            // GridPane untuk setiap data tips
            double layoutX = 16.0, layoutY = 50.0;
            int gridColumns = 0;
            
            for(DaftarAktifitas item : data){
                GridPane grid = new GridPane();
                grid.setVgap(10);
                grid.setPrefSize(190, 100);
                grid.setPadding(new Insets(10,10,10,10));
                grid.getStyleClass().add("tips-grid");
                grid.setStyle("-fx-background-image:url(\"file:db_pictures/aktifitas/" + item.getPath_img() + "\");");
                
                grid.setLayoutX(layoutX); 
                grid.setLayoutY(layoutY);
                gridColumns++;
                if(gridColumns == 2){
                    layoutX = 16.0;
                    layoutY += 105.0;
                    gridColumns = 0;
                } else{
                    layoutX += 195.0;
                }
                        
                // add detail data ke grid
                Label nama_aktifitas = new Label(item.getAktifitas());
                nama_aktifitas.setPrefWidth(190);
                nama_aktifitas.setWrapText(true);
                nama_aktifitas.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 17));
                nama_aktifitas.setTextFill(Color.web("000033"));
                nama_aktifitas.setBackground(new Background(new BackgroundFill(Color.web("#ffffff", 0.5), new CornerRadii(0), new Insets(0))));
                grid.add(nama_aktifitas, 1, 0);
                
                Label jumlah_kalori = new Label("Kalori : " + String.valueOf(item.getKalori_terbakar()) + " kal / 30 menit");
                jumlah_kalori.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                jumlah_kalori.setTextFill(Color.web("#000033"));
                jumlah_kalori.setBackground(new Background(new BackgroundFill(Color.web("#ffffff", 0.5), new CornerRadii(0), new Insets(0))));
                grid.add(jumlah_kalori, 1, 1);
                
                this.bilah_kanan.getChildren().addAll(grid);   
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TipsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleTambahItemTipsLinkAction(ActionEvent event) throws IOException {
         System.out.println("Request Tambah Makanan");
        //Bagian Tips HyperLink
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TambahMakananTipsAdmin.fxml"));
        Parent root= (Parent) loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/TambahMakananTipsStyles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        }
        catch(IOException e){
            System.out.println("Error Terjadi: " + e);
            throw e;
        }
    }
}
