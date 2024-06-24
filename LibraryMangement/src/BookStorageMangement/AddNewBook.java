package BookStorageMangement;

import BorrowMangement.BorrowMangementPage;
import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
public class AddNewBook{
    public AddNewBook(Connection conn,String insertbno,Integer Wnumber,Integer insertcount){
        JFrame jFrame=new JFrame("输入图书信息");
        jFrame.setSize(1000,800);

        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);
        //Bno
        JLabel insertBno=new JLabel("图   书   编   号: "+insertbno);
        insertBno.setFont(new Font("楷体",1,20));
        insertBno.setBackground(Color.GREEN);
        insertBno.setBounds(200,100,400,50);
        jPanel.add(insertBno);

        //Bname
        JLabel insertBname=new JLabel("书             名:");
        insertBname.setFont(new Font("楷体",1,20));
        insertBname.setBackground(Color.GREEN);
        insertBname.setBounds(200,160,200,50);
        jPanel.add(insertBname);
       //Bname文本框
        JTextField textFieldBname=new JTextField();
        textFieldBname.setFont(new Font("宋体",Font.BOLD,20));
        textFieldBname.setBounds(400,160,200,50);
        jPanel.add(textFieldBname);

        //Author

        JLabel insertAuthor=new JLabel("作             者：");
        insertAuthor.setFont(new Font("楷体",1,20));
        insertAuthor.setBackground(Color.GREEN);
        insertAuthor.setBounds(200,220,200,50);
        jPanel.add(insertAuthor);
        //Author文本框
        JTextField textFieldAuthor=new JTextField();
        textFieldAuthor.setFont(new Font("宋体",Font.BOLD,20));
        textFieldAuthor.setBounds(400,220,200,50);
        jPanel.add(textFieldAuthor);

        //Tno
        JLabel insertTno=new JLabel("图  书 类  型  号:");
        insertTno.setFont(new Font("楷体",1,20));
        insertTno.setBackground(Color.GREEN);
        insertTno.setBounds(200,280,200,50);
        jPanel.add(insertTno);

        //Tno文本框
        JTextField textFieldTno=new JTextField();
        textFieldTno.setFont(new Font("宋体",Font.BOLD,20));
        textFieldTno.setBounds(400,280,200,50);
        jPanel.add(textFieldTno);

        //Publisher
        JLabel insertPublisher=new JLabel("出      版     社:");
        insertPublisher.setFont(new Font("楷体",1,20));
        insertPublisher.setBackground(Color.GREEN);
        insertPublisher.setBounds(200,340,200,50);
        jPanel.add(insertPublisher);
        //Publisher文本框
        JTextField textFieldPublisher=new JTextField();
        textFieldPublisher.setFont(new Font("宋体",Font.BOLD,20));
        textFieldPublisher.setBounds(400,340,200,50);
        jPanel.add(textFieldPublisher);

        //Price
        JLabel insertPrice=new JLabel("单             价:");
        insertPrice.setFont(new Font("楷体",1,20));
        insertPrice.setBackground(Color.GREEN);
        insertPrice.setBounds(200,400,200,50);
        jPanel.add(insertPrice);
        //Price文本框
        JTextField textFieldPrice=new JTextField();
        textFieldPrice.setFont(new Font("宋体",Font.BOLD,20));
        textFieldPrice.setBounds(400,400,200,50);
        jPanel.add(textFieldPrice);

        //Words
        JLabel insertWords=new JLabel("字       数(千字):");
        insertWords.setFont(new Font("楷体",1,20));
        insertWords.setBackground(Color.GREEN);
        insertWords.setBounds(200,460,200,50);
        jPanel.add(insertWords);
        //Words文本框
        JTextField textFieldWords=new JTextField();
        textFieldWords.setFont(new Font("宋体",Font.BOLD,20));
        textFieldWords.setBounds(400,460,200,50);
        jPanel.add(textFieldWords);

        //确认按钮
        JButton submitButton=new JButton("确认");
        submitButton.setBounds(400,540,100,50);
        submitButton.setFont(new Font("宋体",Font.BOLD,20));
        jPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                            String s1=insertbno;
                            String s2=textFieldBname.getText();
                            String s3=textFieldAuthor.getText();
                            String s4=textFieldTno.getText();
                            String s5=textFieldPublisher.getText();
                            float s6=Float.parseFloat(textFieldPrice.getText());
                            Integer s7=Integer.valueOf(textFieldWords.getText());

                        try{
                            String sql="insert into Book(Bno,Bname,Author,Tno,Publisher,Price,Words,Tname,Inventory,Available)"
                                    +" values(?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement pstmt=conn.prepareStatement(sql);

                            String s="select Tname from Type where Tno=?";
                            PreparedStatement pstmt2=conn.prepareStatement(s);
                            pstmt2.setString(1,s4);
                            ResultSet r=pstmt2.executeQuery();

                            String s8=null;
                            while(r.next()){
                                s8 =r.getString("Tname");
                            }

                            pstmt.setString(1,s1);
                            pstmt.setString(2,s2);
                            pstmt.setString(3,s3);
                            pstmt.setString(4,s4);
                            pstmt.setString(5,s5);
                            pstmt.setFloat(6,s6);
                            pstmt.setInt(7,s7);
                            pstmt.setString(8,s8);
                            pstmt.setInt(9,insertcount);
                            pstmt.setInt(10,insertcount);

                            int count=pstmt.executeUpdate();

                            String sql2="insert into WarehouseDetaisl values(?,?,?)";
                            PreparedStatement pstmt0=conn.prepareStatement(sql);
                            pstmt0.setInt(1,Wnumber);
                            pstmt0.setString(2,s1);
                            pstmt0.setInt(3,insertcount);
                            int count2=pstmt0.executeUpdate();

                            if (count > 0) {
                                JOptionPane.showMessageDialog(null, "插入成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "插入失败", "失败", JOptionPane.ERROR_MESSAGE);
                            }

                        }catch(SQLException f){
                            f.printStackTrace();
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
                        jFrame.setVisible(false);
                        new AddStorageInformation(new DButil().getconnection());
                    }
                });
            }
        });
        jFrame.add(jPanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        new AddNewBook(new DButil().getconnection(),"113",300,10);
//    }
}
