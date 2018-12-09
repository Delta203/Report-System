package de.reportsystem.delta203.bukkit.utils;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

	public static ItemStack easy(ItemStack item, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack skull(ItemStack item, String name, String owner, String lore1, String lore2, String lore3) {
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> l = new ArrayList<>();
		l.add(lore1);
		l.add(lore2);
		l.add(lore3);
		meta.setLore(l);
		meta.setOwner(owner);
		item.setItemMeta(meta);
		return item;
	}
}