package BookDamageMangement;

//import BookDamageMangement.AddStorageInformation;
//import BookStorageMangement.SelectStorageInformation;
//import Manger.BookMangement;

import BorrowMangement.BorrowMangementPage;
import Manger.BookMangement;
import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DamagePage extends JFrame{
    private JButton AddDamageButton;//添加报损按钮
    private JButton SelectDamageButton;//查询报损按钮；
    private JButton ReturnButton;//返回上层页面按钮

    public DamagePage(){
        setTitle("图书报损信息管理");
        setSize(1000,800);//窗口的尺寸
        setLocationRelativeTo(null); // 窗体居中设置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口的关闭

        JPanel panel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");

        panel.setLayout(null); // 使用null布局管理器
// 添加报损信息按钮
        AddDamageButton = new JButton("添加报损记录");
        AddDamageButton.setFont(new Font("宋体", Font.BOLD, 35));
        AddDamageButton.setBounds(200,300,280,70);
        AddDamageButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(AddDamageButton);

        // 查询报损信息按钮

        SelectDamageButton = new JButton("查询报损记录");
        SelectDamageButton.setFont(new Font("宋体", Font.BOLD, 35));
        SelectDamageButton.setBounds(500,300,280,70);
        panel.add(SelectDamageButton);


        //返回按钮
        ReturnButton = new JButton("返回");
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 21));
        ReturnButton.setBounds(20,20,100,50);
        panel.add(ReturnButton);


        //添加报损信息
        AddDamageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new AddDamageInformation(DButil.getconnection());//管理员登录
                    }
                });
            }
        });
        //查询报损信息
        SelectDamageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new SelectDamageInformation(new DButil().getconnection());//查询入库信息
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
                        new BookMangement();//读者登录
                    }
                });
            }
        });
        add(panel);
        setVisible(true);//可视化面板
    }
//    public static void main(String[]args){
//        new DamagePage();
//    }
}

