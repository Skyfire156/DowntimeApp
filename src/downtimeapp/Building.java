/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package downtimeapp;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author natha_000
 */
public class Building implements Serializable{
    ArrayList<Room> rooms;
    Database db;
    String name;
    boolean[] canearn;
    int[] bonuses;
    int totalbonus;
    
    public Building(Database data){
        rooms = new ArrayList<>();
        bonuses = new int[5];
        canearn = new boolean[5];
        db = data;
        setBonus();
    }
    
   /* public void addRoom(String name){
        Room temp = db.findRoom(name, );
        rooms.add(temp);
    }*/
    
    public void setBonus(){
        bonuses = new int[5];
        for(Room room : rooms){
            if(room.built){
                if(room.earnsgp){
                    bonuses[4] += room.currentbonus[4];
                    canearn[4] = true;
                }
                if(room.earnsgoods){
                    bonuses[0] += room.currentbonus[0];
                    canearn[0] = true;
                }
                if(room.earnsinfluence){
                    bonuses[1] += room.currentbonus[1];
                    canearn[1] = true;
                }
                if(room.earnslabor){
                    bonuses[2] += room.currentbonus[2];
                    canearn[2] = true;
                }
                if(room.earnsmagic){
                    bonuses[3] += room.currentbonus[3];
                    canearn[3] = true;
                }
            }
        }
        
    }
    
    public int getValue(){
        int retval =0;
        for(Room room : rooms){
            retval += room.createcost[0]*20;
            retval += room.createcost[1]*30;
            retval += room.createcost[2]*20;
            retval += room.createcost[3]*100;
        }
        return retval;
    }
    
}
