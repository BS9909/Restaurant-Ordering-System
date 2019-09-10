package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc.SelectTable;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class SelectTableFrame extends JFrame {

	private JPanel contentPane;
	private static SelectTable selectTable = null;
	private static JComboBox selectTableComboBox = null;
	private static JButton selectButton = null;
	private static MainFrame mainFrame = null;
	private static AddOrderFrame addOrderFrame = null;
	private static String tableId = null;

	public SelectTableFrame() {
		try {
			selectTable = new SelectTable();
			createDesing();

		}catch(Exception e) {
			System.out.print("Error in SelectTableFrame constructor: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	private static void addTableToCombobox() throws SQLException {
		DefaultComboBoxModel<String>comboBoxModel = new DefaultComboBoxModel<>();
		
		for(int i=0; i<selectTable.countTables(true); i++) {
			comboBoxModel.addElement(selectTable.getTables(true).get(i).toString());
		}
		selectTableComboBox.setModel(comboBoxModel);
	}
	private static void makeAddOrderFrame() {
		addOrderFrame = new AddOrderFrame();
		addOrderFrame.setLocationRelativeTo(null);
		addOrderFrame.setVisible(true);
		mainFrame.setSelectTableinVisible();
	}
	static String getSelectedTable() {
		return tableId;
	}
	private void createDesing() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 432);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		selectTableComboBox = new JComboBox();
		selectTableComboBox.setBounds(228, 117, 246, 26);
		contentPane.add(selectTableComboBox);
		addTableToCombobox();
		selectTableComboBox.setSelectedItem(null);
		
		JLabel selectaTableLabel = new JLabel("Select a table");
		selectaTableLabel.setForeground(new Color(255, 255, 255));
		selectaTableLabel.setBackground(new Color(255, 255, 255));
		selectaTableLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 24));
		selectaTableLabel.setBounds(15, 117, 199, 20);
		contentPane.add(selectaTableLabel);
		
		selectButton = new JButton("Select");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectTableComboBox.getSelectedItem() != null) {
					tableId = (String) selectTableComboBox.getSelectedItem();
					makeAddOrderFrame();
				}
				else {
					JOptionPane.showMessageDialog(null,"Select table!", "Error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		selectButton.setBackground(new Color(51, 153, 51));
		selectButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 23));
		selectButton.setBounds(139, 211, 246, 69);
		contentPane.add(selectButton);
		
	}
}
