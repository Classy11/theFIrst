package BookStorageMangement;

import BorrowMangement.BorrowMangementPage;
import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddWareHouseDetails extends Exception{
    public AddWareHouseDetails(Connection conn,Integer Wnumber){
        JFrame jFrame=new JFrame("插入入库单明细");
        JPanel jpanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jpanel.setLayout(null);
        jFrame.add(jpanel);

        //插入文本框
        //插入图书入库单编号
        JLabel insertWno=new JLabel("入 库 编 号:    "+Wnumber);
        insertWno.setFont(new Font("楷体",1,30));
        insertWno.setBackground(Color.GREEN);
        insertWno.setBounds(200,100,510,100);
        jpanel.add(insertWno);


        //插入图书编号
        JLabel insertBno=new JLabel("图 书 编 号:");
        insertBno.setFont(new Font("楷体",1,30));
        insertBno.setBackground(Color.GREEN);
        insertBno.setBounds(200,280,210,100);
        jpanel.add(insertBno);

        //插入图书编号输入文本框
        JTextField textField2=new JTextField();
        textField2.setFont(new Font("宋体",Font.BOLD,30));
        textField2.setBounds(450,310,300,50);
        jpanel.add(textField2);


        //插入数量编号
        JLabel insertWDcount=new JLabel("入 库 数 量:");
        insertWDcount.setFont(new Font("楷体",1,30));
        insertWDcount.setBackground(Color.GREEN);
        insertWDcount.setBounds(200,480,210,100);
        jpanel.add(insertWDcount);

        //插入输入文本框
        JTextField textField3=new JTextField();
        textField3.setFont(new Font("宋体",Font.BOLD,30));
        textField3.setBounds(450,510,300,50);
        jpanel.add(textField3);

        //确认提交按钮
        JButton submitButton = new JButton("提交");
        submitButton.setFont(new Font("宋体",1,20));
        submitButton.setBounds(450, 610, 100, 50);
        jpanel.add(submitButton);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String insertbno = textField2.getText();
                    Integer insertcount=Integer.valueOf(textField3.getText());

                    String sql = "select Bno from Book where Bno=?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,insertbno);
                    ResultSet st = pstmt.executeQuery();
                    if(st.next()){
                        try {
                            String sql1 = "insert into WarehouseDetails values(?,?,?)";
                            PreparedStatement pstmt1=conn.prepareStatement(sql1);
                            pstmt1.setInt(1,Wnumber);
                            pstmt1.setString(2,insertbno);
                            pstmt1.setInt(3,insertcount);
                            int count=pstmt1.executeUpdate();

                            if (count > 0) {
                                JOptionPane.showMessageDialog(null, "插入成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "插入失败", "失败", JOptionPane.ERROR_MESSAGE);
                            }
                            pstmt.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                            JOptionPane.showMessageDialog(null, "SQL错误或入库编号不存在");
                        }
                    }
                    else{
                        new AddNewBook(new DButil().getconnection(),insertbno,Wnumber,insertcount);
                    }

                }catch(SQLException ae){
                    ae.printStackTrace();
                }


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
                        new AddStorageInformation(new DButil().getconnection());
                    }
                });
            }
        });
        jFrame.setSize(1000,800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }
    public static void main(String[]args){
        new AddWareHouseDetails(new DButil().getconnection(),10);
    }
}
