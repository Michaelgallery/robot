package com.bjlemon.auto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * QQ/微信群发工具 具备窗体界面的功能 需要监听电脑的F11 F12这两个热键
 *
 */
public class Application extends JFrame {

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
		this.setSize(400, 150);
		this.setLocationRelativeTo(null);
		// 创建面板
		JPanel header = new JPanel();
		JPanel center = new JPanel();
		JPanel foot = new JPanel();
		center.setLayout(new GridLayout(0, 4));
		// TODO 布局较丑，后期可以完善
		JLabel jLabel = new JLabel("F10开始群发 F11结束群发");
		// 群发延迟配置
		JLabel sendDelayJL = new JLabel("群发延时/秒");
		sendDelayJL.setSize(new Dimension(50, 5));
		JTextField sendDelayJTF = new JTextField(5);
		sendDelayJTF.setSize(new Dimension(50, 5));
		JLabel sendDelayTip = new JLabel("");
		sendDelayTip.setSize(new Dimension(50, 5));
		JButton sendDelayBTN = new SendDelayButton(sendDelayJTF, sendDelayTip, "确认");
		sendDelayBTN.setSize(new Dimension(50, 5));

		// 是否切换群配置
		JLabel isSwitchJL = new JLabel("切换群聊");
		isSwitchJL.setSize(new Dimension(50, 5));
		JComboBox isSwitchJCB = new JComboBox();
		isSwitchJCB.addItem("--请选择--");
		isSwitchJCB.addItem("是");
		isSwitchJCB.addItem("否");
		isSwitchJCB.setSize(new Dimension(50, 5));
		JLabel isSwitchTip = new JLabel("");
		isSwitchTip.setSize(new Dimension(50, 5));
		JButton isSwitchBTN = new SwitchButton(isSwitchJCB, isSwitchTip, "确认");
		isSwitchBTN.setSize(new Dimension(50, 5));
		header.add(jLabel, BorderLayout.CENTER);
		this.add(header, BorderLayout.NORTH);
		center.add(sendDelayJL, BorderLayout.WEST);
		center.add(sendDelayJTF, BorderLayout.CENTER);
		center.add(sendDelayTip, BorderLayout.CENTER);
		center.add(sendDelayBTN, BorderLayout.EAST);
		center.add(isSwitchJL, BorderLayout.WEST);
		center.add(isSwitchJCB, BorderLayout.CENTER);
		center.add(isSwitchTip, BorderLayout.CENTER);
		center.add(isSwitchBTN, BorderLayout.EAST);
		ChooseFileButton chooseFile = new ChooseFileButton();
		foot.add(chooseFile, BorderLayout.CENTER);
		this.add(foot, BorderLayout.SOUTH);
		// 将面板加入到窗体
		this.add(center, BorderLayout.CENTER);
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
