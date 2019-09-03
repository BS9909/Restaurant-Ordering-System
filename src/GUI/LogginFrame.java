package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc.UsersCheck;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LogginFrame extends JFrame {

	private JPanel contentPane;
	private static JPasswordField passwordField;
	private static LogginFrame logginForm = null;
	private static SignUpFrame signUpForm = null;
	private static UsersCheck usersCheck = null;
	private static TextField emailtextField = null;
	

	public static void main(String[] args)  {
			try {
				logginForm = new LogginFrame();
				logginForm.setVisible(true);
			} catch (Exception e) {
					e.printStackTrace();
				}
			}



	public LogginFrame() {
		try {
			usersCheck = new UsersCheck();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button logInButton = new Button("Log in");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeLogIn()	;	
			}
		});
		logInButton.setBackground(new Color(30, 144, 255));
		logInButton.setBounds(222, 35, 147, 84);
		contentPane.add(logInButton);
		
		Button signUpButton = new Button("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				makeSignUp();
				}
		});
		
		
		signUpButton.setBackground(new Color(34, 139, 34));
		signUpButton.setBounds(55, 35, 147, 84);
		contentPane.add(signUpButton);
		
		emailtextField = new TextField();
		emailtextField.setBounds(154, 178, 215, 27);
		contentPane.add(emailtextField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(154, 236, 215, 26);
		contentPane.add(passwordField);
		
		JLabel emailLabel = new JLabel("E-mail:");
		emailLabel.setBounds(15, 178, 113, 27);
		contentPane.add(emailLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(15, 239, 113, 27);
		contentPane.add(passwordLabel);
	}
	
	private static void makeSignUp() {
		signUpForm = new SignUpFrame();
		signUpForm.setLocationRelativeTo(null);
		signUpForm.setVisible(true);
		logginForm.setVisible(false);
	}
	private static void makeLogIn() {
		if(usersCheck.checkPassword(emailtextField.getText(), String.valueOf(passwordField.getPassword())) > 0) {
			MainFrame mainFrame = new MainFrame();
			mainFrame.setLocationRelativeTo(null);
			mainFrame.setVisible(true);
			logginForm.setVisible(false);

		}
		else {
			JOptionPane.showMessageDialog(null,"Password or/and email are incorrect", "Error", JOptionPane.ERROR_MESSAGE );
		}
		
	}
	
}
