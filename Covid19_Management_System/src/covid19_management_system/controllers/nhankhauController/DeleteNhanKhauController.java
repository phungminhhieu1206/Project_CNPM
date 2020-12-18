/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.controllers.nhankhauController;

import covid19_management_system.MY_CONNECTION;
import covid19_management_system.models.NhanKhauModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HieuPhung
 */
public class DeleteNhanKhauController {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    public int searchIDFromCMT(String CMT) {
        int ID = 0;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `ID` FROM `nhan_khau` WHERE `chungMinhThu`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setString(1, CMT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ID = resultSet.getInt(1);
            }
            return ID;
        } catch (SQLException ex) {
            Logger.getLogger(EditNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public boolean deleteNhanKhau(int ID) {
        PreparedStatement preparedStatement;
        String query = "DELETE FROM `nhan_khau` WHERE `ID`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setInt(1, ID);
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException ex) {
            Logger.getLogger(EditNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
}
