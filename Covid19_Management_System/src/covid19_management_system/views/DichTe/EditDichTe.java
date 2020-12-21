/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.views.DichTe;

import covid19_management_system.MY_CONNECTION;
import covid19_management_system.controllers.dichTeController.AddDichTeController;
import covid19_management_system.controllers.dichTeController.DeleteDichTeController;
import covid19_management_system.controllers.dichTeController.EditDichTeController;
import covid19_management_system.controllers.nhankhauController.EditNhanKhauController;
import covid19_management_system.models.DichTeModel;
import covid19_management_system.models.NhanKhauModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author HieuPhung
 */
public class EditDichTe extends javax.swing.JFrame {

    /**
     * Creates new form EditDichTe
     */
    MY_CONNECTION my_connection = new MY_CONNECTION();
    private String chungMinhThu;
    private int IDNhanKhau;
    NhanKhauModel nhanKhauModel = new NhanKhauModel();
    DichTeModel dichTeModel = new DichTeModel();
    EditDichTeController editDichTeController = new EditDichTeController();
    EditNhanKhauController editNhanKhauController = new EditNhanKhauController();
    AddDichTeController addDichTeController = new AddDichTeController();
    DeleteDichTeController deleteDichTeController = new DeleteDichTeController();

    public String getChungMinhThu() {
        return chungMinhThu;
    }

    public void setChungMinhThu(String chungMinhThu) {
        this.chungMinhThu = chungMinhThu;
    }
    ButtonGroup bgCheckBHYT = new ButtonGroup();
    ButtonGroup bgTXCovid = new ButtonGroup();
    ButtonGroup bgTuVungDich = new ButtonGroup();

    public EditDichTe(String chungMinhThu) {
        initComponents();
        this.setTitle("Chỉnh sửa thông tin khai báo dịch tễ cá nhân");
        this.chungMinhThu = chungMinhThu;
        this.IDNhanKhau = editDichTeController.searchIDFromCMT(chungMinhThu);

        // set group radio button
        bgCheckBHYT.add(jRadioBHYT_YES);
        bgCheckBHYT.add(jRadioBHYT_NO);
        bgTXCovid.add(jRadioTXCovid_YES);
        bgTXCovid.add(jRadioTXCovid_NO);
        bgTuVungDich.add(jRadioTuVungDich_YES);
        bgTuVungDich.add(jRadioTuVungDich_NO);

        this.searchInfoNhanKhau();
        this.searchInfoDichTe();
        this.searchInfoTrieuChung();
        this.searchInfoBenh();

        // not edit
        this.setEnableFieldsNhanKhau(false);

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

    public void setEnableFieldsNhanKhau(boolean temp) {
        jTFHoVaTen.setEnabled(temp);
        jTFChungMinhThu.setEnabled(temp);
        jDateCNgaySinh.setEnabled(temp);
        jCoBoxGioiTinh.setEnabled(temp);
        jTFQuocTich.setEnabled(temp);
        jRadioBHYT_YES.setEnabled(temp);
        jRadioBHYT_NO.setEnabled(temp);
        jTFMaTheBHYT.setEnabled(temp);
        jTFSoDienThoai.setEnabled(temp);
        jTFEmail.setEnabled(temp);
        jTFDiaChi.setEnabled(temp);
    }

    public void searchInfoNhanKhau() {
        nhanKhauModel = editNhanKhauController.searchAllInfoNhanKhau(this.chungMinhThu);

        jTFChungMinhThu.setText(chungMinhThu);
        jTFHoVaTen.setText(nhanKhauModel.getHoTen().trim());
        // date db to form
        try {
            Date dateIn = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(nhanKhauModel.getNgaySinh().toString());
            jDateCNgaySinh.setDate(dateIn);
        } catch (ParseException ex) {
            Logger.getLogger(EditDichTe.class.getName()).log(Level.SEVERE, null, ex);
        }
        // combobox
        jCoBoxGioiTinh.setSelectedIndex(nhanKhauModel.getGioiTinh());
        jTFQuocTich.setText(nhanKhauModel.getQuocTich().trim());
        jTFSoDienThoai.setText(nhanKhauModel.getSoDienThoai().trim());
        jTFEmail.setText(nhanKhauModel.getEmail().trim());
        jTFDiaChi.setText(nhanKhauModel.getDiaChi().trim());
        // radio
        if (nhanKhauModel.getCheckBHYT() == 1) {
            jRadioBHYT_YES.setSelected(true);
        } else if (nhanKhauModel.getCheckBHYT() == 0) {
            jRadioBHYT_NO.setSelected(true);
        }
        jTFMaTheBHYT.setText(nhanKhauModel.getMaTheBHYT().trim());
    }

    public void searchInfoDichTe() {
        dichTeModel = editDichTeController.searchAllInfoDichTe(this.IDNhanKhau);

        // date
        try {
            Date dateIn = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dichTeModel.getNgayKhaiDichTe().toString());
            jDateCNgayKhaiDichTe.setDate(dateIn);
        } catch (ParseException ex) {
            Logger.getLogger(EditDichTe.class.getName()).log(Level.SEVERE, null, ex);
        }
        // radio
        if (dichTeModel.getTxCovid() == 1) {
            jRadioTXCovid_YES.setSelected(true);
        } else if (dichTeModel.getTxCovid() == 0) {
            jRadioTXCovid_NO.setSelected(true);
        }
        // radioi
        if (dichTeModel.getTuVungDich() == 1) {
            jRadioTuVungDich_YES.setSelected(true);
        } else if (dichTeModel.getTuVungDich() == 0) {
            jRadioTuVungDich_NO.setSelected(true);
        }
    }

