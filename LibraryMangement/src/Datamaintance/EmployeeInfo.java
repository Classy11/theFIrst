package Datamaintance;

import BorrowMangement.BorrowMangementPage;
import Util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeInfo extends JFrame {
    public EmployeeInfo(){
        setTitle("员工信息维护");
        setSize(1000, 800);

        JPanel jPanel=new BorrowMangementPage.BackgroundPanel("C:/Users/86184/Desktop/管理员背景 (1).jpg");
        jPanel.setLayout(null);

        //增加员工信息按钮
        JButton addEmployeeButton=new JButton("添加员工信息");
        addEmployeeButton.setFont(new Font("宋体",Font.BOLD,30));
        addEmployeeButton.setBounds(200, 200, 250, 50);
        jPanel.add(addEmployeeButton);
        //增加员工监听
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new insertEmployee(new DButil().getconnection());
                    }
                }
                );
            }
        });

        //删除员工信息按钮
        JButton deleteEmployeeButton=new JButton("删除员工信息");
        deleteEmployeeButton.setFont(new Font("宋体",Font.BOLD,30));
        deleteEmployeeButton.setBounds(500, 200, 250, 50);
        jPanel.add(deleteEmployeeButton);

        //删除员工信息监听
        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new deleteEmployee(new DButil().getconnection());
                    }
                }
                );
            }
        });



        //查询员工信息按钮
        JButton searchEmployeeButton=new JButton("查询员工信息");
        searchEmployeeButton.setFont(new Font("宋体",Font.BOLD,30));
        searchEmployeeButton.setBounds(200, 400, 250, 50);
        jPanel.add(searchEmployeeButton);

        //查询员工信息监听
        searchEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new searchEmployee(new DButil().getconnection());
                    }
                }
                );
            }
        });



        //修改员工信息按钮
        JButton updateEmployeeButton=new JButton("修改员工信息");
        updateEmployeeButton.setFont(new Font("宋体",Font.BOLD,30));
        updateEmployeeButton.setBounds(500, 400, 250, 50);
        jPanel.add(updateEmployeeButton);

        updateEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                        new updateEmployee(new DButil().getconnection());
                    }
                });
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
//        new EmployeeInfo();
//    }
}
