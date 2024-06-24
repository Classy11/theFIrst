package BorrowMangement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectBorrow{
    private JFrame frame;
    private DefaultTableModel tableModel;

    public selectBorrow(Connection conn) {

        //查询未还书记录

        frame = new JFrame("查询借阅信息");
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);


        //查询未还书的记录按钮
        JButton submitButton=new JButton("查询未还书的信息");
        submitButton.setFont(new Font("宋体",Font.BOLD,20));
        submitButton.setBounds(40,300,220,40);
        jPanel.add(submitButton);

        //返回按钮
        JButton ReturnButton=new JButton("返回");
        ReturnButton.setFont(new Font("楷体",1,15));
        ReturnButton.setBounds(465,720,70,35);
        jPanel.add(ReturnButton);

        //表格
        // 创建表格
        String[] columnNames = {"借书卡号","图书编号","读者姓名","借书日期","应还书日期","罚金"};
        tableModel = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(300, 40, 600, 670);
        //设置表格的行高
        table.setRowHeight(60);
        // 设置表格的列宽
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);

        //设置表格字体
        table.setFont(new Font("宋体", 4, 20));

        jPanel.add(tableScrollPane);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                       //查询未还书的记录
                        try {
                            String sql = "select BorrowList.Rno,Bno, Rname, borrowDate, Due, Blfine from BorrowList,Reader" +
                                    " where returnDate is null and BorrowList.Rno=Reader.Rno";
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            ResultSet rs=pstmt.executeQuery();
                            int i=0;
                            while (rs.next()){
                                String Rno=rs.getString("Rno");
                                String Bno=rs.getString("Bno");
                                String Rname=rs.getString("Rname");
                                String borrowDate=rs.getString("borrowDate");
                                String returnDate=rs.getString("Due");
                                String Fine=rs.getString("Blfine");
                                String[] rowData = {Rno, Bno, Rname, borrowDate, returnDate, Fine};
                                i++;
                                tableModel.addRow(rowData);
                            }
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "没有未还书的信息");
                            }
                        }catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
            }
        });
        //返回监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.setVisible(false);
                        new BorrowMangementPage();//返回界面
                    }
                });
            }
        });
        frame.add(jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        Connection conn = new DButil().getconnection(); // Corrected method name from getconnection to getConnection
//        new selectBorrow(conn);
//    }
}