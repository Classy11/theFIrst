package Datamaintance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//查询所有员工
public class searchEmployee extends JFrame {
    private DefaultTableModel tableModel;
    public searchEmployee(Connection conn){
        setTitle("查询所有员工");
        setSize(1000,800);

        JPanel jPanel=new JPanel();
        jPanel.setLayout(null);
        add(jPanel);

        JButton selectButton=new JButton("查询");
        selectButton.setFont(new Font("宋体", Font.BOLD, 20));
        selectButton.setBounds(40, 300, 100, 50);
        jPanel.add(selectButton);

        // 创建表格
        String[] columnNames = {"员工编号", "姓名", "性别", "身份证号", "入职时间"};
        tableModel = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(tableModel);

        table.setFont(new Font("宋体", Font.PLAIN, 25)); // 设置字体
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(200, 40, 700, 670);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); // 将第一列的宽度设置为100像素
        columnModel.getColumn(1).setPreferredWidth(100); // 将第二列的宽度设置为150像素
        columnModel.getColumn(2).setPreferredWidth(50); // 将第三列的宽度设置为100像素
        columnModel.getColumn(3).setPreferredWidth(250); // 将第四列的宽度设置为200像素
        columnModel.getColumn(4).setPreferredWidth(200); // 将第五列的宽度设置为200像素

        table.setRowHeight(60); // 设置行高为60像素

        jPanel.add(tableScrollPane); // 将表格添加到jPanel中

        //查询按钮监听
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //查询所有员工
                        //查找员工
                        try {
                            String sql="select * from Employee";
                            PreparedStatement pstmt=conn.prepareStatement(sql);
                            ResultSet rs=pstmt.executeQuery();
                            while(rs.next()){
                                Integer s1=rs.getInt(1);
                                String s2=rs.getString(2);
                                String s3=rs.getString(3);
                                String s4=rs.getString(4);
                                Date s5=rs.getDate(5);
                                //加入到面板列表
                                Object[] row={s1,s2,s3,s4,s5};
                                tableModel.addRow(row);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            //弹出弹窗提示该员工号不存在
                            JOptionPane.showMessageDialog(null, "该员工号不存在", "提示", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                });
            }
        });
        //返回按钮
        JButton ReturnButton=new JButton("返回");
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
                        //返回上一个界面
                        new EmployeeInfo();
                    }
                });
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
//
//    public static void main(String[] args) {
//        new searchEmployee(new DButil().getconnection());
//    }
}

