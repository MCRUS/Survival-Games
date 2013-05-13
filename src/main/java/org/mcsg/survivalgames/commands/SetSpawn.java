package org.mcsg.survivalgames.commands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mcsg.survivalgames.Game;
import org.mcsg.survivalgames.GameManager;
import org.mcsg.survivalgames.SettingsManager;



public class SetSpawn implements SubCommand{

    HashMap<Integer, Integer>next = new HashMap<Integer,Integer>();

    public SetSpawn() {
    	
    }

    public void loadNextSpawn(){
        for(Game g:GameManager.getInstance().getGames().toArray(new Game[0])){ //Avoid Concurrency problems
            next.put(g.getID(), SettingsManager.getInstance().getSpawnCount(g.getID())+1);
        }
    }
    
    public boolean onCommand(Player player, String[] args) {
        if(!player.hasPermission("sg.admin.setarenaspawns") && !player.isOp()){
            player.sendMessage(ChatColor.RED+"Недостаточно прав");
            return true;
        }
        
        loadNextSpawn();
        System.out.println("settings spawn");
        Location l = player.getLocation();
        int game = GameManager.getInstance().getBlockGameId(l);
        System.out.println(game+" "+next.size());
        if(game == -1){
            player.sendMessage(ChatColor.RED+"Вы должны быть на арене!");
            return true;
        }
        int i = 0;
        if(args[0].equalsIgnoreCase("next")){
            i = next.get(game);
            next.put(game, next.get(game)+1);
        }
        else{
            try{
            i = Integer.parseInt(args[0]);
            if(i>next.get(game)+1 || i<1){
                player.sendMessage(ChatColor.RED+"Номер точки спавна должен быть от 1 до "+next.get(game));
                return true;
            }
            if(i == next.get(game)){
                next.put(game, next.get(game)+1);
            }
            }catch(Exception e){
                player.sendMessage(ChatColor.RED+"Неверный синтаксис. Параметр должен быть \"next\" или число");
                return false;
            }
        }
        if(i == -1){
            player.sendMessage(ChatColor.RED+"Вы должны быть на арене");
            return true;
        }
        SettingsManager.getInstance().setSpawn(game, i, l.toVector());
        player.sendMessage(ChatColor.GREEN+"Точка спавна "+i +" на арене "+game+" указана!");

        return true;
    }
    
    @Override
    public String help(Player p) {
        return "/sg setspawn next - Указывает точку спавна на арене где расположен игрок";
    }

	@Override
	public String permission() {
		return "sg.admin.setarenaspawns";
	}
}