package edu.grinnell.csc207.lootgenerator;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.grinnell.csc207.lootgenerator.FindMonster.Monster;
import edu.grinnell.csc207.lootgenerator.FindPrefix.Prefix;
import edu.grinnell.csc207.lootgenerator.FindSuffix.Suffix;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/large";
    
    private static boolean gotLucky() {
        int i = (int) (10 * Math.random());
        if(i % 2 == 0) {
            return true;
        }
        return false;
    }

    private static String fightMonster(Monster m) {
        String s = "Fighting " + m.key;
        return s;
    }

    private static String slainMonster(Monster m) {
        String s = "You have slain " + m.key;
        return s;
    }

    private static String lootMonster(Monster m, String t, int ac, Prefix p, Suffix s, int preS, int sufS) {
        boolean prefixLucky = gotLucky();
        boolean suffixLucky = gotLucky();
        String str = m.key + " dropped:" + "\n" + "\n";
        if(prefixLucky) {
            str = str + p.key + " ";
        }
        str = str + t + " ";
        if(suffixLucky) {
            str = str + s.key;
        }
        str = str + "\n";
        str = str + "Defense: " + ac + "\n";
        if(prefixLucky) {
            str = str + preS + " " + p.type + "\n";
        }
        if(suffixLucky) {
            str = str + sufS + " " + s.type + "\n";
        }
        return str;
    }

    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        FindMonster Monster = new FindMonster(DATA_SET + "/monstats.txt");
        FillTreasure Treasure = new FillTreasure(DATA_SET + "/TreasureClassEx.txt");
        FindStats Stats = new FindStats(DATA_SET + "/armor.txt");
        FindPrefix Prefix = new FindPrefix(DATA_SET + "/MagicPrefix.txt");
        FindSuffix Suffix = new FindSuffix(DATA_SET + "/MagicSuffix.txt");

        Monster initMonster = Monster.findSingleMonster();
        String initTreasure = Treasure.findTreasurePiece(initMonster.val);
        int initAC = Stats.findArmorClass(initTreasure);
        Prefix initPrefix = Prefix.findSinglePrefix();
        Suffix initSuffix = Suffix.findSingleSuffix();
        int initPreStat = Prefix.findPrefixStats(initPrefix.key);
        int initSufStat = Suffix.findSuffixStats(initSuffix.key);

        System.out.println("This program kills monsters and generates loot!");
        System.out.println(fightMonster(initMonster));
        System.out.println(slainMonster(initMonster));
        System.out.println(lootMonster(initMonster, initTreasure, initAC, initPrefix, initSuffix, initPreStat, initSufStat));
        System.out.println("Fight Again [y/n]?");
        while(true) {
            char check = s.nextLine().toLowerCase().charAt(0);
            if(check == 'y') {
                Monster monster = Monster.findSingleMonster();
                String treasure = Treasure.findTreasurePiece(monster.val);
                int ac = Stats.findArmorClass(treasure);
                Prefix prefix = Prefix.findSinglePrefix();
                Suffix suffix = Suffix.findSingleSuffix();
                int preStat = Prefix.findPrefixStats(prefix.key);
                int sufStat = Suffix.findSuffixStats(suffix.key);

                System.out.println("This program kills monsters and generates loot!");
                System.out.println(fightMonster(monster));
                System.out.println(slainMonster(monster));
                System.out.println(lootMonster(monster, treasure, ac, prefix, suffix, preStat, sufStat));
                System.out.println("Fight Again [y/n]?");
            } else if (check == 'n') {
                System.out.println("Program has ended");
                break;
            } else {
                System.out.println("Invalid input!");
                System.out.println("Fight Again [y/n]?");
            }
        }
       //finds a random monster
        //System.out.println(Monsters.findSingleMonster().key);
       // System.out.println(Monsters.findSingleMonster().val);
       //finds random piece of treasure
       //System.out.println(Treasure.findTreasurePiece(Monsters.findSingleMonster().val));
       //finds the stats of a piece of treasure
       //System.out.println(Stats.findArmorClass(Treasure.findTreasurePiece(Monsters.findSingleMonster().val)));
      /*  if(gotLucky()) {
        edu.grinnell.csc207.lootgenerator.FindPrefix.Prefix newPrefix = Prefix.findSinglePrefix();
        System.out.println(newPrefix.key);
        System.out.println(newPrefix.type);
        System.out.println(newPrefix.val[0]);
        System.out.println(newPrefix.val[1]);
       } else {
        System.out.println("sad");
       } */
       
       //System.out.println(Suffix.findSingleSuffix().key);
       
    }
}
