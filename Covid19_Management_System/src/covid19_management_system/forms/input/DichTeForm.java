/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.forms.input;

import covid19_management_system.MY_CONNECTION;
import covid19_management_system.entity.DichTe;
import covid19_management_system.entity.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author HieuPhung
 */
public class DichTeForm extends javax.swing.JFrame {

    /**
     * Creates new form DichTe
     */
    Person person = new Person();
    DichTe dichTe = new DichTe();
    MY_CONNECTION my_connection = new MY_CONNECTION();
    ButtonGroup bgBHYT = new ButtonGroup();
    ButtonGroup bgTXBenh = new ButtonGroup();
    ButtonGroup bgTuVungDich = new ButtonGroup();

    public DichTeForm() {
        initComponents();
        
        this.setTitle("Khai báo thông tin dịch tễ");
        setSize(1238, 880);

        bgBHYT.add(jRadioButtonPersonBHYT_YES);
        bgBHYT.add(jRadioButtonPersonBHYT_NO);

        bgTXBenh.add(jRBTXBenh_YES);
        bgTXBenh.add(jRBTXBenh_NO);

        bgTuVungDich.add(jRBTuVungDich_YES);
        bgTuVungDich.add(jRBTuVungDich_NO);
    }
    
    public void clearFiles() {
        
        // remove text from all jtextfields
        jTextPersonCMT.setText(null);
        jTextPersonName.setText(null);
        jDateChooserPersonBirthday.setDate(new Date());
        jComboBoxPersonGender.setSelectedIndex(0);
        jTextFieldPersonPhone.setText(null);
        jTextFieldPersonGmail.setText(null);
        jTextAreaPersonAddress.setText(null);
        bgBHYT.clearSelection();
        jTextFieldPersonMSYT.setText(null);
        jDateNgayKhai.setDate(new Date());
        
        bgTXBenh.clearSelection();
        bgTuVungDich.clearSelection();
        jCheckBoxTC1.setSelected(false);
        jCheckBoxTC2.setSelected(false);
        jCheckBoxTC3.setSelected(false);
        jCheckBoxTC4.setSelected(false);
        jCheckBoxTC5.setSelected(false);
        jCheckBoxTC6.setSelected(false);
        jCheckBoxTC7.setSelected(false);
        jCheckBoxTC8.setSelected(false);
        jCheckBoxTC9.setSelected(false);
        
        jCheckBoxBenh1.setSelected(false);
        jCheckBoxBenh2.setSelected(false);
        jCheckBoxBenh3.setSelected(false);
        jCheckBoxBenh4.setSelected(false);
        jCheckBoxBenh5.setSelected(false);
        jCheckBoxBenh6.setSelected(false);
        jCheckBoxBenh7.setSelected(false);
        jCheckBoxBenh8.setSelected(false);
        jCheckBoxBenh9.setSelected(false);
        
        
    }
    
    public void addTrieuChung(int idPerson){
        if (jCheckBoxTC1.isSelected()) {
            dichTe.addTrieuChung(idPerson, 1);
        }
        if (jCheckBoxTC2.isSelected()) {
            dichTe.addTrieuChung(idPerson, 2);
        }
        if (jCheckBoxTC3.isSelected()) {
            dichTe.addTrieuChung(idPerson, 3);
        }
        if (jCheckBoxTC4.isSelected()) {
            dichTe.addTrieuChung(idPerson, 4);
        }
        if (jCheckBoxTC5.isSelected()) {
            dichTe.addTrieuChung(idPerson, 5);
        }
        if (jCheckBoxTC6.isSelected()) {
            dichTe.addTrieuChung(idPerson, 6);
        }
        if (jCheckBoxTC7.isSelected()) {
            dichTe.addTrieuChung(idPerson, 7);
        }
        if (jCheckBoxTC8.isSelected()) {
            dichTe.addTrieuChung(idPerson, 8);
        }
        if (jCheckBoxTC9.isSelected()) {
            dichTe.addTrieuChung(idPerson, 9);
        }
        
    }
    
