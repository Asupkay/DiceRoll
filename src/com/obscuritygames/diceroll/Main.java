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
			if(isInt(args[0])) {
				sender.sendMessage("You ran /roll with number of sides " + args[0] + " number of args: " + args.length + " Is int: " + isInt(args[0]));
				return true;
			} else {
				sender.sendMessage("The argument of /roll [number of size] must be a number");
				return true;
			}
		}
		return false;
	}
	
	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
