package cn.itcast.demo1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Demo1 {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(300, 200);
		frame.setLocation(200, 200);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn = new JButton("确定");
		
		frame.add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("hello");
			}
		});
		
		frame.setVisible(true);
	}
}
