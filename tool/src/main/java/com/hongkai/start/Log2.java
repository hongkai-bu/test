package com.hongkai.start;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;


public class Log2 extends JFrame {
    private JPanel panel;
    private JLabel label, username, title, pwd;
    private JTextField usernameFid;
    private JPasswordField pwdFid;
    private ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("png").getPath() + "/logon.png");
    private JButton back, confirm;
    private Font font = new Font("宋体", Font.BOLD, 23);
    private boolean flag;

    public Log2() throws HeadlessException {
        setTitle("注册账号");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = (JPanel) getContentPane();
        panel.setOpaque(false);//将面板设置为透明
        label = new JLabel(icon,JLabel.CENTER);
        getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
        label.setBounds(0, 0, 600, 400);
        panel.setLayout(null);

        //设置窗体圆角
        AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(
                0.0D, 0.0D, this.getWidth(), this.getHeight(), 26.0D,
                26.0D));

        //标题
        title = new JLabel("泥河湾国家级自然保护区标本管理系统",JLabel.CENTER);
        title.setBounds(0, 20, 600, 50);
        Font font1 = new Font("宋体", Font.BOLD, 30);
        title.setFont(font1);
        title.setForeground(new Color(254, 253, 255));
        title.setBackground(new Color(37, 52, 179));
        title.setOpaque(true);
        panel.add(title);

        //用户名
        username = new JLabel("用户名:");
        username.setBounds(290, 130, 90, 28);
        username.setFont(font);
        username.setForeground(new Color(254, 253, 255));
        panel.add(username);

        usernameFid = new JTextField();
        usernameFid.setBounds(392, 132, 150, 23);
        usernameFid.setHorizontalAlignment(SwingConstants.LEFT);
        usernameFid.setFont(new Font("宋体", Font.BOLD, 18));
        panel.add(usernameFid);


        //密码
        pwd = new JLabel("密  码:");
        pwd.setBounds(290, 180, 90, 28);
        pwd.setFont(font);
        pwd.setForeground(new Color(254, 253, 255));
        panel.add(pwd);

        pwdFid = new JPasswordField();
        pwdFid.setBounds(392, 182, 150, 23);
        panel.add(pwdFid);


        //按钮
        confirm = new JButton("登录");
        confirm.setBackground(new Color(41, 37, 205));
        confirm.setForeground(new Color(254, 253, 255));
        confirm.setFocusPainted(false);
        confirm.setFont(font);
        confirm.setBounds(293, 278, 100, 32);
        panel.add(confirm);
        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(12));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(0));
            }
        });

        back = new JButton("取消");
        back.setBackground(new Color(41, 37, 205));
        back.setForeground(new Color(254, 253, 255));
        back.setFocusPainted(false);
        back.setFont(font);
        back.setBounds(445, 278, 100, 32);
        panel.add(back);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(12));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(0));

            }
        });
        setVisible(true);

        event();
    }

    private void event() {
        confirm.addActionListener((ActionEvent e)->{
            this.dispose();
            new Start();
        });

        back.addActionListener((ActionEvent e)->{
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        new Log2();
    }
}
