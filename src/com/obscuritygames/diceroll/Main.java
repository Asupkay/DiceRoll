package com.obscuritygames.diceroll;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandSender.Spigot;
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
		//Check to see if the command is /roll and has one argument
		if(command.getName().equalsIgnoreCase("roll") && args.length == 1) {
			//Make sure that the argument for the number of sides of /roll is an integer
			if(isInt(args[0])) {
				//Get the number of sides from the argument
				int sides = Integer.parseInt(args[0]);
				
				//Make a random number generator and generate a random roll
				Random randGen = new Random();
				int randomRoll = randGen.nextInt(sides) + 1;
				
				//Compose a message
				String message = sender.getName() + "rolled and got " + randomRoll + " out of " + sides;
				
				//Broadcast the whole message to the server
				Bukkit.broadcastMessage(ChatColor.DARK_RED + message);
				return true;
			} else {
				//If the argument is not an int tell them
				sender.sendMessage("The argument of /roll [number of size] must be a number");
				return true;
			}
		}
		return false;
	}
	
	//Tell if a string is an int
	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
