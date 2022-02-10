package de.dal3x.soulcore.output;

import java.text.DecimalFormat;

import org.bukkit.entity.Player;

import de.dal3x.soulcore.docking.VaultBankDock;

public class BankOutput {

	public static final String notEnoughPlayerMoney = "§7Du hast §cnicht genug Geld§7, um so viel auf dein Konto zu überweisen";
	public static final String notEnoughBankMoney = "§7Du hast §cnicht genug Geld§7 auf der Bank, um so viel abzuheben";
	
	public static void sendSucPTB(Player p, double amount) {
		p.sendMessage("§7Du hast §e" + amount + " ⛂ §7 eingezahlt");
	}
	
	public static void sendSucBTP(Player p, double amount) {
		p.sendMessage("§7Du hast §e" + amount + " ⛂ §7 abgehoben");
	}
	
	public static void sendBalance(Player p) {
		double bankAmount = VaultBankDock.getBankMoney(p);
		double playerAmount = VaultBankDock.getPlayerMoney(p);
		DecimalFormat df2 = new DecimalFormat("#.##");
		p.sendMessage("§7Kontostand: §e" + df2.format(bankAmount) + " ⛂ ");
		p.sendMessage("§7Bargeld: §e" + df2.format(playerAmount) + " ⛂ ");
	}
	
}
