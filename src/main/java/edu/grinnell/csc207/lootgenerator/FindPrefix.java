package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Prefix class which finds a prefix
 */
public class FindPrefix {
    /**
     * The ArrayList of all the prefixes
     */
    public ArrayList<Prefix> prefix = new ArrayList<>();

    /**
     * The prefix class which creates a single prefix
     */
    public class Prefix {
        public String key;
        public String type;
        public int[] val;

        /**
         * The Constructor for the prefix class.
         * This creates a single prefix with a name
         * the type of damage and stats
         * 
         * @param prefixName String: The name of the prefix
         * @param type       String: The type of stat given by the prefix
         * @param stats      int[]: The min and max possible stat given
         *                   by the prefix
         */
        public Prefix(String prefixName, String type, int[] stats) {
            this.key = prefixName;
            this.type = type;
            this.val = stats;
        }
    }

    /**
     * The Constructor for the FindPrefix class.
     * This reads in all prefixes from the given
     * txt file and adds them to the prefix ArrayList
     * 
     * @param path String: The path that leads to the file
     * @throws FileNotFoundException
     */
    public FindPrefix(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] prefixPiece = line.split("\t");
            int[] minMax = new int[] {
                    Integer.parseInt(prefixPiece[2]),
                    Integer.parseInt(prefixPiece[3]) };
            Prefix singlePrefix = new Prefix(prefixPiece[0], prefixPiece[1], minMax);
            prefix.add(singlePrefix);
        }
        s.close();
    }

    /**
     * Finds a single random prefix from
     * the ArrayList PREFIX
     * 
     * @return a single random prefix
     */
    public Prefix findSinglePrefix() {
        int i = (int) (prefix.size() * Math.random());
        return prefix.get(i);
    }

    /**
     * Finds the stats of a single prefix
     * 
     * @param key String: The name of the prefix
     *            for which the stats are bing found
     * @return A "random" integer value of the stat
     *         between the given values by the prefix.
     */
    public int findPrefixStats(String key) {
        int min = 0;
        int max = 0;
        for (int i = 0; i < prefix.size(); i++) {
            if (prefix.get(i).key.equals(key)) {
                min = prefix.get(i).val[0];
                max = prefix.get(i).val[1];
            }
        }
        int prefixStats = (int) ((max - min) * Math.random()) + min;
        return prefixStats;
    }
}
