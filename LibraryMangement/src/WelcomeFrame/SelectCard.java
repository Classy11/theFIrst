package WelcomeFrame;

import BorrowMangement.BorrowMangementPage;
import Manger.Login;
import Reader.ReaderLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//身份选择
public class SelectCard extends JFrame{
    private JPanel panel;//按钮
    private JButton ManagerButton;//管理员按钮
    private JButton ReaderButton;//读者按钮；
    private JButton ReturnButton;//返回上层页面按钮

    public SelectCard(){
        setTitle("身份选择");
        setSize(1000,800);//窗口的尺寸

        BorrowMangementPage.BackgroundPanel panel= new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        panel.setLayout(null); // 使用null布局管理器

        // 管理员按钮
        ManagerButton = new JButton("我是管理员");
        ManagerButton.setFont(new Font("宋体", Font.BOLD, 40));
        ManagerButton.setBounds(200,300, 280, 70);
        ManagerButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add( ManagerButton);

        // 读者按钮
        ReaderButton = new JButton("我 是 读 者");
        ReaderButton.setFont(new Font("宋体", Font.BOLD, 40));
        ReaderButton.setBounds(550,300, 280, 70);
        ReaderButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(ReaderButton);

        //返回按钮
        ReturnButton = new JButton("返  回");
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 21));
        ReturnButton.setBounds(20, 20, 100, 50);
        panel.add(ReturnButton);

        //我是管理员
        ManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                          setVisible(false);
                        new Login();//管理员登录
                    }
                });
            }
        });
        //我是读者
        ReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new ReaderLogin();//读者登录
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
                        new StartMySql();
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
//        new SelectCard();
//    }
}

