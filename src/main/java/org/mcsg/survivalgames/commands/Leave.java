package org.mcsg.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.GameManager;



public class Leave implements SubCommand {
	
    public boolean onCommand(Player player, String[] args) {
        if(GameManager.getInstance().getPlayerGameId(player) == -1){
            player.sendMessage(ChatColor.RED+"Вы не в игре!");
        }
        else{
            GameManager.getInstance().removePlayer(player, false);
        }
        return true;
    }

    @Override
    public String help(Player p) {
        return "/sg leave - Покинуть игру";
    }

	@Override
	public String permission() {
		return null;
	}
}
