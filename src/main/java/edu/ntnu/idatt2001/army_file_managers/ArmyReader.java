package edu.ntnu.idatt2001.army_file_managers;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Units.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: Daniel Evensen
 * Inspiration drawn from group assignment in Systems Development - IDATT1002
 */

public class ArmyReader {
    private static final String COMMA_DELIMITER = ",";

    public ArmyReader() {}

    public void readArmyFromFile(File file) throws IOException {
        if (!(file.getName().endsWith(".csv"))) {
            throw new IOException("Only .csv-files are supported, please make sure the file is a .csv-file");
        }
        if (!(file.exists())) {
            throw new IOException("File does not exist");
        }

        Army army;

        try (Scanner sc = new Scanner(file)){
            if (!(sc.hasNext())) {
                throw new IOException("The file is empty");
            }
            army = new Army(sc.nextLine());

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] token = line.split(COMMA_DELIMITER);

                if (token.length != 3) {
                    throw new IOException("Line data '" + line + "' is invalid. Make sure each line is on the form 'Unit type, unit name, unit health");
                }
                String unit_Type = token[0];
                String unit_Name = token[1];
                boolean isCorrectTypeOfUnit = false;
                int unit_Health;
                Unit unit = null;

                try {
                    unit_Health = Integer.parseInt(token[2]);
                } catch (NumberFormatException e) {
                    throw new IOException("Health is required to be in an integer format: " + e.getMessage());
                }

                switch (unit_Type) {

                    case "InfantryUnit":
                        unit = new InfantryUnit(unit_Name, unit_Health);
                        isCorrectTypeOfUnit = true;
                        break;

                    case "RangedUnit":
                        unit = new RangedUnit(unit_Name, unit_Health);
                        isCorrectTypeOfUnit = true;
                        break;

                    case "CavalryUnit":
                        unit = new CavalryUnit(unit_Name, unit_Health);
                        isCorrectTypeOfUnit = true;
                        break;

                    case "CommanderUnit":
                        unit = new CommanderUnit(unit_Name, unit_Health);
                        isCorrectTypeOfUnit = true;
                        break;
                }

                if (!isCorrectTypeOfUnit) {
                    throw new IOException("Incorrect unit type. Please make sure unit is of type: Infantry, Ranged, Cavalry or Commander");
                }

                try {
                    army.add(unit);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
