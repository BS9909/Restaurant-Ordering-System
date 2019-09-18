package jdbc;

 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteOrder {
 	private static MakeConnection makeConnection;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public DeleteOrder() throws SQLException {
		makeConnection = MakeConnection.getInstance();
	}
	public void removeFromDB(String orderID) {
		String querry = "DELETE FROM orders WHERE order_id = ?";
		try {
		preparedStatement = makeConnection.getConnection().prepareStatement(querry);
		preparedStatement.setString(1, orderID);
		preparedStatement.executeUpdate();
		}catch(Exception e) {
			System.out.print("Error in removeFromDB() " + e.getMessage()) ;
		}
	}
	public void removeAllOrder(String tableId) {
		String query = "DELETE FROM orders WHERE table_id = ?";
		try {
		preparedStatement = makeConnection.getConnection().prepareStatement(query);
		preparedStatement.setString(1, tableId);
		preparedStatement.executeUpdate();
		}catch(Exception e) {
			System.out.print("Error in removeAllOrder() " + e.getMessage()) ;
		}
	}
	public void removeSplitedOrder(int selecteDishAmount, String dishNames[]) {
		String query = "DELETE FROM orders WHERE dish_name = ?";
		try {
		preparedStatement = makeConnection.getConnection().prepareStatement(query);
		for(int i=0; i<selecteDishAmount; i++) {
			preparedStatement.setString(1, dishNames[i]);
			preparedStatement.executeUpdate();
		}
		}catch(Exception e) {
			System.out.print("removeSplitedOrder(int selecteDishAmount, String dishNames[])" + e.getMessage()) ;
		}
	}
	public void changeIsPay(int selecteDishAmount, String dishNames[]) {
		String query = "UPDATE orders SET is_pay = 1 WHERE dish_name = ?";
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(query);
			for(int i=0; i<selecteDishAmount; i++) {
				preparedStatement.setString(1, dishNames[i]);
				preparedStatement.executeUpdate();
			}
			}catch(Exception e) {
				System.out.print("changeIsPay(int,selecteDishAmount, String dishNames[])  " + e.getMessage()) ;
			}
	}
	public void changeIsPay(String tableId) {
		String query = "UPDATE orders SET is_pay = 1 WHERE table_id = ?";
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, tableId);
			preparedStatement.executeUpdate();
			}
		catch(Exception e) {
				System.out.print("changeIsPay(int,selecteDishAmount, String dishNames[])  " + e.getMessage()) ;
			}
	}
	public boolean checkIsPay(int selecteDishAmount, String dishNames[]) {
		boolean isPay = true;
		String query = "SELECT is_pay FROM orders WHERE dish_name = ?";
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(query);
			for(int i = 0; i <selecteDishAmount; i++) {
				preparedStatement.setString(1, dishNames[i]);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					if(!resultSet.getBoolean(1))
						isPay = false;
				}
			}
			
			}catch(Exception e) {
				System.out.print("changeIsPay(int,selecteDishAmount, String dishNames[])  " + e.getMessage()) ;
			}
		return isPay;
	}
	public boolean checkIsPay(String table_id) {
		boolean isPay = true;
		String query = "SELECT is_pay FROM orders WHERE table_id = ?";
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, table_id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				if(!resultSet.getBoolean(1))
					isPay = false;
				}
			}
		catch(Exception e) {
				System.out.print("changeIsPay(int,selecteDishAmount, String dishNames[])  " + e.getMessage()) ;
			}
		return isPay;
	}
}
