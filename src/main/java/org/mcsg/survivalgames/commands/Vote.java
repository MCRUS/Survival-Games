package org.mcsg.survivalgames.commands;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.GameManager;


public class Vote implements SubCommand {
    
    public boolean onCommand(Player player, String[] args) {
        if(!(player.hasPermission("sg.player.vote"))) return false;
        int game = GameManager.getInstance().getPlayerGameId(player);
        if(game == -1){
            player.sendMessage(ChatColor.RED+"Вы должны быть в игре!");
            return true;
        }

        GameManager.getInstance().getGame(GameManager.getInstance().getPlayerGameId(player)).vote(player);

        return true;
    }
    
    @Override
    public String help(Player p) {
        return "/sg vote - Голосование за старт игры";
    }

	@Override
	public String permission() {
		return "sg.player.vote";
	}
}