package net.smart.staffchat.utils;

import org.bukkit.Bukkit;

public class Message {
	
	public static void consoleMessage(String msg) {
		Bukkit.getConsoleSender().sendMessage("§a[ScreenStaffChat] " + msg);
	}
	public static void consoleMessage2(String msg) {
		Bukkit.getConsoleSender().sendMessage("§c[ScreenStaffChat] " + msg);
	}
	public static void debugMessage(String msg) {
		Bukkit.getConsoleSender().sendMessage("§e[ScreenStaffChat] " + msg);
	}
}
