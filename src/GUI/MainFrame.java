package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private static SelectTableFrame selectTableFrame = null; 
	private static LogInFrame logInFrame = null;


	public MainFrame() {
		try {
			logInFrame = new LogInFrame();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 529);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button makeOrderButton = new Button("New order");
		makeOrderButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 23));
		makeOrderButton.setBackground(new Color(0, 153, 0));
		makeOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeSelectTableVisible();
			}
		});
		makeOrderButton.setBounds(151, 138, 246, 63);
		contentPane.add(makeOrderButton);
	}
	
	private static void makeSelectTableVisible() {
		selectTableFrame = new SelectTableFrame();
		selectTableFrame.setLocationRelativeTo(null);
		selectTableFrame.setVisible(true);
		logInFrame.setMainFrameInvisible();
	}
	static void setSelectTableinVisible() {
		selectTableFrame.setVisible(false);
	}
}
