/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.controllers.cachlyController;

import covid19_management_system.MY_CONNECTION;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HieuPhung
 */
public class DeleteCachLyController {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    public boolean removeCachLy(int idNhanKhau) {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `cach_ly` WHERE `IDNhanKhau`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            ps.setInt(1, idNhanKhau);
            
            return (ps.executeUpdate() > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteCachLyController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean removeAllTest(int idNhanKhau) { // id nhân khẩu

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `test_covid` WHERE `IDNhanKhau`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, idNhanKhau);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(DeleteCachLyController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
}
