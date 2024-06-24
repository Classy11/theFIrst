package BorrowMangement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InputFine {
    public InputFine(Connection conn){
        JFrame jFrame=new JFrame();
        jFrame.setSize(1000,800);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);;
        JLabel inputRno=new JLabel("输入借书卡号");
        inputRno.setFont(new Font("宋体",Font.BOLD,20));
        inputRno.setBounds(100,20,140,40);
        jPanel.add(inputRno);

        JTextField textField1=new JTextField();
        textField1.setFont(new Font("宋体",Font.BOLD,30));
        textField1.setBounds(250,20,200,40);
        jPanel.add(textField1);

        JButton subButton=new JButton("确定");
        subButton.setFont(new Font("宋体",Font.BOLD,20));
        subButton.setBounds(460,20,80,40);
        jPanel.add(subButton);

        //返回按钮
        JButton returnButton=new JButton("返回");
        returnButton.setFont(new Font("宋体",Font.BOLD,15));
        returnButton.setBounds(465,720,70,35);
        jPanel.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jFrame.setVisible(false);
                        new FinePage();
                    }
                });
            }
        });

        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String s=textField1.getText();
                            String sql="select * from Reader where UnpaidFine>0 and Rno=?";
                            PreparedStatement pstmt=conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,
                                    ResultSet.CONCUR_UPDATABLE);
                            pstmt.setString(1,s);
                            ResultSet rs=pstmt.executeQuery();
                            if(rs.next()){

                                String userInput = JOptionPane.showInputDialog(null, "请输入要交费的罚金金额：");
                                float paidFine = Float.parseFloat(userInput);

                                if(paidFine > rs.getFloat("UnpaidFine")){

                                    rs.updateFloat("UnpaidFine", 0);
                                    rs.updateRow();
                                }else{

                                    rs.updateFloat("UnpaidFine", rs.getFloat("UnpaidFine") - paidFine);
                                    rs.updateRow();
                                }

                                // 创建表格并输出借书号、读者名、UnpaidFine
                                String[] columnNames = {"借书号", "读者名", "UnpaidFine"};
                                Object[][] data = {{rs.getString("Rno"), rs.getString("Rname"), rs.getFloat("UnpaidFine")}};

                                //JFrame tableFrame = new JFrame();
                                JTable table = new JTable(data, columnNames);
                                JScrollPane scrollPane = new JScrollPane(table);
                                scrollPane.setBounds(100,100,600,500);
                                jPanel.add(scrollPane);
                            }
                            else{
                                //输出未欠款
                                // 弹出一个弹窗，提示Rno不存在
                                JOptionPane.showMessageDialog(null, "输入的借书号不存在或者未欠款");

                            }
                        }catch(SQLException q){
                            q.printStackTrace();
                            //弹出一个弹窗，提示Rno不存在
                            JOptionPane.showMessageDialog(null, "输入的借书号不存在");
                        }
                    }
                });
            }
        });
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

//    public static void main(String[] args) {
//        new InputFine(new DButil().getconnection());
//    }
}
