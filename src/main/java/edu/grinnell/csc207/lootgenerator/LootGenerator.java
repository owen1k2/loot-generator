package edu.grinnell.csc207.lootgenerator;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.grinnell.csc207.lootgenerator.FindMonster.Monster;
import edu.grinnell.csc207.lootgenerator.FindPrefix.Prefix;
import edu.grinnell.csc207.lootgenerator.FindSuffix.Suffix;

/**
 * Runs the program and generates loot
 */
public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/large";

    /**
     * Checks to see if a prefix or suffix should
     * be added to a piece of armor.
     * 
     * @return True if it should be false otherwise
     */
    private static boolean gotLucky() {
        int i = (int) (10 * Math.random());
        if (i % 2 == 0) {
            return true;
        }
        return false;
    }

    /**
     * Prints the string of the monster being fought.
     * 
     * @param m Monster: The monster being fought
     * @return The string reading "Fighting monster"
     */
    private static String fightMonster(Monster m) {
        String s = "Fighting " + m.key;
        return s;
    }

    /**
     * Prints the string for a monster being slain.
     * 
     * @param m Monster: The monster being slain.
     * @return The string reading: "You have slain monster"
     */
    private static String slainMonster(Monster m) {
        String s = "You have slain " + m.key;
        return s;
    }

    /**
     * Prints the string for the loot received from slaying a monster
     * 
     * @param m    Monster: The monster being slain
     * @param t    String: The treasure looted
     * @param ac   int: The armor class given by the treasure
     * @param p    Prefix: The potential prefix received
     * @param s    Suffix: The potential suffix received
     * @param preS int: The stats from the preffix
     * @param sufS int: The stats from the suffix
     * @return The string that prints the full loot structure
     *         of the monster being looted.
     */
    private static String lootMonster(Monster m, String t, int ac, Prefix p,
            Suffix s, int preS, int sufS) {
        boolean prefixLucky = gotLucky();
        boolean suffixLucky = gotLucky();
        String str = m.key + " dropped:" + "\n" + "\n";
        if (prefixLucky) {
            str = str + p.key + " ";
        }
        str = str + t + " ";
        if (suffixLucky) {
            str = str + s.key;
        }
        str = str + "\n";
        str = str + "Defense: " + ac + "\n";
        if (prefixLucky) {
            str = str + preS + " " + p.type + "\n";
        }
        if (suffixLucky) {
            str = str + sufS + " " + s.type + "\n";
        }
        return str;
    }

    /**
     * The main function that runs the program
     * 
     * @param args String[]: The arguments given to main
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        FindMonster monster = new FindMonster(DATA_SET + "/monstats.txt");
        FillTreasure treasure = new FillTreasure(DATA_SET + "/TreasureClassEx.txt");
        FindStats stats = new FindStats(DATA_SET + "/armor.txt");
        FindPrefix prefix = new FindPrefix(DATA_SET + "/MagicPrefix.txt");
        FindSuffix suffix = new FindSuffix(DATA_SET + "/MagicSuffix.txt");

        Monster initMonster = monster.findSingleMonster();
        String initTreasure = treasure.findTreasurePiece(initMonster.val);
        int initAC = stats.findArmorClass(initTreasure);
        Prefix initPrefix = prefix.findSinglePrefix();
        Suffix initSuffix = suffix.findSingleSuffix();
        int initPreStat = prefix.findPrefixStats(initPrefix.key);
        int initSufStat = suffix.findSuffixStats(initSuffix.key);

        System.out.println("This program kills monsters and generates loot!");
        System.out.println(fightMonster(initMonster));
        System.out.println(slainMonster(initMonster));
        System.out.println(
                lootMonster(initMonster, initTreasure, initAC, initPrefix,
                        initSuffix, initPreStat, initSufStat));
        System.out.println("Fight Again [y/n]?");
        while (true) {
            char check = s.nextLine().toLowerCase().charAt(0);
            if (check == 'y') {
                Monster m = monster.findSingleMonster();
                String t = treasure.findTreasurePiece(m.val);
                int ac = stats.findArmorClass(t);
                Prefix pre = prefix.findSinglePrefix();
                Suffix suf = suffix.findSingleSuffix();
                int preStat = prefix.findPrefixStats(pre.key);
                int sufStat = suffix.findSuffixStats(suf.key);

                System.out.println("This program kills monsters and generates loot!");
                System.out.println(fightMonster(m));
                System.out.println(slainMonster(m));
                System.out.println(lootMonster(m, t, ac, pre, suf, preStat, sufStat));
                System.out.println("Fight Again [y/n]?");
            } else if (check == 'n') {
                System.out.println("Program has ended");
                break;
            } else {
                System.out.println("Invalid input!");
                System.out.println("Fight Again [y/n]?");
            }
        }
        s.close();
    }
}
