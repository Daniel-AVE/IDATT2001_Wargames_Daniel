package edu.ntnu.idatt2001.army_file_managers;

import edu.ntnu.idatt2001.Army.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 * @Author: Daniel Evensen
 *
 * Inspiration drawn from group assignment in Systems Development - IDATT1002
 */

public class ArmyWriter {

    private static final String NEWLINE_DELIMITER = "\n";
    private static final String COMMA_DELIMITER = ",";

    public ArmyWriter(){}

    public void writeArmyToFile(Army army, File file) throws IOException{
        if (!file.getName().endsWith(".csv")){
            throw new IOException("Only .csv-files are supported, please make sure the file is a .csv-file");
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (int i = 0; i < army.getAllUnits().size(); i++) {
                
            }
        }
    }
}
