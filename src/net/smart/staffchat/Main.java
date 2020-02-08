package net.smart.staffchat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import net.smart.staffchat.commands.StaffChatCMD;
import net.smart.staffchat.utils.Message;

public class Main extends JavaPlugin {

	private static Main instance;
	private static Economy eco = null;
	
	public static Economy getEconomy() {
		return eco;
	}
	public static Main getInstance() {
		return instance;
	}
	public void onEnable() {
		instance = this;

		saveDefaultConfig();
		onCommands();
		
		Message.consoleMessage("Plugin habilitado! [Version 1.0.0]");
		Message.consoleMessage("By Smart");
		if (!Bukkit.getPluginManager().getPlugin("PermissionsEx").isEnabled()) {
			Message.consoleMessage2("Não encontramos o plugin 'PermissionsEx'");
			Message.consoleMessage2("Por isso, algumas placeholders não irão funcionar!");
		}
		if (!Bukkit.getPluginManager().getPlugin("Vault").isEnabled()) {
			Message.consoleMessage2("Não encontramos o plugin 'Vault'");
			Message.consoleMessage2("Por isso, algumas placeholders não irão funcionar!");
		}else {
			setupEconomy();
		}
	}
	private void onCommands() {
		getCommand("staffchat").setExecutor(new StaffChatCMD());
	}
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null) {
			eco = economyProvider.getProvider();
		}
		return (eco != null);
	}
}
