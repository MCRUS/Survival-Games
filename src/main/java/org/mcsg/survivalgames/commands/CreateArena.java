package org.mcsg.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.GameManager;



public class CreateArena implements SubCommand{

    public boolean onCommand(Player player, String[] args) {
        if(!player.hasPermission("sg.admin.createarena") && !player.isOp()){
            player.sendMessage(ChatColor.RED+"Недостаточно прав");
            return true;
        }
        GameManager.getInstance().createArenaFromSelection(player);
        return true;
    }

    @Override
    public String help(Player p) {
        return "/sg createarena - Создает арену в зоне выделенной с помощью WorldEdit";
    }

	@Override
	public String permission() {
		return "sg.admin.createarena";
	}
}