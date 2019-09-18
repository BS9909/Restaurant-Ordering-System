package Buttons;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jdbc.DeleteOrder;

@SuppressWarnings("serial")
public class ButtonTableEditor extends DefaultCellEditor {

	protected JButton jButton;
	private String label;
	private boolean clicked;
	private DeleteOrder deleteOrder; 
	
	public ButtonTableEditor(JTextField txt, JTable table, DefaultTableModel model) throws SQLException {
		super(txt);
		deleteOrder = new DeleteOrder();
		jButton = new JButton();
		jButton.setOpaque(false);

		//WHEN BUTTON IS CLICKED
		jButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.convertRowIndexToModel(table.getEditingRow());
				String valueFromTable = table.getModel().getValueAt(row, 0).toString();
				deleteOrder.removeFromDB(valueFromTable);
				model.removeRow(row);
				fireEditingStopped();
			}		
		});	
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj,boolean selected,int row,int column){
		label = (obj.toString());
		jButton.setText(label);
		clicked = true;
		return jButton;
	}
	@Override
	public Object getCellEditorValue(){
		
		if(clicked) {
			JOptionPane.showMessageDialog(jButton,"You deleted this order!");
		}
		clicked = false;
		
		return new String(label);
	}
	@Override
	public boolean stopCellEditing() {
		
		 clicked = false;
		 return super.stopCellEditing();
	}
	@Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
	

}

