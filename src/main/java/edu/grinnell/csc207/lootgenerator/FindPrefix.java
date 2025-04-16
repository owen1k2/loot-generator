package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FindPrefix {
    
    public ArrayList<Prefix> PREFIX = new ArrayList<>();

    public class Prefix{
        public String key;
        public String type;
        public int[] val;
        
        public Prefix(String prefixName, String type, int[] stats) {
            this.key = prefixName;
            this.type = type;
            this.val = stats;
        }
    }

    public FindPrefix(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] prefixPiece = line.split("\t");
            int[] minMax = new int[] 
                {Integer.parseInt(prefixPiece[2]), Integer.parseInt(prefixPiece[3])};
            Prefix prefix = new Prefix(prefixPiece[0], prefixPiece[1], minMax);
            PREFIX.add(prefix);
        }
        s.close();
    }

    public Prefix findSinglePrefix() {
        int i = (int) (PREFIX.size() * Math.random());
            return PREFIX.get(i); 
    }

    public int findPrefixStats(String key) {
        int min = 0;
        int max = 0;
        for(int i = 0; i < PREFIX.size(); i++) {
            if(PREFIX.get(i).key.equals(key)) {
                min = PREFIX.get(i).val[0];
                max = PREFIX.get(i).val[1];
            }
        }
        int prefixStats = (int) ((max - min) * Math.random()) + min;
        return prefixStats;
    }
}
