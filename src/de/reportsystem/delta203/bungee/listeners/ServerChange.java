package de.reportsystem.delta203.bungee.listeners;

import de.reportsystem.delta203.bungee.mysql.Get_MySQl;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerChange implements Listener {

	@EventHandler
	public void onChange(ServerSwitchEvent e) {
		ProxiedPlayer p = e.getPlayer();
		if(Get_MySQl.getReportedPlayers().contains(p.getName())) {
			Get_MySQl.setServer(p.getServer().getInfo().getName(), p.getName());
		}
	}
}
