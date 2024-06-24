package Reader;
//读者只有简单的查询图书信息的功能，也可以选择查询自己的信息

import BorrowMangement.BorrowMangementPage;
import Util.DButil;
import WelcomeFrame.SelectCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReaderLogin extends JFrame {
    private JButton BookInformationButton;//图书信息查询按钮
    private JButton ReaderInformationButton;//数据维护按钮；
    private JButton ReturnButton;//返回按钮

    public ReaderLogin() {
        setTitle("功能选择");
        setSize(1000, 800);//窗口的尺寸

        BorrowMangementPage.BackgroundPanel panel = new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        panel.setLayout(null); // 使用null管理器

        //返回按钮
        ReturnButton = new JButton("返  回");
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 21));
        ReturnButton.setBounds(20,20,100,50);
        panel.add(ReturnButton);


        // 图书信息查询按钮
        BookInformationButton = new JButton("图书信息查询");
        BookInformationButton.setFont(new Font("宋体", Font.BOLD, 30));
        BookInformationButton.setBounds(200,300,280,70);
        BookInformationButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(BookInformationButton);


        // 读者信息按钮
        ReaderInformationButton = new JButton("读者信息查询");
        ReaderInformationButton.setFont(new Font("宋体", Font.BOLD, 30));
        ReaderInformationButton.setBounds(500,300,280,70);
        ReaderInformationButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(ReaderInformationButton);

        //图书信息查询
        BookInformationButton.addActionListener(new ActionListener() {
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
        //读者信息查询
        ReaderInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new ReaderInformationinquiry(new DButil().getconnection());
                    }
                });
            }
        });

        //返回按钮监听
       ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new SelectCard();
                    }
                });
            }
        });
        add(panel);
        setLocationRelativeTo(null); // 窗体居中设置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口的关闭
        setVisible(true);//可视化面板
    }
//    public static void main(String[]args){
//        new ReaderLogin();
//    }
}
