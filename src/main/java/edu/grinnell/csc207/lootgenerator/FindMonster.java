package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FindMonster {
    public ArrayList<Monster> MONSTER = new ArrayList<>();


    public class Monster{
        public String key;
        public String val;
        
        public Monster(String monsterName, String treasureType) {
            this.key = monsterName;
            this.val = treasureType;
        }
    }

    public FindMonster(String path) throws FileNotFoundException {
        File p = new File(path);
        Scanner s = new Scanner(p);
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] fullMonster = line.split("\t");
            Monster m = new Monster(fullMonster[0], fullMonster[3]);
            MONSTER.add(m);
        }
        s.close();
    }

    public Monster findSingleMonster() {
        int i = (int) (MONSTER.size() * Math.random());
        return MONSTER.get(i);
    }
}