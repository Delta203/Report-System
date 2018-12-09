package de.reportsystem.delta203.bukkit.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.reportsystem.delta203.bukkit.files.ConfigYML;

public class MySQl {

public static Connection con;
	
	public static void connect() {
		if(!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + ConfigYML.get().getString("MySQl_url") + ":" + ConfigYML.get().getString("MySQl_port") +"/" + ConfigYML.get().getString("MySQl_database") + "?autoReconnect=true", ConfigYML.get().getString("MySQl_user"), ConfigYML.get().getString("MySQl_password"));
			} catch (final SQLException e) {
				e.printStackTrace();
				System.out.println("MySQl has been connected.");
			}
		}
	}
	
	public static void disconnect() {
		if(isConnected()) {
			try {
				con.close();
				System.out.println("MySQl connection canceled.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isConnected() {
		return(con != null);
	}
	
	public static void createTable() {
		try {
			con.prepareStatement("CREATE TABLE IF NOT EXISTS Reports (SpielerNAME VARCHAR(100),FromNAME VARCHAR(100),Reason VARCHAR(100),Server VARCHAR(100), IsOnline BOOLEAN)").executeUpdate();
			System.out.println("MySQl table has been created.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
