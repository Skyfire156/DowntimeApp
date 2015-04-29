/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package downtimeapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author natha_000
 */
public class DatabaseDriver {

    static void fillData(Database aThis) {
        fillStuff(aThis);
    }
    
    
    
    public DatabaseDriver(){}
    
    /**
     *
     * @param db
     */
    
    public static void fillStuff(Database db) {
        BufferedReader roomdata = null;
        BufferedReader teamdata = null;
        boolean parsingteams = false;
        Database data = db;
        try {
            FileReader roomfile = new FileReader("downtime roomdata.txt");
            roomdata = new BufferedReader(roomfile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FileReader teamfile = new FileReader("downtime teamdata.txt");
            teamdata = new BufferedReader(teamfile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        data.roomstats = new ArrayList<>();
        data.teamstats = new ArrayList<>();
        try {
            parseReader(parsingteams, roomdata, teamdata, db);
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        parsingteams = true;
        try {
            parseReader(parsingteams, roomdata, teamdata, db);
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void parseReader(boolean parsingteams, BufferedReader roomdata, BufferedReader teamdata, Database data) throws IOException{
        String teststring = "";
        String currentline = null;
        BufferedReader workingreader;
        if(parsingteams){
            workingreader = teamdata;
        }else{
            workingreader = roomdata;
        }
        do{
            do{
                try {
                    currentline = workingreader.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                teststring += currentline+"\n";
            }while(!"".equals(currentline));
            parseRooms(teststring, parsingteams, data);
            teststring = "";
        }while(workingreader.ready());
    }
    
    private static void parseRooms(String roomdata, boolean parsingteams, Database data){
        String workstring;
        Room temproom = new Room();
        temproom.name = roomdata.substring(0, roomdata.indexOf("\n"));
        do{
            workstring = roomdata.substring(0, roomdata.indexOf("\n"));
            if(workstring.contains("Earnings")){
                parseEarnings(workstring, temproom);
            }
            if(workstring.contains("Benefit")){
                temproom.benefit = workstring.substring(workstring.indexOf("Benefit")+8);
            }
            if(workstring.contains("Create"))
                parseCreate(workstring, temproom);
            if(workstring.contains("Time"))
                parseTime(workstring, temproom);
            if(workstring.contains("Upgrades To"))
                parseUpgrades(workstring, temproom);
            if(workstring.contains("Upgrades From"))
                parseUpsfrom(workstring, temproom);
            roomdata = roomdata.substring(roomdata.indexOf("\n")+1);
        }while(!"".equals(workstring));
        ArrayList<Room> workstats;
        if(parsingteams){
            workstats = data.teamstats;
        }else{
            workstats = data.roomstats;
        }
        
        workstats.add(temproom);
    }
    
    private static void parseEarnings(String earnings, Room temproom){
        if(earnings.contains("gp")){
            temproom.earnsgp = true;
        }
        if(earnings.contains("Goods")){
            temproom.earnsgoods = true;
        }
        if(earnings.contains("Influence")){
            temproom.earnsinfluence = true;
        }
        if(earnings.contains("Labor")){
            temproom.earnslabor = true;
        }
        if(earnings.contains("Magic")){
            temproom.earnsmagic = true;
        }
        if(earnings.contains("capital")){
            temproom.earnscapital = true;
        }
        Pattern pattern;
        pattern = Pattern.compile("\\D*(\\d*)");
        Matcher matcher = pattern.matcher(earnings);
        if (matcher.matches() && matcher.groupCount() == 1){
            String digitstr = matcher.group(1);
            temproom.bonus = Integer.parseInt(digitstr);
        }
    }
    
    private static void parseCreate(String createcost, Room temproom){
        temproom.createcost = new int[5];
        String[] patterns = {"(\\d) Goods","(\\d+) Influence", "(\\d*) Labor", "(\\d*) Magic", "(\\d*) gp"};
        Pattern pattern;
        Matcher matcher;
        for(int i = 0; i < 5; i++){
            pattern = Pattern.compile(patterns[i]);
            matcher = pattern.matcher(createcost);
            if (matcher.find()){
                String digitstr = matcher.group(1);
                temproom.createcost[i] = Integer.parseInt(digitstr);
            }
        }
    }
    
    private static void parseTime(String time, Room temproom){
        Pattern pattern;
        pattern = Pattern.compile("Time (\\d*) days?");
        Matcher matcher = pattern.matcher(time);
        if (matcher.find()){
            String digitstr = matcher.group(1);
            temproom.createtime = Integer.parseInt(digitstr);
        }
        
    }
    
    private static void parseUpgrades(String upgrades, Room temproom){
        temproom.upgradeto = new String[4];
        upgrades = upgrades.trim();
        Pattern pattern;
        pattern = Pattern.compile("Upgrades To ([A-Z][a-z]+ ?([A-Z][a-z]+)?)(, ([A-Z][a-z]+ ?([A-Z][a-z]+)?))?");
        Matcher matcher = pattern.matcher(upgrades);
        if (matcher.find()){
            String digitstr = matcher.group(1);
            temproom.upgradeto[0] = digitstr;
            if(matcher.group(4) != null)
                temproom.upgradeto[1] = matcher.group(4);
        }
    }
    
    private static void parseUpsfrom(String workstring, Room temproom){
        temproom.upgradefrom = new String[4];
        workstring = workstring.trim();
        Pattern pattern;
        pattern = Pattern.compile("Upgrades From ([A-Z][a-z]+ ?([A-Z][a-z]+)?)(, ([A-Z][a-z]+ ?([A-Z][a-z]+)?))?");
        Matcher matcher = pattern.matcher(workstring);
        if (matcher.find()){
            String digitstr = matcher.group(1);
            temproom.upgradefrom[0] = digitstr;
            if(matcher.group(4) != null)
                temproom.upgradefrom[1] = matcher.group(4);
        }
    }
}
