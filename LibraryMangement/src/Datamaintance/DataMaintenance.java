package Datamaintance;

//读者信息维护：增加，修改，
//员工信息维护：增加，删除，查询，修改
//图书信息维护：删除，修改

import BorrowMangement.BorrowMangementPage;
import Manger.SelectFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataMaintenance extends JFrame {
    public DataMaintenance(){
        setTitle("数据维护功能");
        setSize(1000, 800);

        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);

        //读者信息维护
        JButton inputfineButton=new JButton("读者信息维护");
        inputfineButton.setFont(new Font("宋体",Font.BOLD,30));
        inputfineButton.setBounds(100, 300, 250, 50);
        jPanel.add(inputfineButton);
        //读者信息维护监听
        inputfineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new ReaderInfo();
                    }
                }
                );
            }
        });

        //员工信息维护按钮
        JButton queryfineButton=new JButton("员工信息维护");
        queryfineButton.setFont(new Font("宋体",Font.BOLD,30));
        queryfineButton.setBounds(400, 300, 250, 50);
        jPanel.add(queryfineButton);

        //员工信息监听
        queryfineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new EmployeeInfo();
                    }
                }
                );
            }
        });

        //图书信息维护按钮
        JButton queryfineButton1=new JButton("图书信息维护");
        queryfineButton1.setFont(new Font("宋体",Font.BOLD,30));
        queryfineButton1.setBounds(700, 300, 250, 50);
        jPanel.add(queryfineButton1);

        //图书信息维护监听
        queryfineButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new BookInfo();
                    }
                }
                );
            }
        });

        //返回按钮
        JButton ReturnButton=new JButton("返回");
        ReturnButton.setFont(new Font("宋体",Font.BOLD,21));
        ReturnButton.setBounds(20, 20, 100, 50);
        jPanel.add(ReturnButton);

        //返回监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                                               @Override
                                               public void run() {
                                                   setVisible(false);
                                                   new SelectFunction();
                                               }
                                           }
                );
            }
        });

        //设置背景
        add(jPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        new DataMaintenance();;
//    }
}
