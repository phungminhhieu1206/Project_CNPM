/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.controllers.cachlyController;

import covid19_management_system.MY_CONNECTION;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author HieuPhung
 */
public class ShowTableCachLyController {
    MY_CONNECTION my_connection = new MY_CONNECTION();

    public void showCachLy(JTable table) {
//        PreparedStatement preparedStatement;
//        ResultSet resultSet;
//
//        int IDNhanKhau;
//        String query = "SELECT `id_person`, `ngay_khai`, `tx_benh`, `tu_vung_dich` FROM `dich_te` WHERE 1 ORDER BY `id_person` ASC";
//        try {
//            preparedStatement = my_connection.createConnection().prepareStatement(query);
//            resultSet = preparedStatement.executeQuery();
//            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
//                tableModel.removeRow(i);
//            }
//            Object[] row;
//            while (resultSet.next()) {
//                IDNhanKhau = resultSet.getInt(1);
//                row = new Object[11];
//                for (int i = 0; i < 11; i++) {
//                    row[i] = 0;
//                }
//
//                // - search thông tin cá nhân
//                PreparedStatement preparedStatementNK;
//                ResultSet resultSetNK;
//                String queryNK = "SELECT `ID`, `hoTen`, `ngaySinh`, `gioiTinh`, `diaChi`, `soDienThoai` FROM `nhan_khau` WHERE `ID`=?";
//                try {
//                    preparedStatementNK = my_connection.createConnection().prepareStatement(queryNK);
//                    preparedStatementNK.setInt(1, IDNhanKhau);
//                    resultSetNK = preparedStatementNK.executeQuery();
//
//                    while (resultSetNK.next()) {
//                        row[0] = resultSetNK.getInt(1);
//                        row[1] = resultSetNK.getString(2);
//                        // ngaySinh
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                        row[2] = dateFormat.format(resultSetNK.getDate(3));
//                        // gioiTinh
//                        int gioiTinh = resultSetNK.getInt(4);
//                        switch (gioiTinh) {
//                            case 0:
//                                row[3] = "";
//                                break;
//                            case 1:
//                                row[3] = "Nam";
//                                break;
//                            case 2:
//                                row[3] = "Nữ";
//                                break;
//                            case 3:
//                                row[3] = "Khác";
//                                break;
//                            default:
//                                break;
//                        }
//                        row[4] = resultSetNK.getString(5);
//                        row[5] = resultSetNK.getString(6);
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                // - kết thúc thông tin cá nhân
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                row[6] = dateFormat.format(resultSet.getDate(2));
////                System.out.println("hello");
//
//                int txCovid = resultSet.getInt(3);
//                switch (txCovid) {
//                    case 0:
//                        row[7] = "Không";
//                        break;
//                    case 1:
//                        row[7] = "Có";
//                        break;
//                    default:
//                        break;
//                }
//
//                int tuVungDich = resultSet.getInt(4);
//                switch (tuVungDich) {
//                    case 0:
//                        row[8] = "Không";
//                        break;
//                    case 1:
//                        row[8] = "Có";
//                        break;
//                    default:
//                        break;
//                }
//                
//                // - search thông tin trieu chung
//                PreparedStatement preparedStatementTC;
//                ResultSet resultSetTC;
//                String trieuChung = "";
//                String queryTC = "SELECT `trieu_chung` FROM `khai_trieu_chung` WHERE `id_person`=?";
//                try {
//                    preparedStatementTC = my_connection.createConnection().prepareStatement(queryTC);
//                    preparedStatementTC.setInt(1, IDNhanKhau);
//                    resultSetTC = preparedStatementTC.executeQuery();
//
//                    while (resultSetTC.next()) {
//                        trieuChung += setTrieuChung(resultSetTC.getInt(1)) + ", ";
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                row[9] = trieuChung;
//                // - kết thúc thông tin triệu chứng
//                
//                // - search thông tin bệnh
//                PreparedStatement preparedStatementBenh;
//                ResultSet resultSetBenh;
//                String benh = "";
//                String queryBenh = "SELECT `ma_benh` FROM `khai_benh` WHERE `id_person`=?";
//                try {
//                    preparedStatementBenh = my_connection.createConnection().prepareStatement(queryBenh);
//                    preparedStatementBenh.setInt(1, IDNhanKhau);
//                    resultSetBenh = preparedStatementBenh.executeQuery();
//
//                    while (resultSetBenh.next()) {
//                        benh += setBenh(resultSetBenh.getInt(1)) + ", ";
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                row[10] = benh;
//                // - kết thúc thông tin bệnh
//
//                tableModel.addRow(row);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    // check IDNhanKhau có trong dich_te không ?
    
    public boolean checkIdCachLy(int IDNhanKhau) {
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM `cach_ly` WHERE `IDNhanKhau`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setInt(1, IDNhanKhau);
            try {
                if (preparedStatement.executeQuery().next()) {
                    return true;
                }
            } catch (SQLException e) {}
        } catch (SQLException ex) {
            Logger.getLogger(ShowTableCachLyController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
}
