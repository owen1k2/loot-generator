package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Finds all the monsters from a given txt file.
 */
public class FindMonster {
    /**
     * The ArrayList of all the monsters from a given txt file.
     */
    public ArrayList<Monster> monsters = new ArrayList<>();
    /**
     * Creates a single monster
     */
    public class Monster {
        public String key;
        public String val;

        /**
         * The constructor of the monster class which creates a
         * monster from the given txt file.
         * 
         * @param monsterName  String: The name of the monster.
         * @param treasureType String: The name of the
         *                     type of treasure the monster drops.
         */
        public Monster(String monsterName, String treasureType) {
            this.key = monsterName;
            this.val = treasureType;
        }
    }

    /**
     * Reads in the monsters from the given txt file and creates
     * an ArrayList of all the monsters.
     * 
     * @param path String: The path that leads to the file.
     * @throws FileNotFoundException
     */
    public FindMonster(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] fullMonster = line.split("\t");
            Monster m = new Monster(fullMonster[0], fullMonster[3]);
            monsters.add(m);
        }
        s.close();
    }

    /**
     * Finds and returns a single random monster
     * 
     * @return a single random Moster from the
     *         ArrayList MONSTER.
     */
    public Monster findSingleMonster() {
        int i = (int) (monsters.size() * Math.random());
        return monsters.get(i);
    }
}