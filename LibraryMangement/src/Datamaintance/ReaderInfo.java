package Datamaintance;

import BorrowMangement.BorrowMangementPage;
import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReaderInfo extends JFrame {
    public ReaderInfo(){
        setTitle("读者信息维护");
        setSize(1000, 800);

        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);

        //增加读者信息按钮
        JButton inputReaderButton=new JButton("添加读者信息");
        inputReaderButton.setFont(new Font("宋体",Font.BOLD,30));
        inputReaderButton.setBounds(200, 300, 250, 50);
        jPanel.add(inputReaderButton);
        //增加读者监听
        inputReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new insertReader(new DButil().getconnection());
                    }
                }
                );
            }
        });

        //修改读者信息按钮
        JButton queryfineButton=new JButton("修改读者信息");
        queryfineButton.setFont(new Font("宋体",Font.BOLD,30));
        queryfineButton.setBounds(500, 300, 250, 50);
        jPanel.add(queryfineButton);

        //修改读者信息监听
        queryfineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                    new updateReader(new DButil().getconnection());
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
                                                   new DataMaintenance();
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
//        new ReaderInfo();
//    }
}
