package org.mcsg.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.Game;
import org.mcsg.survivalgames.GameManager;
import org.mcsg.survivalgames.MessageManager;
import org.mcsg.survivalgames.MessageManager.PrefixType;



public class ListArenas implements SubCommand{
	
    public boolean onCommand(Player player, String[] args) {
    	StringBuilder arenas = new StringBuilder();
    	try{
    	if(args.length == 0 || Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[0]) > GameManager.getInstance().getGameCount()){
    		MessageManager.getInstance().sendMessage(PrefixType.WARNING, "Неверная арена", player);
    	}
    	if (GameManager.getInstance().getGames().size() == 0) {
    		arenas.append("Нет арен!");
    		player.sendMessage(ChatColor.RED + arenas.toString());
        	return true;
    	}
    	arenas.append("Арены: ");
        for (Game g : GameManager.getInstance().getGames()) {
        	arenas.append(g.getID() + ", ");
        }
        player.sendMessage(ChatColor.GREEN + arenas.toString());
    	}catch(Exception e){
    		MessageManager.getInstance().sendMessage(PrefixType.WARNING, "Неверная арена", player);
    	}
        return false;
    }
    
    @Override
    public String help(Player p) {
        return "/sg listarenas - Список всех арен";
    }

	@Override
	public String permission() {
		return "sg.player.listarenas";
	}
}