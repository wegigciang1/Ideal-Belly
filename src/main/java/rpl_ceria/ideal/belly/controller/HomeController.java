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
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rpl_ceria.ideal.belly.model.BeratHarian;
import rpl_ceria.ideal.belly.db.BeratHarianDAO;
import rpl_ceria.ideal.belly.model.CountUserBody;
import rpl_ceria.ideal.belly.model.UserSession;
import rpl_ceria.ideal.belly.model.User;

/**
 * FXML Controller class
 *
 * @author nathania
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    private Label selamatDatang_label;
    
    @FXML
    private Button button_add;
    
    private Alert formBodyWeightRoot;
    
     @FXML
    private LineChart<String, Number> grafik;
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
    private Label bb_sekarang;
    @FXML
    private Label bb_rata2;
    @FXML
    private Label bb_kategori;
    @FXML
    private AnchorPane bilah_kanan;
    
    @FXML
    private void handleLogOutLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Log Out");
        
        UserSession.setUserSession(null);
        
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/LoginStyles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void handleEditProfileLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Edit Profile");
        
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/EditProfile.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
    }
    
    private void handleAddBodyWeightButtonAction(boolean isAdd, final BeratHarian brt) {
        System.out.println("Request Add Body Weight");
        
        if(formBodyWeightRoot == null){
            formBodyWeightRoot = new Alert(INFORMATION);
            formBodyWeightRoot.setTitle("Berat Badan Harian");
            formBodyWeightRoot.setHeaderText("Tambah Berat Badan Harian");
            formBodyWeightRoot.initModality(Modality.WINDOW_MODAL);
            ButtonType button_simpan = new ButtonType("Simpan");
            ButtonType button_batal = new ButtonType("Batal", ButtonBar.ButtonData.CANCEL_CLOSE);
            formBodyWeightRoot.getButtonTypes().setAll(button_simpan, button_batal);
            
            FormBodyWeightController brtController = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormBodyWeight.fxml"));
            try {
                Node content = loader.load();
                DialogPane pane = formBodyWeightRoot.getDialogPane();
                pane.setContent(content);
                brtController = loader.getController();
                brtController.setBeratHarian(brt);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Optional<ButtonType> result = formBodyWeightRoot.showAndWait();

            if (result.get() == button_simpan) {
                if (brtController != null){
                    if (isAdd) {
                        // tambahkan data ke tabel
                        try {
                            BeratHarian brtUpdated = brtController.getBeratHarian();
                            if(!Pattern.matches("[0-9]+[\\.0-9]+", String.valueOf(brtUpdated.getBerat_badan()))){
                                throw new Exception("Invalid Input");
                            }
                            User userNow = UserSession.getUserSession();
                            brtUpdated.setBMI(CountUserBody.countBMI(userNow.getTinggi_badan(), brtUpdated.getBerat_badan()));
                            BeratHarianDAO.addBeratHarian(brtUpdated);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception e){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Invalid Input");
                            alert.setHeaderText("Pengisian Tidak Benar");
                            alert.setContentText("Berat badan harus angka");
                            alert.showAndWait();
                        }
                    } else {
                        String emailOld = brt.getEmail();
                        BeratHarian brtUpdated = brtController.getBeratHarian();
                        try {
                            User userNow = UserSession.getUserSession();
                            brtUpdated.setBMI(CountUserBody.countBMI(userNow.getTinggi_badan(), brtUpdated.getBerat_badan()));
                            BeratHarianDAO.updateBeratHarian(emailOld, brtUpdated);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                formBodyWeightRoot = null;
            } else if (result.get() == button_batal) {
                formBodyWeightRoot = null;
            }
        }
    }
    
    @FXML
    private void handleTipsLinkAction(ActionEvent event) throws Exception {
        System.out.println("Request Tips");
        User userNow = UserSession.getUserSession();
        try{  
            
            System.out.println(userNow.getStatus());
            if("admin".equals(userNow.getStatus())){
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/TipsAdmin.fxml"));
                Parent root = (Parent) loader.load();
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
        selamatDatang_label.setText(userNow.getNama());
        selamatDatang_label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        selamatDatang_label.setWrapText(true);
        
        try {//linechart
            double rata2 = 0;
            double akhir = 0;
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();   
            ObservableList<BeratHarian> data;
            String emailNew= userNow.getEmail();
            data = BeratHarianDAO.searchBeratHarianEmails(emailNew);
            System.out.println("Date awal: " + data.get(0).getTanggal_harian().plusDays(1));
            for(int i = 1; i < data.size(); i++){
                if(data.get(i).getTanggal_harian() != data.get(i-1).getTanggal_harian().plusDays(1)){
                    int days = Period.between(data.get(i-1).getTanggal_harian(), data.get(i).getTanggal_harian()).getDays();
                    for(int j = 0; j < days-1; j++){
                        BeratHarian brtNew = data.get(i-1);
                        data.get(i-1).setTanggal_harian(brtNew.getTanggal_harian().plusDays(1));
                        BeratHarianDAO.addBeratHarian(brtNew);
                    }
                }
            }
            int days = Period.between(data.get(data.size()-1).getTanggal_harian(), LocalDate.now(ZoneId.systemDefault())).getDays();
            if(days > 1){
                for(int i = 0; i < days-1; i++){
                    BeratHarian brtNew = data.get(data.size()-1);
                    data.get(data.size()-1).setTanggal_harian(brtNew.getTanggal_harian().plusDays(1));
                    BeratHarianDAO.addBeratHarian(brtNew);
                }
            }
            
            final LineChart<String,Number> lineChart;
            lineChart = new LineChart<>(xAxis,yAxis);
                                   
            XYChart.Series series = new XYChart.Series();
            series.setName("Berat Harian");
            data = BeratHarianDAO.searchBeratHarianEmails(emailNew);
            for(BeratHarian item : data){
                series.getData().add(new XYChart.Data<>(item.getTanggal_harian().toString(), item.getBerat_badan()));
                akhir=item.getBerat_badan();
                rata2+=item.getBerat_badan();
            }
            int a = data.size();
            bb_sekarang.setText(Double.toString(akhir));
            bb_rata2.setText(Double.toString(rata2/a));
            System.out.println("Rata2: "+(rata2/a));
            System.out.println("Berat Sekarang: "+akhir);
            BeratHarian cek_beratharian = BeratHarianDAO.searchBeratHarianByEmailAndDate(userNow.getEmail(), LocalDate.now(ZoneId.systemDefault()));
            if(cek_beratharian != null){    
                bb_kategori.setText(CountUserBody.getStatusBMI(cek_beratharian.getBMI()));
            }
            else{
                cek_beratharian = BeratHarianDAO.searchBeratHarianByEmailAndDate(userNow.getEmail(), LocalDate.now(ZoneId.systemDefault()).minusDays(1));
                bb_kategori.setText(CountUserBody.getStatusBMI(cek_beratharian.getBMI()));
            }
            grafik.getData().add(series);    
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //end
        button_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {       
                try {
                    User userNow = UserSession.getUserSession();
                    BeratHarian cek_beratharian = BeratHarianDAO.searchBeratHarianByEmailAndDate(userNow.getEmail(), LocalDate.now(ZoneId.systemDefault()));
                    if(cek_beratharian == null){    
                        handleAddBodyWeightButtonAction(true, new BeratHarian(userNow.getEmail(), 
                                LocalDate.now(ZoneId.systemDefault()), 0, 0));
                    }
                    else{
                        handleAddBodyWeightButtonAction(false, new BeratHarian(cek_beratharian.getEmail(), 
                                cek_beratharian.getTanggal_harian(), cek_beratharian.getBerat_badan(), 
                                cek_beratharian.getBMI()));
                    }
                //tambahan ciang new
                
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/HomeStyles.css");
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //tambahan ciang new
            }
        });
    }       
}