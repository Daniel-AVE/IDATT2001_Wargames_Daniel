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
 * The type Army reader.
 *
 * @author: Daniel Evensen Inspiration drawn from group assignment in Systems Development - IDATT1002
 */
public class ArmyReader {
    private static final String COMMA_DELIMITER = ",";

    /**
     * Instantiates a new Army reader.
     */
    public ArmyReader() {}

    /**
     * Read army from file army.
     *
     * @param file the file
     * @return the army
     * @throws IOException if file could not be read
     */
    public static Army readArmyFromFile(File file) throws IOException {
        //makes sure file is a .csv file
        if (!(file.getName().endsWith(".csv"))) {
            throw new IOException("Only .csv-files are supported, please make sure the file is a .csv-file");
        }
        //check if file exists or not
        if (!(file.exists())) {
            throw new IOException("File does not exist");
        }
        //creates an instance of Army
        Army army;

        try (Scanner sc = new Scanner(file)){
            //Checks if Scanner is scanning anything, if not throws exception
            if (!(sc.hasNext())) {
                throw new IOException("The file is empty");
            }
            //since scanner is scanning, initializes the instance of Army with what scanner is scanning
            army = new Army(sc.nextLine());

            //while loop, iterating for as long as scanner has a line to read
            while (sc.hasNext()) {
                //Keeps changing string Line to nextLine for each iteration, letting it remember the line for this iteration
                String line = sc.nextLine();

                //splits all lines with a comma for each value in format [x,y,z,d]
                String[] token = line.split(COMMA_DELIMITER);

                //Checks that there are only 3 values (separated by 2 commas) if not, throws new exception for that specific line
                if (token.length != 3) {
                    throw new IOException("Line data '" + line + "' is invalid. Make sure each line is on the form 'Unit type, unit name, unit health");
                }
                //Defines what first value before first comma is, defining it as unit type
                String unit_Type = token[0];

                //Defines what 2nd value before 2nd comma is, defining it as unit name
                String unit_Name = token[1];
                //Int for unit health
                int unit_Health;

                //Makes an instance of interface class UnitType, and sets it's value to null
                UnitType unitType = null;

                //tries parseInting the String from the 3rd value, after 2nd comma, throws exception if fails
                try {
                    unit_Health = Integer.parseInt(token[2]);
                } catch (NumberFormatException e) {
                    throw new IOException("Health is required to be in an integer format: " + e.getMessage());
                }

                //tries defining unitType, and makes sure it's a value of UnitType, throws exception if not
                try {
                    unitType = UnitType.valueOf(unit_Type);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                //adds unit to army, and requires objects to not be null, as a safety measure
                army.add(Objects.requireNonNull(UnitFactory.createUnit(unitType, unit_Name, unit_Health)));
            }
            return army;
        } catch (IOException e) {
            e.printStackTrace();
        }
        // returns null if it fails
        return null;
    }

    /**
     * Code retrieved from an answer from a post on StackOverflow, then edited by me to fit this project.
     * Source: https://stackoverflow.com/questions/39045272/matching-a-string-with-a-file-name
     *
     * Creates a list instance of File, of all files within the path (in this case, within the directory "army_files"
     * Gets army name from army
     *
     * iterates through all files in the directory, if it finds a file with the same name as army name, it returns true
     * if not, it returns false
     *
     * @param army the army
     * @return boolean for if there exists a file with same name as army or not
     */
    public static boolean armyExists(Army army) {
        File[] files = new File("src/main/resources/edu/ntnu/idatt2001/army_files/").listFiles(); //
        String nameOfArmy = army.getName();
        for(File f : files){
            if(f.getName().toLowerCase().indexOf(nameOfArmy.toLowerCase()) != -1)
                return true;
        }
        return false;
    }
}
