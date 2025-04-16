package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;


public class FillTreasure {

    HashMap<String, String[]> treasureTree = new HashMap<>();
    TreeMap<String, String[]> treasure = new TreeMap<>();
    

    public FillTreasure(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] fullLine = line.split("\t");
            String treasureClass = fullLine[0];
            String[] treasureType = new String[] 
                {fullLine[1], fullLine[2], fullLine[3]};
            treasureTree.put(treasureClass, treasureType);
        }
        treasure = new TreeMap<>(treasureTree);
        s.close();
    }

    public TreeMap<String, String[]> findTreasureTree() {
        return treasure;
    }

    public HashMap<String, String[]> findTreasureTreeHash() {
        return treasureTree;
    }
    
        private String findTreasurePieceHelper(String key) {
            if(treasure.containsKey(key)) {
                int i = treasure.get(key).length;
                int piece = (int) (i * Math.random());
                return findTreasurePieceHelper(treasure.get(key)[piece]);
            }
            return key;
        }

    public String findTreasurePiece(String key) {
        return findTreasurePieceHelper(key);
    }
}