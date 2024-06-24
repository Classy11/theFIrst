package Reader;

// 输出属性：类型名，作者，出版社，字数，现存量，借出次数，所在分区
//搜索书名
//搜索作者所包含的书
//按类型查找，输出这个类型所包含的所有书籍

import BorrowMangement.BorrowMangementPage;
import ReaderFunction.Authorname;
import ReaderFunction.Bookname;
import ReaderFunction.Typename;
import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookInformationinquiry extends JFrame {
    private JButton BookNameButton;//按书名查找按钮
    private JButton AuthorButton;//按作者查找按钮；
    private JButton TypeButton;//按类型查找按钮
    private JButton ReturnButton;//返回按钮

    public BookInformationinquiry() {
        setTitle("功能选择");
        setSize(1000, 800);//窗口的尺寸
        setLocationRelativeTo(null); // 窗体居中设置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口的关闭

        BorrowMangementPage.BackgroundPanel panel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        panel.setLayout(null); // 使用GridBagLayout布局管理器

        //返回按钮
        ReturnButton = new JButton("返回");
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 21));
        ReturnButton.setBounds(20,20,100,50);
        panel.add(ReturnButton);

        // 按书名查找按钮
        BookNameButton= new JButton("按 书 名 查 找");
        BookNameButton.setFont(new Font("宋体", Font.BOLD, 30));
        BookNameButton.setBounds(100,300,250,70);
        BookNameButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(BookNameButton);

// 按作者查找按钮
        AuthorButton = new JButton("按 作 者 查 找");
        AuthorButton.setFont(new Font("宋体", Font.BOLD, 30));
        AuthorButton.setBounds(400,300,250,70);
        AuthorButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(AuthorButton);

// 按类型查找按钮
        TypeButton = new JButton("按 类 型 查 找");
        TypeButton.setFont(new Font("宋体", Font.BOLD, 30));
        TypeButton.setBounds(700,300,250,70);
        TypeButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(TypeButton);

        //按书名查找
       BookNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //输出按书名信息查找的表格
                        setVisible(false);
                        new Bookname(new DButil().getconnection());
                    }
                });
            }
        });
        //按作者查找
        AuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //输出按作者信息查找的表格
                        setVisible(false);
                        DButil u=new DButil();
                         new Authorname(u.getconnection());
                    }
                });
            }
        });

        //按类型查找
        TypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //输出按类型信息查找的表格
                        setVisible(false);
                       new Typename(new DButil().getconnection());
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
                        //输出按类型信息查找的表格
                        setVisible(false);
                        new ReaderLogin();
                    }
                });
            }
        });
        add(panel);
        setVisible(true);//可视化面板
    }
//    public static void main(String[]args){
//        new BookInformationinquiry();
//    }
}


