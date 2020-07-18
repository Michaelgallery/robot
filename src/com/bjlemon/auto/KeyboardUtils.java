package com.bjlemon.auto;

import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class KeyboardUtils {
	/**
	 * 鼠标的单击操作
	 * 
	 * @param robot       用来实际模拟鼠标操作的类
	 * @param mouseButton 代表的是鼠标的哪个键
	 */
	public static void mouseClick(Robot robot, int mouseButton) {
		robot.mousePress(mouseButton);// 按下
		robot.mouseRelease(mouseButton);// 释放
	}

	/**
	 * 输入键盘操作
	 * 
	 * @param robot
	 * @param keyCode
	 */
	public static void keyInput(Robot robot, int keyCode) {
		robot.keyPress(keyCode);
		robot.keyRelease(keyCode);
	}

	public static Integer getfasongyanchi() {
		return 100;
	}

	public static Integer getctrlandtabyanchi() {
		return 100;
	}

	public static Integer getsuijiqiehuan() {
		return 100;
	}
	
	/**
	 * 复制图片到剪贴板
	 * 
	 * @param image
	 */
	public static void setClipboardImage(Image image) {
		Transferable trans = new Transferable() {

			@Override
			public DataFlavor[] getTransferDataFlavors() {
				// 转换成图片
				return new DataFlavor[] { DataFlavor.imageFlavor };
			}

			@Override
			public boolean isDataFlavorSupported(DataFlavor paramDataFlavor) {
				// 是否能转换成图片
				return DataFlavor.imageFlavor.equals(paramDataFlavor);
			}

			@Override
			public Object getTransferData(DataFlavor paramDataFlavor) throws UnsupportedFlavorException, IOException {
				// 获取图片转换对象
				if (isDataFlavorSupported(paramDataFlavor)) {
					return image;
				}
				throw new UnsupportedFlavorException(paramDataFlavor);
			}
		};
		// 复制到剪贴板
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
	}
	
	/**
	 * 通过url获取image对象
	 * 
	 * @param urlString
	 * @param i
	 * @return
	 */
	public static Image getImageByUrl(String urlString) {
		Image image = null;
		try {
			// 构造URL
			URL url = new URL(urlString);
			// 打开连接
			URLConnection con = url.openConnection();
			// 输入流
			InputStream is = con.getInputStream();

			image = ImageIO.read(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return image;
	}
}
