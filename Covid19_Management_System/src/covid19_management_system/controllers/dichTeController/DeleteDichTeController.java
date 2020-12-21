/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.controllers.dichTeController;

import covid19_management_system.MY_CONNECTION;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HieuPhung
 * @author HieuPhung
 */
public class DeleteDichTeController {

    MY_CONNECTION my_connection = new MY_CONNECTION();

    public boolean removeDichTe(int idNhanKhau) throws SQLException {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `dich_te` WHERE `id_person`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            ps.setInt(1, idNhanKhau);

            if (ps.executeUpdate() > 0) {
                ps.close();
                my_connection.getConnection().close();
                return true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(DeleteDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            my_connection.getConnection().close();
            return false;
        }
        return false;
    }

    public boolean removeTrieuChung(int idNhanKhau) throws SQLException {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `khai_trieu_chung` WHERE `id_person`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            ps.setInt(1, idNhanKhau);

//            return (ps.executeUpdate() > 0);
            if (ps.executeUpdate() > 0) {
                ps.close();
                my_connection.getConnection().close();
                return true;
            };

        } catch (SQLException ex) {
            Logger.getLogger(DeleteDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            my_connection.getConnection().close();
            return false;
        }
        return false;
    }

    public boolean removeBenh(int idNhanKhau) throws SQLException {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `khai_benh` WHERE `id_person`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            ps.setInt(1, idNhanKhau);

//            return (ps.executeUpdate() > 0);
            if (ps.executeUpdate() > 0) {
                ps.close();
                my_connection.getConnection().close();
                return true;
            };

        } catch (SQLException ex) {
            Logger.getLogger(DeleteDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            my_connection.getConnection().close();
            return false;
        }
        return false;
    }

}
