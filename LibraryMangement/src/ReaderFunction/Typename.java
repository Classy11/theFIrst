package ReaderFunction;

import Reader.BookInformationinquiry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//采用自定义布局完成
public class Typename extends JFrame{
    private JTable table;
    // private JButton ReturnButton;
    public Typename(Connection conn) {
        //创建窗口
        JFrame jFrame = new JFrame();
        jFrame.setTitle("按图书查询查找");

        //创建面板
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        //返回按钮
        JButton ReturnButton=new JButton();
        ReturnButton.setText("返回");
        ReturnButton.setBounds(465,720,70,35);
        ReturnButton.setFont(new Font("宋体",Font.BOLD,12));

        jPanel.add(ReturnButton);
        //图书类别标签
        JLabel BookType = new JLabel();
        BookType.setBounds(40, 20, 140, 40);
        BookType.setFont(new Font("宋体", Font.BOLD, 25));
        BookType.setText("图书类别");
        jPanel.add(BookType);
        //添加按钮

        //文学类按钮
        JButton LiteratureButton = new JButton();
        LiteratureButton.setBounds(40, 70, 100, 40);
        LiteratureButton.setFont(new Font("宋体", Font.BOLD, 20));
        LiteratureButton.setText("文学");
        jPanel.add(LiteratureButton);


        LiteratureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Search(conn,LiteratureButton.getText());
                    }
                });
            }
        });

        //科普类
        JButton ScienceButton = new JButton();
        ScienceButton.setBounds(40, 120, 100, 40);
        ScienceButton.setFont(new Font("宋体", Font.BOLD, 20));
        ScienceButton.setText("科普");
        jPanel.add(ScienceButton);

        ScienceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Search(conn,ScienceButton.getText());
                    }
                });
            }
        });

        //历史类
        JButton HistoryButton = new JButton();
        HistoryButton.setBounds(40, 170, 100, 40);
        HistoryButton.setFont(new Font("宋体", Font.BOLD, 20));
        HistoryButton.setText("历史");
        jPanel.add(HistoryButton);

        HistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Search(conn,HistoryButton.getText());
                    }
                });
            }
        });

        //小说类
        JButton NovelButton = new JButton();
        NovelButton.setBounds(40, 220, 100, 40);
        NovelButton.setFont(new Font("宋体", Font.BOLD, 20));
        NovelButton.setText("小说");
        jPanel.add(NovelButton);

        NovelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Search(conn,NovelButton.getText());
                    }
                });
            }
        });

        //技术类
        JButton TechniqueButton = new JButton();
        TechniqueButton.setBounds(40, 270, 100, 40);
        TechniqueButton.setFont(new Font("宋体", Font.BOLD, 20));
        TechniqueButton.setText("技术");
        jPanel.add(TechniqueButton);

        TechniqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Search(conn,TechniqueButton.getText());
                    }
                });
            }
        });
        //地理类
        JButton GeographyButton = new JButton();
        GeographyButton.setBounds(40, 320, 100, 40);
        GeographyButton.setFont(new Font("宋体", Font.BOLD, 20));
        GeographyButton.setText("地理");
        jPanel.add(GeographyButton);

        GeographyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Search(conn,GeographyButton.getText());
                    }
                });
            }
        });
        //经济类
        JButton EconomicButton = new JButton();
        EconomicButton.setBounds(40, 370, 100, 40);
        EconomicButton.setFont(new Font("宋体", Font.BOLD, 20));
        EconomicButton.setText("经济");
        jPanel.add(EconomicButton);

        EconomicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Search(conn,EconomicButton.getText());
                    }
                });
            }
        });
        //政治类
        JButton PoliticalButton = new JButton();
        PoliticalButton.setBounds(40, 420, 100, 40);
        PoliticalButton.setFont(new Font("宋体", Font.BOLD, 20));
        PoliticalButton.setText("政治");
        jPanel.add(PoliticalButton);

        PoliticalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Search(conn,PoliticalButton.getText());
                    }
                });
            }
        });

        //设置输出信息的表格
        String[] columnNames = {"书名", "作者", "类型", "出版社", "字数", "现存量", "借出次数", "所在分区"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        //设置表格中字体的大小
        table.setFont(new Font("宋体", 4, 20));
        //设置表格的行高
        table.setRowHeight(50);
        //设置表格每列的宽度
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);
        table.getColumnModel().getColumn(6).setPreferredWidth(40);
        table.getColumnModel().getColumn(7).setPreferredWidth(40);
        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane, "Center");//将滚轮设置在中间

        scrollPane.setBounds(200, 30, 780, 680);
        jPanel.add(scrollPane);
        //输入图书类
        JLabel cinType = new JLabel();
        cinType.setBounds(40, 480, 160, 40);
        cinType.setFont(new Font("宋体", Font.BOLD, 25));
        cinType.setText("输入图书类别");
        jPanel.add(cinType);
        //输入框
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("宋体", Font.BOLD, 20));
        textField.setBounds(40, 540, 150, 40);
        jPanel.add(textField);

        //输入框监听
        textField.addActionListener(e -> {
            String BookTypename = textField.getText();
            model.setRowCount(0);
            try {
                // Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=LTLibrary;encrypt=false", "sa", "123456");
                Statement stmt = conn.createStatement();
                //String query = "SELECT * FROM Book WHERE Bname LIKE '%" + bookName + "%'";
                String query = "SELECT * FROM Book JOIN Type on book.Tname=Type.Tname where book.Tname LIKE'%" + BookTypename + "%'";
                ResultSet rs = stmt.executeQuery(query);

                int i = 0;
                while (rs.next()) {
                    String book = rs.getString("Bname");
                    String author = rs.getString("Author");
                    String booktype = rs.getString("Tname");
                    String publisher = rs.getString("Publisher");
                    int wordCount = rs.getInt("Words");
                    int stockCount = rs.getInt("Available");
                    int borrowCount = rs.getInt("BborrowCount");
                    String section = rs.getString("Zone");
                    i++;
                    model.addRow(new Object[]{book, author, booktype, publisher, wordCount, stockCount, borrowCount, section});
                }
                if (i <= 0) {
                    JOptionPane.showMessageDialog(null, "未在该图书馆查询到该类型的图书信息", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                rs.close();
                stmt.close();
                //conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
//返回按钮监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jFrame.setVisible(false);
                        new BookInformationinquiry();
                    }
                });
            }
        });
        jFrame.add(jPanel);
        jFrame.setSize(1000, 800);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void Search(Connection conn,String str){
        //设置输出信息的表格
        //  String[] columnNames = {"书名", "作者", "类型", "出版社", "字数", "现存量", "借出次数", "所在分区"};
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // 清空表格数据
        try{

            String sql = "select *from V_BT where Tname=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, str);
            ResultSet rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                String book = rs.getString("Bname");
                String author = rs.getString("Author");
                String booktype = rs.getString("Tname");
                String publisher = rs.getString("Publisher");
                int wordCount = rs.getInt("Words");
                int stockCount = rs.getInt("Available");
                int borrowCount = rs.getInt("BborrowCount");
                String section = rs.getString("Zone");
                i++;
                model.addRow(new Object[]{book, author, booktype, publisher, wordCount, stockCount, borrowCount, section});
            }
            if (i <= 0) {
                JOptionPane.showMessageDialog(null, "未在该图书馆查询到该类型的图书信息", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
            rs.close();
            //  stmt.close();
            //conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
//    public static void main(String[]args){
//        new Typename(new DButil().getconnection());
//    }
}
