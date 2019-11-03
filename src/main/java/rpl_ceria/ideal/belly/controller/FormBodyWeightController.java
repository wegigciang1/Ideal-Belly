package rpl_ceria.ideal.belly.controller;

import rpl_ceria.ideal.belly.model.BeratHarian;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author budsus
 */
public class FormBodyWeightController implements Initializable {
    private BeratHarian beratHarian;    
    
    @FXML
    private Label tanggal_harian;
    
    @FXML
    private TextField berat_badan;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setBeratHarian(BeratHarian usrOld) {
        this.beratHarian = usrOld;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedTanggal = beratHarian.getTanggal_harian().format(formatter);
        tanggal_harian.setText(formattedTanggal);
        System.out.println(beratHarian.getBerat_badan());
        berat_badan.setText(String.valueOf(beratHarian.getBerat_badan()));
    }
    
    public BeratHarian getBeratHarian() {
//        this.beratHarian.setTanggal_harian(LocalDate.now(ZoneId.systemDefault()));/
        this.beratHarian.setBerat_badan(Double.parseDouble(berat_badan.getText()));
        return this.beratHarian;
    }
}
