/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ciang
 */
public class DaftarAktifitas{

    /**
     * @return the id
     */
    public int getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * @return the aktifitas
     */
    public String getAktifitas() {
        return aktifitas.get();
    }

    /**
     * @param aktifitas the aktifitas to set
     */
    public void setAktifitas(String aktifitas) {
        this.aktifitas.set(aktifitas);
    }

    /**
     * @return the kalori_terbakar
     */
    public int getKalori_terbakar() {
        return kalori_terbakar.get();
    }

    /**
     * @param kalori_terbakar the kalori_terbakar to set
     */
    public void setKalori_terbakar(int kalori_terbakar) {
        this.kalori_terbakar.set(kalori_terbakar);
    }
        
        
        
    private SimpleIntegerProperty id= new SimpleIntegerProperty();
    private SimpleStringProperty aktifitas= new SimpleStringProperty();
    private SimpleIntegerProperty kalori_terbakar= new SimpleIntegerProperty();
    
}
