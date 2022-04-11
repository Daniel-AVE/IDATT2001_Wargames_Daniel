package edu.ntnu.idatt2001.army_file_managers;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Units.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class armyReader {
    private static final String DELIMITER = ",";

    public armyReader(){}

    public Army readArmyFromFile(File file) throws IOException {
        if (!file.getName().endsWith(".csv")){
            throw new IOException("Error: Unsupported file format. Only .csv-files are supported");
        }
        if (!file.exists()){
            throw new IOException("Error: This file doesn't exist.");
        }
        Army army;
        try (Scanner sc = new Scanner(file)){
            if (!sc.hasNext()){
                throw new IOException("Error: This file is empty");
            }
            army = new Army(sc.nextLine());
            while (sc.hasNext()){

                String line = sc.nextLine();
                String[] tokens = line.split(DELIMITER);

                if (tokens.length != 3){
                    throw new IOException("Error: Line data '" + line + "' is invalid." +
                            "Make sure each line is in the form of 'Unit type,Unit name,Unit health'");
                }

                int health;
                try {
                    health = Integer.parseInt(tokens[2]);
                } catch (NumberFormatException exception){
                    throw new IOException("Error: Health must be an integer (" + exception.getMessage() + ")");
                }
                if (health <= 0 || health > 100){
                    throw new IOException("Error: Health must be an integer between and including 1-100");
                }

                String unitType = tokens[0];
                String unitName = tokens[1];
                boolean correctUnitType = false;
                Unit unit = null;

                switch (unitType) {
                    case "InfantryUnit":
                        unit = new InfantryUnit(unitName, health);
                        correctUnitType = true;
                        break;
                    case "RangedUnit":
                        unit = new RangedUnit(unitName, health);
                        correctUnitType = true;
                        break;
                    case "CavalryUnit":
                        unit = new CavalryUnit(unitName, health);
                        correctUnitType = true;
                        break;
                    case "CommanderUnit":
                        unit = new CommanderUnit(unitName, health);
                        correctUnitType = true;
                        break;
                }

                if (!correctUnitType){
                    throw new IOException("Error: Unit must be a valid Unit (InfantryUnit, RangedUnit, CavalryUnit," +
                            "CommanderUnit");
                }
                try {
                    army.add(unit);
                } catch (IllegalArgumentException e){
                    e.getMessage();
                }
            }
        }
        return army;
    }


}
