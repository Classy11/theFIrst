package Manger;

import BorrowMangement.BorrowMangementPage;
import Datamaintance.DataMaintenance;
import WelcomeFrame.SelectCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFunction extends JFrame {
    private JPanel panel;//面板
    private JButton BookMangementButton;//图书管理按钮
    private JButton DataMaintenanceButton;//数据维护按钮；
    private JButton BorrowMangementButton;//借阅管理按钮
    private JButton ReturnButton;//返回按钮

    public SelectFunction() {
        setTitle("功能选择");
        setSize(1000, 800);//窗口的尺寸
        setLocationRelativeTo(null); // 窗体居中设置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口的关闭

         panel = new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
         panel.setLayout(null);

        //返回按钮
        ReturnButton = new JButton("返回");
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 21));
        ReturnButton.setBounds(20,20,100,50);
        ReturnButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(ReturnButton);

       // 图书管理按钮
        BookMangementButton = new JButton("图书管理");
        BookMangementButton.setFont(new Font("宋体", Font.BOLD, 30));
        BookMangementButton.setBounds(100,300,250,70);
        BookMangementButton.setBorderPainted(false); // 隐藏按钮边框
         panel.add(BookMangementButton);

        // 数据维护按钮
        DataMaintenanceButton = new JButton("数据维护");
        DataMaintenanceButton.setFont(new Font("宋体", Font.BOLD, 30));
        DataMaintenanceButton.setBounds(400,300,250,70);
        DataMaintenanceButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(DataMaintenanceButton);

        // 借阅管理按钮
        BorrowMangementButton = new JButton("借阅管理");
        BorrowMangementButton.setFont(new Font("宋体", Font.BOLD, 30));
        BorrowMangementButton.setBounds(700,300,250,70);
        BorrowMangementButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(BorrowMangementButton);

        //图书管理
        BookMangementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new BookMangement();
                    }
                });
            }
        });
        //数据维护
        DataMaintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new DataMaintenance();
                    }
                });
            }
        });

        //借阅管理
        BorrowMangementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new BorrowMangementPage();
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
                        setVisible(false);
                        new SelectCard();
                    }
                });
            }
        });
        add(panel);
        setVisible(true);//可视化面板
    }
//    public static void main(String[]args){
//        new SelectFunction();
//    }
}
