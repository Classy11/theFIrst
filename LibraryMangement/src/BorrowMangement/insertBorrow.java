package BorrowMangement;

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


public class insertBorrow {
    public insertBorrow(Connection conn){
        JFrame jframe = new JFrame("添加借书信息");
        jframe.setSize(1000, 800);
        jframe.setLocationRelativeTo(null);

       BorrowMangementPage.BackgroundPanel jPanel = new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景.jpg");
        jPanel.setLayout(null);

        //输入借书卡号
        JLabel insertRno=new JLabel("借书卡号：");
        insertRno.setFont(new Font("楷体",Font.BOLD,30));
        insertRno.setBounds(100,150,200,50);
        jPanel.add(insertRno);

        JTextField textField1=new JTextField();
        textField1.setFont(new Font("宋体",1,30));
        textField1.setBounds(300,150,200,50);
        jPanel.add(textField1);
        //输入图书编号
        JLabel insertBno=new JLabel("图书编号：");
        insertBno.setFont(new Font("楷体",Font.BOLD,30));
        insertBno.setBounds(100,250,200,50);
        jPanel.add(insertBno);

        JTextField textField2=new JTextField();
        textField2.setFont(new Font("宋体",1,30));
        textField2.setBounds(300,250,200,50);
        jPanel.add(textField2);

        //输入借书日期
        JLabel insertData=new JLabel("借书日期：");
        insertData.setFont(new Font("楷体",Font.BOLD,30));
        insertData.setBounds(100,350,200,50);
        jPanel.add(insertData);

        JTextField textField3=new JTextField();
        textField3.setFont(new Font("宋体",1,30));
        textField3.setBounds(300,350,200,50);
        jPanel.add(textField3);

        //提交按钮
        JButton submitButton=new JButton("确认");
        submitButton.setFont(new Font("宋体",Font.BOLD,20));
        submitButton.setBounds(465,450,100,50);
        jPanel.add(submitButton);


        //提交监听
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String sql="insert into BorrowList values(?,?,?,?,0,null)";

                            Integer s1=Integer.valueOf(textField1.getText());//Rno
                            String s2=textField2.getText(); //Bno
                            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                            Date s3 = format.parse(textField3.getText());  // borrowDate
                            long time=s3.getTime();
                            long day=86400000*120L;

                            String temp=format.format(new Date(time+day));

                            Date s4=format.parse(temp);    //Due

                            PreparedStatement pstmt=conn.prepareStatement(sql);
                            pstmt.setInt(1,s1);
                            pstmt.setString(2,s2);
                            pstmt.setDate(3, new java.sql.Date(s3.getTime()));
                            pstmt.setDate(4, new java.sql.Date(s4.getTime()));

                            int count=pstmt.executeUpdate();
                            if(count>0){
                                //提示插入成功
                                JOptionPane.showMessageDialog(null, "借书记录添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                //提示未插入
                                JOptionPane.showMessageDialog(null, "未插入成功", "提示", JOptionPane.INFORMATION_MESSAGE);

                            }
                        }catch(ParseException qw){
                            qw.printStackTrace();
                        }
                        catch(SQLException aa){
                            aa.printStackTrace();
                                    //提示插入失败，借书卡号不存在或借书单号已存在或书号不存在
                            JOptionPane.showMessageDialog(null, "借书卡号不存在或书不存在", "提示", JOptionPane.INFORMATION_MESSAGE);

                        }

                    }
                });
            }
        });


        //返回按钮
        JButton ReturnButton=new JButton("返回");
        ReturnButton.setFont(new Font("宋体",Font.BOLD,15));
        ReturnButton.setBounds(465,720,70,35);
        jPanel.add(ReturnButton);

        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jframe.setVisible(false);
                        new BorrowMangementPage();
                    }
                });
            }
        });
        jframe.add(jPanel);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        new insertBorrow(new DButil().getconnection());
//    }
}
