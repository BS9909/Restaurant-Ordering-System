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
	private static ShowOrderFrame showOrderFrame = null;

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
				setSelectTableVisible();
			}
		});
		makeOrderButton.setBounds(151, 138, 246, 63);
		contentPane.add(makeOrderButton);
		
		Button showOrdersButton = new Button("Show Orders");
		showOrdersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setShowOrderVisible();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		showOrdersButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 23));
		showOrdersButton.setBackground(new Color(0, 153, 0));
		showOrdersButton.setBounds(151, 219, 246, 63);
		contentPane.add(showOrdersButton);
	}
	
	static void setSelectTableVisible() {
		selectTableFrame = new SelectTableFrame();
		selectTableFrame.setLocationRelativeTo(null);
		selectTableFrame.setVisible(true);
		LogInFrame.setMainFrameInvisible();
	}
	static void setShowOrderVisible() throws SQLException {
		showOrderFrame = new ShowOrderFrame();
		showOrderFrame.setLocationRelativeTo(null);
		showOrderFrame.setVisible(true);
		LogInFrame.setMainFrameInvisible();
	}
	static void setSelectTableInvisible() {
		selectTableFrame.setVisible(false);
	}
	static void setShowOrderFrameInvisble() {
		showOrderFrame.setVisible(false);
	}
}
