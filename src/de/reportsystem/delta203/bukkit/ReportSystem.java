package de.reportsystem.delta203.bukkit;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.reportsystem.delta203.bukkit.commands.Commands;
import de.reportsystem.delta203.bukkit.files.ConfigYML;
import de.reportsystem.delta203.bukkit.files.MessagesYML;
import de.reportsystem.delta203.bukkit.messages.Messages;
import de.reportsystem.delta203.bukkit.mysql.MySQl;
import de.reportsystem.delta203.bukkit.mysql.Register;

public class ReportSystem extends JavaPlugin {

	public static ReportSystem plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		MessagesYML.create();
		Messages.registerMessages();
		ConfigYML.create();
		
		getCommand("reportgui").setExecutor(new Commands());
		Bukkit.getPluginManager().registerEvents(new Commands(), this);
		Bukkit.broadcastMessage(Messages.Prefix + Messages.Plugin_active);
		
		if(ConfigYML.get().getBoolean("Use_MySQl") == true) {
			MySQl.connect();
			MySQl.createTable();
			
			new Register();
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static void sendOtherServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		 
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		}catch(IOException ex) {}
		p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}
}
