package de.reportsystem.delta203.bukkit.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MessagesYML {

	private static File file = new File("plugins/ReportSystem", "messages.yml");
	private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public static void load() {
		try {
			cfg.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void safe() {
		try {
			cfg.save(file);
		} catch (IOException e) {
	    	e.printStackTrace();
		}
	}
	
	public static void create() {
		if (!file.exists()) {	
			cfg.set("Prefix", " &7• &5Report &f| &7");
			cfg.set("Plugin_active", "&aDas Plugin wurde erfolgreich aktiviert.");
			cfg.set("Be_a_player", "&cDu musst ein Spieler sein um das machen zu können.");
			cfg.set("No_permission", "&cDu kannst das jetzt nicht machen.");
			cfg.set("Jumped", "&7Du bist zu &e%name% &7erfolgreich gesprungen. Das vergehen war &e%reason%&7.");
			cfg.set("Removed", "&7Du hast den Report von &e%from% &7gegen &d%target% &7erfolgreich gelöscht.");
			safe();
		}
	}
	
	public static FileConfiguration get() {
		return cfg;
	}
}
