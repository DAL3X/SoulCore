package de.dal3x.soulcore.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.dal3x.soulcore.docking.VaultBankDock;
import de.dal3x.soulcore.output.BankOutput;

public class BankCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			return true;
		}
		if (args.length == 0) {
			return true;
		}
		if(args[0].equalsIgnoreCase("deposit")) {
			double amount = Double.valueOf(args[1]);
			Player p = Bukkit.getPlayer(args[2]);
			VaultBankDock.fromPlayerToBank(p, amount);
		}
		if(args[0].equalsIgnoreCase("withdraw")) {
			double amount = Double.valueOf(args[1]);
			Player p = Bukkit.getPlayer(args[2]);
			VaultBankDock.fromBankToPlayer(p, amount);
		}
		if(args[0].equalsIgnoreCase("balance")) {
			Player p = Bukkit.getPlayer(args[1]);
			BankOutput.sendBalance(p);
		}
		return true;
	}

}
