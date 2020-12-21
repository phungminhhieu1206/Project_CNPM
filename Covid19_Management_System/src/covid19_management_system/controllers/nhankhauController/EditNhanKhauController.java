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
 * @author HieuPhung
 * @author HieuPhung
 */
public class EditNhanKhauController {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    public NhanKhauModel searchAllInfoNhanKhau(String CMT) {
        NhanKhauModel nhanKhauModel = new NhanKhauModel();
        nhanKhauModel.setChungMinhThu(CMT);
        
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `hoTen`, `ngaySinh`, `gioiTinh`, `danToc`, `quocTich`, `ngheNghiep`, `soDienThoai`, `email`, `diaChi`, `checkBHYT`, `maTheBHYT`, `maHoKhau`, `noiLamViec` FROM `nhan_khau` WHERE `chungMinhThu`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            
            // theo thu tu dau ?
            preparedStatement.setString(1, nhanKhauModel.getChungMinhThu());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                nhanKhauModel.setHoTen(resultSet.getString(1));
                nhanKhauModel.setNgaySinh(resultSet.getDate(2));
                nhanKhauModel.setGioiTinh(resultSet.getInt(3));
                nhanKhauModel.setDanToc(resultSet.getString(4));
                nhanKhauModel.setQuocTich(resultSet.getString(5));
                nhanKhauModel.setNgheNghiep(resultSet.getString(6));
                nhanKhauModel.setSoDienThoai(resultSet.getString(7));
                nhanKhauModel.setEmail(resultSet.getString(8));
                nhanKhauModel.setDiaChi(resultSet.getString(9));
                nhanKhauModel.setCheckBHYT(resultSet.getInt(10));
                nhanKhauModel.setMaTheBHYT(resultSet.getString(11));
                nhanKhauModel.setMaHoKhau(resultSet.getString(12));
                nhanKhauModel.setNoiLamViec(resultSet.getString(13));
                
                return nhanKhauModel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }  
        return null;
    }
    
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
    
    public boolean editNhanKhau(int ID, NhanKhauModel temp) {
        NhanKhauModel nhanKhauModel = new NhanKhauModel();
        nhanKhauModel = temp;
        
        PreparedStatement preparedStatement;
        String query = "UPDATE `nhan_khau` SET `hoTen`=?,`chungMinhThu`=?,`ngaySinh`=?,`gioiTinh`=?,`danToc`=?,`quocTich`=?,`ngheNghiep`=?,`soDienThoai`=?,`email`=?,`diaChi`=?,`checkBHYT`=?,`maTheBHYT`=?,`maHoKhau`=?,`noiLamViec`=? WHERE `ID`=?";
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
            preparedStatement.setInt(15, ID);
            
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException ex) {
            Logger.getLogger(EditNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
}
