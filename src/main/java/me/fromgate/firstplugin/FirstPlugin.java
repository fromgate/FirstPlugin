package me.fromgate.firstplugin;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class FirstPlugin extends PluginBase {

    String helloMessage;

    private static FirstPlugin plugin;

    public static FirstPlugin getPlugin(){
        return plugin;
    }

    @Override
    public void onEnable(){
        plugin = this;
        this.initConfig();
        this.loadCfg();
        this.getServer().getPluginManager().registerEvents(new FirstListener(),this);
    }

    public void initConfig(){
        this.getDataFolder().mkdirs();
        this.saveResource("config.yml");
    }

    public void loadCfg(){
        this.reloadConfig();
        this.helloMessage = this.getConfig().getNested("hello-message","Сообщение по умолчанию");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (sender instanceof Player) ? (Player) sender : null;
        if (args.length==0){
            if (player!=null&&!player.hasPermission("firstplugn.news.show"))
                sender.sendMessage(TextFormat.RED+"You have not enough permissions!");
            else sender.sendMessage(TextFormat.colorize("&3[NEWS] &b"+this.helloMessage));
        } else {
            if (player!=null&&!player.hasPermission("firstplugn.news.set"))
                sender.sendMessage(TextFormat.RED+"You have not enough permissions!");
            else {
                StringBuilder sb = new StringBuilder();
                for (String s : args) {
                    if (sb.length() > 0) sb.append(" ");
                    sb.append(s);
                }
                if (player != null) sb.append(" &3(").append(player.getName()).append(")");
                this.helloMessage = sb.toString();
                this.getConfig().setNested("hello-message",this.helloMessage);
                this.saveConfig();
                sender.sendMessage(TextFormat.colorize("&6You configured new announcement:"));
                sender.sendMessage(TextFormat.colorize("&3[NEWS] &b"+this.helloMessage));
            }
        }
        return true;
    }
}