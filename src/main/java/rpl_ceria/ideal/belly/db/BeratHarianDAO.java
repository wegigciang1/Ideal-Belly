package rpl_ceria.ideal.belly.db;

import rpl_ceria.ideal.belly.model.BeratHarian;
import rpl_ceria.ideal.belly.model.BMI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author budsus
 */
public class BeratHarianDAO {
    
    public static BeratHarian searchBeratHarianByEmailAndDate(String email, LocalDate tanggal_harian) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM berat_harian WHERE email='" + email + "' AND "
                + "tanggal_harian='" + tanggal_harian + "' ";
        try {
            ResultSet rsBrt = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            BeratHarian berat_harian = getBeratHarianFromResultSet(rsBrt);
            return berat_harian;
        } catch (SQLException e) {
            System.out.println("Sedang mencari berat_harian dengan email " + email + " dan tanngal_harian " 
                    + tanggal_harian + ", error terjadi: " + e);
            throw e;
        }
    }
    
    public static BeratHarian searchBeratHarian(String email) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM berat_harian WHERE email='" + email + "'";
        try {
            ResultSet rsBrt = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            BeratHarian berat_harian = getBeratHarianFromResultSet(rsBrt);
            return berat_harian;
        } catch (SQLException e) {
            System.out.println("Sedang mencari berat_harian dengan email " + email + ", error terjadi: " + e);
            throw e;
        }
    }

    private static BeratHarian getBeratHarianFromResultSet(ResultSet rs) throws SQLException {
        BeratHarian brt = null;
        if (rs.next()) {
            brt = new BeratHarian();
            brt.setId(rs.getInt("id"));
            brt.setEmail(rs.getString("email"));
            brt.setTanggal_harian(LocalDate.parse(rs.getString("tanggal_harian")));
            brt.setBerat_badan(rs.getDouble("berat_badan"));
            brt.setBMI(rs.getDouble("bmi"));
        }
        return brt;
    }

    public static ObservableList<BeratHarian> searchBeratHarians() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM berat_harian";
        try {
            ResultSet rsBrt = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            ObservableList<BeratHarian> brtList = getBeratHarianList(rsBrt);
            return brtList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e); //Return exception
            throw e;
        }
    }

    private static ObservableList<BeratHarian> getBeratHarianList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<BeratHarian> brtList = FXCollections.observableArrayList();
        while (rs.next()) {
            BeratHarian brt = new BeratHarian();
            brt.setId(rs.getInt("id"));
            brt.setEmail(rs.getString("email"));
            brt.setTanggal_harian(LocalDate.parse(rs.getString("tanggal_harian")));
            brt.setBerat_badan(rs.getDouble("berat_badan"));
            brt.setBMI(rs.getDouble("bmi"));
            brtList.add(brt);
        }
        return brtList;
    }

    public static void updateBeratHarian(String email, BeratHarian brt) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE berat_harian SET email = '" + brt.getEmail() + "',"
                + "tanggal_harian = '" + brt.getTanggal_harian() + "',"
                + "berat_badan = '" + brt.getBerat_badan() + "',"
                + "bmi = '" + brt.getBMI() + "' WHERE email='" + email + "'";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void addBeratHarian(BeratHarian brt) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO berat_harian (id, email, tanggal_harian, berat_badan, bmi) VALUES (null, "
                + "'" + brt.getEmail() + "', "
                + "'" + brt.getTanggal_harian() + "', "
                + "'" + brt.getBerat_badan() + "', "
                + "" + brt.getBMI() + ")";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void deleteBeratHarianWithId(int id) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM berat_harian WHERE id='" + id + "'";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }
}
