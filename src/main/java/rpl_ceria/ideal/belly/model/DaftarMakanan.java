/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ciang
 */
public class DaftarMakanan {

    /**
     * @return the jenis
     */
    public String getJenis() {
        return jenis.get();
    }

    /**
     * @param jenis the jenis to set
     */
    public void setJenis(String jenis) {
        this.jenis.set(jenis);
    }

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
     * @return the nama_makanan
     */
    public String getNama_makanan() {
        return nama_makanan.get();
    }

    /**
     * @param nama_makanan the nama_makanan to set
     */
    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan.set(nama_makanan);
    }

    /**
     * @return the berat
     */
    public double getBerat() {
        return berat.get();
    }

    /**
     * @param berat the berat to set
     */
    public void setBerat(double berat) {
        this.berat.set(berat);
    }

    /**
     * @return the kalori
     */
    public double getKalori() {
        return kalori.get();
    }

    /**
     * @param kalori the kalori to set
     */
    public void setKalori(double kalori) {
        this.kalori.set(kalori);
    }
    
   
    /**
     * @return the id
     */
   
    private SimpleIntegerProperty id= new SimpleIntegerProperty();
    private SimpleStringProperty nama_makanan= new SimpleStringProperty();
    private SimpleDoubleProperty berat= new SimpleDoubleProperty();
    private SimpleDoubleProperty kalori= new SimpleDoubleProperty();
    private SimpleStringProperty jenis= new SimpleStringProperty();

}