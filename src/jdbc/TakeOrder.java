package jdbc;

 import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TakeOrder {

 	private static ResultSet resultSet	= null;
	private static Statement statement = null;
	private static MakeConnection makeConnection = null;
	private static PreparedStatement preparedStatement = null;

	public TakeOrder() throws SQLException {
		makeConnection = MakeConnection.getInstance();
	}
	public int countOrder(){
		String querry = "SELECT COUNT(*) FROM orders";
		try {
			statement = makeConnection.getConnection().createStatement();
			resultSet = statement.executeQuery(querry);
			if(resultSet.next()) {
				return (resultSet.getInt(1));
			}
		}catch(Exception e) {
			System.out.print("Error in countOrder() " + e.getMessage());
		}
		return -1;
	}
	public int countOrderedDish(String tableId){
		String querry = "SELECT COUNT(dish_name) FROM orders WHERE table_id = ?";
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(querry);
			
			preparedStatement.setString(1, tableId);
			
			resultSet = preparedStatement.executeQuery();		
			
			if(resultSet.next()) {
				return (resultSet.getInt(1));
			}
		}catch(Exception e) {
			System.out.print("Error in countOrder() " + e.getMessage());
		}
		return -1;
	}
	public String[][] getOrder() throws SQLException {
		int i = 0;
		String data[][] = new String[countOrder()][5];
		String query = "SELECT order_id,table_id, dish_name,dish_price FROM orders ORDER BY table_id";
		try {
			statement = makeConnection.getConnection().createStatement();
			resultSet = statement.executeQuery(query);
		    while(resultSet.next()) {
		    	 String orderId = resultSet.getString(1);
		    	 String restTableId = resultSet.getString(2);
		    	 String name = resultSet.getString(3);
		    	 int price = resultSet.getInt(4);
		    	 data[i][0] = orderId;
		    	 data[i][1] = restTableId;
		    	 data[i][2] = name;
		    	 data[i][3] = String.valueOf(price);
		    	 data[i][4] = "Delete";
		    	 i++;
		    }
		}catch(Exception e) {
			System.out.print("getOrder(String tableId) error " + e.getMessage());
		}
		return data;
	}
	public int getOrderPrice(String tableId) {
		String query = "SELECT sum(dish_price) FROM orders WHERE table_id = ?";
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(query);
			
			preparedStatement.setString(1, tableId);
			
			resultSet = preparedStatement.executeQuery();
			
		    if(resultSet.next()) {
		    	 return resultSet.getInt(1);
		    }
		}catch(Exception e) {
			System.out.print("getOrder(String tableId) error " + e.getMessage());
		}
	    return -1;
	}
	public int countUnavailableTable() throws SQLException{
		String query = "SELECT count(*) FROM restaurants_tables WHERE table_available = 0;";
		try {
			statement = makeConnection.getConnection().createStatement();
			resultSet = statement.executeQuery(query);
		    if(resultSet.next()) {
		    	 return resultSet.getInt(1);
		    }
		}catch(Exception e) {
			System.out.print("getOrder(String tableId) error " + e.getMessage());
		}
		return -1;
	}
	public ArrayList<String> getUnavailableTable() throws SQLException {
		ArrayList<String> unavailableTableList = new ArrayList<>();
		String query = "SELECT table_id FROM restaurants_tables WHERE table_available = 0;";
		try {
			statement = makeConnection.getConnection().createStatement();
			resultSet = statement.executeQuery(query);
		    while(resultSet.next()) {
		    	unavailableTableList.add(resultSet.getString(1));
		    }
		}catch(Exception e) {
			System.out.print("getOrder(String tableId) error " + e.getMessage());
		}
		return unavailableTableList;
	}
	public String[] getOrderedDishName(String tableId) {
		int i = 0;
		String [] dishData = new String[countOrderedDish(tableId)];
		String query = "SELECT dish_name FROM orders WHERE table_id = ?";
		
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(query);
			
			preparedStatement.setString(1, tableId);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				dishData[i] = resultSet.getString(1); 
				i++;
		    }
		}catch(Exception e) {
			System.out.print("Error in getOrderDishName(String tableId) " + e.getMessage());
		}
		return dishData;
	}

	
	
	public int getPriceForOrder(int dishAmount, String[] orderId, String tableId) throws SQLException {
		String query1 = "SELECT sum(dish_price) FROM orders WHERE "; 
		String query2 = "";
		String finalQuery = "";
		
		for(int i=0; i<dishAmount-1; i++) {
			query2 += "(dish_name = ? AND table_id = '"+tableId+"') or ";
		}
		finalQuery = query1 + query2 + "(dish_name = ? AND table_id = '"+tableId+"')";
		//System.out.print(finalQuery);
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(finalQuery);

			for(int i=0; i<dishAmount; i++) {
				preparedStatement.setString(i+1, orderId[i]);
			}
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		}catch(Exception e) {
			System.out.print("Error in getPriceForOrder(int dishAmount, String[] dishNames) " + e.getMessage());
		}
		return -1;
	}
}
