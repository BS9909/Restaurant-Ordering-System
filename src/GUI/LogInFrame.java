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
import java.awt.Window;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;

public class LogInFrame extends JFrame {

	private JPanel contentPane;
	private static JPasswordField passwordField;
	private static LogInFrame logginForm = null;
	private static SignUpFrame signUpForm = null;
	private static UsersCheck usersCheck = null;
	private static TextField emailtextField = null;
	private static MainFrame mainFrame = null;

	public static void main(String[] args)  {
		try {
			setLogInVisible();
			} 
		catch(Exception e) {
			e.printStackTrace();
			}
		}

	public LogInFrame() throws SQLException {
		usersCheck = new UsersCheck();
		createDesign();
	}
	/**
	 * this function makes sign up frame visible and sets right parameters.
	 * @throws SQLException 
	 */
	static void setLogInVisible() throws SQLException {
		logginForm = new LogInFrame();
		logginForm.setLocationRelativeTo(null);
		logginForm.setVisible(true);
	}
	static void setLogInInvisible() throws SQLException {
		logginForm.setVisible(false);
	}
	static void setSignUpVisible() throws SQLException {
		signUpForm = new SignUpFrame();
		signUpForm.setLocationRelativeTo(null);
		signUpForm.setVisible(true);
		setLogInInvisible();
	}
	/**
	 * this function sets sign up frame to invisible, it  uses in the LogginFrameClass in backToLogginButton.
	 */
	static void setSignUpInvisible() {
		signUpForm.setVisible(false);
	}
	/**
	 * this function sets login frame to invisible or visible
	 */

	/**
	 * this function sets main frame to invisible or visible
	 */
	static void setMainFrameInvisible() {
		mainFrame.setVisible(false);	
		}
	static void setMainFrameVisible() {
		mainFrame.setVisible(true);	
		}
	static boolean getMainFrameVisible() {
		return mainFrame.isVisible();	
	}
	/**
	 * This function makes loggin frame.
	 * @throws SQLException 
	 * @throws HeadlessException 
	 */
	private static void makeMainFrame() throws HeadlessException, SQLException {
		if(usersCheck.checkPassword(emailtextField.getText(), String.valueOf(passwordField.getPassword())) > 0) {
			mainFrame = new MainFrame();
			mainFrame.setLocationRelativeTo(null);
			setMainFrameVisible();
			logginForm.setVisible(false);
		}
		else {
			JOptionPane.showMessageDialog(null,"Password or/and email are incorrect", "Error", JOptionPane.ERROR_MESSAGE );
		}
		
	}
	/**
	 * This function is responsible for making all design of loggin frame.
	 */
	private void createDesign() {
		try {
			usersCheck = new UsersCheck();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 451);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button logInButton = new Button("Log in");
		logInButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 23));
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					makeMainFrame()	;
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		logInButton.setBackground(new Color(0, 51, 204));
		logInButton.setBounds(222, 35, 147, 84);
		contentPane.add(logInButton);
		
		Button signUpButton = new Button("Sign Up");
		signUpButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 23));
		signUpButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				try {
					setSignUpVisible();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
		emailLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 23));
		emailLabel.setForeground(new Color(255, 255, 255));
		emailLabel.setBounds(15, 178, 113, 27);
		contentPane.add(emailLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(new Color(255, 255, 255));
		passwordLabel.setBackground(new Color(255, 255, 255));
		passwordLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 23));
		passwordLabel.setBounds(15, 239, 113, 27);
		contentPane.add(passwordLabel);
	}
	
}
