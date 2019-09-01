package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MainClass  {

	private static String jdbcURL = "jdbc:mysql://localhost:3306/reastaurant_db?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "sochacki24";
	
	private static boolean error = false;	
	private static Connection connection = null;
	private static ResultSet resultSet = null;
	static EditOrder editOrder = null;
	static MakeOrder makeOrder = null;
	static Payment payment = null;
	
	public static void main(String[] args) throws SQLException {
		try {
			connection = DriverManager.getConnection(jdbcURL,user,password);
			System.out.print("Connected to the data base ");
			
			makeOrder = new MakeOrder(connection);
			editOrder = new EditOrder(connection);
			payment = new Payment(connection, "IN1");
			
		//	makeOrder.setOrder("Polêdwiczki","Martini" ,"IN4");
		//	makeOrder.setOrderDish("Kurczak", "OUT2");
		//	makeOrder.setOrderDrink("coca-cola", "OUT3");
		//	editOrder.deleteAllOrder("IN1");
		//	editOrder.editDish("Indyk", "Polêdwiczki", "IN2");
		//	editOrder.editDrink("coca-cola", "Martini", "IN1");
		//	editOrder.deleteDish("Szarlotka", "IN5");
		//	editOrder.deleteDrink("coca-cola","OUT3");
			payment.setPay();
			payment.displaySum();
			payment.deleteAllOrder("IN1");
		}
		catch(Exception exception) {
			error = true;
			exception.printStackTrace();
		}
		finally{
			if(connection!=null)			connection.close();
			if(error)						System.out.print("wtffff ja pier");
			else							System.out.print("Program finished");
		}	
	}
	
}