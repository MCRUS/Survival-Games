package org.mcsg.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.Game;
import org.mcsg.survivalgames.GameManager;
import org.mcsg.survivalgames.MessageManager;
import org.mcsg.survivalgames.MessageManager.PrefixType;
import org.mcsg.survivalgames.util.StringUtil;


public class ForceStart implements SubCommand {
	
	MessageManager msgmgr = MessageManager.getInstance();
	
    public boolean onCommand(Player player, String[] args) {
        
        if(!player.hasPermission("sg.staff.forcestart") && !player.isOp()){
            player.sendMessage(ChatColor.RED+ "Недостаточно прав");
            return true;
        }
        int game = -1;
        int seconds = 10;
        if(args.length == 2){
            seconds = Integer.parseInt(args[1]);
        }
        if(args.length >= 1){
            game = Integer.parseInt(args[0]);
            
        }
        else
            game  = GameManager.getInstance().getPlayerGameId(player);
        if(game == -1){
            player.sendMessage(ChatColor.RED+"Вы должны быть в игре!");
            return true;
        }
        if(GameManager.getInstance().getGame(game).getActivePlayers() < 2){
            player.sendMessage(ChatColor.RED+"Нужно минимум 2 игрока чтобы начать игру!");
            return true;
        }
        
        
		Game g = GameManager.getInstance().getGame(game);
		if(g.getMode() != Game.GameMode.WAITING && !player.hasPermission("sg.arena.restart")){
		    player.sendMessage(ChatColor.RED+"Игра уже запущена!");
		    return true;
		}
		g.countdown(seconds);
		for (Player pl : g.getAllPlayers()) {
        	msgmgr.sendMessage(PrefixType.INFO, "Игра начнется через " + seconds + " " + StringUtil.plural(seconds,"секунду","секунды","секунд") + "!", pl);
        }
		player.sendMessage(ChatColor.GREEN+"Игра на арене "+game+" запущена.");
		
		return true;
	}
    
    @Override
    public String help(Player p) {
        return "/sg forcestart - Форсировано запускает игру";
    }

	@Override
	public String permission() {
		return "sg.staff.forcestart";
	}
}