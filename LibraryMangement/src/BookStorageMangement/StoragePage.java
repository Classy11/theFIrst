package BookStorageMangement;

import BorrowMangement.BorrowMangementPage;
import Manger.BookMangement;
import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoragePage extends JFrame{
    private JPanel panel;//按钮
    private JButton AddStorageButton;//管理员按钮
    private JButton SelectButton;//读者按钮；
    private JButton ReturnButton;//返回上层页面按钮

    public StoragePage(){
        setTitle("图书入库信息管理");
        setSize(1000,800);//窗口的尺寸
        setLocationRelativeTo(null); // 窗体居中设置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口的关闭

        panel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        panel.setLayout(null); // 使用GridBagLayout布局管理器

         // 添加入库记录按钮
        AddStorageButton = new JButton("添加入库记录");
        AddStorageButton.setFont(new Font("宋体", Font.BOLD, 35));
        AddStorageButton.setBounds(200,300,280,70);
        AddStorageButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(AddStorageButton);

        // 查询入库按钮

        SelectButton = new JButton("查询入库记录");
        SelectButton.setFont(new Font("宋体", Font.BOLD, 35));
        SelectButton.setBounds(500,300,280,70);
        SelectButton.setBorderPainted(false); // 隐藏按钮边框
        panel.add(SelectButton);


        //返回按钮
        ReturnButton = new JButton("返回");
        ReturnButton.setFont(new Font("宋体", Font.BOLD, 21));
        ReturnButton.setBounds(20,20,100,50);
        panel.add(ReturnButton);


        //添加
        AddStorageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new AddStorageInformation(new DButil().getconnection());
                    }
                });
            }
        });
        //查询
        SelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                       setVisible(false);
                        new SelectStorageInformation(new DButil().getconnection());//查询入库信息
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
//        new StoragePage();
//    }
}

