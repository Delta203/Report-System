package de.reportsystem.delta203.bungee.commands;

import java.util.ArrayList;
import java.util.HashMap;

import de.reportsystem.delta203.bungee.messages.Messages;
import de.reportsystem.delta203.bungee.mysql.Get_MySQl;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Commands extends Command {

	ArrayList<ProxiedPlayer> confirms = new ArrayList<>();
	HashMap<ProxiedPlayer, String> name = new HashMap<>();
	HashMap<ProxiedPlayer, String> reason = new HashMap<>();
	HashMap<ProxiedPlayer, String> server = new HashMap<>();
	
	public Commands() {
		super("report");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("confirm")) {
					if(confirms.contains(p)) {
						p.sendMessage(Messages.Prefix + Messages.Report_done.replace("%name%", name.get(p)));
						Get_MySQl.addReport(name.get(p), p.getName(), reason.get(p), server.get(p), 1);
						for(ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
							if(all.hasPermission("team.report")) {
								all.sendMessage(Messages.Prefix + Messages.Open_reports + " §8(§e" + Get_MySQl.getReportedPlayers().size() + "§8)");
							}
						}
						confirms.remove(p);
						name.remove(p);
						reason.remove(p);
						server.remove(p);
					}else {
						p.sendMessage(Messages.Prefix + Messages.Cannot_report);
					}
				}else {
					sendHelp(p);
				}
			}else if(args.length == 2) {
				ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);
				if(target != null) {
					if(target.getName() != p.getName()) {
						if(!target.hasPermission("team.cannotreport")) {
							if(!Get_MySQl.getReportedPlayers().contains(target.getName())) {
								confirms.add(p);
								name.put(p, target.getName());
								reason.put(p, args[1]);
								server.put(p, target.getServer().getInfo().getName());
								
								p.sendMessage(Messages.Prefix + Messages.Confirm_reportmsg.replace("%name%", target.getName()));
								
								TextComponent report = new TextComponent(Messages.Confirm_reporttextcomponent);
								report.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report confirm"));
								p.sendMessage(report);
							}else {
								p.sendMessage(Messages.Prefix + Messages.Already_reported.replace("%name%", target.getName()));
							}
						}else {
							p.sendMessage(Messages.Prefix + Messages.Cannot_report.replace("%name%", target.getName()));
						}
					}else {
						p.sendMessage(Messages.Prefix + Messages.Cannot_report.replace("%name%", target.getName()));
					}
				}else {
					p.sendMessage(Messages.Prefix + Messages.Player_not_online.replace("%name%", args[0]));
				}
			}else {
				sendHelp(p);
			}
		}else {
			sender.sendMessage(Messages.Prefix + Messages.Be_a_player);
		}
	}
	

	
	@SuppressWarnings("deprecation")
	private void sendHelp(ProxiedPlayer p) {
		p.sendMessage(Messages.Prefix + "§fInfo");
		p.sendMessage("§d/report <name> <grund> " + Messages.Help1);
		if(p.hasPermission("team.report")) {
			p.sendMessage("§d/reportgui " + Messages.Help2);
		}
	}
}
