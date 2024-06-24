package BookStorageMangement;

import BorrowMangement.BorrowMangementPage;
import Util.DButil;

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
public class AddStorageInformation{

    public AddStorageInformation(Connection conn){
        //JFrame jFrame=new JFrame("添加图书图库信息");
        JFrame jFrame=new JFrame("添加图书图库信息");
        JPanel jpanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jpanel.setLayout(null);
        jFrame.add(jpanel);

        //插入文本框
        //插入订单号
        JLabel insertWno=new JLabel("入 库  编 号:");
        insertWno.setFont(new Font("楷体",1,30));
        insertWno.setBackground(Color.GREEN);
        insertWno.setBounds(200,100,210,100);
        jpanel.add(insertWno);

        //插入输入文本框
        JTextField textField1=new JTextField();
        textField1.setFont(new Font("宋体",Font.BOLD,30));
        textField1.setBounds(450,130,300,50);
        jpanel.add(textField1);

        //插入入库日期
        JLabel insertWdate=new JLabel("入 库  日 期:");
        insertWdate.setFont(new Font("楷体",1,30));
        insertWdate.setBackground(Color.GREEN);
        insertWdate.setBounds(200,250,210,100);
        jpanel.add(insertWdate);

        //插入输入文本框
        JTextField textField2=new JTextField();
        textField2.setFont(new Font("宋体",Font.BOLD,30));
        textField2.setBounds(450,280,300,50);
        jpanel.add(textField2);

        //日期输入提示
        JLabel Prompt=new JLabel("(形如yyyy-MM--dd)");
        Prompt.setFont(new Font("楷体",2,18));
        Prompt.setBackground(Color.GREEN);
        Prompt.setBounds(200,280,210,100);
        jpanel.add(Prompt);

        //插入员工编号
        JLabel insertEno=new JLabel("负责员工编号:");
        insertEno.setFont(new Font("楷体",1,30));
        insertEno.setBackground(Color.GREEN);
        insertEno.setBounds(200,450,210,100);
        jpanel.add(insertEno);

        //插入输入文本框
        JTextField textField3=new JTextField();
        textField3.setFont(new Font("宋体",Font.BOLD,30));
        textField3.setBounds(450,480,300,50);
        jpanel.add(textField3);

        //确认提交按钮
        JButton submitButton = new JButton("提交");
        submitButton.setFont(new Font("宋体",1,20));
        submitButton.setBounds(450, 600, 100, 50);
        jpanel.add(submitButton);


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
                        new StoragePage();
                    }
                });
            }
        });
        submitButton.addActionListener(e-> {

                    Integer s1 = null, s3 = null;
                    try {
                        s1 = Integer.valueOf(textField1.getText());
                        final int number=s1;
                        s3 = Integer.valueOf(textField3.getText());

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = format.parse(textField2.getText());

                        String sql = "insert into WareHouse values(?,?,?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);

                        pstmt.setInt(1, s1);
                        pstmt.setDate(2, new java.sql.Date(date.getTime()));
                        pstmt.setInt(3, s3);

                        int count = pstmt.executeUpdate();
                        if (count > 0) {
                          //  JOptionPane.showMessageDialog(null, "录入成功");
                            // 弹出确认信息成功后，创建一个 JOptionPane 的确认对话框
                            JOptionPane.showMessageDialog(null, "录入成功", "Success", JOptionPane.INFORMATION_MESSAGE);

// 创建并显示新的界面
                            EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                    new AddWareHouseDetails(new DButil().getconnection(),number); // 创建并显示新的界面
                                }
                            });

                        } else {
                            JOptionPane.showMessageDialog(null, "录入失败");
                        }

                    }catch (ParseException pe) {
                        JOptionPane.showMessageDialog(null, "日期格式错误");
                    }catch (NumberFormatException nf) {
                        JOptionPane.showMessageDialog(null, "输入格式错误");
                    }catch (SQLException se) {
                JOptionPane.showMessageDialog(null, "SQL错误或入库号重复或员工不存在");
            }

        });

        jFrame.setSize(1000,800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }
//    public static void main(String[] args) {
//        new AddStorageInformation(new DButil().getconnection());
//    }
}
