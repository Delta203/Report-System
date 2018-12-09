package de.reportsystem.delta203.bukkit.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.reportsystem.delta203.bukkit.ReportSystem;

public class Register {

	public Register() {
		register();
	}
	
	/** every 15 minutes */
	private void register() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(ReportSystem.plugin, new Runnable() {
			
			@Override
			public void run() {
				try {
					PreparedStatement ps = MySQl.con.prepareStatement("SELECT * FROM Reports");
					ps.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		},0, 20 * 60 * 15);
	}
}
