/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.controllers.thongKeController;

import covid19_management_system.MY_CONNECTION;
import covid19_management_system.models.ThongKeModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HieuPhung
 */
public class ThongKeController {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    public ThongKeModel showResultThongKe() {
        ThongKeModel thongKeModel = new ThongKeModel();
        
        // 1. Đếm xem có bao nhiêu nhân khẩu
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query1 = "SELECT COUNT(`ID`) FROM `nhan_khau`";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thongKeModel.setSoNhanKhau(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // 2. Đếm xem có bao nhiêu người đã khai báo dịch tễ, bao nhiêu người chưa khai báo
        String query2 = "SELECT COUNT(DISTINCT `id_person`) FROM `dich_te`";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query2);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thongKeModel.setDaKhaiBao(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // => số người chưa khai báo dịch tễ
        thongKeModel.setChuaKhaiBao(thongKeModel.getSoNhanKhau() - thongKeModel.getDaKhaiBao());
        
        // 3. Đếm xem có bao nhiêu người đi từ vùng dịch về
        String query3 = "SELECT COUNT(`id_person`) FROM `dich_te` GROUP BY `tu_vung_dich` HAVING `tu_vung_dich` = 1";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query3);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thongKeModel.setTuVungDich(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // 4. Đếm xem có bao nhiêu người tiếp xúc với người mắc Covid19
        String query4 = "SELECT COUNT(`id_person`) FROM `dich_te` GROUP BY `tx_benh` HAVING `tx_benh` = 1";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query4);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thongKeModel.setTxCovid(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // 5. Đếm xem có bao nhiêu người mắc covid
        String query5 = "SELECT COUNT(`IDNhanKhau`) FROM `cach_ly` GROUP BY `mucDoCL` HAVING `mucDoCL` = 1";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query5);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thongKeModel.setMacCovid(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // 6. Đếm xem có bao nhiêu người thực hiện cách ly
        String query6 = "SELECT COUNT(DISTINCT `IDNhanKhau`) FROM `cach_ly`";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query6);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thongKeModel.setCachLy(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // 7. Đếm xem có bao nhiêu người cách ly tại nhà
        String query7 = "SELECT COUNT(`IDNhanKhau`) FROM `cach_ly` GROUP BY `loaiCL` HAVING `loaiCL` = 1";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query7);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thongKeModel.setCLTaiNha(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // 8. Đếm xem có bao nhiêu người cách ly tập trung
        String query8 = "SELECT COUNT(`IDNhanKhau`) FROM `cach_ly` GROUP BY `loaiCL` HAVING `loaiCL` = 2";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query8);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thongKeModel.setCLTapTrung(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // 9. Đếm xem có bao nhiêu người đã test covid
        String query9 = "SELECT COUNT(DISTINCT `IDNhanKhau`) FROM `test_covid`";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query9);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thongKeModel.setSoNguoiTestCovid(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return thongKeModel;
    }
    
}
