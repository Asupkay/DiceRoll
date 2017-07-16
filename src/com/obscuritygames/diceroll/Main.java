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
			if(!isInt(args[0])) {
				//If the argument is not an int tell them
				sender.sendMessage("The arguments of /roll [number of size] (number of rolls) must be a number");
				return true;
			}
			
			int numberOfDice = 1;
			String message = "";
			
			//Get the number of sides from the argument
			int sides = Integer.parseInt(args[0]);
			if(sides < 2) {
				message += "There must be more than 1 side on the dice. ";
			}
			
			//If the number of arguments is 1 roll one die if it is 2 make the number of dice the second arg
			if(args.length == 2) {
				if(isInt(args[1])) {
					numberOfDice = Integer.parseInt(args[1]);
					
					if(numberOfDice < 1) {
						message += "There must be atleast 1 die. ";
					}
					
				} else {
					//If the second argument is not an int exit the program
					message += "The arguments of /roll [number of size] (number of rolls) must be a number. ";
				}
			}
				
			//If an error was accumilated spit them out and exit
			if(!message.equals("")) {
				sender.sendMessage(message);
				return true;
			}
			
				
			//Make a random number generator and generate a random roll
			Random randGen = new Random();
			
			//Create an aggregator for the totalRoll
			int totalRoll = 0;
			
			//Roll all the random dice and tally them up
			for(int i = 0; i < numberOfDice; i++) {
				int randomRoll = randGen.nextInt(sides) + 1;
				totalRoll += randomRoll;
			}
			
			//Compose a message
			message = ChatColor.DARK_RED  + sender.getName() + " rolled " + numberOfDice + "die and got " + totalRoll + " out of " + sides * numberOfDice;
			
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
