package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FrameConnection.TableSelection;
import jdbc.DeleteOrder;
import jdbc.SelectTable;
import jdbc.TakeOrder;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class PayFrame extends JFrame {

	private JPanel contentPane;
	private static TakeOrder takeOrder;
	private static DeleteOrder deleteOrder;
	private JButton button;
	private static JList<String> list;
	private String [] dish = null;
	private SelectTable selectTable = null;
	
	
	public PayFrame() throws SQLException {
		takeOrder = new TakeOrder();
		deleteOrder = new DeleteOrder();
		selectTable = new SelectTable();
		createDesign();
	}
	
	private void createDesign() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 520);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPayView = new JLabel("Pay View");
		lblPayView.setForeground(new Color(255, 255, 255));
		lblPayView.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 35));
		lblPayView.setBounds(154, 70, 155, 55);
		contentPane.add(lblPayView);
		
		button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MainFrame.setShowOrderVisible();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ShowOrderFrame.setPayFrameInvisible();
			}
		});
		button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 21));
		button.setBackground(Color.WHITE);
		button.setBounds(15, 16, 112, 29);
		contentPane.add(button);
		
		
		list = new JList<String>(loadList());
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setBounds(53, 160, 150, 214);
		contentPane.add(list);
		contentPane.add(new JScrollPane());	
		
		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 24));
		lblPrice.setBounds(255, 183, 75, 35);
		contentPane.add(lblPrice);
		
		JLabel labelPriceSum = new JLabel("");
		labelPriceSum.setForeground(Color.WHITE);
		labelPriceSum.setText(String.valueOf(takeOrder.getPriceForOrder(dish.length,dish,TableSelection.getSelectedTable())));
		labelPriceSum.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 24));
		labelPriceSum.setBounds(345, 183, 94, 35);
		contentPane.add(labelPriceSum);
		
		JButton btnPay = new JButton("Pay For All");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				payForAllOrder();
			}
		});
		btnPay.setBackground(new Color(50, 205, 50));
		btnPay.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 21));
		btnPay.setBounds(255, 231, 184, 29);
		contentPane.add(btnPay);
		
		JButton btnSplitOrder = new JButton("SplitOrder");
		btnSplitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				splitOrderPrice();
			}
		});
		btnSplitOrder.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 21));
		btnSplitOrder.setBackground(new Color(50, 205, 50));
		btnSplitOrder.setBounds(255, 276, 184, 29);
		contentPane.add(btnSplitOrder);

	}
	private String[] loadList() throws SQLException {
		dish = takeOrder.getOrderedDishName(TableSelection.getSelectedTable());
		//System.out.print(takeOrder.getPriceForOrder(dish.length,dish,TableSelection.getSelectedTable()) + "\n");
		return dish;
	}
	@SuppressWarnings("deprecation")
	private void splitOrderPrice() {
		System.out.print(list.countComponents() + "\n");
		int selectedValue = 0;
		String select[];
		for(int i=0; i<dish.length; i++) {
			if(list.isSelectedIndex(i)) {
				selectedValue++;
				}	
		}
		select = new String[selectedValue];
		for(int i=0; i<selectedValue; i++) {
			select[i] = list.getSelectedValuesList().get(i).toString();
		}
		try {
			Object[] options = {"Yes,pay","No, back to pay view"};
			int choice = JOptionPane.showOptionDialog(null, "Price to pay for: "+ list.getSelectedValuesList().toString() + " = " + String.valueOf(takeOrder.getPriceForOrder(selectedValue,select,TableSelection.getSelectedTable())),"Split order",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[1]);
			if(choice==0){//Yes - option
				if(!deleteOrder.checkIsPay(selectedValue, select)) {
					deleteOrder.changeIsPay(selectedValue, select);
					deleteOrder.removeSplitedOrder(selectedValue, select);
					for(int i=0; i<selectedValue; i++) {
						if(list.isSelectedIndex(i)) {
							list.remove(i);
						}
					}
				if(list.countComponents()<=0)
				{
					selectTable.updateTableAvailable(TableSelection.getSelectedTable(), true);
				}
			}else {
				JOptionPane.showMessageDialog(null,"This order is already payed","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void payForAllOrder(){
		if(!deleteOrder.checkIsPay(TableSelection.getSelectedTable())) {
			JOptionPane.showMessageDialog(null,"The order has been finalized");
			deleteOrder.changeIsPay(TableSelection.getSelectedTable());
			deleteOrder.removeAllOrder(TableSelection.getSelectedTable());
			selectTable.updateTableAvailable(TableSelection.getSelectedTable(), true);
			try {
				MainFrame.setShowOrderVisible();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ShowOrderFrame.setPayFrameInvisible();
		}else {
			JOptionPane.showMessageDialog(null,"This order is already payed","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

