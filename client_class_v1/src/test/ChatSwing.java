package test;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatSwing extends JFrame{
	
	private JLabel showTextLabel;
	private JTextField nameTextField;
	private JButton serverJoinBtn;
	private JPanel backgroundPanel;
	

	public ChatSwing() {
		ininData();
		setInitLayout();
		

	}

	private void ininData() {
		setTitle("메신저");
		setSize(400,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		nameTextField = new JTextField("이름 입력",3);
		serverJoinBtn = new JButton("채팅 서버 입장");
		backgroundPanel = new JPanel();
		
	}

	private void setInitLayout() {
		setLayout(null);
		setVisible(true);
		setBackground(Color.GRAY);
		add(backgroundPanel);
		add(nameTextField,0);
		add(serverJoinBtn,0);
		backgroundPanel.setSize(400,700);
		backgroundPanel.setLocation(0,0);
		backgroundPanel.setBackground(Color.lightGray);
		nameTextField.setSize(200,50);
		nameTextField.setLocation(100,400);
		serverJoinBtn.setSize(200, 50);
		serverJoinBtn.setLocation(100, 500);
	}
	
	
	
	public static void main(String[] args) {
		new ChatSwing();
	}
}
