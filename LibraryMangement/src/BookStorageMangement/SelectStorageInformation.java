package BookStorageMangement;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SelectStorageInformation {
    private JFrame frame;
    private DefaultTableModel tableModel;

    public SelectStorageInformation(Connection conn) {

        frame = new JFrame("查询入库信息");
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        JButton byOrderNumButton = new JButton("按入库编号查询");
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
        String[] columnNames = {"入库编号", "入库日期", "负责员工编号", "图书编号","图书名称","数量"};
        tableModel = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(300, 40, 680, 670);
        //设置行高
        table.setRowHeight(30);
        //设置表格字体
        table.setFont(new Font("宋体", 4, 18));
        //设置列宽
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);


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

                inputPanel.add(new JLabel("输入入库单号: "));
                inputPanel.add(inputField);
                inputPanel.add(searchButton);

                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0); // 清空表格数据
                        try {
                            String sql = "select Warehouse.Wno, Wdate, Eno, WarehouseDetails.Bno,Bname,WDcount " +
                                    "from Warehouse, WarehouseDetails ,Book where " +
                                    "Warehouse.Wno = ? and Warehouse.Wno = WarehouseDetails.Wno and WarehouseDetails.Bno=Book.Bno ";
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
                                i++;
                                model.addRow(new Object[]{s1, s2, s3, s4, s5,s6});
                            }
                            if(i<=0){
                                JOptionPane.showMessageDialog(null, "不存在该入库记录编号", "提示", JOptionPane.INFORMATION_MESSAGE);
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
                            String sql = "select Warehouse.Wno, Wdate, Eno, WarehouseDetails.Bno,Bname,WDcount " +
                                    "from Warehouse, WarehouseDetails ,Book where " +
                                    "WarehouseDetails.Bno = ? and Warehouse.Wno = WarehouseDetails.Wno and WarehouseDetails.Bno=Book.Bno ";
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
                                i++;
                                model.addRow(new Object[]{s1, s2, s3, s4, s5,s6});
                            }
                            if(i<=0){
                                JOptionPane.showMessageDialog(null, "不存在该图书的入库记录", "提示", JOptionPane.INFORMATION_MESSAGE);
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
                            String sql = "SELECT Warehouse.Wno, Warehouse.Wdate, Warehouse.Eno, WarehouseDetails.Bno, Book.Bname, WarehouseDetails.WDcount " +
                                    "FROM Warehouse " +
                                    "JOIN WarehouseDetails ON Warehouse.Wno = WarehouseDetails.Wno " +
                                    "JOIN Book ON WarehouseDetails.Bno = Book.Bno " +
                                    "WHERE Warehouse.Eno = ?; ";
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
                                i++;
                                model.addRow(new Object[]{s1, s2, s3, s4, s5,s6});
                            }
                            if(i<=0){
                                JOptionPane.showMessageDialog(null, "不存在该员工负责的入库记录", "提示", JOptionPane.INFORMATION_MESSAGE);
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
        //返回监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.setVisible(false);
                        new StoragePage();//读者登录
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
//        new SelectStorageInformation(conn);
//    }
}