package de.reportsystem.delta203.bukkit.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.reportsystem.delta203.bukkit.ReportSystem;
import de.reportsystem.delta203.bukkit.files.ConfigYML;
import de.reportsystem.delta203.bukkit.messages.Messages;
import de.reportsystem.delta203.bukkit.mysql.Get_MySQl;
import de.reportsystem.delta203.bukkit.utils.ItemBuilder;

public class Commands implements CommandExecutor, Listener {

	HashMap<Player, Integer> pages = new HashMap<>();
	HashMap<Player, String> selectedUser = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("team.report")) {
				pages.put(p, 1);
				buildgui(p);
			}else {
				p.sendMessage(Messages.Prefix + Messages.No_permission);
			}
		}else {
			sender.sendMessage(Messages.Prefix + Messages.Be_a_player);
		}
		return false;
	}
	
	private void buildgui(Player p) {
		Inventory inv = Bukkit.createInventory(p, 36, ConfigYML.get().getString("Gui.title").replace("&", "§"));
		
		ArrayList<ItemStack> skulls = new ArrayList<>();
		for(int i = 0; i < Get_MySQl.getReportedPlayers().size(); i++) {
			if(Get_MySQl.isOnline(Get_MySQl.getReportedPlayers().get(i))) {
				skulls.add(ItemBuilder.skull(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), "§7" + Get_MySQl.getReportedPlayers().get(i), Get_MySQl.getReportedPlayers().get(i), "§7- Grund: §d" + Get_MySQl.getReason(Get_MySQl.getReportedPlayers().get(i)), "§7- Server: §d" + Get_MySQl.getServer(Get_MySQl.getReportedPlayers().get(i)), "§7- Von: §d" + Get_MySQl.getFrom(Get_MySQl.getReportedPlayers().get(i))));
			}else {
				skulls.add(ItemBuilder.skull(new ItemStack(Material.SKULL_ITEM, 1), "§7" + Get_MySQl.getReportedPlayers().get(i), Get_MySQl.getReportedPlayers().get(i), "§7- Grund: §d" + Get_MySQl.getReason(Get_MySQl.getReportedPlayers().get(i)), "§7- Server: §dOffline", "§7- Von: §d" + Get_MySQl.getFrom(Get_MySQl.getReportedPlayers().get(i))));
			}
		}
		
		int page = pages.get(p);
		int entriesPerPage = 18;
		int startIndex = (page - 1) * entriesPerPage;
		int endIndex = startIndex + entriesPerPage;
		if(endIndex > skulls.size()) {
			endIndex = skulls.size();
		}
		
		for(ItemStack is : skulls.subList(startIndex, endIndex)) {
			inv.addItem(new ItemStack[] {is});
		}
		
		for(int i = 18; i < 27; i++) {
			inv.setItem(i, ItemBuilder.easy(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) ConfigYML.get().getInt("Gui.glasscolor")), " "));
		}

		inv.setItem(27, ItemBuilder.easy(new ItemStack(Material.PAPER), ConfigYML.get().getString("Gui.backpage").replace("&", "§")));
		inv.setItem(35, ItemBuilder.easy(new ItemStack(Material.PAPER), ConfigYML.get().getString("Gui.nextpage").replace("&", "§")));
		
		p.openInventory(inv);
	}
	
	private void buildPlayerManager(Player p, String requestedplayer) {
		selectedUser.put(p, requestedplayer);
		Inventory inv = Bukkit.createInventory(p, 27, "§8" + requestedplayer);
		if(Get_MySQl.isOnline(requestedplayer)) {
			inv.setItem(11, ItemBuilder.easy(new ItemStack(Material.ENDER_PEARL), ConfigYML.get().getString("Gui.jump").replace("&", "§")));
			inv.setItem(13, ItemBuilder.skull(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), "§7" + selectedUser.get(p), selectedUser.get(p), "§7- Grund: §d" + Get_MySQl.getReason(selectedUser.get(p)), "§7- Server: §d" + Get_MySQl.getServer(selectedUser.get(p)), "§7- Von: §d" + Get_MySQl.getFrom(selectedUser.get(p))));	
		}else {
			inv.setItem(13, ItemBuilder.skull(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), "§7" + selectedUser.get(p), selectedUser.get(p), "§7- Grund: §d" + Get_MySQl.getReason(selectedUser.get(p)), "§7- Server: §dOffline", "§7- Von: §d" + Get_MySQl.getFrom(selectedUser.get(p))));
		}
		inv.setItem(15, ItemBuilder.easy(new ItemStack(Material.WOOL, 1, (short) 14), ConfigYML.get().getString("Gui.remove").replace("&", "§")));
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equalsIgnoreCase(ConfigYML.get().getString("Gui.title").replace("&", "§"))) {
			e.setCancelled(true);
			try {
				if(e.getCurrentItem().getType() == Material.SKULL_ITEM) {
					buildPlayerManager(p, e.getCurrentItem().getItemMeta().getDisplayName().replace("§7", ""));
				}else {
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ConfigYML.get().getString("Gui.backpage").replace("&", "§"))) {
						if(pages.get(p) != 1) {
							pages.put(p, pages.get(p) - 1);
							buildgui(p);
						}
					}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ConfigYML.get().getString("Gui.nextpage").replace("&", "§"))) {
						pages.put(p, pages.get(p) + 1);
						buildgui(p);
					}
				}
			}catch(Exception ex) {
			}
		}else if(e.getInventory().getTitle().equalsIgnoreCase("§8" + selectedUser.get(p))) {
			e.setCancelled(true);
			try {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ConfigYML.get().getString("Gui.jump").replace("&", "§"))) {
					p.sendMessage(Messages.Prefix + Messages.Jumped.replace("%name%", selectedUser.get(p)).replace("%reason%", Get_MySQl.getReason(selectedUser.get(p))));
					ReportSystem.sendOtherServer(p, Get_MySQl.getServer(selectedUser.get(p)));
					e.getView().close();
				}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ConfigYML.get().getString("Gui.remove").replace("&", "§"))) {
					p.sendMessage(Messages.Prefix + Messages.Removed.replace("%from%", Get_MySQl.getFrom(selectedUser.get(p))).replace("%target%", selectedUser.get(p)));
					Get_MySQl.deleteReport(selectedUser.get(p), Get_MySQl.getFrom(selectedUser.get(p)));

					pages.put(p, 1);
					buildgui(p);
				}
			}catch(Exception ex) {
			}
		}
	}
}
