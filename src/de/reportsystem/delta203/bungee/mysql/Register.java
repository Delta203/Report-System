package de.reportsystem.delta203.bungee.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import de.reportsystem.delta203.bungee.ReportSystem;
import net.md_5.bungee.BungeeCord;

public class Register {

	public Register() {
		register();
	}
	
	/** every 15 minutes */
	private void register() {
		BungeeCord.getInstance().getScheduler().schedule(ReportSystem.plugin, new Runnable() {
			
			@Override
			public void run() {
				try {
					PreparedStatement ps = MySQl.con.prepareStatement("SELECT * FROM Reports");
					ps.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		},0, 15, TimeUnit.MINUTES);
	}
}