    public void searchInfoTrieuChung() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `trieu_chung` FROM `khai_trieu_chung` WHERE `id_person`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setInt(1, this.IDNhanKhau);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                buildTC(resultSet.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditDichTe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchInfoBenh() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `ma_benh` FROM `khai_benh` WHERE `id_person`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setInt(1, this.IDNhanKhau);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                buildBenh(resultSet.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditDichTe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buildTC(int maTrieuChung) {
        switch (maTrieuChung) {
            case 1:
                jCheckBoxTC1.setSelected(true);
                break;

            case 2:
                jCheckBoxTC2.setSelected(true);
                break;

            case 3:
                jCheckBoxTC3.setSelected(true);
                break;

            case 4:
                jCheckBoxTC4.setSelected(true);
                break;

            case 5:
                jCheckBoxTC5.setSelected(true);
                break;

            case 6:
                jCheckBoxTC6.setSelected(true);
                break;

            case 7:
                jCheckBoxTC7.setSelected(true);
                break;

            case 8:
                jCheckBoxTC8.setSelected(true);
                break;

            case 9:
                jCheckBoxTC9.setSelected(true);
                break;

            default:
                break;
        }
    }

    public void buildBenh(int maBenh) {
        switch (maBenh) {
            case 1:
                jCheckBoxBenh1.setSelected(true);
                break;

            case 2:
                jCheckBoxBenh2.setSelected(true);
                break;

            case 3:
                jCheckBoxBenh3.setSelected(true);
                break;

            case 4:
                jCheckBoxBenh4.setSelected(true);
                break;

            case 5:
                jCheckBoxBenh5.setSelected(true);
                break;

            case 6:
                jCheckBoxBenh6.setSelected(true);
                break;

            case 7:
                jCheckBoxBenh7.setSelected(true);
                break;

            case 8:
                jCheckBoxBenh8.setSelected(true);
                break;

            case 9:
                jCheckBoxBenh9.setSelected(true);
                break;

            default:
                break;
        }
    }

    public void addTrieuChung(int idPerson) throws SQLException {
        if (jCheckBoxTC1.isSelected()) {
            addDichTeController.addTrieuChung(idPerson, 1);
        }
        if (jCheckBoxTC2.isSelected()) {
            addDichTeController.addTrieuChung(idPerson, 2);
        }
        if (jCheckBoxTC3.isSelected()) {
            addDichTeController.addTrieuChung(idPerson, 3);
        }
        if (jCheckBoxTC4.isSelected()) {
            addDichTeController.addTrieuChung(idPerson, 4);
        }
        if (jCheckBoxTC5.isSelected()) {
            addDichTeController.addTrieuChung(idPerson, 5);
        }
        if (jCheckBoxTC6.isSelected()) {
            addDichTeController.addTrieuChung(idPerson, 6);
        }
        if (jCheckBoxTC7.isSelected()) {
            addDichTeController.addTrieuChung(idPerson, 7);
        }
        if (jCheckBoxTC8.isSelected()) {
            addDichTeController.addTrieuChung(idPerson, 8);
        }
        if (jCheckBoxTC9.isSelected()) {
            addDichTeController.addTrieuChung(idPerson, 9);
        }
    }

    public void addBenh(int idPerson) throws SQLException {
        if (jCheckBoxBenh1.isSelected()) {
            addDichTeController.addBenh(idPerson, 1);
        }
        if (jCheckBoxBenh2.isSelected()) {
            addDichTeController.addBenh(idPerson, 2);
        }
        if (jCheckBoxBenh3.isSelected()) {
            addDichTeController.addBenh(idPerson, 3);
        }
        if (jCheckBoxBenh4.isSelected()) {
            addDichTeController.addBenh(idPerson, 4);
        }
        if (jCheckBoxBenh5.isSelected()) {
            addDichTeController.addBenh(idPerson, 5);
        }
        if (jCheckBoxBenh6.isSelected()) {
            addDichTeController.addBenh(idPerson, 6);
        }
        if (jCheckBoxBenh7.isSelected()) {
            addDichTeController.addBenh(idPerson, 7);
        }
        if (jCheckBoxBenh8.isSelected()) {
            addDichTeController.addBenh(idPerson, 8);
        }
        if (jCheckBoxBenh9.isSelected()) {
            addDichTeController.addBenh(idPerson, 9);
        }

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
        jPanelKhaiBao = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTFChungMinhThu = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTFHoVaTen = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jDateCNgaySinh = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jCoBoxGioiTinh = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTFQuocTich = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTFSoDienThoai = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTFDiaChi = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jRadioBHYT_YES = new javax.swing.JRadioButton();
        jRadioBHYT_NO = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTFMaTheBHYT = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jButtonEditDichTe = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTFEmail = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jDateCNgayKhaiDichTe = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jRadioTXCovid_YES = new javax.swing.JRadioButton();
        jRadioTXCovid_NO = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jRadioTuVungDich_NO = new javax.swing.JRadioButton();
        jRadioTuVungDich_YES = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jCheckBoxTC1 = new javax.swing.JCheckBox();
        jCheckBoxTC2 = new javax.swing.JCheckBox();
        jCheckBoxTC3 = new javax.swing.JCheckBox();
        jCheckBoxTC6 = new javax.swing.JCheckBox();
        jCheckBoxTC5 = new javax.swing.JCheckBox();
        jCheckBoxTC4 = new javax.swing.JCheckBox();
        jCheckBoxTC7 = new javax.swing.JCheckBox();
        jCheckBoxTC8 = new javax.swing.JCheckBox();
        jCheckBoxTC9 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jCheckBoxBenh1 = new javax.swing.JCheckBox();
        jCheckBoxBenh2 = new javax.swing.JCheckBox();
        jCheckBoxBenh3 = new javax.swing.JCheckBox();
        jCheckBoxBenh6 = new javax.swing.JCheckBox();
        jCheckBoxBenh5 = new javax.swing.JCheckBox();
        jCheckBoxBenh4 = new javax.swing.JCheckBox();
        jCheckBoxBenh7 = new javax.swing.JCheckBox();
        jCheckBoxBenh8 = new javax.swing.JCheckBox();
        jCheckBoxBenh9 = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("CHỈNH SỬA THÔNG TIN KHAI BÁO DỊCH TỄ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(443, 443, 443)
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

        jPanelKhaiBao.setBackground(new java.awt.Color(255, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("CMT:");

        jTFChungMinhThu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTFChungMinhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTFChungMinhThuMouseEntered(evt);
            }
        });
        jTFChungMinhThu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFChungMinhThuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFChungMinhThuKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTFChungMinhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFChungMinhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Họ và tên:");

        jTFHoVaTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(jTFHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Ngày sinh:");

        jDateCNgaySinh.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(jDateCNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateCNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Giới tính:");

        jCoBoxGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Chọn giới tính -", "Nam", "Nữ", "Khác" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(37, 37, 37)
                .addComponent(jCoBoxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jCoBoxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Quốc tịch:");

        jTFQuocTich.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(27, 27, 27)
                .addComponent(jTFQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTFQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("SĐT:");

        jTFSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTFSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTFSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Địa chỉ:");

        jTFDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTFDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Thẻ BHYT:");

        jRadioBHYT_YES.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioBHYT_YES.setText("CÓ");

        jRadioBHYT_NO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioBHYT_NO.setText("KHÔNG");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(43, 43, 43)
                .addComponent(jRadioBHYT_YES)
                .addGap(18, 18, 18)
                .addComponent(jRadioBHYT_NO)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jRadioBHYT_YES)
                    .addComponent(jRadioBHYT_NO))
                .addContainerGap())
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Mã thẻ BHYT:");

        jTFMaTheBHYT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFMaTheBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTFMaTheBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButtonEditDichTe.setText("EDIT");
        jButtonEditDichTe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditDichTeActionPerformed(evt);
            }
        });

        jButton3.setText("CANCEL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEditDichTe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButtonEditDichTe))
                .addContainerGap())
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Email:");

        jTFEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Ngày khai:");

        jDateCNgayKhaiDichTe.setDateFormatString("dd/MM/yyyy");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Trong vòng 14 ngày qua anh/chị có:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("- Tiếp xúc với người bệnh Covid không ?");

        jRadioTXCovid_YES.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioTXCovid_YES.setText("CÓ");

        jRadioTXCovid_NO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioTXCovid_NO.setText("KHÔNG");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("- Đi về từ vùng dịch Covid không ?");

        jRadioTuVungDich_NO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioTuVungDich_NO.setText("KHÔNG");

        jRadioTuVungDich_YES.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioTuVungDich_YES.setText("CÓ");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setText("(*)");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setText("(*)");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("(*)");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(17, 17, 17)
                        .addComponent(jDateCNgayKhaiDichTe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jRadioTuVungDich_YES)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioTuVungDich_NO)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel24))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jRadioTXCovid_YES)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioTXCovid_NO)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel23)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateCNgayKhaiDichTe, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioTXCovid_YES)
                    .addComponent(jRadioTXCovid_NO)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioTuVungDich_YES)
                    .addComponent(jRadioTuVungDich_NO)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jCheckBoxTC1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxTC1.setText("Sốt");

        jCheckBoxTC2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxTC2.setText("Ho");

        jCheckBoxTC3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxTC3.setText("Khó thở");
        jCheckBoxTC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC3ActionPerformed(evt);
            }
        });

        jCheckBoxTC6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxTC6.setText("Mệt mỏi");
        jCheckBoxTC6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC6ActionPerformed(evt);
            }
        });

        jCheckBoxTC5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxTC5.setText("Đau họng");
        jCheckBoxTC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC5ActionPerformed(evt);
            }
        });

        jCheckBoxTC4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxTC4.setText("Viêm phổi");
        jCheckBoxTC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC4ActionPerformed(evt);
            }
        });

        jCheckBoxTC7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxTC7.setText("Buồn nôn");
        jCheckBoxTC7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC7ActionPerformed(evt);
            }
        });

        jCheckBoxTC8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxTC8.setText("Tiêu chảy");
        jCheckBoxTC8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC8ActionPerformed(evt);
            }
        });

        jCheckBoxTC9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxTC9.setText("Xuất huyết ngoài da");
        jCheckBoxTC9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC9ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Trong vòng 14 ngày qua anh/chị có dấu hiệu nào dưới đây ?");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jCheckBoxTC3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBoxTC6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxTC2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBoxTC1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxTC4)
                                    .addComponent(jCheckBoxTC5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxTC9)
                            .addComponent(jCheckBoxTC8)
                            .addComponent(jCheckBoxTC7))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxTC1)
                    .addComponent(jCheckBoxTC4)
                    .addComponent(jCheckBoxTC7))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxTC2)
                    .addComponent(jCheckBoxTC5)
                    .addComponent(jCheckBoxTC8))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxTC3)
                    .addComponent(jCheckBoxTC6)
                    .addComponent(jCheckBoxTC9))
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jCheckBoxBenh1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxBenh1.setText("Gan mãn tính");

        jCheckBoxBenh2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxBenh2.setText("Máu mãn tính");

        jCheckBoxBenh3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxBenh3.setText("Phổi mãn tính");

        jCheckBoxBenh6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxBenh6.setText("Huyết áp cao");

        jCheckBoxBenh5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxBenh5.setText("Tim mạch");

        jCheckBoxBenh4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxBenh4.setText("Thận mãn tính");

        jCheckBoxBenh7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxBenh7.setText("HIV/AIDS");

        jCheckBoxBenh8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxBenh8.setText("Tiểu đường");

        jCheckBoxBenh9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxBenh9.setText("Ung thư");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Hiện tại anh/chị có đang mắc các bệnh nào dưới đây ?");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxBenh1)
                            .addComponent(jCheckBoxBenh2)
                            .addComponent(jCheckBoxBenh3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jCheckBoxBenh5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBoxBenh4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBoxBenh6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBoxBenh8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBoxBenh7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBoxBenh9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxBenh1)
                    .addComponent(jCheckBoxBenh4)
                    .addComponent(jCheckBoxBenh7))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxBenh2)
                    .addComponent(jCheckBoxBenh5)
                    .addComponent(jCheckBoxBenh8))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxBenh3)
                    .addComponent(jCheckBoxBenh6)
                    .addComponent(jCheckBoxBenh9))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout jPanelKhaiBaoLayout = new javax.swing.GroupLayout(jPanelKhaiBao);
        jPanelKhaiBao.setLayout(jPanelKhaiBaoLayout);
        jPanelKhaiBaoLayout.setHorizontalGroup(
            jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKhaiBaoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelKhaiBaoLayout.createSequentialGroup()
                        .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelKhaiBaoLayout.setVerticalGroup(
            jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKhaiBaoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelKhaiBaoLayout.createSequentialGroup()
                        .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelKhaiBaoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelKhaiBaoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelKhaiBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelKhaiBaoLayout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(55, 55, 55)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelKhaiBao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelKhaiBao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFChungMinhThuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFChungMinhThuMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFChungMinhThuMouseEntered

    private void jTFChungMinhThuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFChungMinhThuKeyPressed

    }//GEN-LAST:event_jTFChungMinhThuKeyPressed

    private void jTFChungMinhThuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFChungMinhThuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFChungMinhThuKeyReleased

    private void jButtonEditDichTeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditDichTeActionPerformed

    }//GEN-LAST:event_jButtonEditDichTeActionPerformed

    public void editDichTe() throws SQLException {
        if (validateValueInForm()) {
            DichTeModel dichTeModel = new DichTeModel();
            dichTeModel.setID_PERSON(this.IDNhanKhau);
            // date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // theo chuan cua db dang sd
            dichTeModel.setNgayKhaiDichTeStr(dateFormat.format(jDateCNgayKhaiDichTe.getDate()));
            dichTeModel.setNgayKhaiDichTe(jDateCNgayKhaiDichTe.getDate());
            // radio
            if (jRadioTXCovid_YES.isSelected()) {
                dichTeModel.setTxCovid(1);
            } else if (jRadioTXCovid_NO.isSelected()) {
                dichTeModel.setTxCovid(0);
            }
            if (jRadioTuVungDich_YES.isSelected()) {
                dichTeModel.setTuVungDich(1);
            } else if (jRadioTuVungDich_NO.isSelected()) {
                dichTeModel.setTuVungDich(0);
            }

            try {
                if (editDichTeController.editDichTe(IDNhanKhau, dichTeModel)) {
                    // xóa trước các bảng nếu có
                    // Bắt đầu xóa !!!
                    try {
                        if (deleteDichTeController.removeTrieuChung(IDNhanKhau) && deleteDichTeController.removeBenh(IDNhanKhau)) {
//                    JOptionPane.showMessageDialog(rootPane, "Dich Te deleted successfully !", "Remove Khai Bao DichTe", JOptionPane.INFORMATION_MESSAGE);
//                    this.clearFiles();
                        } else {
//                            JOptionPane.showMessageDialog(rootPane, "Fail Delete Trieu Chung & Benh !", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage() + " - Enter the dichTe's id (Number) !", "Dich Te Id Error", JOptionPane.ERROR_MESSAGE);
                    }

                    this.addTrieuChung(IDNhanKhau);
                    this.addBenh(IDNhanKhau);
                    JOptionPane.showMessageDialog(rootPane, "Chỉnh sửa thành công!", "Infomation", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Lỗi. Không chỉnh sửa được !", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage() + " - Enter the person fields number !", "Person Fields Type Number Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public JButton getjButtonEditDichTe() {
        return jButtonEditDichTe;
    }

    public void setjButtonEditDichTe(JButton jButtonEditDichTe) {
        this.jButtonEditDichTe = jButtonEditDichTe;
    }

    // check cac gia tri duoc nhap vao form
    private boolean validateValueInForm() {
        // check null
        if ((!jRadioTXCovid_YES.isSelected() && !jRadioTXCovid_NO.isSelected())
                || (!jRadioTuVungDich_YES.isSelected() && !jRadioTuVungDich_NO.isSelected())) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập hết các trường bắt buộc", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đóng không ?", "Xác nhận thao tác", JOptionPane.YES_NO_OPTION) == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBoxTC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC3ActionPerformed

    private void jCheckBoxTC6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC6ActionPerformed

    private void jCheckBoxTC5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC5ActionPerformed

    private void jCheckBoxTC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC4ActionPerformed

    private void jCheckBoxTC7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC7ActionPerformed

    private void jCheckBoxTC8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC8ActionPerformed

    private void jCheckBoxTC9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonEditDichTe;
    private javax.swing.JCheckBox jCheckBoxBenh1;
    private javax.swing.JCheckBox jCheckBoxBenh2;
    private javax.swing.JCheckBox jCheckBoxBenh3;
    private javax.swing.JCheckBox jCheckBoxBenh4;
    private javax.swing.JCheckBox jCheckBoxBenh5;
    private javax.swing.JCheckBox jCheckBoxBenh6;
    private javax.swing.JCheckBox jCheckBoxBenh7;
    private javax.swing.JCheckBox jCheckBoxBenh8;
    private javax.swing.JCheckBox jCheckBoxBenh9;
    private javax.swing.JCheckBox jCheckBoxTC1;
    private javax.swing.JCheckBox jCheckBoxTC2;
    private javax.swing.JCheckBox jCheckBoxTC3;
    private javax.swing.JCheckBox jCheckBoxTC4;
    private javax.swing.JCheckBox jCheckBoxTC5;
    private javax.swing.JCheckBox jCheckBoxTC6;
    private javax.swing.JCheckBox jCheckBoxTC7;
    private javax.swing.JCheckBox jCheckBoxTC8;
    private javax.swing.JCheckBox jCheckBoxTC9;
    private javax.swing.JComboBox<String> jCoBoxGioiTinh;
    private com.toedter.calendar.JDateChooser jDateCNgayKhaiDichTe;
    private com.toedter.calendar.JDateChooser jDateCNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelKhaiBao;
    private javax.swing.JRadioButton jRadioBHYT_NO;
    private javax.swing.JRadioButton jRadioBHYT_YES;
    private javax.swing.JRadioButton jRadioTXCovid_NO;
    private javax.swing.JRadioButton jRadioTXCovid_YES;
    private javax.swing.JRadioButton jRadioTuVungDich_NO;
    private javax.swing.JRadioButton jRadioTuVungDich_YES;
    private javax.swing.JTextField jTFChungMinhThu;
    private javax.swing.JTextField jTFDiaChi;
    private javax.swing.JTextField jTFEmail;
    private javax.swing.JTextField jTFHoVaTen;
    private javax.swing.JTextField jTFMaTheBHYT;
    private javax.swing.JTextField jTFQuocTich;
    private javax.swing.JTextField jTFSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
