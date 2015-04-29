/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package downtimeapp;

/**
 *
 * @author natha_000
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable{
    
    ArrayList<Room> roomstats;
    ArrayList<Room> teamstats;
    
    
    public Database(){
        DatabaseDriver.fillData(this);
    }
    
    
    public Room findRoom(String room, boolean isRoom){
        Room temp = new Room();
        ArrayList<Room> worklist;
        if(isRoom){
            worklist = roomstats;
        }else{
            worklist = teamstats;
        }
        for(int i = 0; i < worklist.size(); i++){
            if(room.equals(worklist.get(i).name))
                temp = worklist.get(i);
        }
        Room temp2 = new Room();
        temp2.copy(temp);
        return temp2;
    }
    
}
