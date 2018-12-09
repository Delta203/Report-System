package de.reportsystem.delta203.bungee.messages;

import de.reportsystem.delta203.bungee.files.MessagesYML;

public class Messages {

	public static String Prefix;
	public static String Be_a_player;
	public static String Plugin_active;
	public static String No_permission;
	public static String Help1;
	public static String Help2;
	public static String Player_not_online;
	public static String Cannot_report;
	public static String Already_reported;
	public static String Confirm_reportmsg;
	public static String Confirm_reporttextcomponent;
	public static String Report_done;
	public static String Open_reports;
	
	public static void registerMessages() {
		Prefix = MessagesYML.get().getString("Prefix").replace('&', '§');
		Be_a_player = MessagesYML.get().getString("Be_a_player").replace('&', '§');
		Plugin_active = MessagesYML.get().getString("Plugin_active").replace('&', '§');
		No_permission = MessagesYML.get().getString("No_permission").replace('&', '§');
		Help1 = MessagesYML.get().getString("Help.1").replace('&', '§');
		Help2 = MessagesYML.get().getString("Help.2").replace('&', '§');
		Player_not_online = MessagesYML.get().getString("Player_not_online").replace('&', '§');
		Cannot_report = MessagesYML.get().getString("Cannot_report").replace('&', '§');
		Already_reported = MessagesYML.get().getString("Already_reported").replace('&', '§');
		Confirm_reportmsg = MessagesYML.get().getString("Confirm_report.msg").replace('&', '§');
		Confirm_reporttextcomponent = MessagesYML.get().getString("Confirm_report.textcomponent").replace('&', '§');
		Report_done = MessagesYML.get().getString("Report_done").replace('&', '§');
		Open_reports = MessagesYML.get().getString("Open_reports").replace('&', '§');
	}
}
