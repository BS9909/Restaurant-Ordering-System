package FrameConnection;

public class TableSelection {
	private static String tableId;
	
	public static void setSelectedTable(String selectedTable) {
		tableId = selectedTable;
	}
	public static String getSelectedTable() {
		return tableId;
	}
}
