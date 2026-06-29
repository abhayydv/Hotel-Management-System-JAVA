package com.hotel.managent.system.one;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;


public class HotelManagementSystem {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/hotel_db_01";
	private static final String username = "root";
	private static final String password = "Your_password_here";
	
	public static void main(String args[]) throws ClassNotFoundException , SQLException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try (Connection connection = DriverManager.getConnection(url,username,password);){
			Scanner sc = new Scanner(System.in);

			while(true){
				System.out.println();
				System.out.println("HOTEL MANAGEMENT SYSTEM");
				System.out.println("1. Reserve a room");
				System.out.println("2. View Reservations");
				System.out.println("3. Get Room Number");
				System.out.println("4. Update Reservations");
				System.out.println("5. Delete Reservations");
				System.out.println("0. Exit");
				System.out.print("Enter your choice : ");
				int choice = sc.nextInt();
				
				switch(choice) {
					case 1 :
						reserveRoom(connection,sc);
						break;
					case 2:
						viewReservations(connection);
						break;
					case 3:
						getRoomNumber(connection,sc);
						break;
					case 4:
						updateReservations(connection,sc);
						break;
					case 5:
						deleteReservation(connection,sc);
						break;
					case 0:
						exit();
						sc.close();
						return;
					default:
						System.out.println("Invalid choice. Try again.");
				}
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void reserveRoom(Connection connection,Scanner sc) {
		try {
			sc.nextLine();
			System.out.print("Enter guest name: ");
			String guestName = sc.nextLine();
			
			System.out.print("Enter room number: ");
			int roomNumber = sc.nextInt();
			System.out.print("Enter contact number: ");
			String contactNumber = sc.next();
			
			String sql = "INSERT INTO reservations(guest_name , room_number,contact_number)"
					+ "VALUES('"+guestName+ "',"+roomNumber+",'"+contactNumber+"')";
			
			try (Statement statement = connection.createStatement()){
				int affectedRows = statement.executeUpdate(sql);
				
				if(affectedRows > 0) {
					System.out.println("Reservation succesful!");
				}else {
					System.out.println("Reservation failed");
				}
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void viewReservations(Connection connection) throws SQLException {
		String sql = "Select reservation_id,guest_name,room_number,contact_number,reservation_date from reservations;";
		
		try (Statement statement = connection.createStatement()){
			ResultSet rs = statement.executeQuery(sql);
			
			System.out.println("Current Reservations: ");
			System.out.println("+----------------+--------------------+----------------+--------------------+---------------------------+");
			System.out.println("| Reservation ID |  Guest             |  Room Number   |  Contact Number    |  Reservation Date         |");
			System.out.println("+----------------+--------------------+----------------+--------------------+---------------------------+");
			
			
			while(rs.next()) {
				int reservationId = rs.getInt("reservation_id");
				String guestName = rs.getString("guest_name");
				int roomNumber = rs.getInt("room_number");
				String contactNumber = rs.getString("contact_number");
				String reservationDate = rs.getString("reservation_date").toString();
				
				System.out.printf("| %-14d | %-15s    | %-13d  | %-20s  | %-19s  |%n",
				        reservationId, guestName, roomNumber, contactNumber, reservationDate);
			}
			
			System.out.println("+----------------+--------------------+----------------+--------------------+---------------------------+");
		}
	}
	
	public static void getRoomNumber(Connection connection , Scanner sc) {
		try {
			System.out.print("Enter reservation ID: ");
			int reservationId = sc.nextInt();
			
			System.out.print("Enter guest name: ");
			String guestName = sc.next();
			
			String sql = "select room_number from reservations where reservation_id = "+reservationId+" and "
					+ "guest_name = '"+guestName+"';";
			
			try (Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(sql)){
				
				if(rs.next()) {
					int roomNumber = rs.getInt("room_number");
					System.out.println("Room number for Resrvation ID "+reservationId+" and guest "+ guestName+" is: "+roomNumber);
				}else {
					System.out.println("Reservation not found for given ID and guest name.");
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateReservations(Connection connection , Scanner sc) {
		try {
			System.out.print("Enter reservation ID to update: ");
			int reservationId = sc.nextInt();
			sc.nextLine();
			
			if(!reservationExists(connection,reservationId)) {
				System.out.prinln("Reservation not found for the given ID.");
				return;
			}
			System.out.print("Enter new guest name: ");
			String name = sc.nextLine();
			System.out.print("Enter new room number: ");
			int newRoom = sc.nextInt();
			System.out.print("Enter new contact number: ");
			String contact = sc.next();
			
			String sql = "Update reservations set guest_name = '"+name+"',"+"room_number = "+newRoom+","+"contact_number= '"+
			contact+"' "+"where reservation_id = "+reservationId+";";
			
			try (Statement statement = connection.createStatement()){
				int affectedRow = statement.executeUpdate(sql);
				
				if(affectedRow > 0) {
					System.out.println("Reservation updated succesfully!");
				}else {
					System.out.println("Reservation update failed");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteReservation(Connection connection , Scanner sc) {
		try {
			System.out.print("Enter reservation ID to delete: ");
			int id = sc.nextInt();
			
			if(!reservationExists(connection,id)) {
				System.out.println("Reservation not found for the given ID.");
				return;
			}
			
			String sql = "Delete from reservations where reservation_id = "+id;
			
			try(Statement statement = connection.createStatement()){
				int affectedRow = statement.executeUpdate(sql);
				
				if(affectedRow > 0) {
					System.out.println("Reservation deleted succesfully!");
				}else {
					System.out.println("Reservation deleted failed!");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean reservationExists(Connection connection,int id) {
		try {
			String sql = "Select reservation_id from reservations where reservation_id = "+id;
			
			try(Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(sql)){
				
				return rs.next();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void exit() throws InterruptedException{
		System.out.print("Exiting System");
		int i = 5;
		while(i != 0) {
			System.out.print(".");
			Thread.sleep(450);
			i--;
		}
		System.out.println();
		System.out.println("ThankYou For Using Hotem Reservation System!");
	}
}