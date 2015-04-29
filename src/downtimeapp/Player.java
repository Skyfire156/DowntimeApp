/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package downtimeapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    String name;
    ArrayList<Building> buildings;
    ArrayList<Building> orgs;
    int Goods;
    int Influence;
    int Labor;
    int Magic;
    double gp;
    double storedgp;
    int day;
    Database db;
    String[] actionspossible;
    String[] actionstaken;
    
    public Player(Database data){
        buildings = new ArrayList<>();
        orgs = new ArrayList<>();
        db = data;
        actionspossible = new String[]{
            "Add Spells to Spellbook", 
            "Craft Magic Items", 
            "Craft Mundane Items", 
            "Earn Capital", 
            "Earn XP", 
            "Gather Information", 
            "Heal Others", "Run Kingdom", 
            "Promote Business", 
            "Replace Animal Companion", 
            "Replace Familiar", 
            "Research Spell", 
            "Research Facts", 
            "Rest", "Retrain", 
            "Run Business", 
            "Plot for Adventure", 
            "Train Animal"
        };
        
    }
    
    public void addBuilding(String buildname){
        Building temp = new Building(db);
        temp.name = buildname;
        buildings.add(temp);
    }
    
    public void addOrganization(String buildname){
        Building temp = new Building(db);
        temp.name = buildname;
        orgs.add(temp);
    }
    
    /*
    public void copyBuilding(Building tocopy){
        Building newbuild = new Building(tocopy.db);
        newbuild.rooms = (ArrayList<Room>) tocopy.rooms.clone(); //keep an eye on this, might not work as intended.
        newbuild.name = tocopy.name;
    }
    */
    
    public void buildRoom(Building building, String roomname, boolean isRoom){
        Room temp = db.findRoom(roomname, isRoom);
        temp.daystarted = day;
        if(temp.createtime==0){
            temp.built = true;
        }
        building.rooms.add(temp);
        Goods -= temp.createcost[0];
        Influence -= temp.createcost[1];
        Labor -= temp.createcost[2];
        Magic -= temp.createcost[3];
    }
    
    
    public void adminbuildRoom(Building building, String roomname, boolean isRoom, int startday){
        Room temp = db.findRoom(roomname, isRoom);
        temp.daystarted = startday;
        if(day-temp.daystarted>= temp.createtime)
            temp.built = true;
        building.rooms.add(temp);
    }
    
    public void upgradeRoom(Building building, Room room, String upname, boolean isRoom){
        //make so cannot upgrade something that costs more than you have
        if(upname.equals("Furnish")&&!room.name.contains("(Furnished)")&&Goods>=8&&Labor>=7){
            room.name = room.name+"(Furnished)";
            room.built = false;
            room.createtime = 20;
            room.daystarted = day;
            room.bonus += 5;
            room.createcost[0] += 8;
            room.createcost[2] += 7;
            Goods -= 8;
            Labor -= 7;
        } else{
        Room temp = db.findRoom(upname, isRoom);
        building.rooms.remove(room);
        temp.createtime -= room.createtime;
        temp.daystarted = day;
        if(temp.createtime == 0 )
            temp.built = true;
        building.rooms.add(temp);
        Goods -= (temp.createcost[0]-room.createcost[0]);
        Influence -= (temp.createcost[1]-room.createcost[1]);
        Labor -= (temp.createcost[2]-room.createcost[2]);
        Magic -= (temp.createcost[3]-room.createcost[3]);
        }
    }
    
    public void adminupgradeRoom(Building building, Room room, String upname, boolean isRoom){
        if(upname.equals("Furnish")&&!room.name.contains("(Furnished)")){
            room.name = room.name+"(Furnished)";
            room.bonus += 5;
            room.createcost[0] += 8;
            room.createcost[2] += 7;
        } else{
        Room temp = db.findRoom(upname, isRoom);
        building.rooms.remove(room);
        building.rooms.add(temp);
        }
    }
    
    public ArrayList<Room> canBuild(boolean isRoom){
        ArrayList<Room> templist = new ArrayList<>();
        ArrayList<Room> worklist;
        if(isRoom){
            worklist = db.roomstats;
        }else{
            worklist = db.teamstats;
        }
        for(int i = 0; i < worklist.size(); i++){
            Room temp = worklist.get(i);
            if(Goods >= temp.createcost[0])
                templist.add(temp);
        }
        for(int i = 0; i < templist.size(); i++){
            Room temp = templist.get(i);
            if(temp.createcost[1]>this.Influence){
                templist.remove(i);
                i--;
            }
        }
        for(int i = 0; i < templist.size(); i++){
            Room temp = templist.get(i);
            if(temp.createcost[2]>this.Labor){
                templist.remove(i);
                i--;
            }
        }
        for(int i = 0; i < templist.size(); i++){
            Room temp = templist.get(i);
            if(temp.createcost[3]>this.Magic){
                templist.remove(i);
                i--;
            }
        }
        return templist;
        
    }
    
    public void advanceDay(String actTaken, int[] preearned){
        int[] tempB = calcEarnings(preearned);
        double tempgp = (double)tempB[4]/10;
        storedgp += tempgp;
        if(storedgp >= tempB[0]*10){
            Goods += tempB[0];
            storedgp -= (tempB[0]*10);
        }
        if(storedgp >= tempB[1]*15){
            Influence += tempB[1];
            storedgp -= (tempB[1]*15);
        }
        if(storedgp >= tempB[2]*10){
            Labor += tempB[2];
            storedgp -= (tempB[2]*10);
        }
        if(storedgp >= tempB[3]*50){
            Magic += tempB[3];
            storedgp -= (tempB[3]*50);
        }
        day++;
        for(Building building : buildings){
            for(Room room: building.rooms){
                if(!room.built&&day >=(room.daystarted+room.createtime))
                    room.built = true;
            }
        }
        for(Building building : orgs){
            for(Room room: building.rooms){
                if(!room.built&&day >=(room.daystarted+room.createtime))
                    room.built = true;
            }
        }
    }
    
    
    
    public int[] calcEarnings(int[] preearned){
        int SingleBonus;
        int[] BonusSet = preearned;
        
        for(Building building : buildings){
            building.setBonus();
            for(int i = 0; i< building.bonuses.length; i++){
                SingleBonus = building.bonuses[i];
                if(building.bonuses[i]>0){
                    SingleBonus += 10;
                    if(i != 4)
                        SingleBonus /= 10;
                }
                BonusSet[i] += SingleBonus;
            }
        }
        for(Building building : orgs){
            building.setBonus();
            for(int i = 0; i< building.bonuses.length; i++){
                SingleBonus = building.bonuses[i];
                if(building.bonuses[i]>0){
                    SingleBonus += 10;
                    if(i != 4)
                        SingleBonus /= 10;
                }
                BonusSet[i] += SingleBonus;
            }
        }
        return BonusSet;
    }
    
    public String[] getPossibleActs(){
        return actionspossible;
    }
}
