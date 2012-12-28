/*    */ package sea.wry.antimelt;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.configuration.Configuration;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.event.block.BlockCanBuildEvent;
/*    */ import org.bukkit.event.block.BlockFadeEvent;
/*    */ import org.bukkit.event.block.BlockFormEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class AntiMeltBlockListener
/*    */   implements Listener
/*    */ {
/*    */   boolean noMelting;
/*    */   boolean iceDrop;
/*    */   boolean noFreeze;
/*    */   boolean iceWater;
/*    */   public static AntiMelt plugin;
/*    */ 
/*    */   public AntiMeltBlockListener(AntiMelt plugin)
/*    */   {
/* 23 */     Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
/* 24 */     Configuration config = plugin.getConfig();
/* 25 */     this.noMelting = config.getBoolean("general.noMelting");
/* 26 */     this.iceDrop = config.getBoolean("general.iceDrop");
/* 27 */     this.noFreeze = config.getBoolean("general.noFreeze");
/* 28 */     this.iceWater = config.getBoolean("general.iceWater");
/*    */   }
/*    */ 
/*    */   @EventHandler
/*    */   public void onBlockFade(BlockFadeEvent event)
/*    */   {
/* 34 */     if (((event.getBlock().getType() == Material.ICE) || (event.getBlock().getType() == Material.SNOW) || (event.getBlock().getType() == Material.SNOW_BLOCK)) && (this.noMelting))
/* 35 */       event.setCancelled(true);
/*    */   }
/*    */ 
/*    */   @EventHandler
/*    */   public void onBlockForm(BlockFormEvent event)
/*    */   {
/* 41 */     if (this.noFreeze)
/* 42 */       event.setCancelled(true);
/*    */   }
/*    */ 
/*    */   @EventHandler
/*    */   public void onBlockBreak(BlockBreakEvent event)
/*    */   {
/* 48 */     if ((event.getBlock().getType() == Material.ICE) && (this.iceDrop))
/*    */     {
/* 50 */       ItemStack item = new ItemStack(Material.ICE, 1);
/* 51 */       event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
/*    */     }
/* 53 */     if ((!this.iceWater) && (event.getBlock().getType() == Material.ICE))
/* 54 */       event.getBlock().setType(Material.AIR);
/*    */   }
/*    */ 
/*    */   @EventHandler
/*    */   public void onBlockCanBuild(BlockCanBuildEvent event)
/*    */   {
/* 60 */     Block block = event.getBlock();
/* 61 */     if (block.getType() == Material.TORCH)
/* 62 */       event.setBuildable(true);
/*    */   }
/*    */ }