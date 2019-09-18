package GUI;



import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Buttons.ButtonTableEditor;
import Buttons.ButtonTableRenderer;
import FrameConnection.TableSelection;
import jdbc.TakeOrder;

import javax.swing.JTable;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ShowOrderFrame extends JFrame {
	private static JTable table;
	private static TakeOrder takeOrder;
	private static PayFrame payFrame;
	private static JComboBox tableBox ;

	public ShowOrderFrame() throws SQLException {
		getContentPane().setBackground(new Color(0, 51, 102));
		takeOrder = new TakeOrder();
		createDesign();
	}
	static void setPayFrameVisible() throws SQLException {
		payFrame = new PayFrame();
		payFrame.setLocationRelativeTo(null);
		payFrame.setVisible(true);
		MainFrame.setShowOrderFrameInvisble();
	}
	static void setPayFrameInvisible() {
		payFrame.setVisible(false);
	}
	private void createDesign() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1029, 626);
		
		loadOrdersTable();
		table.setBounds(1, 36, 986, 528);
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(245, 245, 245));
		getContentPane().add(table);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(1, 51, 1014, 465);
		getContentPane().add(scrollPane);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInFrame.setMainFrameVisible();
				MainFrame.setShowOrderFrameInvisble();
			}
		});
		button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 21));
		button.setBackground(Color.WHITE);
		button.setBounds(15, 6, 112, 29);
		getContentPane().add(button);
		
		tableBox = new JComboBox();
		addTableToCombobox();
		tableBox.setSelectedItem(null);
		tableBox.setBounds(425, 532, 211, 29);
		getContentPane().add(tableBox);
		
		JButton PayButton = new JButton("Pay");
		PayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null,"Select Table!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
				try {
					TableSelection.setSelectedTable((String)tableBox.getSelectedItem());
					setPayFrameVisible();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
			}
		});
		PayButton.setBackground(new Color(50, 205, 50));
		PayButton.setBounds(648, 532, 199, 29);
		getContentPane().add(PayButton);
		
		JLabel lblSelectTable = new JLabel("Select table:");
		lblSelectTable.setForeground(new Color(255, 255, 255));
		lblSelectTable.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 21));
		lblSelectTable.setBounds(259, 532, 151, 29);
		getContentPane().add(lblSelectTable);
		
	
	}
	private static void addTableToCombobox() throws SQLException {
		DefaultComboBoxModel<String>comboBoxModel = new DefaultComboBoxModel<>();
		
		for(int i=0; i<takeOrder.countUnavailableTable(); i++) {
			comboBoxModel.addElement(takeOrder.getUnavailableTable().get(i).toString());
		}
		tableBox.setModel(comboBoxModel);
	}
	private static void loadOrdersTable() throws SQLException {
		String[][]data = new String[takeOrder.countOrder()][5];
	    String[] columnNames = {"Order ID", "Table ID","Name","Price","Delete Button"};
	    data = takeOrder.getOrder();
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable( model );
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonTableRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonTableEditor(new JTextField(),table, model));	
	}	
}