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
 *
 * @author HieuPhung
 */
public class DeleteDichTeController {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    public boolean removeDichTe(int idNhanKhau) {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `dich_te` WHERE `id_person`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            ps.setInt(1, idNhanKhau);
            
            return (ps.executeUpdate() > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean removeTrieuChung(int idNhanKhau) {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `khai_trieu_chung` WHERE `id_person`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            ps.setInt(1, idNhanKhau);
            
            return (ps.executeUpdate() > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean removeBenh(int idNhanKhau) {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `khai_benh` WHERE `id_person`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            ps.setInt(1, idNhanKhau);
            
            return (ps.executeUpdate() > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
