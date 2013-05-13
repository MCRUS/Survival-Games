package org.mcsg.survivalgames.commands;

import org.bukkit.entity.Player;



public class SetStatsWall implements SubCommand {

    @Override
    public boolean onCommand(Player player, String[] args) {
        //StatsWallManager.getInstance().setStatsSignsFromSelection(player);
        return false;
    }

    
    public String help(Player p){
        return "/sg setstatswall - Указывает стену статистики";
    }

	@Override
	public String permission() {
		return null;
	}
}