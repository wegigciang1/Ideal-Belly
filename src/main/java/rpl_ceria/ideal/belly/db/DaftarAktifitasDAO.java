/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rpl_ceria.ideal.belly.model.DaftarAktifitas;

/**
 *
 * @author ciang
 */
public class DaftarAktifitasDAO {

   
    public static DaftarAktifitas searchAktifitas(int id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM  aktivitas WHERE id='" + id + "'";
        try {
            ResultSet rsAkt = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            DaftarAktifitas akt = getAktifitasFromResultSet(rsAkt);
            return akt;
        } catch (SQLException e) {
            System.out.println("Sedang mencari aktifitas dengan id " + id + ", error terjadi: " + e);
            throw e;
        }
    }

    private static DaftarAktifitas getAktifitasFromResultSet(ResultSet rs) throws SQLException {
        DaftarAktifitas akt = null;
        if (rs.next()) {
            akt = new DaftarAktifitas();
            akt.setId(rs.getInt("id"));
            akt.setAktifitas(rs.getString("aktifitas"));
            akt.setKalori_terbakar(rs.getInt("kalori_terbakar"));
            akt.setPath_img(rs.getString("path_img"));
        }
        return akt;
    }
    //iterasi 3
    public static DaftarAktifitas searchAktifitasByName(String nama_aktifitas) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM  aktivitas WHERE aktifitas='" + nama_aktifitas + "'";
        try {
            ResultSet rsAkt = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            DaftarAktifitas akt = getAktifitasFromResultSet(rsAkt);
            return akt;
        } catch (SQLException e) {
            System.out.println("Sedang mencari aktifitas dengan nama " + nama_aktifitas + ", error terjadi: " + e);
            throw e;
        }
    }

    public static ObservableList<DaftarAktifitas> searchAktifitass() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM aktivitas";
        try {
            ResultSet rsAkt = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            ObservableList<DaftarAktifitas> aktList;
            aktList = getAktifitasList(rsAkt);
            return aktList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e); //Return exception
            throw e;
        }
    }

    private static ObservableList<DaftarAktifitas> getAktifitasList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<DaftarAktifitas> aktList = FXCollections.observableArrayList();
        while (rs.next()) {
            DaftarAktifitas akt = new DaftarAktifitas();
            akt.setId(rs.getInt("id"));
            akt.setAktifitas(rs.getString("aktifitas"));
            akt.setKalori_terbakar(rs.getInt("kalori_terbakar"));
            akt.setPath_img(rs.getString("path_img"));
            
            aktList.add(akt);
        }
        //return aktList;
        Random rand = new Random(); 
		// create a temporary list for storing 
		// selected element 
                //untuk menentukan mau brp banyak yang di random
                int jumlah_random= 2;
		ObservableList<DaftarAktifitas> aktNewList = FXCollections.observableArrayList(); 
		for (int i = 0; i < jumlah_random; i++) { 

			// take a raundom index between 0 to size 
			// of given List 
			int randomIndex = rand.nextInt(aktList.size()); 

			// add element in temporary list 
                        aktNewList.add(aktList.get(randomIndex));
		
		} 
	return aktNewList; 
    }

    public static void updateAktifitas(String id, DaftarAktifitas akt) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE aktivitas SET id='" + akt.getId() + "',"
                + "nama = '" + akt.getAktifitas() + "',"
                + "jenis = '" + akt.getKalori_terbakar() + "',"
                + "path_img = '" + akt.getPath_img() + "' WHERE id='" + id + "'";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void addAktifitas(DaftarAktifitas akt) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO aktivitas (id, aktifitas, kalori_terbakar, path_img) VALUES (null, '"
                + akt.getAktifitas() + "',"
                + akt.getKalori_terbakar() + ",'"
                + akt.getPath_img() + "')";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void deleteAktifitasWithId(String id) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM aktivitas WHERE id='" + id + "'";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

}