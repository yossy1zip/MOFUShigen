package com.yiorno.mofushigen;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static com.yiorno.mofushigen.MOFUShigen.instance;

public class Event implements Listener {

    //ブロック破壊
    @EventHandler
    public void onHarvest(BlockBreakEvent e){

        Check c = new Check();

        //キャンセルするかどうか
        if(c.toCancel(e.getPlayer(), e.getBlock())){

            //キャンセル
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.YELLOW + "これは壊せません！");
        }

        //木を植え直すかどうか
        if(c.toReplantTree(e.getPlayer(), e.getBlock())) {

            String matStr = String.valueOf(e.getBlock().getType());

            //5ticks後に植え直し
            String saplingStr = matStr.replace("_LOG", "_SAPLING");
            Material sapling = Material.getMaterial(saplingStr);

            new BukkitRunnable() {

                @Override
                public void run() {
                    e.getBlock().getLocation().getBlock().setType(sapling);
                }
            }.runTaskLater(instance, 5);

        }

        //作物を植え直すかどうか
        if(c.toReplantCrop(e.getPlayer(), e.getBlock())){
            Block b = e.getBlock();
            Material cropBlockType = null;

            // Get the type of the broken block
            if (b.getType() == Material.WHEAT) {
                cropBlockType = Material.WHEAT;
            } else if (b.getType() == Material.POTATOES) {
                cropBlockType = Material.POTATOES;
            } else if (b.getType() == Material.CARROTS) {
                cropBlockType = Material.CARROTS;
            } else if (b.getType() == Material.BEETROOTS) {
                cropBlockType = Material.BEETROOTS;
            }

            // Main functionality of the plugin
            if (cropBlockType != null) {

                Material seedType = null;
                if (cropBlockType == Material.WHEAT) {
                    seedType = Material.WHEAT_SEEDS;
                } else if (cropBlockType == Material.POTATOES) {
                    seedType = Material.POTATO;
                } else if (cropBlockType == Material.CARROTS) {
                    seedType = Material.CARROT;
                } else if (cropBlockType == Material.BEETROOTS) {
                    seedType = Material.BEETROOT_SEEDS;
                }

                if(seedType==null){ return; }

                Material finalSeedType = seedType;
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        b.setType(finalSeedType);
                    }
                }.runTaskLater(instance, 5);
            }
        }
    }

    //ブロック設置
    @EventHandler
    public void onPlace(BlockPlaceEvent e){

        Check c = new Check();

        //採取資源じゃなかったらキャンセルしない
        if(!c.isSaishu(e.getPlayer().getWorld())){
            return;
        }

        //作物ならキャンセルしない
        if(c.isCrop(e.getBlock().getType())){
            return;
        }

        //スタッフならキャンセルしない
        if(c.isStaff(e.getPlayer())){
            return;
        }

        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.YELLOW + "これは置けません！");

    }

    //バケツ操作
    @EventHandler
    public void onBucket(PlayerBucketEntityEvent e){

        Check c = new Check();

        //採取資源じゃなかったらキャンセルしない
        if(!c.isSaishu(e.getPlayer().getWorld())){
            return;
        }

        //スタッフならキャンセルしない
        if(c.isStaff(e.getPlayer())){
            return;
        }

        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.YELLOW + "バケツは使えません！");

    }

    @EventHandler
    public void onBucket(PlayerBucketFillEvent e){

        Check c = new Check();

        //採取資源じゃなかったらキャンセルしない
        if(!c.isSaishu(e.getPlayer().getWorld())){
            return;
        }

        //スタッフならキャンセルしない
        if(c.isStaff(e.getPlayer())){
            return;
        }

        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.YELLOW + "バケツは使えません！");

    }

    @EventHandler
    public void onBucket(PlayerBucketEmptyEvent e){

        Check c = new Check();

        //採取資源じゃなかったらキャンセルしない
        if(!c.isSaishu(e.getPlayer().getWorld())){
            return;
        }

        //スタッフならキャンセルしない
        if(c.isStaff(e.getPlayer())){
            return;
        }

        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.YELLOW + "バケツは使えません！");

    }

}
