package org.mcsg.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.Game;
import org.mcsg.survivalgames.GameManager;



public class Disable implements SubCommand{

    @Override
    public boolean onCommand(Player player, String[] args) {        
        if(!player.hasPermission("sg.staff.disablearena") && !player.isOp()){
            player.sendMessage(ChatColor.RED+"Недостаточно прав");
            return true;
        }

        if(args.length == 0){
            for(Game g: GameManager.getInstance().getGames()){
                g.disable();
            }
            player.sendMessage(ChatColor.GREEN+"Все арены выключены");

        }else{

            GameManager.getInstance().disableGame(Integer.parseInt(args[0]));
            player.sendMessage(ChatColor.GREEN+"Арена "+args[0]+" выключена");
        }
        return true;
    }
    @Override
    public String help(Player p) {
        return "/sg disable <id> - Выключает арену <id>";
    }
	@Override
	public String permission() {
		return "sg.staff.disablearena";
	}
}
