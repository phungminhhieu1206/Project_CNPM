/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.controllers.dichTeController;

import covid19_management_system.MY_CONNECTION;
import covid19_management_system.controllers.nhankhauController.EditNhanKhauController;
import covid19_management_system.models.DichTeModel;
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
public class EditDichTeController {
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
    
    public DichTeModel searchAllInfoDichTe(int IDNhanKhau) {
        DichTeModel dichTeModel = new DichTeModel();
        dichTeModel.setID_PERSON(IDNhanKhau);
        
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `ngay_khai`, `tx_benh`, `tu_vung_dich` FROM `dich_te` WHERE `id_person`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);

            preparedStatement.setInt(1, dichTeModel.getID_PERSON());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                dichTeModel.setNgayKhaiDichTe(resultSet.getDate(1));
                dichTeModel.setTxCovid(resultSet.getInt(2));
                dichTeModel.setTuVungDich(resultSet.getInt(3));
                
                return dichTeModel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }  
        return null;
    }
    
    public boolean editDichTe(int ID, DichTeModel temp) {
        DichTeModel dichTeModel = new DichTeModel();
        dichTeModel = temp;
        
        PreparedStatement preparedStatement;
        String query = "UPDATE `dich_te` SET `ngay_khai`=?,`tx_benh`=?,`tu_vung_dich`=? WHERE `id_person`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            
            // date
            java.sql.Date ngayKhai = new java.sql.Date(dichTeModel.getNgayKhaiDichTe().getTime());
            preparedStatement.setDate(1, ngayKhai);
            // radio
            preparedStatement.setInt(2, dichTeModel.getTxCovid());
            preparedStatement.setInt(3, dichTeModel.getTuVungDich());
            preparedStatement.setInt(4, ID);
            
//            return (preparedStatement.executeUpdate() > 0);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            my_connection.getConnection().close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
}
