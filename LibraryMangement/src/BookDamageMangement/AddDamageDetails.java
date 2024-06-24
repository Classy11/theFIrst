package BookDamageMangement;

import BorrowMangement.BorrowMangementPage;
import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDamageDetails extends Exception{
    public AddDamageDetails(Connection conn,String insertDnumber){
        JFrame jFrame=new JFrame("插入报损单明细");
        JPanel jpanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jpanel.setLayout(null);
        jFrame.add(jpanel);

        //插入文本框
        //插入图书报损单编号
        JLabel insertWno=new JLabel("报 损 编 号:    "+insertDnumber);
        insertWno.setFont(new Font("楷体",1,30));
        insertWno.setBackground(Color.GREEN);
        insertWno.setBounds(200,50,410,100);
        jpanel.add(insertWno);

        //插入图书编号
        JLabel insertBno=new JLabel("图 书 编 号: ");
        insertBno.setFont(new Font("楷体",1,30));
        insertBno.setBackground(Color.GREEN);
        insertBno.setBounds(200,200,210,100);
        jpanel.add(insertBno);

        //插入图书编号输入文本框
        JTextField textField2=new JTextField();
        textField2.setFont(new Font("宋体",Font.BOLD,30));
        textField2.setBounds(450,230,300,50);
        jpanel.add(textField2);


        //插入数量编号
        JLabel insertWDcount=new JLabel("报 损 数 量:");
        insertWDcount.setFont(new Font("楷体",1,30));
        insertWDcount.setBackground(Color.GREEN);
        insertWDcount.setBounds(200,350,210,100);
        jpanel.add(insertWDcount);

        //插入输入文本框
        JTextField textField3=new JTextField();
        textField3.setFont(new Font("宋体",Font.BOLD,30));
        textField3.setBounds(450,380,300,50);
        jpanel.add(textField3);

        //破损情况
        JLabel insertDamage=new JLabel("破 损 情 况");
        insertDamage.setFont(new Font("楷体",1,30));
        insertDamage.setBackground(Color.GREEN);
        insertDamage.setBounds(200,500,210,100);
        jpanel.add(insertDamage);

        //输入框
        JTextField textField4=new JTextField();
        textField4.setFont(new Font("宋体",Font.BOLD,30));
        textField4.setBounds(450,530,300,50);
        jpanel.add(textField4);

        // 确认提交按钮
        JButton submitButton = new JButton("提交");
        submitButton.setFont(new Font("宋体",1,20));
        submitButton.setBounds(450, 600, 100, 50);
        jpanel.add(submitButton);

        //确认监听
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String s1 = textField2.getText();
                            Integer s2 = Integer.valueOf(textField3.getText());
                            String s3 = textField4.getText();

                            String sql = "insert into DamageDetails values(?,?,?,?)";
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, insertDnumber);
                            pstmt.setString(2, s1);
                            pstmt.setInt(3, s2);
                            pstmt.setString(4, s3);
                            int count=pstmt.executeUpdate();
                            if (count > 0) {
                                JOptionPane.showMessageDialog(null, "插入成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "插入失败", "失败", JOptionPane.ERROR_MESSAGE);
                            }
                        }catch(SQLException a1){
                            a1.printStackTrace();
                            //提示插入失败
                        }
                    }
                });
            }
        });





        //添加返回按钮
        JButton ReturnButton=new JButton("返回");
        ReturnButton.setFont(new Font("宋体",1,15));
        ReturnButton.setBounds(465,720,70,35);
        jpanel.add(ReturnButton);
        jFrame.add(jpanel);

        //返回监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jFrame.setVisible(false);
                        new AddDamageInformation(new DButil().getconnection());
                    }
                });
            }
        });
        jFrame.setSize(1000,800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }
//    public static void main(String[]args){
//        new AddDamageDetails(new DButil().getconnection(),"2");
//    }
}
