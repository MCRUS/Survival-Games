package org.mcsg.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.Game;
import org.mcsg.survivalgames.MessageManager;
import org.mcsg.survivalgames.Game.GameMode;
import org.mcsg.survivalgames.MessageManager.PrefixType;
import org.mcsg.survivalgames.GameManager;



public class Enable implements SubCommand{

	@Override
	public boolean onCommand(Player player, String[] args) {        
		if(!player.hasPermission("sg.arena.enable") && !player.isOp()){
			player.sendMessage(ChatColor.RED+"Недостаточно прав");
			return true;
		}
		try{
			if(args.length == 0){
				for(Game g:GameManager.getInstance().getGames()){
					if(g.getMode() == GameMode.DISABLED)
						g.enable();
				}
				player.sendMessage(ChatColor.GREEN+"Все арены включены");
			}
			else{
				GameManager.getInstance().enableGame(Integer.parseInt(args[0]));
				player.sendMessage(ChatColor.GREEN+"Арена "+ args[0]+ " включена");
			}
		}catch(NumberFormatException e){
			MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.input",player, "message-Номер арены может быть только числовой!");
		}catch(NullPointerException e){
			MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.input",player, "message-Арена с таким номером не найдена!");
		}
		return true;

	}


	@Override
	public String help(Player p) {
		return "/sg enable <id> - Включает арену <id>";
	}

	@Override
	public String permission() {
		return "sg.staff.enablearena";
	}
}