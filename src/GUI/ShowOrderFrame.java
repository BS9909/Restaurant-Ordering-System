package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.eclipse.swt.widgets.Table;

import jdbc.TakeOrder;

import javax.swing.JTable;
import java.awt.ScrollPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;

public class ShowOrderFrame extends JFrame {
	private static JTable table;
	private static ResultSet resultSet = null;
	private static TakeOrder takeOrder;

	public ShowOrderFrame() throws SQLException {
		getContentPane().setBackground(new Color(0, 51, 102));
		takeOrder = new TakeOrder();
		createDesign();
	}
	
	
	private void createDesign() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1029, 562);
	  
		getContentPane().setLayout(null);
		
		loadOrdersTable();
		
		table.setBounds(1, 56, 1012, 408);
		table.setFillsViewportHeight(true);
		getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(1, 51, 1014, 465);
		getContentPane().add(scrollPane);
		
	
	}
	@SuppressWarnings("null")
	private static void loadOrdersTable() throws SQLException {
		String[][]data = new String[takeOrder.countOrder()][4];
	    String[] columnName = { "Table ID","Name","Price","Delete Button"};
	    data = takeOrder.getOrder();
		table = new JTable(data, columnName);
		table.setBackground(new Color(204, 204, 204));

	}
}