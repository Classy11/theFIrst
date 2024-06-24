package Datamaintance;

//图书信息维护：删除，修改

import BorrowMangement.BorrowMangementPage;
import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BookInfo extends JFrame {
    public BookInfo(){
        setTitle("图书信息维护");
        setSize(1000, 800);

        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);

        //删除图书信息按钮
        JButton inputReaderButton=new JButton("删除图书信息");
        inputReaderButton.setFont(new Font("宋体",Font.BOLD,30));
        inputReaderButton.setBounds(200, 300, 250, 50);
        jPanel.add(inputReaderButton);
        //删除图书信息监听
        inputReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new deleteBook(new DButil().getconnection());
                    }
                }
                );
            }
        });

        //修改图书信息按钮
        JButton queryfineButton=new JButton("修改图书信息");
        queryfineButton.setFont(new Font("宋体",Font.BOLD,30));
        queryfineButton.setBounds(500, 300, 250, 50);
        jPanel.add(queryfineButton);

        //修改图书信息监听
        queryfineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new updateBook(new DButil().getconnection());
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

        add(jPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//
//    public static void main(String[] args) {
//        new BookInfo();
//    }
}

