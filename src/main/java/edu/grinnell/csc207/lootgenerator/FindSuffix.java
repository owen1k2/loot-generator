package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Finds the suffix of a piece of armor
 */
public class FindSuffix {
    /**
     * An ArrayList of all the possible suffix's
     */
    public ArrayList<Suffix> suffix = new ArrayList<>();

    /**
     * Creates a single suffix
     */
    public class Suffix {
        public String key;
        public String type;
        public int[] val;

        /**
         * The Constructor for the Suffix class.
         * This creates a single suffix with a name
         * the type of damage and stats
         * 
         * @param suffixName String: The name of the suffix
         * @param type       String: The type of stat given by the suffix
         * @param stats      int[]: The min and max possible stat given
         *                   by the suffix
         */
        public Suffix(String suffixName, String type, int[] stats) {
            this.key = suffixName;
            this.type = type;
            this.val = stats;
        }
    }

    /**
     * The Constructor for the FindSuffix class.
     * This reads in all suffixes from the given
     * txt file and adds them to the SUFFIX ArrayList
     * 
     * @param path String: The path that leads to the file
     * @throws FileNotFoundException
     */
    public FindSuffix(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] suffixPiece = line.split("\t");
            int[] minMax = new int[] {
                    Integer.parseInt(suffixPiece[2]), Integer.parseInt(suffixPiece[3]) };
            Suffix singleSuffix = new Suffix(suffixPiece[0], suffixPiece[1], minMax);
            suffix.add(singleSuffix);
        }
        s.close();
    }

    /**
     * Finds a single random suffix from
     * the ArrayList SUFFIX
     * 
     * @return a single random suffix
     */
    public Suffix findSingleSuffix() {
        int i = (int) (suffix.size() * Math.random());
        return suffix.get(i);
    }

    /**
     * Finds the stats of a single suffix
     * 
     * @param key String: The name of the suffix
     *            for which the stats are bing found
     * @return A "random" integer value of the stat
     *         between the given values by the suffix.
     */
    public int findSuffixStats(String key) {
        int min = 0;
        int max = 0;
        for (int i = 0; i < suffix.size(); i++) {
            if (suffix.get(i).key.equals(key)) {
                min = suffix.get(i).val[0];
                max = suffix.get(i).val[1];
            }
        }
        int suffixStats = (int) ((max - min) * Math.random()) + min;
        return suffixStats;
    }
}
