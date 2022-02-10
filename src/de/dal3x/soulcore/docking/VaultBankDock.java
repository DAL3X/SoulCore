package de.dal3x.soulcore.docking;

import org.bukkit.OfflinePlayer;

import de.dal3x.soulcore.file.BankFileHandler;
import de.dal3x.soulcore.main.CorePlugin;
import de.dal3x.soulcore.output.BankOutput;
import net.milkbowl.vault.economy.Economy;

public class VaultBankDock {
	
	private static Economy eco;
	
	public static void registerVault() {
		eco = CorePlugin.getInstance().getServer().getServicesManager().getRegistration(Economy.class).getProvider();
	}

	public static double getBankMoney(OfflinePlayer p) {
		return BankFileHandler.getBankAccount(p.getUniqueId());
	}
	
	public static double getPlayerMoney(OfflinePlayer p) {
		return eco.getBalance(p);
	}
	
	public static boolean hasPlayerMoney(OfflinePlayer p, double cost) {
		return eco.has(p, cost);
	}
	
	public static boolean hasBankMoney(OfflinePlayer p, double cost) {
		return BankFileHandler.getBankAccount(p.getUniqueId()) >= cost;
	}
	
	public static void removePlayerMoney(OfflinePlayer p, double amount) {
		eco.withdrawPlayer(p, amount);
	}
	
	public static void addPlayerMoney(OfflinePlayer p, double amount) {
		eco.depositPlayer(p, amount);
	}
	
	public static void addBankMoney(OfflinePlayer p, double amount) {
		double newAmount = BankFileHandler.getBankAccount(p.getUniqueId()) + amount;
		BankFileHandler.setBankAccount(p.getUniqueId(), newAmount);
	}
	
	public static void removeBankMoney(OfflinePlayer p, double amount) {
		double newAmount = BankFileHandler.getBankAccount(p.getUniqueId()) - amount;
		BankFileHandler.setBankAccount(p.getUniqueId(), newAmount);
	}
	
	public static void fromPlayerToBank(OfflinePlayer p, double amount) {
		if(!hasPlayerMoney(p, amount)) {
			p.getPlayer().sendMessage(BankOutput.notEnoughPlayerMoney);
			return;
		}
		removePlayerMoney(p, amount);
		addBankMoney( p, amount);
		BankOutput.sendSucPTB(p.getPlayer(), amount);
	}
	
	public static void fromBankToPlayer(OfflinePlayer p, double amount) {
		if(!hasBankMoney(p, amount)) {
			p.getPlayer().sendMessage(BankOutput.notEnoughBankMoney);
			return;
		}
		removeBankMoney(p, amount);
		addPlayerMoney( p, amount);
		BankOutput.sendSucBTP(p.getPlayer(), amount);
	}
	
}
