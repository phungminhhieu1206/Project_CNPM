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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HieuPhung
 */
public class ShowTableCachLyController {
    MY_CONNECTION my_connection = new MY_CONNECTION();

    public void showCachLy(JTable table) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        int IDNhanKhau;
        String query = "SELECT `IDNhanKhau`, `ngayKhaiCL`, `loaiCL`, `mucDoCL`, `ngayBatDauCL`, `diaChiCL`, `soPhongCL`, `soGiuongCL`, `tenNgCungPhCL` FROM `cach_ly` WHERE 1 ORDER BY `IDNhanKhau` ASC";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
            Object[] row;
            while (resultSet.next()) {
                IDNhanKhau = resultSet.getInt(1);
                row = new Object[12];
                for (int i = 0; i < 12; i++) {
                    row[i] = 0;
                }
                // - lấy được ID

                // - search thông tin cá nhân
                PreparedStatement preparedStatementNK;
                ResultSet resultSetNK;
                String queryNK = "SELECT `ID`, `hoTen`, `ngaySinh`, `gioiTinh`, `diaChi`, `soDienThoai` FROM `nhan_khau` WHERE `ID`=?";
                try {
                    preparedStatementNK = my_connection.createConnection().prepareStatement(queryNK);
                    preparedStatementNK.setInt(1, IDNhanKhau);
                    resultSetNK = preparedStatementNK.executeQuery();

                    while (resultSetNK.next()) {
                        row[0] = resultSetNK.getInt(1);
                        row[1] = resultSetNK.getString(2);
                        // date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        row[2] = dateFormat.format(resultSetNK.getDate(3));
                        // combobox
                        int gioiTinh = resultSetNK.getInt(4);
                        switch (gioiTinh) {
                            case 0:
                                row[3] = "";
                                break;
                            case 1:
                                row[3] = "Nam";
                                break;
                            case 2:
                                row[3] = "Nữ";
                                break;
                            case 3:
                                row[3] = "Khác";
                                break;
                            default:
                                break;
                        }
                        row[4] = resultSetNK.getString(5);
                        row[5] = resultSetNK.getString(6);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableCachLyController.class.getName()).log(Level.SEVERE, null, ex);
                }
                // - kết thúc thông tin cá nhân
                
                // - đến thông tin cách ly

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                row[6] = dateFormat.format(resultSet.getDate(5)); // ngayCachLy
//                System.out.println("hello");

                int mucDoCL = resultSet.getInt(4); // mucDoCL
                switch (mucDoCL) {
                    case 1:
                        row[7] = "FO";
                        break;
                    case 2:
                        row[7] = "F1";
                        break;
                    case 3:
                        row[7] = "F2";
                        break;
                    case 4:
                        row[7] = "F3";
                        break;
                    default:
                        break;
                }

                int loaiCL = resultSet.getInt(3);
                switch (loaiCL) {
                    case 1:
                        row[8] = "Tại nhà";
                        break;
                    case 2: // loaiCachLy: Tập trung
                        {
                            String diaChiCL = resultSet.getString(6);
                            String soPhongCL = resultSet.getString(7);
                            String soGiuongCL = resultSet.getString(8);
                            String tenNgCungPhCL = resultSet.getString(9);
                            row[8] = "Phòng:" + soPhongCL + ", giường:" + soGiuongCL + ", tại: " + diaChiCL;
                        }
                        break;
                    default:
                        break;
                }
                
                // - search thông tin lần cuối cùng test covid
                PreparedStatement ps;
                ResultSet rs;
                String queryTestCovid = "SELECT `ngayTest`, `lanTest`, `hinhThucTest`, `diaDiemTest`, `ketQuaTest` FROM `test_covid` WHERE `IDNhanKhau`=? ORDER BY `ngayTest` DESC, `lanTest` DESC LIMIT 1";
                try {
                    ps = my_connection.createConnection().prepareStatement(queryTestCovid);
                    ps.setInt(1, IDNhanKhau);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        // ngayTest
                        row[9] = dateFormat.format(rs.getDate(1));
                        // thông tin test
                        int hinhThucTest = rs.getInt(3); // hình thức test
                        String hinhThucTestStr = "";
                        switch (hinhThucTest) {
                            case 1:
                                hinhThucTestStr = "Test PCR";
                                break;
                            case 2:
                                hinhThucTestStr = "Test nhanh";
                                break;
                            default:
                                break;
                        }
                        String diaDiemTest = rs.getString(4).trim(); // địa điểm test
                        row[10] = hinhThucTestStr + ", tại:" + diaDiemTest;
                        
                        // ketQuaTest
                        int ketQuaTest = rs.getInt(5);
                        switch (ketQuaTest) {
                            case 1:
                                row[11] = "Âm tính";
                                break;
                            case 2:
                                row[11] = "Dương tính";
                                break;
                            default:
                                break;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableCachLyController.class.getName()).log(Level.SEVERE, null, ex);
                }
                

                tableModel.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ShowTableCachLyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showCachLyWithCMT(JTable table, String CMT) {
        // ---------- search ID nhân khẩu -------------------
        int IDNhanKhau = 0;
        PreparedStatement ps1;
        ResultSet rs1;
        String querySearch1 = "SELECT `ID` FROM `nhan_khau` WHERE `chungMinhThu`=?";
        try {
            ps1 = my_connection.createConnection().prepareStatement(querySearch1);
            ps1.setString(1, CMT);
            rs1 = ps1.executeQuery();
            
            while(rs1.next()){
                IDNhanKhau = rs1.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ShowTableCachLyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (IDNhanKhau == 0) return;
        // -------------- đã có ID nhân khẩu -----------------------
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `IDNhanKhau`, `ngayKhaiCL`, `loaiCL`, `mucDoCL`, `ngayBatDauCL`, `diaChiCL`, `soPhongCL`, `soGiuongCL`, `tenNgCungPhCL` FROM `cach_ly` WHERE `IDNhanKhau`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setInt(1, IDNhanKhau);
            resultSet = preparedStatement.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
            Object[] row;
            while (resultSet.next()) {
                IDNhanKhau = resultSet.getInt(1);
                row = new Object[12];
                // - lấy được ID

                // - search thông tin cá nhân
                PreparedStatement preparedStatementNK;
                ResultSet resultSetNK;
                String queryNK = "SELECT `ID`, `hoTen`, `ngaySinh`, `gioiTinh`, `diaChi`, `soDienThoai` FROM `nhan_khau` WHERE `ID`=?";
                try {
                    preparedStatementNK = my_connection.createConnection().prepareStatement(queryNK);
                    preparedStatementNK.setInt(1, IDNhanKhau);
                    resultSetNK = preparedStatementNK.executeQuery();

                    while (resultSetNK.next()) {
                        row[0] = resultSetNK.getInt(1);
                        row[1] = resultSetNK.getString(2);
                        // date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        row[2] = dateFormat.format(resultSetNK.getDate(3));
                        // combobox
                        int gioiTinh = resultSetNK.getInt(4);
                        switch (gioiTinh) {
                            case 0:
                                row[3] = "";
                                break;
                            case 1:
                                row[3] = "Nam";
                                break;
                            case 2:
                                row[3] = "Nữ";
                                break;
                            case 3:
                                row[3] = "Khác";
                                break;
                            default:
                                break;
                        }
                        row[4] = resultSetNK.getString(5);
                        row[5] = resultSetNK.getString(6);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableCachLyController.class.getName()).log(Level.SEVERE, null, ex);
                }
                // - kết thúc thông tin cá nhân
                
                // - đến thông tin cách ly

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                row[6] = dateFormat.format(resultSet.getDate(5)); // ngayCachLy
//                System.out.println("hello");

                int mucDoCL = resultSet.getInt(4); // mucDoCL
                switch (mucDoCL) {
                    case 1:
                        row[7] = "FO";
                        break;
                    case 2:
                        row[7] = "F1";
                        break;
                    case 3:
                        row[7] = "F2";
                        break;
                    case 4:
                        row[7] = "F3";
                        break;
                    default:
                        break;
                }

                int loaiCL = resultSet.getInt(3);
                switch (loaiCL) {
                    case 1:
                        row[8] = "Tại nhà";
                        break;
                    case 2: // loaiCachLy: Tập trung
                        {
                            String diaChiCL = resultSet.getString(6);
                            String soPhongCL = resultSet.getString(7);
                            String soGiuongCL = resultSet.getString(8);
                            String tenNgCungPhCL = resultSet.getString(9);
                            row[8] = "Phòng:" + soPhongCL + ", giường:" + soGiuongCL + ", tại: " + diaChiCL;
                        }
                        break;
                    default:
                        break;
                }
                
                // - search thông tin lần cuối cùng test covid
                PreparedStatement ps;
                ResultSet rs;
                String queryTestCovid = "SELECT `ngayTest`, `lanTest`, `hinhThucTest`, `diaDiemTest`, `ketQuaTest` FROM `test_covid` WHERE `IDNhanKhau`=? ORDER BY `ngayTest` DESC LIMIT 1";
                try {
                    ps = my_connection.createConnection().prepareStatement(queryTestCovid);
                    ps.setInt(1, IDNhanKhau);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        // ngayTest
                        row[9] = dateFormat.format(rs.getDate(1));
                        // thông tin test
                        int hinhThucTest = rs.getInt(3); // hình thức test
                        String hinhThucTestStr = "";
                        switch (hinhThucTest) {
                            case 1:
                                hinhThucTestStr = "Test PCR";
                                break;
                            case 2:
                                hinhThucTestStr = "Test nhanh";
                                break;
                            default:
                                break;
                        }
                        String diaDiemTest = rs.getString(4).trim(); // địa điểm test
                        row[10] = hinhThucTestStr + ", tại:" + diaDiemTest;
                        
                        // ketQuaTest
                        int ketQuaTest = rs.getInt(5);
                        switch (ketQuaTest) {
                            case 1:
                                row[11] = "Âm tính";
                                break;
                            case 2:
                                row[11] = "Dương tính";
                                break;
                            default:
                                break;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableCachLyController.class.getName()).log(Level.SEVERE, null, ex);
                }
                

                tableModel.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ShowTableCachLyController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
