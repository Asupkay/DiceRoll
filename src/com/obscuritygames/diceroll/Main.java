package com.obscuritygames.diceroll;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender,
			Command command,
			String label,
			String[] args) {
		if(command.getName().equalsIgnoreCase("roll") && args.length == 1) {
			sender.sendMessage("You ran /roll now");
			return true;
		}
		return false;
	}
}
