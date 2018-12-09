package de.reportsystem.delta203.bungee;

import de.reportsystem.delta203.bungee.commands.Commands;
import de.reportsystem.delta203.bungee.files.ConfigYML;
import de.reportsystem.delta203.bungee.files.MessagesYML;
import de.reportsystem.delta203.bungee.listeners.Join;
import de.reportsystem.delta203.bungee.listeners.Quit;
import de.reportsystem.delta203.bungee.listeners.ServerChange;
import de.reportsystem.delta203.bungee.messages.Messages;
import de.reportsystem.delta203.bungee.mysql.MySQl;
import de.reportsystem.delta203.bungee.mysql.Register;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

public class ReportSystem extends Plugin {
	
	public static ReportSystem plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		MessagesYML.create();
		ConfigYML.create();
		Messages.registerMessages();

		BungeeCord.getInstance().getPluginManager().registerListener(this, new Join());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new Quit());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new ServerChange());
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Commands());
		
		BungeeCord.getInstance().broadcast(Messages.Prefix + Messages.Plugin_active);
		
		if(ConfigYML.get().getBoolean("Use_MySQl") == true) {
			MySQl.connect();
			MySQl.createTable();
			
			new Register();
		}
	}
	
	@Override
	public void onDisable() {
		
	}
}
