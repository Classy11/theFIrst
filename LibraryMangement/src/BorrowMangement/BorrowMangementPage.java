package BorrowMangement;

import Manger.SelectFunction;
import Util.DButil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class BorrowMangementPage{

    public BorrowMangementPage() {
        JFrame jframe = new JFrame("借阅管理");
        jframe.setSize(1000, 800);
        jframe.setLocationRelativeTo(null);

        BackgroundPanel jPanel = new BackgroundPanel("C:/Users/86184/Desktop/管理员背景.jpg");
        jPanel.setLayout(null);

        // 借书记录按钮
        JButton borrowBook = new JButton("添加借书记录");
        borrowBook.setFont(new Font("宋体", Font.BOLD, 30));
        borrowBook.setBounds(200, 250, 250, 60);
        borrowBook.setBorderPainted(false); // 隐藏按钮边框
        borrowBook.setFocusPainted(false); // 隐藏焦点边框
        jPanel.add(borrowBook);
        // 添加还书记录按钮
        JButton ReturnBook=new JButton("添加还书记录");
        ReturnBook.setFont(new Font("宋体",Font.BOLD,30));
        ReturnBook.setBounds(500,250,250,60);
        ReturnBook.setBorderPainted(false);
        ReturnBook.setFocusPainted(false);
        jPanel.add(ReturnBook);

        // 查询借阅表按钮
        JButton selectBook=new JButton("查询借阅表");
        selectBook.setFont(new Font("宋体",Font.BOLD,30));
        selectBook.setBounds(200,400,250,60);
        selectBook.setBorderPainted(false);
        selectBook.setFocusPainted(false);
        jPanel.add(selectBook);


        //罚金管理
        JButton fineManagement=new JButton("罚 金 管 理");
        fineManagement.setFont(new Font("宋体",Font.BOLD,30));
        fineManagement.setBounds(500,400,250,60);
        fineManagement.setBorderPainted(false);
        fineManagement.setFocusPainted(false);
        jPanel.add(fineManagement);

        //返回按钮
        JButton ReturnButton=new JButton("返回");
        ReturnButton.setFont(new Font("宋体",Font.BOLD,21));
        ReturnButton.setBounds(20,20,100,50);
        ReturnButton.setBorderPainted(false);
        ReturnButton.setFocusPainted(false);
        jPanel.add(ReturnButton);

        //借书记录监听
        borrowBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jframe.setVisible(false);
                        new insertBorrow(new DButil().getconnection());
                    }
                });
            }
        });
        //添加还书按钮监听
        ReturnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jframe.setVisible(false);
                        new insertReturn(new DButil().getconnection());
                    }
                });
            }
        });
        //查询借阅表按钮监听
        selectBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jframe.setVisible(false);
                        new selectBorrow(new DButil().getconnection());
                    }
                });
            }
        });
        //罚金管理按钮监听
        fineManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jframe.setVisible(false);
                        new FinePage();
                    }
                });
            }
        });

        //返回按钮监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jframe.setVisible(false);
                        new SelectFunction();
                    }
                });
            }
        });

        jframe.add(jPanel);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = ImageIO.read(new File(imagePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
//    public static void main(String[] args) {
//        new BorrowMangementPage();
//    }
}
