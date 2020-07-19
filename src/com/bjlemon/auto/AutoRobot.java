package com.bjlemon.auto;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bjlemon.entity.Product;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class AutoRobot {
	// 机器人类，java提供自动化测试的核心api
	private Robot robot;
	// 剪贴板对象
	private static Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	// 将热键的值转化成自己定义的常量
	private static final int GLOBAL_HOTKEY_F10 = 0;
	private static final int GLOBAL_HOTKEY_F11 = 1;
	// 是否终止
	private boolean isPause = false;
	// 延迟发送key
	private static final String SEND_DELAY = "sendDelay";
	// 延迟发送key
	private static final String IS_SWITCH = "isSwitch";
	// 切换
	private static final int SWITCH = 1;
	// 按键延迟默认1秒 单位毫秒
	private static final int KEY_DELAY = 1000;
	// 切换群聊的个数默认10个
	private static final int SWITCH_NUM = 10;

	/**
	 * 初始化自动发送机器人
	 */
	public AutoRobot() {
	}

	public void init() {
		// 初始化机器人类
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 添加热键监听
		initHotKeyListener();
	}

	/**
	 * 初始化热键监听
	 */
	public void initHotKeyListener() {
		// 第三方插件 注册热键 F10的常量值是是121 F11的常量值是122
		JIntellitype.getInstance().registerHotKey(GLOBAL_HOTKEY_F10, 0, 121);
		JIntellitype.getInstance().registerHotKey(GLOBAL_HOTKEY_F11, 0, 122);
		// 监听热键 匿名内部类
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			@Override
			public void onHotKey(int arg0) {// arg0 就是转换后的值
				switch (arg0) {
				case GLOBAL_HOTKEY_F10:
					isPause = false;
					// 启动多线程
					new Thread(new Runnable() {
						@Override
						public void run() {
							send();
						}
					}).start();// 启动线程
					break;
				case GLOBAL_HOTKEY_F11:
					// 停止群发
					isPause = true;
					break;
				}
			}
		});
	}

	/**
	 * 群发消息 1.获取鼠标的当前位置 属于初始位置 2.鼠标单击一次选中要被发送消息的好友 3.按下回车 打开聊天界面 ，聊天界面的焦点会自动在文本框
	 * 4.执行粘贴操作 ctrl+v 5.按下回车进行消息的发送 6.按下esc键推出当前的聊天窗口 7.将鼠标移动到初始位置 单击 8.按下方向键的下
	 * 将选择的好友移动至下一位
	 * 
	 * 4-9循环操作
	 * 
	 */
	public synchronized void send() {
		// 读取excel文件数据
		List<Product> products = CacheUtils.getProductCache("products");
		// 用到Java MouseInfo 可以获取到鼠标在屏幕上的坐标
		Point p = MouseInfo.getPointerInfo().getLocation();
		// 鼠标单击 两个步骤 按下 和 放开
		KeyboardUtils.mouseClick(robot, InputEvent.BUTTON1_MASK);// 代表单击左键
		// 计数器
		int index = 0;
		long startTime = System.currentTimeMillis();
		while (!isPause) {
			if (products != null) {
				for (Product product : products) {
					// 是否切换群聊
					if (SWITCH == isSwitch()) {
						for (int i = 0; i < SWITCH_NUM; i++) {
							sendProductMessage(product);
							ctrlAndTab();
							sleep();
						}
					} else {
						// 发送信息
						sendProductMessage(product);
						if (index > 0) {
							sleep();
						}
					}
					index++;
				}
			} else {
				sleep();
			}
		}
		// 按下ESC键推出聊天窗口
		KeyboardUtils.keyInput(robot, KeyEvent.VK_ESCAPE);
		// 回到初始位置单击
		robot.mouseMove(p.x, p.y);
		KeyboardUtils.mouseClick(robot, InputEvent.BUTTON1_MASK);
		// 方向键往下按一下
		KeyboardUtils.keyInput(robot, KeyEvent.VK_DOWN);
	}
	
	
	/**
	 * 休眠线程
	 */
	private void sleep() {
		try {
			Thread.sleep(getMillisecond());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 是否切换群聊
	 * 
	 * @return
	 */
	private int isSwitch() {
		int SWITCH = 0;
		if (CacheUtils.isContainKey(IS_SWITCH)) {
			SWITCH = Integer.valueOf(CacheUtils.getCache(IS_SWITCH));
		}
		return SWITCH;
	}

	/**
	 * 组合图片和文字发送信息
	 * 
	 * @param product
	 */
	public void sendProductMessage(Product product) {
		Image image = KeyboardUtils.getImageByUrl(product.getImage());
		KeyboardUtils.setClipboardImage(image);
		// 执行Ctrl+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(product.getName() + "\n");
		BigDecimal price = new BigDecimal(product.getPrice());
		BigDecimal coupanPrice = new BigDecimal(product.getCoupanPrice());
		double afterCoupanPrice = price.subtract(coupanPrice).doubleValue();
		buffer.append("原价：" + product.getPrice() + "元   后价：" + afterCoupanPrice + "元  \n ");
		buffer.append(product.getCoupanSearchPassword() + "\n");
		buffer.append(product.getCoupanPath() + "\n");
		buffer.append("复制本消息打开【手机陶宝】查看");
		StringSelection message = new StringSelection(buffer.toString());
		cb.setContents(message, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		// 按下回车 发送消息
		KeyboardUtils.keyInput(robot, KeyEvent.VK_ENTER);
	}

	/**
	 * 按 crtl+tab 键
	 */
	private void ctrlAndTab() {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
	}

	/**
	 * 按 crtl+down 键
	 */
	private void ctrlAndDown() {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_DOWN);
	}

	/**
	 * 按 crtl+up 键
	 */
	private void ctrlAndUp() {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_UP);
	}

	/**
	 * 获取延迟发送毫秒数
	 * 
	 * @return
	 */
	private int getMillisecond() {
		int time = 1000 * 60;
		if (CacheUtils.isContainKey(SEND_DELAY)) {
			time = CacheUtils.getCache(SEND_DELAY);
			time = time * 1000;
		}
		return time;
	}

}
