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
		if(command.getName().equalsIgnoreCase("roll") && args.length >= 1 && args.length <= 2) {
			//Make sure that the argument for the number of sides of /roll is an integer
			if(isInt(args[0])) {
				
				int numberOfDice;
				
				if(args.length == 1) {
					numberOfDice = 1;
				} else {
					if(isInt(args[1])) {
						numberOfDice = Integer.parseInt(args[1]);
					} else {
						sender.sendMessage("The arguments of /roll [number of size] (number of rolls) must be a number");
						return true;
					}
				}
				
				
				//Get the number of sides from the argument
				int sides = Integer.parseInt(args[0]);
				
				//Make a random number generator and generate a random roll
				Random randGen = new Random();
				
				int totalRoll = 0;
				
				
				for(int i = 0; i < numberOfDice; i++) {
					int randomRoll = randGen.nextInt(sides) + 1;
					totalRoll += randomRoll;
				}
				
				//Compose a message
				String message = ChatColor.DARK_RED  + sender.getName() + " rolled " + numberOfDice + "die and got " + totalRoll + " out of " + sides * numberOfDice;
				
				//If the random roll is a critical add some flavor text
				if(totalRoll == numberOfDice || totalRoll == numberOfDice * sides) {
					if(totalRoll == numberOfDice) {
						message = message  + ChatColor.BLACK + " Critical Fail!";
					} else {
						message = message + ChatColor.DARK_BLUE + " Critical Success!";
					}
				}
				
				//Broadcast the whole message to the server
				Bukkit.broadcastMessage(message);
				
				return true;
			} else {
				//If the argument is not an int tell them
				sender.sendMessage("The arguments of /roll [number of size] (number of rolls) must be a number");
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
