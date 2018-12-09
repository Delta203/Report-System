package de.reportsystem.delta203.bukkit.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigYML {

	private static File file = new File("plugins/ReportSystem", "config.yml");
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
			cfg.set("Use_MySQl", true);
			cfg.set("MySQl_url", "localhost");
			cfg.set("MySQl_port", "3306");
			cfg.set("MySQl_database", "buildplay");
			cfg.set("MySQl_user", "root");
			cfg.set("MySQl_password", "yoshiihsoy");
			cfg.set("Gui.title", "&5Reports");
			cfg.set("Gui.glasscolor", 15);
			cfg.set("Gui.nextpage", "&eNächste Seite");
			cfg.set("Gui.backpage", "&eVorherige Seite");
			cfg.set("Gui.jump", "&eSpringen");
			cfg.set("Gui.remove", "&cEntfernen");
			safe();
		}
	}
	
	public static FileConfiguration get() {
		return cfg;
	}
}
