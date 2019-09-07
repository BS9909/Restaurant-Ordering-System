package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc.AddOrder;
import jdbc.SelectDishes;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Button;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;


public class AddOrderFrame extends JFrame {

	private JPanel contentPane;
	private static JComboBox meatComboBox = null;
	private static JComboBox soupComboBox = null;
	private static JComboBox desertComboBox = null;
	private static JComboBox otherComboBox = null;
	private static JComboBox saladsComboBox = null;
	private static JComboBox drinkComboBox = null;
	private static Button addButton = null;
	private static SelectDishes selectDishes = null;
	private static JComboBox fishComboBox = null;
	private static JComboBox othercomboBox = null;
	private static JComboBox additivescomboBox = null;
	private static JComboBox hotDrinkComboBox = null;
	private static JComboBox alcoholComboBox = null;
	private static JComboBox coldDrinkComboBox = null;
	private static JComboBox orderComboBox = null;
	private static SelectTableFrame selectTableFrame = null;
	static JLabel tableIdLabel = null;
	private String meatName,soupName,saladsName,fishName,otherName,additivesName,desertName,coldDrinkName,hotDrinkName,alcoholName;
	private static AddOrder addOrder = null;
	
	public AddOrderFrame() {
		
		try {
			selectDishes = new SelectDishes();
			selectTableFrame = new SelectTableFrame();
			addOrder = new AddOrder();
			createDesing();

		} catch (SQLException e) {
			System.out.print("Error in AddOrderForm constructor" + e.getMessage());
			e.printStackTrace();
		}

	}
	
	
	public static void addFoodToComboBox(String tableName, String columnName,String type,String food) throws SQLException {
		DefaultComboBoxModel<String>comboBoxModel =new DefaultComboBoxModel<>();
		
		for(int i=0; i<selectDishes.countOrder(tableName,type,food); i++) {
			comboBoxModel.addElement(selectDishes.insertOrder(tableName,columnName,type,food).get(i).toString());
		}
		if(food == "soup") {
			soupComboBox.setModel(comboBoxModel);
		}
		if(food == "meat") {
			meatComboBox.setModel(comboBoxModel);
		}
		if(food == "desert") {
			desertComboBox.setModel(comboBoxModel);
		}
		if(food == "other") {
			othercomboBox.setModel(comboBoxModel);
		}
		if(food == "salads") {
			saladsComboBox.setModel(comboBoxModel);
		}
		if(food == "fish") {
			fishComboBox.setModel(comboBoxModel);
		}
		if(food == "additives") {
			additivescomboBox.setModel(comboBoxModel);
		}
		
	}
	public static void addDrinkToComboBox(String tableName, String firstColumnName,String secondColumnName,String drink) throws SQLException {
		DefaultComboBoxModel<String>comboBoxModel = new DefaultComboBoxModel<>();
		
		for(int i=0; i<selectDishes.countOrder(tableName,secondColumnName,drink); i++) {
			comboBoxModel.addElement(selectDishes.insertOrder(tableName,firstColumnName,secondColumnName,drink).get(i).toString());
		}
		if(drink == "Cold Drink") {
			coldDrinkComboBox.setModel(comboBoxModel);
		}
		if(drink == "Hot Drink") {
			hotDrinkComboBox.setModel(comboBoxModel);
		}
		if(drink == "Alcohol") {
			alcoholComboBox.setModel(comboBoxModel);
		}
		
	}

