package edu.ntnu.idatt2001.IO;

import edu.ntnu.idatt2001.Army.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;


/**
 * The type Army writer.
 *
 * @author: Daniel Evensen Inspiration drawn from group assignment in Systems Development - IDATT1002
 */
public class ArmyWriter {

    private static final String NEWLINE = "\n";
    private static final String COMMA_DELIMITER = ",";

    /**
     * Instantiates a new Army writer.
     *
     * @Author Daniel Evensen
     */
    public ArmyWriter(){}

    /**
     * Write army to file.
     *
     * checks that the file ends with .csv
     * check that the file is being written to correct directory
     * check that the army it's going to write is not null
     *
     * creates a String from army name, which will be the first line
     *
     * tries following methods for (filewriter):
     * tried to write String armyNameLine + NEWLINE
     * for each unit in army, it tries to write information from that unit onto empty line, getting simple class name
     *  (without .java) which is used as Unit Type, getting name of unit, and unit health. Separated by commas
     *
     *  catches exception if exception is thrown while trying to write unit information
     *
     *  catches exception if it fails to write anything at all
     *
     * @param army the army
     * @param file the file
     * @throws IOException the io exception
     */
    public static void writeArmyToFile(Army army, File file) throws IOException{
        if (!file.getName().endsWith(".csv")){
            throw new IOException("Only .csv-files are supported, please make sure the file is a .csv-file");
        }
        if (!(file.getPath().startsWith(FileSystems.getDefault().getPath("src","main", "resources", "edu/ntnu/idatt2001/army_files").toString()))) {
            throw new IOException("File is not being written to src/main/resources/edu/ntnu/idatt2001/army_files. Please make sure it does so");
        }
        if (army == null) {
            throw new IOException("Army is null");

        }
        String armyNameLine = army.getName();
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(armyNameLine + NEWLINE);
            army.getAllUnits().forEach(unit -> {
                try {
                    fileWriter.write(unit.getClass().getSimpleName() + COMMA_DELIMITER + unit.getName() + COMMA_DELIMITER +
                            unit.getHealth() + NEWLINE);

                    } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("Error occurred, could not write army to file" + e.getMessage());
        }
    }
}
