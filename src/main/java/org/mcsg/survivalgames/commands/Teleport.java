package org.mcsg.survivalgames.commands;

import org.bukkit.entity.Player;
import org.mcsg.survivalgames.GameManager;
import org.mcsg.survivalgames.MessageManager;
import org.mcsg.survivalgames.MessageManager.PrefixType;
import org.mcsg.survivalgames.SettingsManager;

public class Teleport implements SubCommand{

	@Override
	public boolean onCommand(Player player, String[] args) {
		if(player.hasPermission(permission())){
			if(args.length == 1){
				try{
					int a = Integer.parseInt(args[0]);
					try{
						player.teleport(SettingsManager.getInstance().getSpawnPoint(a, 1));
					}catch(Exception e){
						MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.input",player, "message-Не указаны точки спавна на арене!");
					}
				}catch(NumberFormatException e){
					MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.input",player, "message-Номер игры должен быть числовым!");
				}
			}
			else{
				MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.input",player, "message-Нужно указать номер арены!");
			}
		}
		else {
			MessageManager.getInstance().sendFMessage(PrefixType.WARNING, "error.nopermission", player);
		}
		return true;
	}

	@Override
	public String help(Player p) {
		return "/sg tp <номер арены> телепортирует на арену";
	}

	@Override
	public String permission() {
		return "sg.arena.teleport";
	}

}
