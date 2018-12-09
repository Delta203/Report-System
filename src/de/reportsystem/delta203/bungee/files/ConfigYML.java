package de.reportsystem.delta203.bungee.files;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigYML {

	private static File file = new File("plugins/ReportSystem", "config.yml");
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
		}else {
			try {
		        if(!file.exists()) {
		    		try {
						file.createNewFile();
						
						cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
						
						cfg.set("Use_MySQl", true);
						cfg.set("MySQl_url", "localhost");
						cfg.set("MySQl_port", "3306");
						cfg.set("MySQl_database", "buildplay");
						cfg.set("MySQl_user", "root");
						cfg.set("MySQl_password", "");
						
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
}
