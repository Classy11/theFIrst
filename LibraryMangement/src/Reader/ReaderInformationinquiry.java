
//输入自己的借书证号，看是否存在，如果存在，就列出该读者的整个信息，否则，就输出不存在
package Reader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReaderInformationinquiry extends JFrame {
    private JTextField textField;
    private JTable table;
    public ReaderInformationinquiry(Connection connection) {
        setTitle("查询读者信息");
        setSize(1000, 800);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);

        // 输入框
        JLabel label = new JLabel("请输入您的借书卡号: ");
        label.setBounds(320, 10, 180, 30);
        label.setFont(new Font("宋体", Font.BOLD, 16));
        topPanel.add(label);

        textField = new JTextField(20);
        textField.setBounds(500, 10, 200, 30);
        textField.setFont(new Font("宋体", Font.BOLD, 16));
        topPanel.add(textField);


        // 表格
        String[] columnNames = {"借书卡号", "姓名", "性别", "身份证号", "图书借阅次数", "是否挂失", "未交罚款金额", "可借阅册数"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        //设置表格中字体的大小
        table.setFont(new Font("宋体", 4, 20));
        //设置表格的行高
        table.setRowHeight(50);
        //设置表格每列的宽度
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(240);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        table.getColumnModel().getColumn(7).setPreferredWidth(60);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 970, 650);

        topPanel.add(scrollPane);
        // 返回按钮
        JButton ReturnButton = new JButton("返回");
        ReturnButton.setBounds(465, 720, 70, 35);
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 15)) ;
        topPanel.add(ReturnButton);

        add(topPanel);
        setLocationRelativeTo(null); // 窗体居中设置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // 输入框事件监听
        textField.addActionListener(e -> {
            String temp = textField.getText();
            model.setRowCount(0); // 清空表格数据
            try {
                String query = "SELECT * FROM V_R WHERE Rno="+temp+"";
                PreparedStatement pstmt=connection.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    int Rno = rs.getInt("Rno");
                    String Rname = rs.getString("Rname");
                    String Rgender = rs.getString("Rgender");
                    String rid = rs.getString("Rid");
                    int RborrowCount = rs.getInt("RborrowCount");
                    int isLost = rs.getInt("isLost");
                    float UnpaidFine=rs.getFloat("UnpaidFine");
                    int ba=rs.getInt("BorrowAvaiable");
                    String islost;
                    switch(isLost)
                    {
                        case 0:
                            islost="否";break;
                        case 1:
                            islost="是";break;
                        default:
                            islost="未登记";break;
                    }
                    model.addRow(new Object[]{Rno, Rname, Rgender, rid, RborrowCount, islost,UnpaidFine,ba});
                }
                else {
                    JOptionPane.showMessageDialog(null, "没有读者信息", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                rs.close();
                pstmt.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });

        //返回
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new ReaderLogin();
                    }
                });
            }
        });
    }

//    public static void main(String[] args) {
//        new ReaderInformationinquiry(new DButil().getconnection());
//    }
}
