package org.mcsg.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.MessageManager;
import org.mcsg.survivalgames.SettingsManager;
import org.mcsg.survivalgames.MessageManager.PrefixType;



public class ResetSpawns implements SubCommand{

    public boolean onCommand(Player player, String[] args) {
        
        if(!player.hasPermission("sg.admin.resetspawns") && !player.isOp()){
            player.sendMessage(ChatColor.RED+ "Недостаточно прав");
            return true;
        }
        try{
        SettingsManager.getInstance().getSpawns().set("spawns."+Integer.parseInt(args[0]), null);
        return true;
		}catch(NumberFormatException e){
			MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.input",player, "message-Номер арены может быть только числовым!");
		}catch(NullPointerException e){
			MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.input",player, "message-Арена с таким номером не найдена!");
		}
        return true;
    }   

    @Override
    public String help(Player p) {
        return "/sg resetspawns <id> - Сбрасывает точки спавна на арене <id>";
    }

	@Override
	public String permission() {
		return "sg.admin.resetspawns";
	}
}