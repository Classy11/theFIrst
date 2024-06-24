package Datamaintance;

import BorrowMangement.BorrowMangementPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class updateReader extends JFrame {

    public updateReader(Connection conn) {
        setTitle("修改读者信息");
        setSize(1000, 800);

        // 创建面板
        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 1000, 110);
        // 创建标签和文本框
        //读者借书卡号文本框
        JLabel label1 = new JLabel("读者借书卡号：");
        label1.setFont(new Font("宋体", Font.BOLD, 30));
        label1.setBounds(200, 50, 250, 60);
        jPanel.add(label1);

        JTextField textField1 = new JTextField();
        textField1.setFont(new Font("宋体", Font.BOLD, 30));
        textField1.setBounds(450, 50, 300, 60);
        jPanel.add(textField1);

        JLabel hintlabel = new JLabel("请输入要修改的信息");
        hintlabel.setFont(new Font("宋体", Font.BOLD, 20));
        hintlabel.setBounds(340, 140, 500, 60);

        JLabel label2 = new JLabel("读 者  姓 名：");
        label2.setFont(new Font("宋体", Font.BOLD, 30));
        label2.setBounds(200, 220, 250, 60);
        //jPanel.add(label2);
        JTextField textField2 = new JTextField();
        textField2.setFont(new Font("宋体", Font.BOLD, 30));
        textField2.setBounds(450, 220, 300, 60);
       // jPanel.add(textField2);


        JLabel label3 = new JLabel("读 者  性 别：");
        label3.setFont(new Font("宋体", Font.BOLD, 30));
        label3.setBounds(200, 320, 250, 60);
       // jPanel.add(label3);
        String[] genders = {"女","男"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        genderComboBox.setFont(new Font("宋体", Font.BOLD, 30));
        genderComboBox.setBounds(450, 320, 300, 60);
        //jPanel.add(genderComboBox);

        JLabel label4 = new JLabel("读者身份证号：");
        label4.setFont(new Font("宋体", Font.BOLD, 30));
        label4.setBounds(200, 420, 250, 60);
       // jPanel.add(label4);
        JTextField textField4 = new JTextField();
        textField4.setFont(new Font("宋体", Font.BOLD, 30));
        textField4.setBounds(450, 420, 300, 60);
       // jPanel.add(textField4);


        //确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.setFont(new Font("宋体", Font.BOLD, 20));
        confirmButton.setBounds(800, 50, 100, 60);
        jPanel.add(confirmButton);

        //提交按钮
        JButton submitButton = new JButton("提交");
        submitButton.setFont(new Font("宋体", Font.BOLD, 20));
        submitButton.setBounds(450, 520, 100, 60);

        //确认按钮监听
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //先查询读者借书卡号是否存在
                        try {
                            String s = textField1.getText();
                            String sql = "select *from Reader where Rno=?";
                            PreparedStatement pstmt =conn.prepareStatement(sql);
                            pstmt.setString(1, s);
                            ResultSet rs = pstmt.executeQuery();
                            if (rs.next()) {
                                //存在则修改读者信息窗口
                                jPanel.add(hintlabel);
                                jPanel.add(label2);
                                jPanel.add(textField2);
                                jPanel.add(label3);
                                jPanel.add(genderComboBox);
                                jPanel.add(label4);
                                jPanel.add(textField4);
                                jPanel.repaint();
                                //提交按钮

                                jPanel.add(submitButton);
                                jPanel.repaint();
                                submitButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {



                                                try {
                                                    String sql="update Reader set Rname=?,Rid=?,Rgender=? "
                                                                                + "where Rno=?";
                                                    PreparedStatement pstmt=conn.prepareStatement(sql);

                                                    String s1=textField2.getText();//读者姓名
                                                    String s2=(String) genderComboBox.getSelectedItem();//读者性别
                                                    String s3=textField4.getText();//读者身份证号

                                                    pstmt.setString(1, s1);
                                                    pstmt.setString(2, s3);
                                                    pstmt.setString(3, s2);
                                                    pstmt.setString(4, s);

                                                    int count=pstmt.executeUpdate();
                                                    if(count>0){
                                                        JOptionPane.showMessageDialog(null, "信息已录入", "提示", JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                    else{
                                                        JOptionPane.showMessageDialog(null, "信息录入失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                    //弹窗提示出
                                                    JOptionPane.showMessageDialog(null, "错误", "提示", JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            }
                                        });
                                    }
                                });
                            } else {
                                JOptionPane.showMessageDialog(null, "借书卡号不存在", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } catch (SQLException a) {
                            a.printStackTrace();
                            JOptionPane.showMessageDialog(null, "cww", "提示", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
            }
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
                        new ReaderInfo();
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
//        new updateReader(new DButil().getconnection());
//    }
}