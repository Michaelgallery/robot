package com.bjlemon.auto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

public class SendDelayButton extends JButton {
	public SendDelayButton(JTextField sendDelayJTF, JLabel sendDelayTip, String name) {
		super(name);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sendDelayText = sendDelayJTF.getText();
				if (StringUtils.isNumeric(sendDelayText)) {
					// 清空配置信息
					CacheUtils.setCache("sendDelay", Integer.valueOf(sendDelayText));
					sendDelayTip.setText("正确");
					sendDelayTip.setForeground(Color.green);
				} else {
					sendDelayTip.setText("错误");
					sendDelayTip.setForeground(Color.red);
				}
			}
		});
	}
}
