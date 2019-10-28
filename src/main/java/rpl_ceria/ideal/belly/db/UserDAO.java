package rpl_ceria.ideal.belly.db;

import rpl_ceria.ideal.belly.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

/**
 *
 * @author dewi
 */
public class UserDAO {
    
    public static void addUser(User usr) throws SQLException, ClassNotFoundException{
        String addStmt = "INSERT INTO user VALUES(null, '";
        addStmt = addStmt.concat(usr.getEmail() + "','");
        addStmt = addStmt.concat(usr.getPassword() + "','");
        addStmt = addStmt.concat(usr.getNama() + "','");
        addStmt = addStmt.concat(usr.getTanggal_lahir().toString() + "',");
        addStmt = addStmt.concat(usr.getTinggi_badan() + ")");
        try{
            DBUtil.getInstance().dbExecuteQueryInsert(addStmt);
        }catch (SQLException e){
            System.out.println("Insert Failed " + e);
            throw e;
        }
    }
    
    public static User searchUser(String email, String password) throws SQLException, ClassNotFoundException, ParseException {
        String selectStmt = "SELECT * FROM user WHERE email='" + email 
                + "' AND password='" + password + "'";
        try {
            ResultSet rsUsr = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            User user = getUserFromResultSet(rsUsr);
            return user;
        } catch (SQLException e) {
            System.out.println("Sedang mencari user dengan email: " + email + ", error terjadi: " + e);
            throw e;
        }
    }
    
    public static User searchUserByEmail(String email) throws SQLException, ClassNotFoundException, ParseException {
        String selectStmt = "SELECT * FROM user WHERE email='" + email + "'";
        try {
            ResultSet rsUsr = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            User user = getUserFromResultSet(rsUsr);
            return user;
        } catch (SQLException e) {
            System.out.println("Sedang mencari user dengan email: " + email + ", error terjadi: " + e);
            throw e;
        }
    }

    private static User getUserFromResultSet(ResultSet rs) throws SQLException, ParseException {
        User usr = null;
        if (rs.next()) {
            System.out.println("Enter");
            usr = new User();
            usr.setId(rs.getInt("id"));
            usr.setEmail(rs.getString("email"));
            usr.setPassword(rs.getString("password"));
            usr.setNama(rs.getString("nama"));
            usr.setTanggal_lahir(LocalDate.parse(rs.getString("tanggal_lahir")));
//            usr.setTanggal_lahir(rs.getDate("tanggal_lahir"));
            usr.setTinggi_badan(rs.getDouble("tinggi_badan"));
        }
        return usr;
    }

    public static void updateUser(int id, User usr) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE user SET";
        updateStmt = !usr.getEmail().equals("") ? 
                updateStmt.concat(" email='" + usr.getEmail() + "',") 
                : updateStmt;
        
        updateStmt = !usr.getNama().equals("") ? 
                updateStmt.concat(" nama='" + usr.getNama() + "',") 
                : updateStmt;
        
        updateStmt = updateStmt.concat(" password='" + usr.getPassword() + "',");
        updateStmt = updateStmt.concat(" tanggal_lahir='" + usr.getTanggal_lahir() + "',");
        updateStmt = updateStmt.concat(" tinggi_badan=" + usr.getTinggi_badan());
        
        updateStmt = updateStmt.concat(" WHERE id=" + id);
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void deleteEmpWithId(int id) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM user WHERE id='" + id + "'";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }
}
