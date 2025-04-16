package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FindSuffix {
    
    public ArrayList<Suffix> SUFFIX = new ArrayList<>();

    public class Suffix{
        public String key;
        public String type;
        public int[] val;
        
        public Suffix(String suffixName, String type, int[] stats) {
            this.key = suffixName;
            this.type = type;
            this.val = stats;
        }
    }

    public FindSuffix(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] suffixPiece = line.split("\t");
            int[] minMax = new int[] 
                {Integer.parseInt(suffixPiece[2]), Integer.parseInt(suffixPiece[3])};
            Suffix suffix = new Suffix(suffixPiece[0], suffixPiece[1], minMax);
            SUFFIX.add(suffix);
        }
        s.close();
    }

    public Suffix findSingleSuffix() {
        int i = (int) (SUFFIX.size() * Math.random());
            return SUFFIX.get(i); 
    }

    public int findSuffixStats(String key) {
        int min = 0;
        int max = 0;
        for(int i = 0; i < SUFFIX.size(); i++) {
            if(SUFFIX.get(i).key.equals(key)) {
                min = SUFFIX.get(i).val[0];
                max = SUFFIX.get(i).val[1];
            }
        }
        int suffixStats = (int) ((max - min) * Math.random()) + min;
        return suffixStats;
    }
}
