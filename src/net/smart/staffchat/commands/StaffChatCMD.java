package net.smart.staffchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.smart.staffchat.Main;
import net.smart.staffchat.utils.Message;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class StaffChatCMD implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("staffchat")) {
			if (!(sender instanceof Player)) {
				Message.consoleMessage2("Comando apenas para jogadores.");
				return true;
			}
			Player p = (Player) sender;
			String permission = Main.getInstance().getConfig().getString("Pex_Manager.permission");
			String permissionMessage = Main.getInstance().getConfig().getString("Pex_Manager.permission-message");
			if (!p.hasPermission(permission)) {
				p.sendMessage(permissionMessage.replace("&", "§"));
				return true;
			}
			String argsError = Main.getInstance().getConfig().getString("args-erro").replace("&", "§").replace("{command}", label);
			if (args.length == 0) {
				p.sendMessage(argsError);
				return true;
			}
			String msg = "";
			for (int i = 0; i < args.length; i++) {
				msg = msg + args[i] + " ";
			}
			if (args.length >= 1) {
				//get format
				String prefix = "{prefix}";
				String suffix = "{suffix}";
				String groupName = "{groupName}";
				String groupPrefix = "{groupPrefix}";
				String groupSuffix = "{groupSuffix}";
				if (Bukkit.getPluginManager().getPlugin("PermissionsEx").isEnabled()) {
					prefix = PermissionsEx.getUser(p).getPrefix();
					suffix = PermissionsEx.getUser(p).getSuffix();
					for (PermissionGroup grupos : PermissionsEx.getUser(p).getGroups()) {
						groupName = grupos.getName();
						groupPrefix = grupos.getPrefix().replace("&", "§");
						groupSuffix = grupos.getSuffix().replace("&", "§");
					}
				}
				for (String format : Main.getInstance().getConfig().getStringList("format")) {
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (all.hasPermission(permission)) {
							String formatFINAL = format.replace("&", "§").replace("{player}", p.getName())
							.replace("{mensagem}", msg.replace("&", "§")).replace("{money}", Main.getEconomy().getBalance(p) + "")
							.replace("{world}", p.getWorld().getName()).replace("{ping}", ((CraftPlayer)p).getHandle().ping + "")
							.replace("{prefix}", prefix).replace("{suffix}", suffix).replace("{groupName}", groupName)
							.replace("{gamemode}", p.getGameMode() + "").replace("{displayname}", p.getDisplayName())
							.replace("{health}", p.getHealth() + "").replace("{groupPrefix}", groupPrefix)
							.replace("{groupSuffix}", groupSuffix);
							
							all.sendMessage(formatFINAL);
						}
					}
				}
			}
		}
		return true;
	}
}
