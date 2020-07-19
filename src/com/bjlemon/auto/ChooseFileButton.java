package com.bjlemon.auto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.bjlemon.entity.Product;

/**
 * @author
 *
 */
public class ChooseFileButton extends JButton {

	protected ChooseFileButton() {
		super("导入发送文件");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser jfc = new JFileChooser();
				jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.showDialog(new JLabel(), "选择");
				File file = jfc.getSelectedFile();
				List<Product> products = ExcelUtils.readExcel(file);
				CacheUtils.setProductCache("products", products);
			}
		});
	}

}
