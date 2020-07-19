package com.bjlemon.auto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class SwitchButton extends JButton {
	// 是切换群聊
	private static final String YES = "是";
	// 否切换群聊
	private static final String NO = "否";

	public SwitchButton(JComboBox isSwitchJCB, JLabel isSwitchTip, String name) {
		super(name);
		// 绑定是否切换群聊按钮监听
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String isSwitchText = isSwitchJCB.getSelectedItem().toString();
				if (YES.equals(isSwitchText) || NO.equals(isSwitchText)) {
					CacheUtils.setCache("isSwitch", YES.equals(isSwitchText) ? 1 : 0);
					isSwitchTip.setText("正确");
					isSwitchTip.setForeground(Color.green);
				} else {
					isSwitchTip.setText("错误");
					isSwitchTip.setForeground(Color.red);
				}
			}
		});
	}
}
