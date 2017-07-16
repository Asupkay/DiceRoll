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
		
		//Check to see if the command is /roll and has one or two arguments
		if(command.getName().equalsIgnoreCase("roll") && args.length >= 1 && args.length <= 2) {
			
			//Check the arguments if there is an error return true
			if(checkRollArgs(sender, args)) {
				return true;
			}
			
			//Initialize the number of dice and the message
			int numberOfDice = 1;
			String message = "";
			
			//Get the number of sides from the argument
			int sides = Integer.parseInt(args[0]);
			
			//If the number of arguments is 2 make the number of dice the second arg
			if(args.length == 2) {
				numberOfDice = Integer.parseInt(args[1]);
			}
			
			//Make a random number generator
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
	
	//Check all the arguments to make sure they comply with roll
	private boolean checkRollArgs(CommandSender sender, String[] args) {
		String errorMessage = "";
		boolean isError = false;
		
		//Check if the first argument is an integer
		if(isInt(args[0])) {
			
			//Get the sides from the argument
			int sides = Integer.parseInt(args[0]);
			
			//Check that the number of sides makes sense
			if(sides < 2) {
				errorMessage += "There must be more than 1 side on the dice. ";
				isError = true;
			}
		} else {
			errorMessage += "The arguments of /roll [number of size] (number of rolls) must be a number ";
			isError = true;
		}
		

		//If is has two arguments
		if(args.length == 2) {
			//Make sure that the second argument is an int
			if(isInt(args[1])) {
				
				//Get the number of dice from the argument
				int numberOfDice = Integer.parseInt(args[1]);
				
				//Check that the number of dice makes sense
				if(numberOfDice < 1) {
					errorMessage += "There must be atleast 1 die. ";
					isError = true;
				}
				
			} else {
				//If the second argument is not an int exit the program
				errorMessage += "The arguments of /roll [number of size] (number of rolls) must be a number. ";
				isError = true;
			}
		}
		
		//If there was an error spit it out
		if(isError) {
			sender.sendMessage(errorMessage);
		}
		
		return isError;
	}
}
