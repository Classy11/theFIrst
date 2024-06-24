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

//查询所有借阅记录的总罚款并排序
public class selectSumFine {
    private DefaultTableModel tableModel;
    public selectSumFine(Connection conn){
        JFrame jFrame=new JFrame();
        jFrame.setTitle("查询所有借阅记录的总罚款");
        jFrame.setSize(1000,800);

        JPanel jPanel=new JPanel();
        jPanel.setLayout(null);
        jFrame.add(jPanel);

        JButton selectButton=new JButton("查询");
        selectButton.setFont(new Font("宋体", Font.BOLD, 20));
        selectButton.setBounds(40, 300, 100, 50);
        jPanel.add(selectButton);

        //创建表格
        String[]columnNames={"借书卡号","姓名","性别","累计罚款金额"};
        tableModel=new DefaultTableModel(null,columnNames);
        JTable table=new JTable(tableModel);

        //设置表格的列宽
        //设置表格行高
        table.setRowHeight(60); //将行高设置为30像素，可以根据需要调整
        //设置表格中字体的大小
        table.setFont(new Font("宋体", 4, 25));
        JScrollPane tableScrollPane=new JScrollPane(table);
        tableScrollPane.setBounds(200,40,700,670);
        jPanel.add(tableScrollPane);

        //查询按钮监听
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //查询所有借阅记录的总罚款并排序
                        try {
                            String sql = "SELECT BorrowList.Rno, Reader.Rname, Reader.Rgender, SUM(BorrowList.Blfine) as sumfine" +
                                    " FROM BorrowList JOIN Reader ON BorrowList.Rno = Reader.Rno" +
                                    " GROUP BY BorrowList.Rno, Reader.Rname, Reader.Rgender" +
                                    " ORDER BY sumfine DESC";
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            ResultSet rs=pstmt.executeQuery();
                            int i=0;
                            while(rs.next()) {
                                String Rno = rs.getString("Rno");
                                String Rname = rs.getString("Rname");
                                String Rgender = rs.getString("Rgender");
                                String sumfine = rs.getString("sumfine");
                                i++;
                                Object[] row = {Rno, Rname, Rgender, sumfine};
                                tableModel.addRow(row);
                            }
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "没有数据");
                            }
                        }catch(SQLException a){
                            a.printStackTrace();
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
                        //返回上一个界面
                        jFrame.setVisible(false);
                        new FinePage();
                    }
                });
            }
        });

        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

//    public static void main(String[] args) {
//        new selectSumFine(new DButil().getconnection());
//    }
}