    public void addBenh(int idPerson){
        if (jCheckBoxBenh1.isSelected()) {
            dichTe.addBenh(idPerson, 1);
        }
        if (jCheckBoxBenh2.isSelected()) {
            dichTe.addBenh(idPerson, 2);
        }
        if (jCheckBoxBenh3.isSelected()) {
            dichTe.addBenh(idPerson, 3);
        }
        if (jCheckBoxBenh4.isSelected()) {
            dichTe.addBenh(idPerson, 4);
        }
        if (jCheckBoxBenh5.isSelected()) {
            dichTe.addBenh(idPerson, 5);
        }
        if (jCheckBoxBenh6.isSelected()) {
            dichTe.addBenh(idPerson, 6);
        }
        if (jCheckBoxBenh7.isSelected()) {
            dichTe.addBenh(idPerson, 7);
        }
        if (jCheckBoxBenh8.isSelected()) {
            dichTe.addBenh(idPerson, 8);
        }
        if (jCheckBoxBenh9.isSelected()) {
            dichTe.addBenh(idPerson, 9);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldPersonPhone = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldPersonGmail = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jRadioButtonPersonBHYT_YES = new javax.swing.JRadioButton();
        jRadioButtonPersonBHYT_NO = new javax.swing.JRadioButton();
        jComboBoxPersonGender = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPersonAddress = new javax.swing.JTextArea();
        jButtonEDIT = new javax.swing.JButton();
        jButtonADD = new javax.swing.JButton();
        jButtonREMOVE = new javax.swing.JButton();
        jButtonCLEAR = new javax.swing.JButton();
        jTextPersonCMT = new javax.swing.JTextField();
        jDateNgayKhai = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButtonSEARCH = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextPersonName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jRBTXBenh_YES = new javax.swing.JRadioButton();
        jRBTXBenh_NO = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jRBTuVungDich_YES = new javax.swing.JRadioButton();
        jRBTuVungDich_NO = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jCheckBoxTC1 = new javax.swing.JCheckBox();
        jCheckBoxTC2 = new javax.swing.JCheckBox();
        jCheckBoxTC3 = new javax.swing.JCheckBox();
        jCheckBoxTC4 = new javax.swing.JCheckBox();
        jCheckBoxTC5 = new javax.swing.JCheckBox();
        jCheckBoxTC6 = new javax.swing.JCheckBox();
        jCheckBoxTC7 = new javax.swing.JCheckBox();
        jCheckBoxTC8 = new javax.swing.JCheckBox();
        jCheckBoxTC9 = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jCheckBoxBenh1 = new javax.swing.JCheckBox();
        jCheckBoxBenh2 = new javax.swing.JCheckBox();
        jCheckBoxBenh3 = new javax.swing.JCheckBox();
        jCheckBoxBenh4 = new javax.swing.JCheckBox();
        jCheckBoxBenh5 = new javax.swing.JCheckBox();
        jCheckBoxBenh6 = new javax.swing.JCheckBox();
        jCheckBoxBenh7 = new javax.swing.JCheckBox();
        jCheckBoxBenh8 = new javax.swing.JCheckBox();
        jCheckBoxBenh9 = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldPersonMSYT = new javax.swing.JTextField();
        jDateChooserPersonBirthday = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 0, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KHAI BÁO DỊCH TỄ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, 56));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1238, 100));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CMT/CCCD:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 173, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Số điện thoại: ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 339, -1, -1));

        jTextFieldPersonPhone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextFieldPersonPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 336, 298, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Gmail: ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 380, -1, -1));

        jTextFieldPersonGmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextFieldPersonGmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 377, 298, -1));

        jPanel4.setBackground(new java.awt.Color(51, 0, 204));

        jRadioButtonPersonBHYT_YES.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonPersonBHYT_YES.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonPersonBHYT_YES.setText("Có");

        jRadioButtonPersonBHYT_NO.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonPersonBHYT_NO.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonPersonBHYT_NO.setText("Không");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jRadioButtonPersonBHYT_YES)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonPersonBHYT_NO)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButtonPersonBHYT_YES)
                .addComponent(jRadioButtonPersonBHYT_NO))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 591, -1, -1));

        jComboBoxPersonGender.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBoxPersonGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Chọn giới tính -", "Nam", "Nữ", "Khác" }));
        jComboBoxPersonGender.setToolTipText("");
        jPanel1.add(jComboBoxPersonGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 301, 170, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Địa chỉ: ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 423, -1, -1));

        jTextAreaPersonAddress.setColumns(20);
        jTextAreaPersonAddress.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextAreaPersonAddress.setRows(5);
        jScrollPane1.setViewportView(jTextAreaPersonAddress);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 423, 298, 150));

        jButtonEDIT.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButtonEDIT.setText("Sửa");
        jButtonEDIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEDITActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEDIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 756, 86, 45));

        jButtonADD.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButtonADD.setText("Thêm mới");
        jButtonADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonADDActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 756, 141, 45));

        jButtonREMOVE.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButtonREMOVE.setText("Xóa");
        jButtonREMOVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonREMOVEActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonREMOVE, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 756, 87, 45));

        jButtonCLEAR.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButtonCLEAR.setText("Làm mới");
        jButtonCLEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCLEARActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCLEAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(826, 756, 145, 45));

        jTextPersonCMT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextPersonCMT, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 170, 298, -1));

        jDateNgayKhai.setDateFormatString("dd/MM/yyyy");
        jDateNgayKhai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jDateNgayKhai, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 690, 290, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Thẻ BHYT: ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 590, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Ngày khai:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 690, -1, -1));

        jButtonSEARCH.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButtonSEARCH.setText("Tìm kiếm");
        jButtonSEARCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSEARCHActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSEARCH, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 756, 141, 45));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("THÔNG TIN NGƯỜI KHAI:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 119, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Họ và tên:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 205, -1, -1));

        jTextPersonName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextPersonName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPersonNameActionPerformed(evt);
            }
        });
        jPanel1.add(jTextPersonName, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 212, 298, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ngày sinh:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 247, -1, 36));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Giới tính:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 301, -1, 28));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("KHAI BÁO DỊCH TỄ:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 119, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Trong vòng 14 ngày qua anh/chị có:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 161, -1, 28));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Tiếp xúc với người bệnh Covid-19 ?");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jRBTXBenh_YES.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jRBTXBenh_YES.setText("Có");

        jRBTXBenh_NO.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jRBTXBenh_NO.setText("Không");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jRBTXBenh_YES)
                .addGap(18, 18, 18)
                .addComponent(jRBTXBenh_NO))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRBTXBenh_YES)
                .addComponent(jRBTXBenh_NO))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Đi về từ vùng dịch Covid-19 ?");

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jRBTuVungDich_YES.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jRBTuVungDich_YES.setText("Có");

        jRBTuVungDich_NO.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jRBTuVungDich_NO.setText("Không");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jRBTuVungDich_YES)
                .addGap(18, 18, 18)
                .addComponent(jRBTuVungDich_NO))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRBTuVungDich_YES)
                .addComponent(jRBTuVungDich_NO))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 194, -1, -1));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBoxTC1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxTC1.setText("Sốt");
        jPanel7.add(jCheckBoxTC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 83, -1));

        jCheckBoxTC2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxTC2.setText("Ho");
        jPanel7.add(jCheckBoxTC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 83, -1));

        jCheckBoxTC3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxTC3.setText("Khó thở");
        jCheckBoxTC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC3ActionPerformed(evt);
            }
        });
        jPanel7.add(jCheckBoxTC3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jCheckBoxTC4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxTC4.setText("Viêm phổi");
        jCheckBoxTC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC4ActionPerformed(evt);
            }
        });
        jPanel7.add(jCheckBoxTC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        jCheckBoxTC5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxTC5.setText("Đau họng");
        jCheckBoxTC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC5ActionPerformed(evt);
            }
        });
        jPanel7.add(jCheckBoxTC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 101, -1));

        jCheckBoxTC6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxTC6.setText("Mệt mỏi");
        jCheckBoxTC6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC6ActionPerformed(evt);
            }
        });
        jPanel7.add(jCheckBoxTC6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 101, -1));

        jCheckBoxTC7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxTC7.setText("Buồn nôn");
        jCheckBoxTC7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC7ActionPerformed(evt);
            }
        });
        jPanel7.add(jCheckBoxTC7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jCheckBoxTC8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxTC8.setText("Tiêu chảy");
        jCheckBoxTC8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC8ActionPerformed(evt);
            }
        });
        jPanel7.add(jCheckBoxTC8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        jCheckBoxTC9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxTC9.setText("Xuất huyết ngoài da");
        jCheckBoxTC9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTC9ActionPerformed(evt);
            }
        });
        jPanel7.add(jCheckBoxTC9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 344, 490, 170));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Hiện anh/chị có mắc các bệnh nào dưới đây:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 532, 493, -1));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jCheckBoxBenh1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxBenh1.setText("Gan mãn tính");

        jCheckBoxBenh2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxBenh2.setText("Máu mãn tính");

        jCheckBoxBenh3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxBenh3.setText("Phổi mãn tính");

        jCheckBoxBenh4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxBenh4.setText("Thận mãn tính");

        jCheckBoxBenh5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxBenh5.setText("Tim mạch");

        jCheckBoxBenh6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxBenh6.setText("Huyết áp cao");

        jCheckBoxBenh7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxBenh7.setText("HIV/AIDS");

        jCheckBoxBenh8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxBenh8.setText("Tiểu đường");

        jCheckBoxBenh9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBoxBenh9.setText("Ung thư");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBoxBenh3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxBenh2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxBenh1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxBenh4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxBenh5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxBenh6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBoxBenh8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxBenh7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxBenh9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxBenh1)
                    .addComponent(jCheckBoxBenh4)
                    .addComponent(jCheckBoxBenh7))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxBenh2)
                    .addComponent(jCheckBoxBenh5)
                    .addComponent(jCheckBoxBenh8))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxBenh3)
                    .addComponent(jCheckBoxBenh6)
                    .addComponent(jCheckBoxBenh9))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 561, 493, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Trong vòng 14 ngày qua anh/chị có dấu hiệu nào dưới đây:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 315, 493, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Mã số thẻ: ");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 638, -1, -1));

        jTextFieldPersonMSYT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextFieldPersonMSYT, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 635, 299, -1));

        jDateChooserPersonBirthday.setDateFormatString("dd/MM/yyyy");
        jDateChooserPersonBirthday.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jDateChooserPersonBirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 253, 298, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextPersonNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPersonNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPersonNameActionPerformed

    private void jCheckBoxTC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC3ActionPerformed

    private void jCheckBoxTC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC4ActionPerformed

    private void jCheckBoxTC5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC5ActionPerformed

    private void jCheckBoxTC6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC6ActionPerformed

    private void jCheckBoxTC7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC7ActionPerformed

    private void jCheckBoxTC8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC8ActionPerformed

    private void jCheckBoxTC9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTC9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTC9ActionPerformed

    private void jButtonSEARCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSEARCHActionPerformed

        String cmt = null;
        cmt = jTextPersonCMT.getText().trim();
        if (cmt.trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập CMT/CCCD trước khi nhấn \"Tìm kiếm\"", "CMT/CCCD đang trống", 2);
        } else {
            // search the person with person's CMT
            if (person.searchPerson(cmt) == false) {
                JOptionPane.showMessageDialog(rootPane, "Cư dân có CMT: " + cmt + " chưa được khai báo thông tin cá nhân !\nHãy tiến hành thêm mới thông tin cư dân trong mẫu \"Thông tin cư dân\"", "Tìm kiếm thông tin cư dân", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Thông tin cá nhân
                String name = null;
                String birthday = null;
                String gender = null;
                String bhyt = null;
                String bhyt_num = null;
                String phone = null;
                String email = null;
                String address = null;

                // Thông tin dịch tễ
                String ngayKhai = null;
                String txBenh = null;
                String tuVungDich = null;

                // Thông tin triệu chứng
                
                
                // Thông tin cách ly
                
                
//                ----- get ID Person -----
                int idPerson = 0;
                // connect to database to get data with cmt/cccd
                PreparedStatement psID;
                ResultSet rsID;
                String searchQueryID = "SELECT `id` FROM `people` WHERE `cmt`=?";
                try {
                    psID = my_connection.createConnection().prepareStatement(searchQueryID);
                    psID.setString(1, cmt);

                    rsID = psID.executeQuery();

                    while (rsID.next()) {
                        idPerson = rsID.getInt(1);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
//                ----- END get ID -----

                // 1. Lấy ra thông tin cá nhân
                PreparedStatement ps1;
                ResultSet rs1;
                String searchQuery1 = "SELECT `name`,`birthday`,`gender`,`bhyt`,`bhyt_num`,`phone`,`email`,`address` FROM `people` WHERE `cmt`=?";
                try {
                    ps1 = my_connection.createConnection().prepareStatement(searchQuery1);
                    ps1.setString(1, cmt);

                    rs1 = ps1.executeQuery();

                    while (rs1.next()) {
                        name = rs1.getString(1);
                        birthday = rs1.getString(2);
                        gender = rs1.getString(3);
                        bhyt = rs1.getString(4);
                        bhyt_num = rs1.getString(5);
                        phone = rs1.getString(6);
                        email = rs1.getString(7);
                        address = rs1.getString(8);

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(PersonForm.class.getName()).log(Level.SEVERE, null, ex);
                }

                jTextPersonName.setText(name);

                try {
                    Date dateIn = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                    jDateChooserPersonBirthday.setDate(dateIn);
                } catch (ParseException ex) {
                    Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
                }

                jComboBoxPersonGender.setSelectedItem(gender);

                if (bhyt.equals("Có")) {
                    jRadioButtonPersonBHYT_YES.setSelected(true);
                } else if (bhyt.equals("Không")) {
                    jRadioButtonPersonBHYT_NO.setSelected(true);
                }

                jTextFieldPersonMSYT.setText(bhyt_num);
                jTextFieldPersonPhone.setText(phone);
                jTextFieldPersonGmail.setText(email);
                jTextAreaPersonAddress.setText(address);

                // 2. Lấy thông tin dịch tễ
                PreparedStatement ps2;
                ResultSet rs2;
                String searchQuery2 = "SELECT `ngay_khai`, `tx_benh`, `tu_vung_dich` FROM `dich_te` WHERE `id_person`=?";
                try {
                    ps2 = my_connection.createConnection().prepareStatement(searchQuery2);
                    ps2.setInt(1, idPerson);

                    rs2 = ps2.executeQuery();

                    while (rs2.next()) {
                        ngayKhai = rs2.getString(1);
                        txBenh = rs2.getString(2);
                        tuVungDich = rs2.getString(3);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    Date dateIn = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(ngayKhai);
                    jDateNgayKhai.setDate(dateIn);
                } catch (ParseException ex) {
                    Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (txBenh.trim().equals("Có")) {
                    jRBTXBenh_YES.setSelected(true);
                } else if (txBenh.trim().equals("Không")) {
                    jRBTXBenh_NO.setSelected(true);
                }

                if (tuVungDich.trim().equals("Có")) {
                    jRBTuVungDich_YES.setSelected(true);
                } else if (tuVungDich.trim().equals("Không")) {
                    jRBTuVungDich_NO.setSelected(true);
                }

                // 3. Lấy thông tin triệu chứng
                PreparedStatement ps3;
                ResultSet rs3;
                String searchQuery3 = "SELECT `trieu_chung` FROM `khai_trieu_chung` WHERE `id_person`=?";
                try {
                    ps3 = my_connection.createConnection().prepareStatement(searchQuery3);
                    ps3.setInt(1, idPerson);

                    rs3 = ps3.executeQuery();

                    while (rs3.next()) {
                        buildTC(rs3.getInt(1));
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // 3. Lấy thông tin bệnh
                PreparedStatement ps4;
                ResultSet rs4;
                String searchQuery4 = "SELECT `ma_benh` FROM `khai_benh` WHERE `id_person`=?";
                try {
                    ps4 = my_connection.createConnection().prepareStatement(searchQuery4);
                    ps4.setInt(1, idPerson);

                    rs4 = ps4.executeQuery();

                    while (rs4.next()) {
                        buildBenh(rs4.getInt(1));
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        }


    }//GEN-LAST:event_jButtonSEARCHActionPerformed

    private void jButtonADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonADDActionPerformed

        // lấy idPerson
        int idPerson = 0;
        String cmt = null;
        cmt = jTextPersonCMT.getText();
        PreparedStatement ps;
        ResultSet rs;
        String searchQuery = "SELECT `id` FROM `people` WHERE `cmt`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(searchQuery);
            ps.setString(1, cmt);

            rs = ps.executeQuery();

            while (rs.next()) {
                idPerson = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        // ----- lấy xong idPerson -----

        try {

            // date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // theo chuan cua db dang sd
            String ngayKhai = dateFormat.format(jDateNgayKhai.getDate());
            // radio
            String txBenh = null;
            if (jRBTXBenh_YES.isSelected()) {
                txBenh = "Có";
            }
            if (jRBTXBenh_NO.isSelected()) {
                txBenh = "Không";
            }

            String tuVungDich = null;
            if (jRBTuVungDich_YES.isSelected()) {
                tuVungDich = "Có";
            }
            if (jRBTuVungDich_NO.isSelected()) {
                tuVungDich = "Không";
            }

            if (dichTe.addDichTe(idPerson, ngayKhai, txBenh, tuVungDich)) {
                
                this.addTrieuChung(idPerson);
                this.addBenh(idPerson);
                
                JOptionPane.showMessageDialog(rootPane, "New Dich Te added successfully !", "Add Dich Te", JOptionPane.INFORMATION_MESSAGE);
                this.clearFiles();

            } else {
                JOptionPane.showMessageDialog(rootPane, "Dich Te Form not added !", "Add Dich Te Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage() + " - Enter fields number !", "Fields Type Number Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonADDActionPerformed

    private void jButtonCLEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCLEARActionPerformed
        this.clearFiles();
    }//GEN-LAST:event_jButtonCLEARActionPerformed

    private void jButtonREMOVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonREMOVEActionPerformed
        int idPerson = 0;
        // connect to database to get data with cmt/cccd
        String cmt = null;
        cmt = jTextPersonCMT.getText();
        PreparedStatement ps;
        ResultSet rs;
        String searchQuery = "SELECT `id` FROM `people` WHERE `cmt`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(searchQuery);
            ps.setString(1, cmt);

            rs = ps.executeQuery();

            while (rs.next()) {
                idPerson = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        // => Đến đây là có idPerson -----
        
        // Find idDichTe in khai_dich_te database
        int idDichTe = 0;
        String searchQueryDichTe = "SELECT `id` FROM `dich_te` WHERE `id_person`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(searchQueryDichTe);
            ps.setInt(1, idPerson);

            rs = ps.executeQuery();

            while (rs.next()) {
                idDichTe = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Bắt đầu xóa !!!
        try {

            // trong check if duoi, da co edit database !!!
            if (dichTe.removeDichTe(idDichTe)) {
                dichTe.removeTrieuChung(idPerson);
                dichTe.removeBenh(idPerson);
                JOptionPane.showMessageDialog(rootPane, "Dich Te deleted successfully !", "Remove Khai Bao DichTe", JOptionPane.INFORMATION_MESSAGE);
                this.clearFiles();
                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Dich Te not deleted !", "Remove Dich Te Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage() + " - Enter the dichTe's id (Number) !", "Dich Te Id Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonREMOVEActionPerformed

    private void jButtonEDITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEDITActionPerformed
        
        // get idPerson from dichTe table
        int idPerson = 0;
        String cmt_temp = null;
        cmt_temp = jTextPersonCMT.getText();
        PreparedStatement ps;
        ResultSet rs;
        String searchQuery = "SELECT `id` FROM `people` WHERE `cmt`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(searchQuery);
            ps.setString(1, cmt_temp);
            rs = ps.executeQuery();

            while (rs.next()) {
                idPerson = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DichTeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        // => Đến đây đã có idPerson -----
        
        // edit the dich_te database
        try {

            // date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // theo chuan cua db dang sd
            String ngayKhai = dateFormat.format(jDateNgayKhai.getDate());
            // radio
            String txBenh = null;
            if (jRBTXBenh_YES.isSelected()) {
                txBenh = "Có";
            }
            if (jRBTXBenh_NO.isSelected()) {
                txBenh = "Không";
            }

            String tuVungDich = null;
            if (jRBTuVungDich_YES.isSelected()) {
                tuVungDich = "Có";
            }
            if (jRBTuVungDich_NO.isSelected()) {
                tuVungDich = "Không";
            }
            
            // xóa trước các bảng nếu có
            // Bắt đầu xóa !!!
            try {
                // trong check if duoi, da co edit database !!!
                if (dichTe.removeTrieuChung(idPerson) && dichTe.removeBenh(idPerson)) {
//                    JOptionPane.showMessageDialog(rootPane, "Dich Te deleted successfully !", "Remove Khai Bao DichTe", JOptionPane.INFORMATION_MESSAGE);
//                    this.clearFiles();

                } else {
//                    JOptionPane.showMessageDialog(rootPane, "Dich Te not deleted !", "Remove Dich Te Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(rootPane, ex.getMessage() + " - Enter the dichTe's id (Number) !", "Dich Te Id Error", JOptionPane.ERROR_MESSAGE);
            }

            if (dichTe.editDichTe(idPerson, ngayKhai, txBenh, tuVungDich)) {
                // thêm mới
                this.addTrieuChung(idPerson);
                this.addBenh(idPerson);
                
                JOptionPane.showMessageDialog(rootPane, "Dich Te update successfully !", "Update Dich Te", JOptionPane.INFORMATION_MESSAGE);
                this.clearFiles();

            } else {
                JOptionPane.showMessageDialog(rootPane, "Dich Te Form not updated !", "Update Dich Te Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage() + " - Enter fields number !", "Fields Type Number Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        
        
    }//GEN-LAST:event_jButtonEDITActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DichTeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DichTeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DichTeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DichTeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DichTeForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonADD;
    private javax.swing.JButton jButtonCLEAR;
    private javax.swing.JButton jButtonEDIT;
    private javax.swing.JButton jButtonREMOVE;
    private javax.swing.JButton jButtonSEARCH;
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
    private javax.swing.JComboBox<String> jComboBoxPersonGender;
    private com.toedter.calendar.JDateChooser jDateChooserPersonBirthday;
    private com.toedter.calendar.JDateChooser jDateNgayKhai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRBTXBenh_NO;
    private javax.swing.JRadioButton jRBTXBenh_YES;
    private javax.swing.JRadioButton jRBTuVungDich_NO;
    private javax.swing.JRadioButton jRBTuVungDich_YES;
    private javax.swing.JRadioButton jRadioButtonPersonBHYT_NO;
    private javax.swing.JRadioButton jRadioButtonPersonBHYT_YES;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaPersonAddress;
    private javax.swing.JTextField jTextFieldPersonGmail;
    private javax.swing.JTextField jTextFieldPersonMSYT;
    private javax.swing.JTextField jTextFieldPersonPhone;
    private javax.swing.JTextField jTextPersonCMT;
    private javax.swing.JTextField jTextPersonName;
    // End of variables declaration//GEN-END:variables
}
