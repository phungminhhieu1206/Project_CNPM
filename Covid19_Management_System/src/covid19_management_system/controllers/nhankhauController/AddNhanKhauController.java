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
public class AddNhanKhauController {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    public boolean addNewNhanKhau(NhanKhauModel temp) {
        NhanKhauModel nhanKhauModel = new NhanKhauModel();
        nhanKhauModel = temp;
        
        PreparedStatement preparedStatement;
        String query = "INSERT INTO `nhan_khau`(`hoTen`, `chungMinhThu`, `ngaySinh`, `gioiTinh`, `danToc`, `quocTich`, `ngheNghiep`, `soDienThoai`, `email`, `diaChi`, `checkBHYT`, `maTheBHYT`, `maHoKhau`, `noiLamViec`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            
            // theo thu tu dau ?
            preparedStatement.setString(1, nhanKhauModel.getHoTen());
            preparedStatement.setString(2, nhanKhauModel.getChungMinhThu());
            
            java.sql.Date ngaySinh = new java.sql.Date(nhanKhauModel.getNgaySinh().getTime());
            preparedStatement.setDate(3, ngaySinh);
            
            preparedStatement.setInt(4, nhanKhauModel.getGioiTinh());
            preparedStatement.setString(5, nhanKhauModel.getDanToc());
            preparedStatement.setString(6, nhanKhauModel.getQuocTich());
            preparedStatement.setString(7, nhanKhauModel.getNgheNghiep());
            preparedStatement.setString(8, nhanKhauModel.getSoDienThoai());
            preparedStatement.setString(9, nhanKhauModel.getEmail());
            preparedStatement.setString(10, nhanKhauModel.getDiaChi());
            preparedStatement.setInt(11, nhanKhauModel.getCheckBHYT());
            preparedStatement.setString(12, nhanKhauModel.getMaTheBHYT());
            preparedStatement.setString(13, nhanKhauModel.getMaHoKhau());
            preparedStatement.setString(14, nhanKhauModel.getNoiLamViec());
            
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException ex) {
            Logger.getLogger(AddNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
    public boolean equalCMT(String temp) {
        String chungMinhThu = temp.trim();
        
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM `nhan_khau` WHERE `chungMinhThu`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            
            // theo thu tu dau ?
            preparedStatement.setString(1, chungMinhThu);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
}