	private void sendOrder() {
		meatName = (String) meatComboBox.getSelectedItem(); 
		soupName = (String) soupComboBox.getSelectedItem(); 
		desertName = (String) desertComboBox.getSelectedItem(); 
		additivesName = (String) additivescomboBox.getSelectedItem(); 
		fishName = (String) fishComboBox.getSelectedItem(); 
		otherName = (String) othercomboBox.getSelectedItem(); 
		saladsName = (String) saladsComboBox.getSelectedItem(); 
		coldDrinkName = (String) coldDrinkComboBox.getSelectedItem(); 
		hotDrinkName = (String) hotDrinkComboBox.getSelectedItem(); 
		alcoholName = (String) alcoholComboBox.getSelectedItem(); 
		try {
		if(meatComboBox.getSelectedItem() != null)			addOrder.insertDish(SelectTableFrame.getSelectedTable(), meatName);
		if(soupComboBox.getSelectedItem() != null)			addOrder.insertDish(SelectTableFrame.getSelectedTable(), soupName);;
		if(desertComboBox.getSelectedItem() != null)		addOrder.insertDish(SelectTableFrame.getSelectedTable(), desertName);
		if(saladsComboBox.getSelectedItem() != null)		addOrder.insertDish(SelectTableFrame.getSelectedTable(), saladsName);
		if(fishComboBox.getSelectedItem() != null)			addOrder.insertDish(SelectTableFrame.getSelectedTable(), fishName);
		if(othercomboBox.getSelectedItem() != null)			addOrder.insertDish(SelectTableFrame.getSelectedTable(), otherName);
		if(additivescomboBox.getSelectedItem() != null)		addOrder.insertDish(SelectTableFrame.getSelectedTable(), additivesName);
		if(coldDrinkComboBox.getSelectedItem() != null)		addOrder.insertDrink(SelectTableFrame.getSelectedTable(), coldDrinkName);
		if(hotDrinkComboBox.getSelectedItem() != null)		addOrder.insertDrink(SelectTableFrame.getSelectedTable(), hotDrinkName);
		if(alcoholComboBox.getSelectedItem() != null)		addOrder.insertDrink(SelectTableFrame.getSelectedTable(), alcoholName);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private void createDesing() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1015, 591);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel newOrderLabel = new JLabel("Add new order for table: ");
		newOrderLabel.setForeground(new Color(255, 255, 255));
		newOrderLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 45));
		newOrderLabel.setBounds(160, 42, 492, 86);
		contentPane.add(newOrderLabel);
		
		JLabel meatLabel = new JLabel("Meat");
		meatLabel.setForeground(new Color(255, 255, 255));
		meatLabel.setBounds(30, 167, 97, 20);
		contentPane.add(meatLabel);
		
		JLabel soupLabel = new JLabel("Soup");
		soupLabel.setForeground(new Color(255, 255, 255));
		soupLabel.setBounds(30, 203, 97, 20);
		contentPane.add(soupLabel);
		
		JLabel desertLabel = new JLabel("Desert");
		desertLabel.setForeground(new Color(255, 255, 255));
		desertLabel.setBounds(30, 239, 97, 20);
		contentPane.add(desertLabel);
		
		JLabel saladsLabel = new JLabel("Salads");
		saladsLabel.setForeground(new Color(255, 255, 255));
		saladsLabel.setBounds(30, 275, 102, 20);
		contentPane.add(saladsLabel);
		
		JLabel drinkLabel = new JLabel("Cold Drink");
		drinkLabel.setForeground(new Color(255, 255, 255));
		drinkLabel.setBounds(532, 242, 97, 20);
		contentPane.add(drinkLabel);
		
		orderComboBox = new JComboBox();
		orderComboBox.setBounds(365, 457, 320, 26);
		contentPane.add(orderComboBox);
		
		meatComboBox = new JComboBox();
		meatComboBox.setBounds(142, 164, 231, 26);
		contentPane.add(meatComboBox);
		
		soupComboBox = new JComboBox();
		soupComboBox.setBounds(142, 200, 231, 26);
		contentPane.add(soupComboBox);
		
		desertComboBox = new JComboBox();
		desertComboBox.setBounds(142, 236, 231, 26);
		contentPane.add(desertComboBox);
		
		saladsComboBox = new JComboBox();
		saladsComboBox.setBounds(142, 272, 231, 26);
		contentPane.add(saladsComboBox);
		
	   
		JLabel hotDrinkLabel = new JLabel("Hot Drink");
		hotDrinkLabel.setForeground(Color.WHITE);
		hotDrinkLabel.setBounds(532, 269, 68, 20);
		contentPane.add(hotDrinkLabel);
		
		JLabel alcoholDrinkLabel = new JLabel("Alcohol");
		alcoholDrinkLabel.setForeground(Color.WHITE);
		alcoholDrinkLabel.setBounds(532, 305, 60, 20);
		contentPane.add(alcoholDrinkLabel);
		
		JLabel additivesLabel = new JLabel("Additives");
		additivesLabel.setForeground(new Color(255, 255, 255));
		additivesLabel.setBounds(30, 311, 69, 20);
		contentPane.add(additivesLabel);
		
		JLabel fishLabel = new JLabel("Fish");
		fishLabel.setForeground(Color.WHITE);
		fishLabel.setBounds(532, 170, 97, 20);
		contentPane.add(fishLabel);
		
		JLabel otherLabel = new JLabel("Other");
		otherLabel.setForeground(Color.WHITE);
		otherLabel.setBounds(532, 206, 97, 20);
		contentPane.add(otherLabel);
		
		fishComboBox = new JComboBox();
		fishComboBox.setBounds(632, 167, 231, 26);
		contentPane.add(fishComboBox);
		
		othercomboBox = new JComboBox();
		othercomboBox.setBounds(632, 203, 231, 26);
		contentPane.add(othercomboBox);
		
		coldDrinkComboBox = new JComboBox();
		coldDrinkComboBox.setBounds(632, 239, 231, 26);
		contentPane.add(coldDrinkComboBox);
		
		alcoholComboBox = new JComboBox();
		alcoholComboBox.setBounds(632, 311, 231, 26);
		contentPane.add(alcoholComboBox);
		
		hotDrinkComboBox = new JComboBox();
		hotDrinkComboBox.setBounds(632, 275, 231, 26);
		contentPane.add(hotDrinkComboBox);
		
		additivescomboBox = new JComboBox();
		additivescomboBox.setBounds(142, 308, 231, 26);
		contentPane.add(additivescomboBox);
		
		addFoodToComboBox("main_dishes","dish_name","dish_type","soup");
		addFoodToComboBox("main_dishes","dish_name","dish_type","meat");
		addFoodToComboBox("main_dishes","dish_name","dish_type","desert");
		addFoodToComboBox("main_dishes","dish_name","dish_type","other");
		addFoodToComboBox("main_dishes","dish_name","dish_type","salads");
		addFoodToComboBox("main_dishes","dish_name","dish_type","fish");
		addFoodToComboBox("main_dishes","dish_name","dish_type","additives");
		addDrinkToComboBox("drink","drink_name","drink_type","Cold Drink");
		addDrinkToComboBox("drink","drink_name","drink_type","Hot Drink");
		addDrinkToComboBox("drink","drink_name","drink_type","Alcohol");
		meatComboBox.setSelectedItem(null);
		soupComboBox.setSelectedItem(null);
		desertComboBox.setSelectedItem(null);
		othercomboBox.setSelectedItem(null);
		saladsComboBox.setSelectedItem(null);
		fishComboBox.setSelectedItem(null);
		additivescomboBox.setSelectedItem(null);
		hotDrinkComboBox.setSelectedItem(null);
		coldDrinkComboBox.setSelectedItem(null);
		alcoholComboBox.setSelectedItem(null);
		
		tableIdLabel = new JLabel("");
		tableIdLabel.setForeground(new Color(255, 255, 255));
		tableIdLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 45));
		tableIdLabel.setText(SelectTableFrame.getSelectedTable());
		tableIdLabel.setBounds(664, 42, 167, 69);
		contentPane.add(tableIdLabel);
		
		JButton deleteMeatButton = new JButton("delete");
		deleteMeatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				meatComboBox.setSelectedItem(null);
			}
		});
		deleteMeatButton.setBounds(388, 163, 82, 29);
		contentPane.add(deleteMeatButton);
		
		JButton deleteSoupButton = new JButton("delete");
		deleteSoupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soupComboBox.setSelectedItem(null);
			}
		});
		deleteSoupButton.setBounds(388, 199, 82, 29);
		contentPane.add(deleteSoupButton);
		
		JButton deleteDesertButton = new JButton("delete");
		deleteDesertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desertComboBox.setSelectedItem(null);
			}
		});
		deleteDesertButton.setBounds(388, 235, 82, 29);
		contentPane.add(deleteDesertButton);
		
		JButton deleteSaladsButton = new JButton("delete");
		deleteSaladsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saladsComboBox.setSelectedItem(null);
			}
		});
		deleteSaladsButton.setBounds(388, 271, 82, 29);
		contentPane.add(deleteSaladsButton);
		
		
		JButton deleteAdditivesButton = new JButton("delete");
		deleteAdditivesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				additivescomboBox.setSelectedItem(null);
			}
		});
		deleteAdditivesButton.setBounds(388, 307, 82, 29);
		contentPane.add(deleteAdditivesButton);
		
		JButton deleteFishButton = new JButton("delete");
		deleteFishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fishComboBox.setSelectedItem(null);
			}
		});
		deleteFishButton.setBounds(878, 163, 82, 29);
		contentPane.add(deleteFishButton);
		
		JButton deleteOtherButton = new JButton("delete");
		deleteOtherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				othercomboBox.setSelectedItem(null);
			}
		});
		deleteOtherButton.setBounds(878, 199, 82, 29);
		contentPane.add(deleteOtherButton);
		
		JButton deleteColdButton = new JButton("delete");
		deleteColdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coldDrinkComboBox.setSelectedItem(null);
			}
		});
		deleteColdButton.setBounds(878, 235, 82, 29);
		contentPane.add(deleteColdButton);
		
		JButton deleteHotButton = new JButton("delete");
		deleteHotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotDrinkComboBox.setSelectedItem(null);
			}
		});
		deleteHotButton.setBounds(878, 271, 82, 29);
		contentPane.add(deleteHotButton);
		
		JButton deleteAlcoholbutton = new JButton("delete");
		deleteAlcoholbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alcoholComboBox.setSelectedItem(null);
			}
		});
		deleteAlcoholbutton.setBounds(878, 311, 82, 29);
		contentPane.add(deleteAlcoholbutton);
		
		addButton = new Button("Add");
		    addButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		sendOrder();
		    	}
		    	
		    });
	    addButton.setForeground(new Color(0, 0, 0));
	    addButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 23));		
	    addButton.setBackground(new Color(0, 153, 51));	
	    addButton.setBounds(423, 357, 206, 94);
	    contentPane.add(addButton);

	}
}
