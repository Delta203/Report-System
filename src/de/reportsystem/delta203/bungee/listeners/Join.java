package de.reportsystem.delta203.bungee.listeners;

import java.util.ArrayList;

import de.reportsystem.delta203.bungee.messages.Messages;
import de.reportsystem.delta203.bungee.mysql.Get_MySQl;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Join implements Listener {

	public static ArrayList<ProxiedPlayer> players = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(ServerConnectEvent e) {
		if(!players.contains(e.getPlayer())) {
			players.add(e.getPlayer());
			ProxiedPlayer p = e.getPlayer();
			Get_MySQl.setOnline(1, p.getName());
			if(Get_MySQl.getReportedPlayers().size() != 0) {
				p.sendMessage(Messages.Prefix + Messages.Open_reports + " §8(§e" + Get_MySQl.getReportedPlayers().size() + "§8)");
			}
		}
	}
}
