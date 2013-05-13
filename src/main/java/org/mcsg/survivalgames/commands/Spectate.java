package org.mcsg.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.GameManager;
import org.mcsg.survivalgames.SettingsManager;



public class Spectate implements SubCommand{

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(!player.hasPermission("sg.player.spectate") && !player.isOp()){
            player.sendMessage(ChatColor.RED+"Недостаточно прав");
            return true;
        }
        
        if(args.length == 0){
            if(GameManager.getInstance().isSpectator(player)){
                GameManager.getInstance().removeSpectator(player);
                return true;
            }
            else{
                player.sendMessage(ChatColor.RED+"Вы не наблюдаете за игрой. ВВедите /sg spectate <номер арены> для наблюдения!");
                return true;
            }
        }
        if(SettingsManager.getInstance().getSpawnCount(Integer.parseInt(args[0])) == 0){
            player.sendMessage(ChatColor.RED+"Точки спавна не указаны!");
            return true;
        }
        if(GameManager.getInstance().isPlayerActive(player)){
            player.sendMessage(ChatColor.RED+"Нельзя переключится в режим наблюдения во время игры!");
            return true;
        }
        GameManager.getInstance().getGame(Integer.parseInt(args[0])).addSpectator(player);
        return true;
    }

    @Override
    public String help(Player p) {
        return "/sg spectate <id> - Наблюдение за игрой на арене";
    }

	@Override
	public String permission() {
		return "sg.player.spectate";
	}

}
