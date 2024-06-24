package BookDamageMangement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SelectDamageInformation {
    private JFrame frame;
    private DefaultTableModel tableModel;

    public SelectDamageInformation(Connection conn) {

        frame = new JFrame("查询图书破损信息");
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        JButton byOrderNumButton = new JButton("按破损编号查询");
        byOrderNumButton.setFont(new Font("楷体", 1, 22));

        JButton byBookNumButton = new JButton("按图书编号查询");
        byBookNumButton.setFont(new Font("楷体", 1, 22));

        JButton byStaffButton = new JButton("按负责员工编号查询");
        byStaffButton.setFont(new Font("楷体", 1, 22));

        //返回按钮
        JButton ReturnButton=new JButton("返回");
        ReturnButton.setFont(new Font("楷体",1,15));
        //编辑按钮位置
        byOrderNumButton.setBounds(40, 100, 240, 50);
        byBookNumButton.setBounds(40, 250, 240, 50);
        byStaffButton.setBounds(40, 400, 240, 50);
        ReturnButton.setBounds(465,720,70,35);

        //表格
        // 创建表格
        String[] columnNames = {"破损编号", "破损日期", "负责员工编号", "图书编号","图书名称","数量","破损情况"};
        tableModel = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(300, 40, 680, 670);

        //设置表格行高
        table.setRowHeight(30);

        //设置表格中字体
        table.setFont(new Font("宋体", 4, 18));
        //设置表格的列宽
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(220);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);








        // 添加数据到表格



        jPanel.add(tableScrollPane);

        jPanel.add(byOrderNumButton);
        jPanel.add(byBookNumButton);
        jPanel.add(byStaffButton);
        jPanel.add(ReturnButton);

        byOrderNumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "查询", true);
                dialog.setSize(400, 100);

                JPanel inputPanel = new JPanel();
                JTextField inputField = new JTextField(20);
                JButton searchButton = new JButton("确认");

                inputPanel.add(new JLabel("输入报损单号: "));
                inputPanel.add(inputField);
                inputPanel.add(searchButton);

                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0); // 清空表格数据
                        try {
                            String sql = "SELECT DamagedReport.DRno, DamagedReport.DRdate, DamagedReport.Eno, " +
                                    "DamageDetails.Bno, Book.Bname, DamageDetails.DDcount,Damage " +
                                    "FROM DamagedReport " +
                                    "JOIN DamageDetails ON DamagedReport.DRno = DamageDetails.DRno " +
                                    "JOIN Book ON DamageDetails.Bno = Book.Bno " +
                                    "WHERE DamagedReport.DRno = ?";
                            Integer input = Integer.valueOf(inputField.getText());
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setInt(1, input);

                            ResultSet rs = pstmt.executeQuery();
                            int i=0;
                            while (rs.next()) {

                                int s1 = rs.getInt(1);
                                Date s2 = rs.getDate(2);
                                int s3 = rs.getInt(3);
                                String s4 = rs.getString(4);
                                String s5=rs.getString(5);
                                int s6 = rs.getInt(6);
                                String s7=rs.getString(7);
                                i++;
                                model.addRow(new Object[]{s1, s2, s3, s4, s5,s6,s7});
                            }
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "报损单号不存在");
                            }
                            rs.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                            JOptionPane.showMessageDialog(null, "SQL错误或入库编号不存在");
                        }
                        dialog.dispose();
                    }
                });

                dialog.setLayout(new FlowLayout());
                dialog.add(inputPanel);
                dialog.setVisible(true);
            }
        });

        byBookNumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //按图书编号
                JDialog dialog = new JDialog(frame, "查询", true);
                dialog.setSize(400, 100);

                JPanel inputPanel = new JPanel();
                JTextField inputField = new JTextField(20);
                JButton searchButton = new JButton("确认");

                inputPanel.add(new JLabel("输入图书编号: "));
                inputPanel.add(inputField);
                inputPanel.add(searchButton);

                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0); // 清空表格数据
                        try {
                            String sql = "SELECT DamagedReport.DRno, DRdate, Eno,Book.Bno, Bname, DDcount,Damage "+
                            "FROM DamagedReport "+
                            "JOIN DamageDetails ON DamagedReport.DRno = DamageDetails.DRno "+
                            "JOIN Book ON DamageDetails.Bno = Book.Bno "+
                            "WHERE DamageDetails.Bno = ?";
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, inputField.getText());

                            ResultSet rs = pstmt.executeQuery();
                            int i=0;
                            while (rs.next()) {
                                int s1 = rs.getInt(1);
                                Date s2 = rs.getDate(2);
                                int s3 = rs.getInt(3);
                                String s4 = rs.getString(4);
                                String s5=rs.getString(5);
                                int s6 = rs.getInt(6);
                                String s7=rs.getString(7);
                                i++;
                                model.addRow(new Object[]{s1, s2, s3, s4, s5,s6,s7});
                            }
                            if(i<=0){
                                JOptionPane.showMessageDialog(null, "不存在该图书的报损记录", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                            rs.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                            JOptionPane.showMessageDialog(null, "SQL错误或报损单编号不存在");
                        }
                        dialog.dispose();
                    }
                });

                dialog.setLayout(new FlowLayout());
                dialog.add(inputPanel);
                dialog.setVisible(true);
            }
        });

        byStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //按员工编号查找
                JDialog dialog = new JDialog(frame, "查询", true);
                dialog.setSize(400, 100);

                JPanel inputPanel = new JPanel();
                JTextField inputField = new JTextField(20);
                JButton searchButton = new JButton("确认");

                inputPanel.add(new JLabel("输入员工编号: "));
                inputPanel.add(inputField);
                inputPanel.add(searchButton);

                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0); // 清空表格数据
                        try {
                            String sql = "SELECT DamagedReport.DRno, DRdate, Eno, DamageDetails.Bno, Book.Bname, DamageDetails.DDcount, Damage " +
                                    "FROM DamagedReport " +
                                    "JOIN DamageDetails ON DamagedReport.DRno = DamageDetails.DRno " +
                                    "JOIN Book ON DamageDetails.Bno = Book.Bno " +
                                    "WHERE DamagedReport.Eno = ?; ";
                            Integer input=Integer.valueOf(inputField.getText());
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setInt(1, input);

                            ResultSet rs = pstmt.executeQuery();
                            int i=0;
                            while (rs.next()) {
                                int s1 = rs.getInt(1);
                                Date s2 = rs.getDate(2);
                                int s3 = rs.getInt(3);
                                String s4 = rs.getString(4);
                                String s5=rs.getString(5);
                                int s6 = rs.getInt(6);
                                String s7=rs.getString(7);
                                i++;
                                model.addRow(new Object[]{s1, s2, s3, s4, s5,s6,s7});
                            }
                            if(i<=0){
                                JOptionPane.showMessageDialog(null, "不存在该员工负责的报损记录", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                            rs.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                            JOptionPane.showMessageDialog(null, "SQL错误或报损单编号不存在");
                        }
                        dialog.dispose();
                    }
                });

                dialog.setLayout(new FlowLayout());
                dialog.add(inputPanel);
                dialog.setVisible(true);
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
                        new DamagePage();//读者登录
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
//        new SelectDamageInformation(conn);
//    }
}