package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import jdbc.UsersCheck;
import java.awt.Button;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class SignUpFrame extends JFrame {

	private JPanel contentPane;
	private static JPasswordField passwordField;
	private static JPasswordField confirmPasswordField;
	private static JTextPane firstNameTextPane;
	private static JTextPane lastNameTextPane;
	private static JTextPane emailTextPane = null;
	private static JLabel firstNameLabel = null;
	private static JLabel lastNameLabel = null;	
	private static JLabel emailLabel = null;
	private static JLabel passwordLabel = null;
	private static JLabel passwordControllLabel = null;
	private static Button signUpButton = null;
	
	private static UsersCheck usersCheck = null;

	public SignUpFrame() {
		try {
			usersCheck = new UsersCheck();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		firstNameTextPane = new JTextPane();
		firstNameTextPane.setBounds(250, 58, 302, 26);
		contentPane.add(firstNameTextPane);
		
		lastNameTextPane = new JTextPane();
		lastNameTextPane.setBounds(250, 100, 302, 26);
		contentPane.add(lastNameTextPane);
		
		emailTextPane = new JTextPane();
		emailTextPane.setBounds(250, 142, 302, 26);
		contentPane.add(emailTextPane);
		
		firstNameLabel = new JLabel("First name");
		firstNameLabel.setBounds(59, 58, 132, 26);
		contentPane.add(firstNameLabel);
		
		lastNameLabel = new JLabel("Last name");
		lastNameLabel.setBounds(59, 100, 132, 26);
		contentPane.add(lastNameLabel);
		
		emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(59, 142, 132, 26);
		contentPane.add(emailLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(59, 184, 132, 26);
		contentPane.add(passwordLabel);
		
		passwordControllLabel = new JLabel("Confrim password");
		passwordControllLabel.setBounds(59, 226, 132, 26);
		contentPane.add(passwordControllLabel);
		
		signUpButton = new Button("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					makeSignUp();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		signUpButton.setBackground(new Color(34, 139, 34));
		signUpButton.setBounds(187, 314, 245, 84);
		contentPane.add(signUpButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(250, 184, 302, 26);
		contentPane.add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(250, 226, 302, 26);
		contentPane.add(confirmPasswordField);
	}
	private static  boolean matchingPassword() {
		return (String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword())));
	}
	
	private static boolean checkEmptyField() {
		if(firstNameTextPane.getText().isEmpty() || lastNameTextPane.getText().isEmpty() || 
				emailTextPane.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty() ||
				String.valueOf(confirmPasswordField.getPassword()).isEmpty()) {
			return false;
		}
		return true;
	}

	private static void makeSignUp() throws SQLException {
		if(!matchingPassword()) {
			JOptionPane.showMessageDialog(null,"Please enter same password", "Error", JOptionPane.ERROR_MESSAGE );
			return;
		}
		if(!checkEmptyField()) {
			JOptionPane.showMessageDialog(null,"Please fill all field", "Error", JOptionPane.ERROR_MESSAGE );
			return;
		}
		if(usersCheck.insertUsers(firstNameTextPane.getText(), lastNameTextPane.getText(), emailTextPane.getText(), 
				String.valueOf(passwordField.getPassword()))){
			JOptionPane.showMessageDialog(null,"You have signed up!", "Success", JOptionPane.INFORMATION_MESSAGE);

		}
		else {
			JOptionPane.showMessageDialog(null,"Account with this e-mail adress already exists", "Error", JOptionPane.ERROR_MESSAGE );
		}
	}
	
}
