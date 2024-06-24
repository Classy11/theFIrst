package Manger;

import BookDamageMangement.DamagePage;
import BookStorageMangement.StoragePage;
import BorrowMangement.BorrowMangementPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookMangement extends JFrame{
    private JPanel panel;//面板
    private JButton BookStorageButton;//图书入库按钮
    private JButton BookReportlossButton;//图书报损管理；
    private JButton ReturnButton;//返回上层页面按钮

    public BookMangement(){

        setTitle("图书管理");
        setSize(1000,800);//窗口的尺寸
        setLocationRelativeTo(null); // 窗体居中设置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口的关闭

        panel = new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        panel.setLayout(null); // 使用GridBagLayout布局管理器
//返回按钮
        ReturnButton = new JButton("返回");
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 21));
        ReturnButton.setBounds(20,20,100,50);
        panel.add(ReturnButton);
// 入库按钮
        BookStorageButton = new JButton("图书入库管理");
        BookStorageButton.setFont(new Font("宋体", Font.BOLD, 38));
        BookStorageButton.setBounds(200,300,280,70);
        BookStorageButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(BookStorageButton);

        // 报损按钮
        BookReportlossButton = new JButton("图书报损管理");
        BookReportlossButton.setFont(new Font("宋体", Font.BOLD, 38));
        BookReportlossButton.setBounds(500,300,280,70);
        BookReportlossButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(BookReportlossButton);


        BookStorageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new StoragePage();//入库
                    }
                });
            }
        });
        BookReportlossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new DamagePage();//报损
                    }
                });
            }
        });

        //返回按钮监视
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new SelectFunction();//读者登录
                    }
                });
            }
        });
        add(panel);
        setVisible(true);//可视化面板
    }
//    public static void main(String[]args){
//        new BookMangement();
//    }
}

