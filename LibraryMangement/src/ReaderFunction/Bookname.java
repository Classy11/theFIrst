package ReaderFunction;

import Reader.BookInformationinquiry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bookname extends JFrame {
    private JTextField textField;
    private JTable table;
    public Bookname(Connection conn) {
        setTitle("按书名搜索");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 输入框
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);

        // 输入框
        JLabel label = new JLabel("请  输  入  书  名: ");
        label.setBounds(320, 10, 180, 30);
        label.setFont(new Font("宋体", Font.BOLD, 16));
        topPanel.add(label);

        textField = new JTextField(20);
        textField.setBounds(500, 10, 200, 30);
        textField.setFont(new Font("宋体", Font.BOLD, 16));
        topPanel.add(textField);

        // 表格
        String[] columnNames = {"书名", "作者","图书类型","出版社", "字数(千字)", "现存量", "借出次数", "所在分区"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        //设置表格中字体的大小
        table.setFont(new Font("宋体", 4, 20));
        //设置表格的行高
        table.setRowHeight(50);
        //设置表格每列的宽度
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        table.getColumnModel().getColumn(7).setPreferredWidth(60);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,50,970,650);
        topPanel.add(scrollPane);

        // 返回按钮
        JButton ReturnButton = new JButton("返回");
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 15));
        ReturnButton.setBounds(465, 720, 70, 35);
        topPanel.add(ReturnButton);

        add(topPanel);
        // 输入框事件监听
        textField.addActionListener(e -> {
            String bookName = textField.getText();
            model.setRowCount(0); // 清空表格数据

            try {

                Statement stmt = conn.createStatement();

                String query = "SELECT * FROM V_BT where Bname LIKE'%" + bookName + "%'";
                ResultSet rs = stmt.executeQuery(query);

                int i = 0;
                while (rs.next()) {
                    String book = rs.getString("Bname");
                    String author = rs.getString("Author");
                    String booktype=rs.getString("Tname");
                    String publisher = rs.getString("Publisher");
                    int wordCount = rs.getInt("Words");
                    int stockCount = rs.getInt("Available");
                    int borrowCount = rs.getInt("BborrowCount");
                    String section = rs.getString("Zone");
                    i++;
                    model.addRow(new Object[]{book, author, booktype,publisher, wordCount, stockCount, borrowCount, section});
                }
                if (i <= 0) {
                    JOptionPane.showMessageDialog(null, "未在该图书馆查询到该图书信息", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                rs.close();
                stmt.close();
               // conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        setVisible(true);

        //按钮监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new BookInformationinquiry();
                    }
                });
            }
        });
    }
//   public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            DButil u=new DButil();
//            Bookname gui = new Bookname(u.getconnection());
//            gui.setVisible(true);
//        });
//    }
}
