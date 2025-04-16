package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FindStats {

    public ArrayList<Armor> ARMOR = new ArrayList<>();

    public class Armor{
        public String key;
        public int[] val;
        
        public Armor(String armorName, int[] stats) {
            this.key = armorName;
            this.val = stats;
        }

    }

    public FindStats(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] armorPiece = line.split("\t");
            int[] minMax = new int[] 
                {Integer.parseInt(armorPiece[1]), Integer.parseInt(armorPiece[2])};
            Armor a = new Armor(armorPiece[0], minMax);
            ARMOR.add(a);
        }
        s.close();
    }

    public int findArmorClass(String key) {
        int min = 0;
        int max = 0;
        for(int i = 0; i < ARMOR.size(); i++) {
            if(ARMOR.get(i).key.equals(key)) {
                min = ARMOR.get(i).val[0];
                max = ARMOR.get(i).val[1];
            }
        }
        int armorClass = (int) ((max - min) * Math.random()) + min;
        return armorClass;
    }
}