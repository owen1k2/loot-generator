package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The class that finds the stats for a piece of armor.
 */
public class FindStats {
    /**
     * The ArrayList of all pieces of armor from given txt file.
     */
    public ArrayList<Armor> armor = new ArrayList<>();
    /**
     * The class that creates a single piece of armor.
     */
    public class Armor {
        public String key;
        public int[] val;

        /**
         * The Constructor for the Amor class.
         * Creates a single piece of armor.
         * 
         * @param armorName String: The name of the piece of armor
         * @param stats     int[]: The minimum and maximum int value
         *                  for the stat of the piece of armor.
         */
        public Armor(String armorName, int[] stats) {
            this.key = armorName;
            this.val = stats;
        }

    }

    /**
     * The Constructor for the FindStats class.
     * Scans in all the possible armor pieces from the given
     * txt file and adds them to the ArrayList ARMOR.
     * 
     * @param path String: The path for the given txt file.
     * @throws FileNotFoundException
     */
    public FindStats(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] armorPiece = line.split("\t");
            int[] minMax = new int[] {
                    Integer.parseInt(armorPiece[1]), Integer.parseInt(armorPiece[2]) };
            Armor a = new Armor(armorPiece[0], minMax);
            armor.add(a);
        }
        s.close();
    }

    /**
     * Finds a "random" armor value from between
     * the min and max armor values of the given
     * piece of armor.
     * 
     * @param key String: The name of a piece of armor.
     * @return An int which is the armor class of the
     *         given piece of armor.
     */
    public int findArmorClass(String key) {
        int min = 0;
        int max = 0;
        for (int i = 0; i < armor.size(); i++) {
            if (armor.get(i).key.equals(key)) {
                min = armor.get(i).val[0];
                max = armor.get(i).val[1];
            }
        }
        int armorClass = (int) ((max - min) * Math.random()) + min;
        return armorClass;
    }
}