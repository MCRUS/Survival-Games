package org.mcsg.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.LobbyManager;



public class AddWall implements SubCommand{

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(!player.hasPermission("sg.admin.setlobby") && !player.isOp()){
            player.sendMessage(ChatColor.RED+"Недостаточно прав");
            return true;
        }
        else if(args.length<1){
        	player.sendMessage("Пожалуйста, укажите id игры");
        	return true;
        }
       LobbyManager.getInstance().setLobbySignsFromSelection(player, Integer.parseInt(args[0]));
       return true;
    }

    @Override
    public String help(Player p) {
        return "/sg addwall <id> - Добавляет стену статистики для указаной арены <id>";
    }

	@Override
	public String permission() {
		return "sg.admin.setlobby";
	}

    //TODO: TAKE A W.E SELECTIONA AND SET THE LOBBY. ALSO SET LOBBY WALL
}