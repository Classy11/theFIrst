package Datamaintance;

import BorrowMangement.BorrowMangementPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertReader extends JFrame {
    public insertReader(Connection conn) {
        setTitle("添加读者信息");
        setSize(1000, 800);

        // 创建面板
        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);
        // 创建标签和文本框
        //读者借书卡号文本框
        JLabel label1 = new JLabel("读者借书卡号：");
        label1.setFont(new Font("宋体", Font.BOLD, 30));
        label1.setBounds(200, 100, 250, 60);
        jPanel.add(label1);
        JTextField textField1 = new JTextField();
        textField1.setFont(new Font("宋体", Font.BOLD, 30));
        textField1.setBounds(450, 100, 300, 60);
        jPanel.add(textField1);

        JLabel label2 = new JLabel("读 者  姓 名：");
        label2.setFont(new Font("宋体", Font.BOLD, 30));
        label2.setBounds(200, 200, 250, 60);
        jPanel.add(label2);
        JTextField textField2 = new JTextField();
        textField2.setFont(new Font("宋体", Font.BOLD, 30));
        textField2.setBounds(450, 200, 300, 60);
        jPanel.add(textField2);


        JLabel label3 = new JLabel("读 者  性 别：");
        label3.setFont(new Font("宋体", Font.BOLD, 30));
        label3.setBounds(200, 300, 250, 60);
        jPanel.add(label3);
        String[] genders = {"女","男"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        genderComboBox.setFont(new Font("宋体", Font.BOLD, 30));
        genderComboBox.setBounds(450, 300, 300, 60);
        jPanel.add(genderComboBox);

        JLabel label4 = new JLabel("读者身份证号：");
        label4.setFont(new Font("宋体", Font.BOLD, 30));
        label4.setBounds(200, 400, 250, 60);
        jPanel.add(label4);
        JTextField textField4 = new JTextField();
        textField4.setFont(new Font("宋体", Font.BOLD, 30));
        textField4.setBounds(450, 400, 300, 60);
        jPanel.add(textField4);

        //确认按钮
        JButton submitButton = new JButton("确认");
        submitButton.setFont(new Font("宋体", Font.BOLD, 30));
        submitButton.setBounds(450, 500, 150, 60);
        jPanel.add(submitButton);

        //确认按钮监听
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            String sql="insert into Reader " +
                                    "values(?,?,?,?,0,0,0,20)";
                            PreparedStatement pstmt=conn.prepareStatement(sql);
                            Integer s1=Integer.valueOf(textField1.getText());
                            String s2=textField2.getText();
                            String s3=genderComboBox.getSelectedItem().toString();
                            String s4=textField4.getText();
                            pstmt.setInt(1, s1);
                            pstmt.setString(2, s2);
                            pstmt.setString(3, s3);
                            pstmt.setString(4, s4);

                            int count=pstmt.executeUpdate();
                            if(count>0){
                                JOptionPane.showMessageDialog(null, "信息已录入", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "信息录入失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            //弹窗提示出错，借书卡号已存在
                            JOptionPane.showMessageDialog(null, "借书卡号已存在", "提示", JOptionPane.INFORMATION_MESSAGE);
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
        // 添加面板到窗口
        add(jPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        new insertReader(new DButil().getconnection());
//    }
}