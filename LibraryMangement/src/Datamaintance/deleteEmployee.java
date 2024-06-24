package Datamaintance;

//删除员工信息

import BorrowMangement.BorrowMangementPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteEmployee extends JFrame {
    public deleteEmployee(Connection conn){
        setTitle("删除员工信息");
        setSize(1000,800);

        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);


        JLabel jLabel=new JLabel("请输入要删除的员工编号");
        jLabel.setFont(new Font("宋体",Font.BOLD,30));
        jLabel.setBounds(350,100,350,60);
        jPanel.add(jLabel);

        JTextField textField=new JTextField(20);
        textField.setFont(new Font("宋体",Font.BOLD,30));
        textField.setBounds(350,200,350,60);
        jPanel.add(textField);


        JButton deleteButton=new JButton("删除");
        deleteButton.setFont(new Font("宋体",Font.BOLD,30));
        deleteButton.setBounds(450,300,100,60);
        jPanel.add(deleteButton);


        //删除按钮
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //删除员工信息
                        try{
                            String sql="delete from Employee where Eno=?";
                            PreparedStatement pstmt=conn.prepareStatement(sql);
                            pstmt.setInt(1,Integer.valueOf(textField.getText()));
                            int i=pstmt.executeUpdate();
                            if(i>0){
                                JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }catch(SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "该员工号不存在", "提示", JOptionPane.INFORMATION_MESSAGE);
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

        //返回按钮
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
//        new deleteEmployee(new DButil().getconnection());
//    }
}
