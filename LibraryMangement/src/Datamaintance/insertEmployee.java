package Datamaintance;

import BorrowMangement.BorrowMangementPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class insertEmployee extends JFrame {
    public insertEmployee(Connection conn){
        setTitle("增添员工信息");
        setSize(1000,800);

        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);

        JLabel jLabel=new JLabel("员   工   编   号:");
        jLabel.setFont(new Font("宋体" ,Font.BOLD,30));
        jLabel.setBounds(200,100,300,60);
        jPanel.add(jLabel);

        JTextField textField1=new JTextField();
        textField1.setFont(new Font("宋体" ,Font.BOLD,30));
        textField1.setBounds(500,100,400,60);
        jPanel.add(textField1);

        JLabel jLabel1=new JLabel("姓             名:");
        jLabel1.setFont(new Font("宋体" ,Font.BOLD,30));
        jLabel1.setBounds(200,200,300,60);
        jPanel.add(jLabel1);

        JTextField textField2=new JTextField();
        textField2.setFont(new Font("宋体" ,Font.BOLD,30));
        textField2.setBounds(500,200,400,60);
        jPanel.add(textField2);

        JLabel jLabel2=new JLabel("性             别:");
        jLabel2.setFont(new Font("宋体" ,Font.BOLD,30));
        jLabel2.setBounds(200,300,300,60);
        jPanel.add(jLabel2);

//性别选择
        String[] sex={"女","男"};
        JComboBox comboBox=new JComboBox(sex);
        comboBox.setFont(new Font("宋体" ,Font.BOLD,30));
        comboBox.setBounds(500,300,400,60);
        jPanel.add(comboBox);

        JLabel jLabel3=new JLabel("身   份   证   号:");
        jLabel3.setFont(new Font("宋体" ,Font.BOLD,30));
        jLabel3.setBounds(200,400,300,60);
        jPanel.add(jLabel3);

        JTextField textField4=new JTextField();
        textField4.setFont(new Font("宋体" ,Font.BOLD,30));
        textField4.setBounds(500,400,400,60);
        jPanel.add(textField4);

        JLabel jLabel4=new JLabel("入   职   时   间:");
        jLabel4.setFont(new Font("宋体" ,Font.BOLD,30));
        jLabel4.setBounds(200,500,300,60);
        jPanel.add(jLabel4);

        JTextField textField5=new JTextField();
        textField5.setFont(new Font("宋体" ,Font.BOLD,30));
        textField5.setBounds(500,500,400,60);
        jPanel.add(textField5);

        JButton subButton=new JButton("提交");
        subButton.setFont(new Font("宋体" ,Font.BOLD,25));
        subButton.setBounds(450,600,100,50);
        jPanel.add(subButton);

        //提交监听
        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            String data=textField5.getText();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(textField5.getText());
                            String sql="insert into Employee values(?,?,?,?,?)";
                            PreparedStatement pstmt=conn.prepareStatement(sql);
                            pstmt.setInt(1,Integer.valueOf(textField1.getText()));//Eno
                            pstmt.setString(2,textField2.getText());//Ename
                            pstmt.setString(3, comboBox.getSelectedItem().toString());//Egender
                            pstmt.setString(4,textField4.getText());//Eid
                            pstmt.setDate(5,new java.sql.Date(date.getTime()));//Edate
                            int count=pstmt.executeUpdate();
                            if(count>0){
                                JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null, "添加失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }catch(ParseException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "日期格式错误", "提示", JOptionPane.INFORMATION_MESSAGE);
                        } catch(SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "该员工号已存在", "提示", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                });
            }
        });


        //返回按钮
        JButton ReturnButton=new JButton("返回");
        ReturnButton.setFont(new Font("宋体" ,Font.BOLD,15));
        ReturnButton.setBounds(460,700,70,35);
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
//    public static void main(String[] args) {
//        new insertEmployee(new DButil().getconnection());
//    }
}
