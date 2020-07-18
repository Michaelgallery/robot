package com.bjlemon.auto;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import com.melloware.jintellitype.*;

/**
 * QQ/微信群发工具 具备窗体界面的功能 需要监听电脑的F11 F12这两个热键
 *
 */
public class Application extends JFrame {
	// 是切换群聊
	private static final String YES = "是";
	// 否切换群聊
	private static final String NO = "否";

	

	/**
	 * 构造方法 Applicaiton类被实例化的时候要用来显示
	 */
	public Application() {
		// 初始化图形界面
		initPanel();
		// 初始化自动群发机器人
		new AutoRobot().init();
	}

	/**
	 * 初始化图形化界面
	 */
	public void initPanel() {
		// 设置窗体的标题
		this.setTitle("木木机器人QQ/微信群发");
		// 设置窗体的大小
		this.setSize(300, 350);
        this.setLayout(new GridLayout(3, 3, 10, 10));
		// 创建面板
		JPanel jpanel = new JPanel();
		// TODO 布局较丑，后期可以完善
		JLabel jLabel = new JLabel("           F10开始群发 F11结束群发             ");
		// 群发延迟配置
		JLabel sendDelayJL = new JLabel("群发延时/秒");
		JTextField sendDelayJTF = new JTextField(5);
		JLabel sendDelayTip = new JLabel("  ");
		JLabel lineBreak = new JLabel("    ");
        JButton sendDelayBTN = new JButton("确认");
        // 绑定群发延迟按钮确认信息监听
        sendDelayBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String sendDelayText = sendDelayJTF.getText();
            	if(StringUtils.isNumeric(sendDelayText)) {
            		// 清空配置信息
            		CacheUtils.setCache("sendDelay", Integer.valueOf(sendDelayText));
            		sendDelayTip.setText("正确");
            		sendDelayTip.setForeground(Color.green);
            	}else {
            		sendDelayTip.setText("错误");
            		sendDelayTip.setForeground(Color.red);
            	}
            }
        });
        // 是否切换群配置
		JLabel isSwitchJL = new JLabel("切换群聊");
        JComboBox isSwitchJCB=new JComboBox();
        isSwitchJCB.addItem("--请选择--");    
        isSwitchJCB.addItem("是");
        isSwitchJCB.addItem("否");
        JLabel isSwitchTip = new JLabel("");
        JButton isSwitchBTN = new JButton("确认");
        // 绑定是否切换群聊按钮监听
        isSwitchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String isSwitchText = isSwitchJCB.getSelectedItem().toString();
            	if(YES.equals(isSwitchText)||NO.equals(isSwitchText)) {
            		CacheUtils.setCache("isSwitch",YES.equals(isSwitchText)?1:0 );
            		isSwitchTip.setText("正确");
            		isSwitchTip.setForeground(Color.green);
            	}else {
            		isSwitchTip.setText("错误");
            		isSwitchTip.setForeground(Color.red);
            	}
            }
        });
        
        jpanel.add(jLabel);
        jpanel.add(sendDelayJL);
        jpanel.add(sendDelayJTF);
        jpanel.add(sendDelayTip);
        jpanel.add(sendDelayBTN);
        jpanel.add(lineBreak);
        jpanel.add(isSwitchJL);
        jpanel.add(isSwitchJCB);
        jpanel.add(isSwitchTip);
        jpanel.add(isSwitchBTN);
		// 将面板加入到窗体
		this.add(jpanel);
		// 设置默认屏幕居中
		this.setLocationRelativeTo(null);
		// 设置窗体关闭的时候 退出进程
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		// 设置可见
		this.setVisible(true);
	}




	/**
	 * 开发应用程序 JFrame
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Application application = new Application();
	}
}
