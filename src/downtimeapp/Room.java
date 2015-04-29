/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package downtimeapp;

import java.io.Serializable;

/**
 *
 * @author natha_000
 */
public class Room implements Serializable{
    String name;
    boolean earnsgp;
    boolean earnsgoods;
    boolean earnsinfluence;
    boolean earnslabor;
    boolean earnsmagic;
    boolean earnscapital;
    int[] currentbonus;
    int bonus;
    String benefit;
    int[] createcost;
    int createtime;
    String[] upgradeto;
    String[] upgradefrom;
    boolean built;
    int daystarted;
    
    public Room(){
        currentbonus = new int[5];
    }
    
    public void copy(Room oldroom){
        this.name = oldroom.name;
        this.earnsgp = oldroom.earnsgp;
        this.earnsgoods = oldroom.earnsgoods;
        this.earnsinfluence = oldroom.earnsinfluence;
        this.earnslabor = oldroom.earnslabor;
        this.earnsmagic = oldroom.earnsmagic;
        this.earnscapital = oldroom.earnscapital;
        this.bonus = oldroom.bonus;
        this.benefit = oldroom.benefit;
        this.createcost = oldroom.createcost;
        this.createtime = oldroom.createtime;
        this.upgradeto = oldroom.upgradeto;
        this.upgradefrom = oldroom.upgradefrom;
        this.currentbonus[4] = oldroom.bonus;
    }
    
    public void incrementBonus(String captype){
        if(currentbonus[4]>0){
            switch(captype){
                case "Goods": 
                    if(earnsgoods){
                    currentbonus[0]++;
                    currentbonus[4]--;
                    }
                    break;
                case "Influence": 
                    if(earnsinfluence){
                        currentbonus[1]++;
                        currentbonus[4]--;
                    }
                    break;
                case "Labor": 
                    if(earnslabor){
                        currentbonus[2]++;
                        currentbonus[4]--;
                    }
                    break;
                case "Magic": 
                    if(earnsmagic){
                        currentbonus[3]++;
                        currentbonus[4]--;
                    }
                    break;
            } // switch
        } //if
    }
    
    public void decrementBonus(String captype){
        if(earnsgp){
            switch(captype){
                case "Goods": 
                    if(currentbonus[0]>0){
                        currentbonus[0]--;
                        currentbonus[4]++;
                    }
                    break;
                case "Influence": 
                    if(currentbonus[1]>0){
                        currentbonus[1]--;
                        currentbonus[4]++;
                    }
                    break;
                case "Labor": 
                    if(currentbonus[2]>0){
                        currentbonus[2]--;
                        currentbonus[4]++;
                    }
                    break;
                case "Magic": 
                    if(currentbonus[3]>0){
                        currentbonus[3]--;
                        currentbonus[4]++;
                    }
                    break;
            } // switch
        } // if
    }
    
}
