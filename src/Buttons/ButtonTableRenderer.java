package Buttons;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class ButtonTableRenderer extends JButton implements TableCellRenderer {

	public ButtonTableRenderer() {
		setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row,
			int column) {
		setText(obj.toString());
		return this;
	}
}
