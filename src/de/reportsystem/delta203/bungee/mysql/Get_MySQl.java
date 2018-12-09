package de.reportsystem.delta203.bungee.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Get_MySQl {

	public static List<String> getReportedPlayers() {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("SELECT * FROM Reports");
			ResultSet rs = ps.executeQuery();
			ArrayList<String> req = new ArrayList<>();
			while(rs.next()) {
				req.add(rs.getString("SpielerNAME"));
			}
			return req;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getFrom(String reportedplayername) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("SELECT FromNAME FROM Reports WHERE SpielerNAME = ?");
			ps.setString(1, reportedplayername);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("FromNAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getReason(String reportedplayername) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("SELECT Reason FROM Reports WHERE SpielerNAME = ?");
			ps.setString(1, reportedplayername);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("Reason");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getServer(String reportedplayername) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("SELECT Server FROM Reports WHERE SpielerNAME = ?");
			ps.setString(1, reportedplayername);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("Server");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setServer(String server, String reportedplayername) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("UPDATE Reports SET Server = ? WHERE SpielerNAME = ?");
			ps.setString(1, server);
			ps.setString(2, reportedplayername);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isOnline(String name) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("SELECT IsOnline FROM Reports WHERE SpielerNAME = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getBoolean("IsOnline");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static void setOnline(int bool, String name) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("UPDATE Reports SET IsOnline = ? WHERE SpielerNAME = ?");
			ps.setInt(1, bool);
			ps.setString(2, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addReport(String reportedplayername, String fromplayername, String reason, String server, int state) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("INSERT INTO Reports (SpielerNAME,FromNAME,Reason,Server,IsOnline) VALUES (?,?,?,?,?)");
			ps.setString(1, reportedplayername);
			ps.setString(2, fromplayername);
			ps.setString(3, reason);
			ps.setString(4, server);
			ps.setInt(5, state);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteReport(String reportedplayername, String fromplayername) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("DELETE FROM Reports WHERE SpielerNAME = ? AND FromNAME = ?");
			ps.setString(1, reportedplayername);
			ps.setString(2, fromplayername);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
