package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;


	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEellooooooByyyniuu = new JLabel("EELLOOOOOO BYYYNIUU");
		lblEellooooooByyyniuu.setFont(new Font("Impact", Font.PLAIN, 58));
		lblEellooooooByyyniuu.setBackground(new Color(255, 69, 0));
		lblEellooooooByyyniuu.setBounds(15, 75, 519, 111);
		contentPane.add(lblEellooooooByyyniuu);
	}

}
