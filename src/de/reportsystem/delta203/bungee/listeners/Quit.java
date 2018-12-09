package de.reportsystem.delta203.bungee.listeners;

import de.reportsystem.delta203.bungee.mysql.Get_MySQl;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Quit implements Listener {

	@EventHandler
	public void onQuit(PlayerDisconnectEvent e) {
		ProxiedPlayer p = e.getPlayer();

		Get_MySQl.setOnline(0, p.getName());
		Join.players.remove(p);
	}
}
