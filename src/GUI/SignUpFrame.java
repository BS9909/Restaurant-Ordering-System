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
import java.awt.Font;

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
	private static LogInFrame logginForm = null;
	private static SignUpFrame signUpForm = null;
	private static UsersCheck usersCheck = null;
	private Button BackToLogInbutton;

	public SignUpFrame() throws SQLException {
		logginForm = new LogInFrame();
		createDesign();
	}
	private static  boolean matchingPassword() {
		return (String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword())));
	}
	
	/**
	 * This function checks, when you try to sign up, if any field is empty
	 * @return
	 * true - if is any empty field
	 * false - if all fields are fill
	 */
	private static boolean checkEmptyField() {
		if(firstNameTextPane.getText().isEmpty() || lastNameTextPane.getText().isEmpty() || 
				emailTextPane.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty() ||
				String.valueOf(confirmPasswordField.getPassword()).isEmpty()) {
			return false;
		}
		return true;
	}
	/**
	 * Function check all condition need to sign up, and return dialog messege if is any problem with sign up.
	 * @throws SQLException
	 */
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
			usersCheck.insertUsers(firstNameTextPane.getText(), lastNameTextPane.getText(), emailTextPane.getText(), 
					String.valueOf(passwordField.getPassword()));
		}
		else {
			JOptionPane.showMessageDialog(null,"Account with this e-mail adress already exists", "Error", JOptionPane.ERROR_MESSAGE );
		}
	}
	/**
	 * Function which makes again log in frame visible and sign up frame invisible
	 */
	private static void backToLogIn() {
		logginForm.setLocationRelativeTo(null);
		logginForm.setLogInVisible(true);
		LogInFrame.setSignUpInvisible();
	}
	/**
	 * Function creates all sign up  frame view
	 */
	private void createDesign() {
		try {
			usersCheck = new UsersCheck();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 514);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		firstNameTextPane = new JTextPane();
		firstNameTextPane.setBounds(251, 142, 302, 26);
		contentPane.add(firstNameTextPane);
		
		lastNameTextPane = new JTextPane();
		lastNameTextPane.setBounds(251, 184, 302, 26);
		contentPane.add(lastNameTextPane);
		
		emailTextPane = new JTextPane();
		emailTextPane.setBounds(251, 226, 302, 26);
		contentPane.add(emailTextPane);
		
		firstNameLabel = new JLabel("First name");
		firstNameLabel.setForeground(new Color(255, 255, 255));
		firstNameLabel.setBounds(60, 142, 132, 26);
		contentPane.add(firstNameLabel);
		
		lastNameLabel = new JLabel("Last name");
		lastNameLabel.setForeground(new Color(255, 255, 255));
		lastNameLabel.setBounds(60, 184, 132, 26);
		contentPane.add(lastNameLabel);
		
		emailLabel = new JLabel("E-mail");
		emailLabel.setForeground(new Color(255, 255, 255));
		emailLabel.setBounds(60, 226, 132, 26);
		contentPane.add(emailLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(new Color(255, 255, 255));
		passwordLabel.setBounds(60, 268, 132, 26);
		contentPane.add(passwordLabel);
		
		passwordControllLabel = new JLabel("Confrim password");
		passwordControllLabel.setForeground(new Color(255, 255, 255));
		passwordControllLabel.setBounds(60, 310, 132, 26);
		contentPane.add(passwordControllLabel);
		
		signUpButton = new Button("Sign Up");
		signUpButton.setForeground(new Color(0, 0, 0));
		signUpButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 23));
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
		signUpButton.setBounds(172, 353, 245, 84);
		contentPane.add(signUpButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(251, 268, 302, 26);
		contentPane.add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(251, 310, 302, 26);
		contentPane.add(confirmPasswordField);
		
		BackToLogInbutton = new Button("Back to log in");
		BackToLogInbutton.setForeground(new Color(0, 0, 0));
		BackToLogInbutton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		BackToLogInbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToLogIn();
			}
		});
		BackToLogInbutton.setBackground(new Color(0, 51, 204));
		BackToLogInbutton.setBounds(172, 31, 245, 84);
		contentPane.add(BackToLogInbutton);
	}
}
