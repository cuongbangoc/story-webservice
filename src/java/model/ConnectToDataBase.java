package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDataBase {
	private String url;
	private String driver;
	private Connection conn;
	private String username;
	private String pass;
	public ConnectToDataBase() {
		url = "jdbc:mysql://localhost:3306/storyandroid";
		driver = "com.mysql.jdbc.Driver";
		conn = null;
		username = "root";
		pass = "";
		//connect();
	}
	public ConnectToDataBase(String databaseName, String driver, Connection conn,String username, String pass) {
		this.url = "jdbc:mysql://localhost:3306/" + databaseName;
		this.driver = driver;
		this.conn = conn;
		this.username = username;
		this.pass = pass;
		
	}
	
	public ConnectToDataBase(String databaseName,String username,String pass) {
		url = "jdbc:mysql://localhost:3306/" + databaseName;
		driver = "com.mysql.jdbc.Driver";
		conn = null;
		this.username = username;
		this.pass = pass;
	}
	
	// Open Connection
	public Connection connect(){
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,pass);
		}
		catch (SQLException e){
			System.out.println( e.getMessage());
		}catch(Exception e){
			System.out.println("Lỗi không kết nối được CSDL");
			e.printStackTrace();
		}
		return conn;
	}
	
	//Close Connection
	public void close() {
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Lỗi không đóng được kết nối CSDL");
				e.printStackTrace();
			}
		}
	}
	
	//Insert , Update , Delete
	public boolean execute(String sqlQuery) {
		try {
			connect();
			Statement stt = conn.createStatement();
			//stt.execute(sqlQuery);
			stt.executeUpdate(sqlQuery);
			return true;
		} catch (SQLException e) {
			System.out.println("Lỗi không thực hiện được câu truy vấn SQL");
			e.printStackTrace();
			return false;
		}finally{
			//close();
		}
	}
	
	//Select
	public ResultSet executeQuerry(String sqlQuery) {
		try {
			connect();                        
			Statement stt = conn.createStatement();
			ResultSet rs = stt.executeQuery(sqlQuery);
			return rs;
		} catch (SQLException e) {
                                 
			System.out.println("Lỗi không thực hiên được câu truy vấn SELECT");
			e.printStackTrace();
			return null;
		}finally{
			//close();
		}
	}
}
