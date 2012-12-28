/*    */ package sea.wry.antimelt;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.logging.Logger;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.Configuration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class AntiMelt extends JavaPlugin
/*    */ {
/*    */   Logger log;
/* 20 */   final HashMap<Player, ?> NoMeltBuildUsers = new HashMap<Player, Object>();
/*    */   boolean noMelting;
/*    */   boolean iceDrop;
/*    */   boolean noFreeze;
/*    */   boolean iceWater;
/*    */ 
/*    */   public AntiMelt()
/*    */   {
/* 27 */     this.log = Logger.getLogger("Minecraft");
/*    */   }
/*    */ 
/*    */   public void onDisable()
/*    */   {
/* 32 */     this.log.info("Disabled AntiMelt");
/*    */   }
/*    */ 
/*    */   public void onEnable()
/*    */   {
/* 37 */     PluginManager pm = getServer().getPluginManager();
/* 38 */     pm.registerEvents(new AntiMeltBlockListener(this), this);
/* 39 */     Configuration config = getConfig();
/* 40 */     getConfig().options().copyDefaults(true);
/* 41 */     saveConfig();
/* 42 */     this.noMelting = config.getBoolean("general.noMelting");
/* 43 */     this.iceDrop = config.getBoolean("general.iceDrop");
/* 44 */     this.noFreeze = config.getBoolean("general.noFreeze");
/* 45 */     this.iceWater = config.getBoolean("general.iceWater");
/* 46 */     this.log.info("[AntiMelt] Enabled");
/* 47 */     if (this.noMelting)
/* 48 */       this.log.info("[AntiMelt] - Melting is disabled.");
/*    */     else {
/* 50 */       this.log.info("[AntiMelt] - Melting is enabled.");
/*    */     }
/* 52 */     if (this.iceDrop)
/* 53 */       this.log.info("[AntiMelt] - Ice will drop");
/*    */     else {
/* 55 */       this.log.info("[AntiMelt] - Ice will not drop.");
/*    */     }
/* 57 */     if (this.noFreeze)
/* 58 */       this.log.info("[AntiMelt] - Freezing is Disabled.");
/*    */     else {
/* 60 */       this.log.info("[AntiMelt] - Freezing Enabled.");
/*    */     }
/* 62 */     if (!this.iceWater)
/* 63 */       this.log.info("[AntiMelt] - Ice will NOT turn to water upon breaking.");
/*    */     else
/* 65 */       this.log.info("[AntiMelt] - Ice WILL turn into water upon breaking.");
/*    */   }
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 71 */     Player player = (Player)sender;
/* 72 */     if (commandLabel.equalsIgnoreCase("nomelt"))
/*    */     {
/* 74 */       toggleBuild(player);
/* 75 */       return true;
/*    */     }
/*    */ 
/* 78 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean enabled(Player player)
/*    */   {
/* 84 */     return this.NoMeltBuildUsers.containsKey(player);
/*    */   }
/*    */ 
/*    */   public void toggleBuild(Player player)
/*    */   {
/* 89 */     if (enabled(player))
/*    */     {
/* 91 */       this.NoMeltBuildUsers.remove(player);
/* 92 */       player.sendMessage("You have diabled no-melt building.");
/*    */     }
/*    */     else {
/* 95 */       this.NoMeltBuildUsers.put(player, null);
/* 96 */       player.sendMessage("You have enabled no-melt building.");
/*    */     }
/*    */   }
/*    */ }