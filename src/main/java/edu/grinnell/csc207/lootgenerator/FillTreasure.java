package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
/**
 * The class that finds all the treasure
 */
public class FillTreasure {

    HashMap<String, String[]> treasureTree = new HashMap<>();
    TreeMap<String, String[]> treasure = new TreeMap<>();

    /**
     * The constructor for the FillTreasure class.
     * Fills first the HashMap treasureTree with all the
     * possible treasures from the given txt file and then
     * sorts into the TreeMap treasure creating a map of all
     * treasures from the given txt file.
     * 
     * @param path String: The path that leads to the file
     * @throws FileNotFoundException
     */
    public FillTreasure(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] fullLine = line.split("\t");
            String treasureClass = fullLine[0];
            String[] treasureType = new String[] {fullLine[1], fullLine[2], fullLine[3] };
            treasureTree.put(treasureClass, treasureType);
        }
        treasure = new TreeMap<>(treasureTree);
        s.close();
    }

    /**
     * Returns the TreeMap treasure
     * 
     * @return The TreeMap Treasure
     */
    public TreeMap<String, String[]> findTreasureTree() {
        return treasure;
    }

    /**
     * Returns the HashMap treasureTree
     * 
     * @return the Hashmap treasureTree
     */
    public HashMap<String, String[]> findTreasureTreeHash() {
        return treasureTree;
    }

    /**
     * Helper method that does the recursion to find
     * a random single piece of treasure.
     * 
     * @param key String: The name of the piece of
     *            treasure selected
     * @return The key found by the recursion
     */
    private String findTreasurePieceHelper(String key) {
        if (treasure.containsKey(key)) {
            int i = treasure.get(key).length;
            int piece = (int) (i * Math.random());
            return findTreasurePieceHelper(treasure.get(key)[piece]);
        }
        return key;
    }

    /**
     * Finds a key which is the name of a single
     * piece of treasure
     * 
     * @param key String: The value of a monster or the
     *            key that shows the treasure it drops.
     * @return A randomly selected piece of treasure
     */
    public String findTreasurePiece(String key) {
        return findTreasurePieceHelper(key);
    }
}