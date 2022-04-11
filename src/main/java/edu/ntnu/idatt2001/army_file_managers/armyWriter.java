package edu.ntnu.idatt2001.army_file_managers;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Units.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;


public class armyWriter {
    private static final String NEWLINE = "\n";
    private static final String DELIMITER = ",";

    public armyWriter(){}

    public static void writeToFile(Army army, File file) throws IOException{
        if (!file.getPath().startsWith(FileSystems.getDefault()
                .getPath("src", "main", "resources").toString())){
            throw new IOException("Error: Please write file to src/main/resources/");
        }
        if (!file.getName().endsWith(".csv")){
            throw new IOException("Error: Unsupported file format. Only .csv-files are supported");
        }
        if (army == null){
            throw new IOException("Error: Army is null");
        }
        String line = army.getName();
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(line + NEWLINE);
            army.getAllUnits().forEach(unit -> {
                try {
                    fileWriter.write(unit.getClass().getSimpleName() + DELIMITER + unit.getName() +
                            DELIMITER + unit.getHealth() + NEWLINE);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            });
        } catch (IOException exception){
            throw new IOException("Error: Unable to write to army file: " + exception.getMessage());
        }
    }

}
