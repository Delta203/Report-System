package de.reportsystem.delta203.bukkit.messages;

import de.reportsystem.delta203.bukkit.files.MessagesYML;

public class Messages {

	public static String Prefix;
	public static String Plugin_active;
	public static String Be_a_player;
	public static String No_permission;
	public static String Jumped;
	public static String Removed;
	
	public static void registerMessages() {
		Prefix = MessagesYML.get().getString("Prefix").replace('&', '§');
		Plugin_active = MessagesYML.get().getString("Plugin_active").replace('&', '§');
		Be_a_player = MessagesYML.get().getString("Be_a_player").replace('&', '§');
		No_permission = MessagesYML.get().getString("No_permission").replace('&', '§');
		Jumped = MessagesYML.get().getString("Jumped").replace('&', '§');
		Removed = MessagesYML.get().getString("Removed").replace('&', '§');
	}
}
