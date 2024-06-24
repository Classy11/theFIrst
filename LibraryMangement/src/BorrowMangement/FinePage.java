package BorrowMangement;

import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinePage extends JFrame {
    public FinePage(){
        setTitle("罚金功能选择");
        setSize(1000, 800);

        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);

        //交罚金按钮
        JButton inputfineButton=new JButton("交    罚    金");
        inputfineButton.setFont(new Font("宋体",Font.BOLD,30));
        inputfineButton.setBounds(200, 300, 250, 50);
        jPanel.add(inputfineButton);
        //交罚金监听
        inputfineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new InputFine(new DButil().getconnection());
                    }
                }
                );
            }
        });

        //查询罚金按钮
        JButton queryfineButton=new JButton("查看累计罚金");
        queryfineButton.setFont(new Font("宋体",Font.BOLD,30));
        queryfineButton.setBounds(500, 300, 250, 50);
        jPanel.add(queryfineButton);

        //查询罚金监听
        queryfineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new selectSumFine(new DButil().getconnection());
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
                        new BorrowMangementPage();
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
//        new FinePage();
//    }
}
