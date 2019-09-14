package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TakeOrder {

	private static Connection connection = null;
	private static ResultSet resultSet	= null;
	private static Statement statement = null;
	private static CallableStatement callableStatement ;
	private static MakeConnection makeConnection = null;
	
	public TakeOrder() throws SQLException {
		makeConnection = new MakeConnection(connection);
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
	public String[][] getOrder() throws SQLException {
		int i = 0;
		String data[][] = new String[countOrder()][4];
		String querry = "SELECT table_id, dish_name,dish_price FROM orders";
		try {
			statement = makeConnection.getConnection().createStatement();
			resultSet = statement.executeQuery(querry);
		    while(resultSet.next()) {
		    	 String restTableId = resultSet.getString(1);
		    	 String name = resultSet.getString(2);
		    	 int price = resultSet.getInt(3);
		    	 data[i][0] = restTableId;
		    	 data[i][1] = name;
		    	 data[i][2] = String.valueOf(price);
		    	 data[i][3] = "Delete";
		    	 i++;
		    }
		    System.out.print(i);
		}catch(Exception e) {
			System.out.print("getOrder(String tableId) error " + e.getMessage());
		}
		return data;
	}
}
