package org.mcsg.survivalgames.commands;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.GameManager;
import org.mcsg.survivalgames.MessageManager;
import org.mcsg.survivalgames.SettingsManager;
import org.mcsg.survivalgames.MessageManager.PrefixType;


public class Join implements SubCommand{

	public boolean onCommand(Player player, String[] args) {
		if(args.length == 1){

			try{
				int a = Integer.parseInt(args[0]);
				GameManager.getInstance().addPlayer(player, a);
			}catch(NumberFormatException e){
				MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.input",player, "message-Номер арены может быть только числовым!");
			}
		}
		else{
			if(player.hasPermission("sg.lobby.join")){
				if(GameManager.getInstance().getPlayerGameId(player)!=-1){
					player.sendMessage(ChatColor.RED+"Нельзя перейти в лобби во время игры");
					return true;
				}
				player.teleport(SettingsManager.getInstance().getLobbySpawn());
				return true;
			}
			else{
				MessageManager.getInstance().sendFMessage(PrefixType.WARNING, "error.nopermission", player);
			}
		}
		return true;
	}

	@Override
	public String help(Player p) {
		return "/sg join - Переход в лобби";
	}

	@Override
	public String permission() {
		return "sg.arena.join";
	}
}

