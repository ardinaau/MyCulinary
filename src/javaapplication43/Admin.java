/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication43;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ardina
 */
public class Admin extends javax.swing.JFrame {

    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form Admin
     */
    CardLayout cardLayout;

    public Admin() {
        initComponents();
        cardLayout = (CardLayout) (pnlCard.getLayout());
        usertypeLb.setText(LoginSession.Usertype);
        nicknameLb.setText(LoginSession.Nickname);
        updateCombo();
        fetchData();
        getHarga();
        getPemesanan();

    }
    //sama seperti yang di pemesanann
    private void updateCombo() {
        String sql = "Select * from makanan";
        try {
            Connection con = MySqlConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                comboBoxMakanan.addItem(rs.getString("id_makanan") + ") " + rs.getString("nama"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
        }
    }
//sama yang kayak di pemesanann
    private void getHarga() {
        String makananItem = comboBoxMakanan.getSelectedItem().toString();
        String makanan = makananItem.split("\\)", 2)[0];

        String sql = "SELECT * from makanan where id_makanan =  " + makanan;
        try {
            Connection con = MySqlConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
//                comboBoxProfesi.addItem(rs.getString("id_kategori"));
                hargaTxt.setText(rs.getString("harga"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
        }
    }
//sama kayak yang di pemesanann
    private void getTotal() {
        try {
            int harga = Integer.parseInt(hargaTxt.getText());
            int jumlah = Integer.parseInt(jumlahTxt.getText());
            double total = (harga * jumlah);
            totalTxt.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    //memanggil data dari tabel dan mencocokan data tiap kolom menggunakan array dan looping for
    private void fetchData() {
        ArrayList<makananList> list = makananList();
        DefaultTableModel model = (DefaultTableModel) makananData.getModel();
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getId();
            row[1] = list.get(i).getNama_makanan();
            row[2] = list.get(i).getHarga();
            row[3] = list.get(i).getJumlah();
            row[4] = list.get(i).getTotal();
            row[5] = list.get(i).getNo_meja();
            model.addRow(row);
        }
    }

    //memanggil data dari database total dari pesanan hari ini
    public void getPemesanan() {
        try {
            Connection con = MySqlConnection.getConnection();
            String query = "SELECT COUNT(pemesanan.id_pemesanan) as id_pemesanan FROM pemesanan INNER JOIN makanan ON makanan.id_makanan = pemesanan.id_pemesanan ";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                labelTotal.setText(rs.getString("id_pemesanan"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    //memanggil data dari database yang nanti akan ditampilkan ke dalam tabel
    public ArrayList<makananList> makananList() {
        ArrayList<makananList> makanList = new ArrayList<>();
        try {
            Connection con = MySqlConnection.getConnection();
            String sql = "select pemesanan.id_pemesanan,makanan.nama,makanan.harga, pemesanan.jumlah,pemesanan.total,pemesanan.no_meja from pemesanan inner join makanan on makanan.id_makanan = pemesanan.id_makanan ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            makananList makan;
            while (rs.next()) {
                makan = new makananList(rs.getInt("id_pemesanan"), rs.getString("nama"), rs.getInt("harga"), rs.getInt("jumlah"), rs.getInt("total"), rs.getInt("no_meja"));
                makanList.add(makan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return makanList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        pnlCard = new javax.swing.JPanel();
        pnlCard1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nicknameLb = new javax.swing.JLabel();
        usertypeLb = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        pnlCard2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        makananData = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        comboBoxMakanan = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        hargaTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jumlahTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        totalTxt = new javax.swing.JTextField();
        deleteBtn = new javax.swing.JButton();
        tambahBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        noMejaTxt = new javax.swing.JTextField();
        hitungBtn = new javax.swing.JButton();
        pnlCard3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jSplitPane1.setLeftComponent(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));

        jButton1.setText("Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Daftar Pesanan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Selamat Datang Admin");

        jButton3.setText("Logout");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jLabel11))
                .addGap(36, 36, 36))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jButton1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addGap(94, 94, 94)
                .addComponent(jButton1)
                .addGap(34, 34, 34)
                .addComponent(jButton2)
                .addGap(35, 35, 35)
                .addComponent(jButton3)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel5);

        pnlCard.setLayout(new java.awt.CardLayout());

        pnlCard1.setBackground(new java.awt.Color(255, 204, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nickname :");

        nicknameLb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        usertypeLb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("UserType :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Jumlah Pesanan Masuk");

        labelTotal.setBackground(new java.awt.Color(255, 255, 255));
        labelTotal.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        labelTotal.setForeground(new java.awt.Color(51, 0, 51));
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlCard1Layout = new javax.swing.GroupLayout(pnlCard1);
        pnlCard1.setLayout(pnlCard1Layout);
        pnlCard1Layout.setHorizontalGroup(
            pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard1Layout.createSequentialGroup()
                .addContainerGap(447, Short.MAX_VALUE)
                .addGroup(pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard1Layout.createSequentialGroup()
                        .addGroup(pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usertypeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nicknameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(114, 114, 114))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(309, 309, 309))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard1Layout.createSequentialGroup()
                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(381, 381, 381))))
        );
        pnlCard1Layout.setVerticalGroup(
            pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nicknameLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usertypeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        pnlCard.add(pnlCard1, "pnlCard1");

        pnlCard2.setBackground(new java.awt.Color(255, 153, 255));

        makananData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nama Makanan", "Harga", "Jumlah", "Total", "No Meja"
            }
        ));
        makananData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                makananDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(makananData);

        jLabel7.setText("Nama Makanan");

        comboBoxMakanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBoxMakananMouseClicked(evt);
            }
        });
        comboBoxMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMakananActionPerformed(evt);
            }
        });

        jLabel4.setText("Harga ");

        hargaTxt.setEditable(false);
        hargaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaTxtActionPerformed(evt);
            }
        });

        jLabel5.setText("Jumlah");

        jLabel8.setText("Total");

        totalTxt.setEditable(false);

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        tambahBtn.setText("Tambah");
        tambahBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        jLabel9.setText("No Meja");

        hitungBtn.setText("Hitung");
        hitungBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCard2Layout = new javax.swing.GroupLayout(pnlCard2);
        pnlCard2.setLayout(pnlCard2Layout);
        pnlCard2Layout.setHorizontalGroup(
            pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard2Layout.createSequentialGroup()
                .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard2Layout.createSequentialGroup()
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCard2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)))
                            .addGroup(pnlCard2Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(tambahBtn)))
                        .addGap(28, 28, 28)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCard2Layout.createSequentialGroup()
                                .addComponent(resetBtn)
                                .addGap(27, 27, 27)
                                .addComponent(hitungBtn))
                            .addComponent(comboBoxMakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jumlahTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(totalTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(hargaTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(noMejaTxt)))
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn)))
                .addContainerGap())
        );
        pnlCard2Layout.setVerticalGroup(
            pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard2Layout.createSequentialGroup()
                .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCard2Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(comboBoxMakanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(hargaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jumlahTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(noMejaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambahBtn)
                            .addComponent(resetBtn)
                            .addComponent(hitungBtn))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBtn)
                    .addComponent(editBtn))
                .addGap(56, 56, 56))
        );

        pnlCard.add(pnlCard2, "pnlCard2");

        javax.swing.GroupLayout pnlCard3Layout = new javax.swing.GroupLayout(pnlCard3);
        pnlCard3.setLayout(pnlCard3Layout);
        pnlCard3Layout.setHorizontalGroup(
            pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 991, Short.MAX_VALUE)
        );
        pnlCard3Layout.setVerticalGroup(
            pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );

        pnlCard.add(pnlCard3, "pnlCard3");

        jSplitPane1.setRightComponent(pnlCard);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 30)); // NOI18N
        jLabel1.setText("Welcome Admin!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCard, "pnlCard1");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCard, "pnlCard2");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        LoginAdmin login = new LoginAdmin();
        Logout.logOut(this, login);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void comboBoxMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMakananActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_comboBoxMakananActionPerformed

    private void hargaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaTxtActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        //reset form untuk mengkosongkan data form
        hargaTxt.setText("");
        totalTxt.setText("");
        jumlahTxt.setText("");
        noMejaTxt.setText("");
        comboBoxMakanan.setSelectedItem("");
    }//GEN-LAST:event_resetBtnActionPerformed

    private void tambahBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBtnActionPerformed
        // TODO add your handling code here:
        
        //sama kayak yang di pemesanan
        String makananItem = comboBoxMakanan.getSelectedItem().toString();
        String makanan = makananItem.split("\\)", 2)[0];

        try {
            Connection con = MySqlConnection.getConnection();

            pst = con.prepareStatement("INSERT INTO pemesanan (id_makanan, harga, jumlah, total, no_meja) VALUES(?,?,?,?,?)");

            if (jumlahTxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Jumlah tidak boleh kosong");
            } else if (totalTxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Total tidak boleh kosong");
            } else {

                pst.setString(1, makanan);
                pst.setString(2, hargaTxt.getText());
                pst.setString(3, jumlahTxt.getText());
                pst.setString(4, totalTxt.getText());
                pst.setString(5, noMejaTxt.getText());
                pst.executeUpdate();

                hargaTxt.setText("");
                totalTxt.setText("");
                jumlahTxt.setText("");
                noMejaTxt.setText("");
                comboBoxMakanan.setSelectedItem("");

                JOptionPane.showMessageDialog(null, "Memesan Berhasil, Mohon tunggu di meja anda");
                DefaultTableModel model = (DefaultTableModel) makananData.getModel();
                model.setRowCount(0);
                fetchData();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());

        }
    }//GEN-LAST:event_tambahBtnActionPerformed

    private void hitungBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungBtnActionPerformed
        // TODO add your handling code here:
        getTotal();
    }//GEN-LAST:event_hitungBtnActionPerformed

    private void makananDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_makananDataMouseClicked
        // TODO add your handling code here:
        //event dari tabel ketika di mouse click, akan mengambil data dari row yang siap di delete atau di update
        int i = makananData.getSelectedRow();
        TableModel model = makananData.getModel();
        hargaTxt.setText(model.getValueAt(i, 2).toString());
        jumlahTxt.setText(model.getValueAt(i, 3).toString());
        totalTxt.setText(model.getValueAt(i, 4).toString());
        noMejaTxt.setText(model.getValueAt(i, 5).toString());
        String comboSelect = model.getValueAt(i, 1).toString();
        switch (comboSelect) {
            case "Pecel Pitik":
                comboBoxMakanan.setSelectedIndex(0);
                break;

        }
    }//GEN-LAST:event_makananDataMouseClicked

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        
        //ini untuk ngedit data
        String makananItem = comboBoxMakanan.getSelectedItem().toString();
        String makanan = makananItem.split("\\)", 2)[0];
        try {

            Connection con = MySqlConnection.getConnection();
            int row = makananData.getSelectedRow();
            String value = (makananData.getModel().getValueAt(row, 0).toString());
            String sql = "UPDATE pemesanan SET id_makanan=?, harga=?, jumlah=?, total=?, no_meja=? WHERE id_pemesanan=" + value;
            if (jumlahTxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Jumlah tidak boleh kosong");
            } else if (totalTxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Total tidak boleh kosong");
            } else {
                pst = con.prepareStatement(sql);
                pst.setString(1, makanan);
                pst.setString(2, hargaTxt.getText());
                pst.setString(3, jumlahTxt.getText());
                pst.setString(4, totalTxt.getText());
                pst.setString(5, noMejaTxt.getText());
                pst.executeUpdate();

                //refresh tabel
                DefaultTableModel model = (DefaultTableModel) makananData.getModel();
                model.setRowCount(0);
                fetchData();
                hargaTxt.setText("");
                totalTxt.setText("");
                jumlahTxt.setText("");
                noMejaTxt.setText("");
                comboBoxMakanan.setSelectedItem("");
                JOptionPane.showMessageDialog(null, "Update Selesai");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        //ini untuk hapus data
        try {
            Connection con = MySqlConnection.getConnection();
            int row = makananData.getSelectedRow();
            String value = (makananData.getModel().getValueAt(row, 0).toString());
            String sql = "DELETE FROM pemesanan WHERE id_pemesanan=" + value;
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) makananData.getModel();
            model.setRowCount(0);
            fetchData();
            hargaTxt.setText("");
            totalTxt.setText("");
            jumlahTxt.setText("");
            noMejaTxt.setText("");
            comboBoxMakanan.setSelectedItem("");
            JOptionPane.showMessageDialog(rootPane, "Data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void comboBoxMakananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboBoxMakananMouseClicked
        // TODO add your handling code here:
        getHarga();
    }//GEN-LAST:event_comboBoxMakananMouseClicked

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxMakanan;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField hargaTxt;
    private javax.swing.JButton hitungBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jumlahTxt;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTable makananData;
    private javax.swing.JLabel nicknameLb;
    private javax.swing.JTextField noMejaTxt;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlCard1;
    private javax.swing.JPanel pnlCard2;
    private javax.swing.JPanel pnlCard3;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton tambahBtn;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JLabel usertypeLb;
    // End of variables declaration//GEN-END:variables
}
