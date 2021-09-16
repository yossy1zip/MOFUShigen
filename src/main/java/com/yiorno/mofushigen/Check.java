package com.yiorno.mofushigen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Check {


    public boolean isSaishu(World w){
        return w == Bukkit.getWorld("saishu");
    }


    public boolean isShigen(World w){
        return w == Bukkit.getWorld("saishu") || w == Bukkit.getWorld("shigen_normal") ||
                w == Bukkit.getWorld("shigen_nether") || w == Bukkit.getWorld("shigen_the_end");
    }


    public boolean isCrop(Material m){

        String ms = m.toString();

        return (ms.contains("SEEDS")) || (ms.contains("WHEAT")) || (ms.contains("ROOT")) || (ms.contains("CARROT")) ||
                (ms.contains("POTATO")) || (ms.contains("MELON")) || (ms.contains("PUMPKIN")) || (ms.contains("BAMBOO")) ||
                (ms.contains("BEANS")) || (ms.contains("CANE")) || (ms.contains("BERRIES")) || (ms.contains("CACTUS")) ||
                (ms.contains("MUSHROOMS")) || (ms.contains("KELP")) || (ms.contains("PICKLE")) || (ms.contains("WART")) ||
                (ms.contains("FRUIT")) || (ms.contains("FUNGUS")) || (ms.contains("LOG")) || (ms.contains("SAPLING")) ||
                (ms.contains("COCOA")) || (ms.contains("LEAVES")) || (ms.contains("VINE")) ;
    }


    public boolean isLog(Material m){

        String ms = m.toString();

        return (ms.contains("_LOG"));
    }


    public boolean isOnDirt(Block b){
        return b.getRelative(0, -1, 0).getType() == Material.DIRT;
    }


    public boolean isStaff(Player p){
        return p.hasPermission("mofucraft.staff");
    }

    public boolean toCancel(Player p, Block b){
        //採取資源じゃなかったらキャンセルしない
        if(!isSaishu(p.getWorld())){
            return false;
        }

        //作物ならキャンセルしない
        if(isCrop(b.getType())){
            return false;
        }

        //スタッフならキャンセルしない
        if(isStaff(p)){
            return false;
        }

        return true;
    }

    public boolean toReplantTree(Player p, Block b){

        //資源じゃなかったら植え直しをしない
        if(!isShigen(p.getWorld())){
            return false;
        }

        //木じゃなかったら植え直しをしない
        if(!isLog(b.getType())) {
            return false;
        }

        //下が土じゃなかったら植え直しをしない
        if(!isOnDirt(b)) {
            return false;
        }

        return true;
    }

    public boolean toReplantCrop(Player p, Block b){
        //採取資源じゃなかったら植え直しをしない
        if(!isSaishu(p.getWorld())){
            return false;
        }

        //作物じゃなかったら植え直しをしない
        if(!isCrop(b.getType())){
            return false;
        }

        return true;
    }
}
