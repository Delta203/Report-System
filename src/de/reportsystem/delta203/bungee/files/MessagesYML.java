package de.reportsystem.delta203.bungee.files;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class MessagesYML {

	private static File file = new File("plugins/ReportSystem", "messages.yml");
	private static Configuration cfg; 
	
	public static Configuration get() {
		return cfg;
	}

	public static void safe() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void create() {
		if(!new File("plugins/ReportSystem").exists()) {
        	new File("plugins/ReportSystem").mkdir();
		}
		try {
	        if(!file.exists()) {
	    		try {
					file.createNewFile();
					
					cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
					
					cfg.set("Prefix", " &7• &5Report &f| &7");
					cfg.set("Plugin_active", "&aDas Plugin wurde erfolgreich aktiviert.");
					cfg.set("Be_a_player", "&cDu musst ein Spieler sein um das machen zu können.");
					cfg.set("No_permission", "&cDu kannst das jetzt nicht machen.");
					cfg.set("Help.1", "&7Melde einen Spieler, der sich nicht richtig verhält.");
					cfg.set("Help.2", "&7Öffne die Report GUI, um die Reports zu bearbeiten.");
					cfg.set("Player_not_online", "&cDer Spieler &e%name% &cist nicht online.");
					cfg.set("Cannot_report", "&cDu kannst den Spieler nicht reporten.");
					cfg.set("Already_reported", "&cDer Spieler &e%name% &cwurde bereits reportet.");
					cfg.set("Confirm_report.msg", "&7Du willst &e%name% &7reporten. Bitte bestätige deinen Report und beachte, dass falsche Reports Konsequenzen mit sich führen.");
					cfg.set("Confirm_report.textcomponent", "&a&l[Klick]");
					cfg.set("Report_done", "&7Du hast &e%name% &7erfolgreich reportet. Vielen Dank für deine Unterstützung!");
					cfg.set("Open_reports", "&7Es gibt noch offene Reports.");
					
					
					safe();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
			}
    	} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
