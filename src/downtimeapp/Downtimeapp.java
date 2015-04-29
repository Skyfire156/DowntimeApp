/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package downtimeapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Downtimeapp {
    Player character;
    Database data;
    DowntimeGUI gui;
    public static void main(String[] args) {
        Downtimeapp program = new Downtimeapp();
    }
    
    public Downtimeapp(){
        data = new Database();
        
        try {
            character = loadData();
        } catch (IOException ex) {
            Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(character == null)
            character = new Player(data);
        
        gui = new DowntimeGUI(character, this);
        gui.setVisible(true);
        fillGUI();
        gui.updateGUI();
        
    }
    
    public Player loadData() throws FileNotFoundException, IOException, ClassNotFoundException{
        String loadpath = "DowntimeSave.sav";
        File savedgame = new File(loadpath);
        FileInputStream loadfile = new FileInputStream(savedgame);
        Player room;
        try (ObjectInputStream loaddata = new ObjectInputStream(loadfile)) {
            room = (Player) loaddata.readObject();
        }
        return room;
    }
    
    public void saveData(Player savdata){
        FileOutputStream savefile = null;
        try {
            File newsave = new File("DowntimeSave.sav");
            savefile = new FileOutputStream(newsave);
            ObjectOutputStream savedata = new ObjectOutputStream(savefile);
            savedata.writeObject(savdata);
            savedata.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                savefile.close();
            } catch (IOException ex) {
                Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void fillGUI(){
        for(Building build: character.buildings){
            gui.addBuilding(true);
        }
        for(Building build: character.orgs){
            gui.addBuilding(false);
        }
    }
    
}