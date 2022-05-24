package edu.ntnu.idatt2001.IO;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Units.*;
import edu.ntnu.idatt2001.Unit_Factory.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * @Author: Daniel Evensen
 * Inspiration drawn from group assignment in Systems Development - IDATT1002
 */

public class ArmyReader {
    private static final String COMMA_DELIMITER = ",";
    private static boolean armyExists;

    public ArmyReader() {}

    public static Army readArmyFromFile(File file) throws IOException {
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
                UnitType unitType = null;

                try {
                    unit_Health = Integer.parseInt(token[2]);
                } catch (NumberFormatException e) {
                    throw new IOException("Health is required to be in an integer format: " + e.getMessage());
                }


                try {
                    unitType = UnitType.valueOf(unit_Type);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                army.add(Objects.requireNonNull(UnitFactory.createUnit(unitType, unit_Name, unit_Health)));
            }
            return army;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Code retrieved from an answer from a post on StackOverflow, then edited by me to fit this project.
     * Source: https://stackoverflow.com/questions/39045272/matching-a-string-with-a-file-name
     * @param army
     * @return
     */
    public static boolean armyExists(Army army) {
        File[] files = new File("src/main/resources/edu/ntnu/idatt2001/army_files/").listFiles(); //
        String nameOfArmy = army.getName();
        for(File f : files){
            if(f.getName().toLowerCase().indexOf(nameOfArmy.toLowerCase()) != -1)
                return true;
        }
        /*File file = new File("src/main/resources/edu/ntnu/idatt2001/army_files" + army.getName() + ".csv");
        if (file.getName() == (army.getName() + ".csv") || file.exists()) {
            return true;
        }*/
        return false;
    }
}
