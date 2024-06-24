package WelcomeFrame;

import BorrowMangement.BorrowMangementPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMySql extends JFrame {
    private JPanel panel;//按钮
    private JButton welcomeButton;
    public StartMySql() {
        setTitle("图书馆");
        setSize(1000, 800);//窗口大小
        BorrowMangementPage.BackgroundPanel panel = new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/图书馆.jpg");


        panel.setLayout(null); // 使用null布局管理器
        JLabel welcomeLabel = new JLabel("欢迎来到龙的传人图书馆");
        welcomeLabel.setFont(new Font("楷体", Font.BOLD, 70)); // 设置字体样式和大小
        // 设置文本颜色
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBounds(100, 350, 800, 100); // 设置文本位置为中上方


        panel.add(welcomeLabel); //将welcomeLabel添加到panel，并使用buttonGbc的约束

        //设计一个进入图书馆的按钮
        welcomeButton=new JButton("进入图书馆");
        welcomeButton.setFont(new Font("宋体",Font.BOLD,50));
        welcomeButton.setBounds(350,600,300,100);
        welcomeButton.setBorderPainted(false); // 隐藏按钮边框

        panel.add(welcomeButton); //将welcomeButton添加到panel，并使用buttonGbc的约束

        welcomeButton.addActionListener(new ActionListener() {
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭
        setVisible(true);
    }

    public static void main(String[] args) {
        // 显示欢迎界面
        StartMySql welcomjFream= new StartMySql();

    }
}
