package myPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;


public class Student{
	public static void main(String args[]) {
		//Student stu=new Student();
		Scanner sc=new Scanner(System.in);
		int option;
		do {
		System.out.println("Enter any option:");
		System.out.println("1.Create a student");
		System.out.println("2.Read students data from table");
		System.out.println("3.Update student");
		System.out.println("4.Delete the student from table");
		System.out.println("5.Exit");
		option=sc.nextInt();
		switch(option) {
		case 1:createUser(sc);
		break;
		case 2:readUsers();
		break;
		case 3:updateUser(sc);
		break;
		case 4:deleteData(sc);
		break;
		case 5:System.out.println("Exiting.....");
		break;
		default:System.out.println("Invalid option.Please try again.");
		}
	}while(option!=5);
		sc.close();
	}
	private static void createUser (Scanner sc) {
	    String url = "jdbc:mysql://localhost:3306/db";
	    String username = "root";
	    String password = "psanju18";
	    String q = "INSERT INTO Student(Stu_id, Stu_name, Branch, Email_address) VALUES(?, ?, ?, ?)";
	    
	    try (Connection conn = DriverManager.getConnection(url, username, password);
	         PreparedStatement pst = conn.prepareStatement(q)) {
	        
	        System.out.print("Enter student id: ");
	        int id = sc.nextInt();
	        sc.nextLine(); // Consume newline
	        System.out.print("Enter student name: ");
	        String name = sc.nextLine();
	        System.out.print("Enter branch: ");
	        String branch = sc.nextLine();
	        System.out.print("Enter email address: ");
	        String email = sc.nextLine();
	        
	        pst.setInt(1, id);
	        pst.setString(2, name);
	        pst.setString(3, branch);
	        pst.setString(4, email);
	        pst.executeUpdate();
	        
	        System.out.println("Data inserted successfully");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
private static void readUsers() {
	// TODO Auto-generated method stub
	
	try {
		String url="jdbc:mysql://localhost:3306/db";
		String username="root";
		String password="psanju18";
		Connection conn=DriverManager.getConnection(url,username,password);
		PreparedStatement pst=conn.prepareStatement("Select *from Student");
		
		ResultSet set=pst.executeQuery();
			while(set.next()) {
			int Stu_id=set.getInt("Stu_id");
			System.out.println("Id:"+Stu_id);
			String Stu_name=set.getString("Stu_name");
			System.out.println("Name:"+Stu_name);
			String Branch=set.getString("Branch");
			System.out.println("Branch:"+Branch);
			String Email_address=set.getString("Email_address");
			System.out.println("Email:"+Email_address);
			
		}
		System.out.println("data retrived successfully");
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

private static void updateUser (Scanner sc) {
    System.out.println("Enter user id to update:");
    int id = sc.nextInt();
    sc.nextLine();
    System.out.println("Enter new name:");
    String name = sc.nextLine();
    System.out.println("Enter new branch:");
    String branch = sc.nextLine();
    System.out.println("Enter new email:");
    String email = sc.nextLine();
    String q = "UPDATE Student SET Stu_name=?, Branch=?, Email_address=? WHERE Stu_id=?";
    
    try (Connection conn = DriverManager.getConnection(url, username, password);
         PreparedStatement pst = conn.prepareStatement(q)) {
        
        pst.setString(1, name);
        pst.setString(2, branch);
        pst.setString(3, email);
        pst.setInt(4, id);
        
        pst.executeUpdate();
        System.out.println("Student data updated.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private static void deleteData(Scanner sc) {
    try {
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "psanju18";
        System.out.println("Enter user id to delete:");
        int id = sc.nextInt();
        String q = "DELETE FROM Student WHERE Stu_id=?";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement(q)) {
            
            pst.setInt(1, id);
            int delete = pst.executeUpdate();
            if (delete > 0) {
                System.out.println("Student with ID " + id + " deleted successfully");
            } else {
                System.out.println("No user found with ID " + id + ".");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}