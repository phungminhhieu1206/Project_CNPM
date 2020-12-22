/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.views.CachLy;

import covid19_management_system.controllers.cachlyController.EditCachLyController;
import covid19_management_system.controllers.cachlyController.ShowTableCachLyController;
import covid19_management_system.controllers.nhankhauController.EditNhanKhauController;
import covid19_management_system.controllers.nhankhauController.ShowInfoNhanKhauController;
import covid19_management_system.models.CachLyModel;
import covid19_management_system.models.NhanKhauModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author HieuPhung
 */
public class ShowInfoCachLy extends javax.swing.JFrame {

    /**
     * Creates new form ShowInfoCachLy
     */
    private String chungMinhThu;
    private int IDNhanKhau;
    NhanKhauModel nhanKhauModel = new NhanKhauModel();
    CachLyModel cachLyModel = new CachLyModel();
    ShowInfoNhanKhauController showInfoNhanKhauController = new ShowInfoNhanKhauController();
    ShowTableCachLyController showTableCachLyController = new ShowTableCachLyController();
    EditNhanKhauController editNhanKhauController = new EditNhanKhauController();
    EditCachLyController editCachLyController = new EditCachLyController();

    public ShowInfoCachLy(String chungMinhThu) {
        initComponents();
        this.chungMinhThu = chungMinhThu;
        this.IDNhanKhau = editNhanKhauController.searchIDFromCMT(chungMinhThu);
        this.setTitle("Nhân khẩu có CMT: " + this.chungMinhThu);
        this.settingTableShowNhanKhau();

        this.searchAllInfoNhanKhau();
        this.searchAllInfoCachLy();
        showTableCachLyController.showTest(jTableCacLanTestCL, IDNhanKhau);

        // confirm de thuc hien dong
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đóng không ?", "Xác nhận thao tác", JOptionPane.YES_NO_OPTION) == 0) {
                    dispose();
                }
            }
        });
    }

    public void settingTableShowNhanKhau() {
        jTableCacLanTestCL.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTableCacLanTestCL.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTableCacLanTestCL.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTableCacLanTestCL.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTableCacLanTestCL.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTableCacLanTestCL.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTableCacLanTestCL.setRowHeight(30);
    }

    public void searchAllInfoCachLy() {
        this.cachLyModel = editCachLyController.searchAllInfoCachLy(IDNhanKhau);

        // ngày khai
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        jLNgayKhaiCL.setText(dateFormat.format(cachLyModel.getNgayKhaiCL()));

        // ngày bắt đầu cách ly
        jLNgayBatDauCL.setText(dateFormat.format(cachLyModel.getNgayBatDauCL()));

        // mức độ cách ly
        int mucDoCL = cachLyModel.getMucDoCL();
        String mucDoCLStr = null;
        switch (mucDoCL) {
            case 0:
                mucDoCLStr = "";
                break;
            case 1:
                mucDoCLStr = "FO";
                break;
            case 2:
                mucDoCLStr = "F1";
                break;
            case 3:
                mucDoCLStr = "F2";
                break;
            case 4:
                mucDoCLStr = "F3";
                break;
            default:
                break;
        }
        jLMucDoCL.setText(mucDoCLStr.trim());

        // thông tin cách ly:
        int loaiCL = cachLyModel.getLoaiCL();
        String temp = "";
        switch (loaiCL) {
            case 1: // loaiCachLy: Tại nhà
                temp = "Cách ly tại nhà";
                break;
            case 2: // loaiCachLy: Tập trung
            {
                String diaChiCL = cachLyModel.getDiaChiCL();
                String soPhongCL = cachLyModel.getSoPhongCL();
                String soGiuongCL = cachLyModel.getSoGiuongCL();
                String tenNgCungPhCL = cachLyModel.getTenNgCungPhCL();
                temp = "Cách ly tập trung: Phòng:" + soPhongCL + ", giường:" + soGiuongCL + ", tại: " + diaChiCL;
            }
            break;
            default:
                break;
        }
        
        jLThongTinCachLy.setText(temp.trim());

    }

    public void searchAllInfoNhanKhau() {
        this.nhanKhauModel = showInfoNhanKhauController.showInfoNhanKhau(this.chungMinhThu);

        jLChungMinhThu.setText(chungMinhThu);
        jLHoVaTen.setText(nhanKhauModel.getHoTen().trim());
        // ngaySinh
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        jLNgaySinh.setText(dateFormat.format(nhanKhauModel.getNgaySinh()));
        int gioiTinh = nhanKhauModel.getGioiTinh();
        String gioiTinhStr = null;
        switch (gioiTinh) {
            case 0:
                gioiTinhStr = "";
                break;
            case 1:
                gioiTinhStr = "Nam";
                break;
            case 2:
                gioiTinhStr = "Nữ";
                break;
            case 3:
                gioiTinhStr = "Khác";
                break;
            default:
                break;
        }
        jLGioiTinh.setText(gioiTinhStr.trim());
        jLDanToc.setText(nhanKhauModel.getDanToc().trim());
        jLQuocTich.setText(nhanKhauModel.getQuocTich().trim());
        jLNgheNghiep.setText(nhanKhauModel.getNgheNghiep().trim());
        jLSoDienThoai.setText(nhanKhauModel.getSoDienThoai().trim());
        jLEmail.setText(nhanKhauModel.getEmail().trim());
        jLDiaChi.setText(nhanKhauModel.getDiaChi().trim());
        // radio
        if (nhanKhauModel.getCheckBHYT() == 1) {
            jLBHYT.setText(nhanKhauModel.getMaTheBHYT().trim());
        } else if (nhanKhauModel.getCheckBHYT() == 0) {
            jLBHYT.setText("");
        }
        jLMaHoKhau.setText(nhanKhauModel.getMaHoKhau().trim());
        jLNoiLamViec.setText(nhanKhauModel.getNoiLamViec().trim());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLHoVaTen = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLChungMinhThu = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLNgaySinh = new javax.swing.JLabel();
        jLGioiTinh = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLDanToc = new javax.swing.JLabel();
        jLQuocTich = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLSoDienThoai = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLEmail = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLDiaChi = new javax.swing.JLabel();
        jLBHYT = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLMaHoKhau = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLNgheNghiep = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLNoiLamViec = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLNgayKhaiCL = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLMucDoCL = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLThongTinCachLy = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCacLanTestCL = new javax.swing.JTable();
        jLNgayBatDauCL = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("THÔNG TIN CÁCH LY");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(607, 607, 607)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Họ và tên:");

        jLHoVaTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLHoVaTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("CMT:");

        jLChungMinhThu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLChungMinhThu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("Ngày sinh:");

        jLNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLNgaySinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLGioiTinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("Giới tính:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setText("Dân tộc:");

        jLDanToc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLDanToc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLQuocTich.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLQuocTich.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setText("Quốc tịch:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setText("SĐT:");

        jLSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setText("Email:");

        jLEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel25.setText("Địa chỉ:");

        jLDiaChi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLBHYT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLBHYT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel28.setText("Thẻ BHYT:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel29.setText("Mã hộ khẩu:");

        jLMaHoKhau.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLMaHoKhau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel31.setText("Nghề nghiệp:");

        jLNgheNghiep.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLNgheNghiep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel33.setText("Nơi làm việc");

        jLNoiLamViec.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLNoiLamViec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel31)
                            .addGap(20, 20, 20)
                            .addComponent(jLNgheNghiep, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel11))
                                .addComponent(jLabel16)
                                .addComponent(jLabel17)
                                .addComponent(jLabel7)
                                .addComponent(jLabel20)
                                .addComponent(jLabel21)
                                .addComponent(jLabel23)
                                .addComponent(jLabel25)
                                .addComponent(jLabel28)
                                .addComponent(jLabel29))
                            .addGap(28, 28, 28)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLChungMinhThu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                .addComponent(jLNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLDanToc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLQuocTich, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLSoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLMaHoKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLBHYT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLHoVaTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(30, 30, 30)
                        .addComponent(jLNoiLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLHoVaTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLChungMinhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLDanToc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLQuocTich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLBHYT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLMaHoKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLNgheNghiep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLNoiLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLNgayKhaiCL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLNgayKhaiCL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("Ngày khai:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Mức độ cách ly:");

        jLMucDoCL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLMucDoCL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("Thông tin thêm:");

        jLThongTinCachLy.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLThongTinCachLy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Thông tin các lần test covid (Nếu có):");

        jTableCacLanTestCL.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTableCacLanTestCL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lần test", "Ngày test", "Hình thức test", "Địa điểm test", "Kết quả"
            }
        )

        // make the jtable cells not edittable
        {public boolean isCellEditTable(int row, int column){
            return false;
        }}

    );
    jTableCacLanTestCL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    jTableCacLanTestCL.setGridColor(new java.awt.Color(255, 255, 0));
    jTableCacLanTestCL.setRowHeight(25);
    jTableCacLanTestCL.setSelectionBackground(new java.awt.Color(0, 102, 51));
    jTableCacLanTestCL.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTableCacLanTestCLMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jTableCacLanTestCLMouseEntered(evt);
        }
    });
    jScrollPane4.setViewportView(jTableCacLanTestCL);

    jLNgayBatDauCL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
    jLNgayBatDauCL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel6.setText("Bắt đầu CLy:");

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addGap(18, 18, 18)
                    .addComponent(jLMucDoCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel4)
                    .addGap(55, 55, 55)
                    .addComponent(jLNgayKhaiCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLThongTinCachLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jLabel9)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel6)
                    .addGap(39, 39, 39)
                    .addComponent(jLNgayBatDauCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLNgayKhaiCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLNgayBatDauCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLMucDoCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLThongTinCachLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableCacLanTestCLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCacLanTestCLMouseClicked

    }//GEN-LAST:event_jTableCacLanTestCLMouseClicked

    private void jTableCacLanTestCLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCacLanTestCLMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableCacLanTestCLMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLBHYT;
    private javax.swing.JLabel jLChungMinhThu;
    private javax.swing.JLabel jLDanToc;
    private javax.swing.JLabel jLDiaChi;
    private javax.swing.JLabel jLEmail;
    private javax.swing.JLabel jLGioiTinh;
    private javax.swing.JLabel jLHoVaTen;
    private javax.swing.JLabel jLMaHoKhau;
    private javax.swing.JLabel jLMucDoCL;
    private javax.swing.JLabel jLNgayBatDauCL;
    private javax.swing.JLabel jLNgayKhaiCL;
    private javax.swing.JLabel jLNgaySinh;
    private javax.swing.JLabel jLNgheNghiep;
    private javax.swing.JLabel jLNoiLamViec;
    private javax.swing.JLabel jLQuocTich;
    private javax.swing.JLabel jLSoDienThoai;
    private javax.swing.JLabel jLThongTinCachLy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableCacLanTest;
    private javax.swing.JTable jTableCacLanTestCL;
    // End of variables declaration//GEN-END:variables
}
