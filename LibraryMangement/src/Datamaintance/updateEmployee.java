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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class updateEmployee extends JFrame {

    public updateEmployee(Connection conn) {

        setTitle("修改员工信息");
        setSize(1000, 800);

        // 创建面板
        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 1000, 110);
        // 创建标签和文本框
        //读者借书卡号文本框
        JLabel label1 = new JLabel("输入员工编号：");
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

        JLabel label2 = new JLabel("员 工 姓 名：");
        label2.setFont(new Font("宋体", Font.BOLD, 30));
        label2.setBounds(200, 220, 250, 60);

        JTextField textField2 = new JTextField();
        textField2.setFont(new Font("宋体", Font.BOLD, 30));
        textField2.setBounds(450, 220, 300, 60);



        JLabel label3 = new JLabel("员 工 性 别：");
        label3.setFont(new Font("宋体", Font.BOLD, 30));
        label3.setBounds(200, 320, 250, 60);

        String[] genders = {"女","男"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        genderComboBox.setFont(new Font("宋体", Font.BOLD, 30));
        genderComboBox.setBounds(450, 320, 300, 60);


        JLabel label4 = new JLabel("员工身份证号：");
        label4.setFont(new Font("宋体", Font.BOLD, 30));
        label4.setBounds(200, 420, 250, 60);

        JTextField textField4 = new JTextField();
        textField4.setFont(new Font("宋体", Font.BOLD, 30));
        textField4.setBounds(450, 420, 300, 60);


        //入职时间
        JLabel label5 = new JLabel("员工入职时间：");
        label5.setFont(new Font("宋体", Font.BOLD, 30));
        label5.setBounds(200, 520, 250, 60);

        JTextField textField5 = new JTextField();
        textField5.setFont(new Font("宋体", Font.BOLD, 30));
        textField5.setBounds(450, 520, 300, 60);


        //确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.setFont(new Font("宋体", Font.BOLD, 20));
        confirmButton.setBounds(800, 50, 100, 60);
        jPanel.add(confirmButton);

        //提交按钮
        JButton submitButton = new JButton("提交");
        submitButton.setFont(new Font("宋体", Font.BOLD, 20));
        submitButton.setBounds(450, 600, 100, 60);

        //确认按钮监听
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //先查询员工编号是否存在
                        try {
                            String s = textField1.getText();
                            String sql = "select *from Employee where Eno=?";
                            PreparedStatement pstmt =conn.prepareStatement(sql);
                            pstmt.setInt(1, Integer.valueOf(s));
                            ResultSet rs = pstmt.executeQuery();
                            if (rs.next()) {
                                //存在则修改员工信息窗口
                                jPanel.add(hintlabel);
                                jPanel.add(label2);
                                jPanel.add(textField2);
                                jPanel.add(label3);
                                jPanel.add(genderComboBox);
                                jPanel.add(label4);
                                jPanel.add(textField4);
                                jPanel.repaint();
                                jPanel.add(label5);
                                jPanel.add(textField5);
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
                                                    String sql="update Employee set Ename=?,Egender=?,Eid=?,EstartTime=?"
                                                            + "where Eno=?";
                                                    PreparedStatement pstmt=conn.prepareStatement(sql);

                                                    String s1=textField2.getText();//员工姓名
                                                    String s2=(String) genderComboBox.getSelectedItem();//员工性别
                                                    String s3=textField4.getText();//员工身份证号
                                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                                    Date date = format.parse(textField5.getText());//员工入职日期
                                                    java.sql.Date sdate = new java.sql.Date(date.getTime());

                                                    pstmt.setString(1, s1);
                                                    pstmt.setString(2, s2);
                                                    pstmt.setString(3, s3);
                                                    pstmt.setDate(4, sdate);
                                                    pstmt.setString(5, s);

                                                    int count=pstmt.executeUpdate();

                                                    if(count>0){
                                                        JOptionPane.showMessageDialog(null, "信息已录入", "提示", JOptionPane.INFORMATION_MESSAGE);
                                                        // 在成功录入信息后清除文本框内容
                                                        textField1.setText("");//清空编号
                                                        textField2.setText(""); // 清空员工姓名文本框
                                                        textField4.setText(""); // 清空员工身份证号文本框
                                                        textField5.setText(""); // 清空员工入职时间文本框
                                                    }
                                                    else{
                                                        JOptionPane.showMessageDialog(null, "信息录入失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                }catch(ParseException e){
                                                    e.printStackTrace();
                                                }
                                                catch (SQLException e) {
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
                        new EmployeeInfo();
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
//        new updateEmployee(new DButil().getconnection());
//    }
}