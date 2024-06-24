package Datamaintance;

//更新图书信息

import BorrowMangement.BorrowMangementPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class updateBook extends JFrame {

    public updateBook(Connection conn) {
        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        setTitle("修改图书信息");
        setSize(1000, 800);

        // 创建面板

        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 1000, 110);
        // 创建标签和文本框
        //图书编号文本框
        JLabel label1 = new JLabel("图书编号：");
        label1.setFont(new Font("宋体", Font.BOLD, 30));
        label1.setBounds(200, 50, 250, 60);
        jPanel.add(label1);

        JTextField textField1 = new JTextField();
        textField1.setFont(new Font("宋体", Font.BOLD, 30));
        textField1.setBounds(450, 50, 300, 60);
        jPanel.add(textField1);

        JLabel hintlabel = new JLabel("请输入要修改的信息");
        hintlabel.setFont(new Font("宋体", Font.BOLD, 20));
        hintlabel.setBounds(340, 120, 500, 60);

        //要修改的信息有：图书名字
        JLabel label2 = new JLabel("图 书 名 字：");
        label2.setFont(new Font("宋体", Font.BOLD, 20));
        label2.setBounds(200, 200, 200, 50);

        JTextField textField2 = new JTextField();
        textField2.setFont(new Font("宋体", Font.BOLD, 20));
        textField2.setBounds(450, 200, 300, 50);


        //作者
        JLabel label3 = new JLabel("作       者：");
        label3.setFont(new Font("宋体", Font.BOLD, 20));
        label3.setBounds(200, 270, 200, 50);

        JTextField textField3 = new JTextField();
        textField3.setFont(new Font("宋体", Font.BOLD, 20));
        textField3.setBounds(450, 270, 300, 50);

        //出版社
        JLabel label4 = new JLabel("出  版  社：");
        label4.setFont(new Font("宋体", Font.BOLD, 20));
        label4.setBounds(200, 340, 200, 50);

        JTextField textField4 = new JTextField();
        textField4.setFont(new Font("宋体", Font.BOLD, 20));
        textField4.setBounds(450, 340, 200, 50);

        //字数
        JLabel label5 = new JLabel("字       数：");
        label5.setFont(new Font("宋体", Font.BOLD, 20));
        label5.setBounds(200, 410, 200, 50);

        JTextField textField5 = new JTextField();
        textField5.setFont(new Font("宋体", Font.BOLD, 20));
        textField5.setBounds(450, 410, 300, 50);

        //图书类型
        JLabel label6 = new JLabel("图书类型：");
        label6.setFont(new Font("宋体", Font.BOLD, 20));
        label6.setBounds(200, 480, 200, 50);

        //图书类型做成选择框
        String[] types = {"技术", "政治", "历史","经济","文学","科普","地理","小说类"};
        JComboBox<String> comboBox = new JComboBox<>(types);
        comboBox.setFont(new Font("宋体", Font.BOLD, 20));
        comboBox.setBounds(450, 480, 300, 50);


        //确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.setFont(new Font("宋体", Font.BOLD, 20));
        confirmButton.setBounds(800, 50, 100, 60);
        jPanel.add(confirmButton);

        //提交按钮
        JButton submitButton = new JButton("提交");
        submitButton.setFont(new Font("宋体", Font.BOLD, 20));
        submitButton.setBounds(450, 550, 100, 60);

        //确认按钮监听
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //先查询读者借书卡号是否存在
                        try {
                            String s1 = textField1.getText();
                            String sql = "select *from Book where Bno=?";
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, s1);

                            ResultSet rs = pstmt.executeQuery();

                            if (rs.next()) {
                                //存在则修改读者信息窗口
                                jPanel.add(hintlabel);
                                jPanel.add(label2);
                                jPanel.add(textField2);

                                jPanel.add(label3);
                                jPanel.add(textField3);

                                jPanel.add(label4);
                                jPanel.add(textField4);

                                jPanel.add(label5);
                                jPanel.add(textField5);

                                jPanel.add(label6);
                                jPanel.add(comboBox);
                                //提交按钮
                                jPanel.add(submitButton);
                                jPanel.repaint();

                                submitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                //提交更新
                                                try {
                                                    conn.setAutoCommit(false);
                                                    String s2 = textField2.getText();
                                                    String s3 = textField3.getText();
                                                    String s4 = textField4.getText();
                                                    String s5 = textField5.getText();
                                                    String s6 = (String) comboBox.getSelectedItem();

                                                    String sqlGetTno = "SELECT Tno FROM Type WHERE Tname=?";
                                                    PreparedStatement pstmtGetTno = conn.prepareStatement(sqlGetTno);
                                                    pstmtGetTno.setString(1, s6);
                                                    ResultSet rsGetTno = pstmtGetTno.executeQuery();

                                                    if (rsGetTno.next()) {
                                                        String Tno = rsGetTno.getString("Tno");

                                                        String sqlUpdate = "UPDATE Book SET Bname=?, Author=?, Publisher=?, Words=?, Tno=? WHERE Bno=?";
                                                        PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);

                                                        pstmtUpdate.setString(1, s2);
                                                        pstmtUpdate.setString(2, s3);
                                                        pstmtUpdate.setString(3, s4);
                                                        pstmtUpdate.setInt(4, Integer.valueOf(s5));
                                                        pstmtUpdate.setString(5, Tno);
                                                        pstmtUpdate.setString(6, s1);

                                                        int count = pstmtUpdate.executeUpdate();
                                                        if (count > 0) {
                                                            JOptionPane.showMessageDialog(null, "更新成功！");
                                                            conn.commit();
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "更新失败！");
                                                            conn.rollback();
                                                        }
                                                    } else {
                                                        // 不存在该图书类型
                                                        JOptionPane.showMessageDialog(null, "不存在该图书类型！");
                                                    }

                                                } catch (SQLException ex) {
                                                    try {
                                                        conn.rollback();
                                                    } catch (SQLException e) {
                                                        e.printStackTrace();
                                                    }
                                                    ex.printStackTrace();
                                                }
                                            }
                                        });
                                    }
                                });
                            }else{
                                //不存在图书编号
                                JOptionPane.showMessageDialog(null, "不存在该图书编号！");
                            }
                        } catch (SQLException e) {

                            e.printStackTrace();
                        }
                    }
                });
            };
        });
        //返回按钮
        JButton ReturnButton = new JButton("返回");
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 15));
        ReturnButton.setBounds(465, 720, 70, 35);
        jPanel.add(ReturnButton);

        //返回监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new BookInfo();
                    }
                });
            }
        });
        add(jPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        new updateBook(new DButil().getconnection());
//    }
}