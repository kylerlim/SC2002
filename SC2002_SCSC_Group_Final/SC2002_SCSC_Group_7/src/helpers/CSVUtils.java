package helpers;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Utility Class used to read from and write to CSV files that
 * database system used in CAMs.
 */
public class CSVUtils {

    /**
     * Declare the seperator used in CSV files to distinguish between fields.
     */
    private static final String CSV_SEPARATOR = ",";

    // Original readCSV method

    /**
     * Reads a CSV file and returns its contents as a list of maps.
     *
     * @param filename The name of the CSV file to be read.
     * @return An ArrayList of maps, where each map represents a record in the CSV file.
     */
    public static ArrayList<Map<String, String>> readCSV(String filename) {
        ArrayList<Map<String, String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            String[] headers = line.split(CSV_SEPARATOR);

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    String value = i < values.length ? values[i].replaceAll("^\"|\"$", "") : "";
                    row.put(headers[i], value);
                }
                records.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    // Original writeCSV method

    /**
     * Writes data to a CSV file.
     *
     * @param filename The name of the CSV file to write.
     * @param records  A list of maps representing the data to be written.
     */
    public static void writeCSV(String filename, List<Map<String, String>> records) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            if (!records.isEmpty()) {
                StringJoiner headerJoiner = new StringJoiner(CSV_SEPARATOR);
                records.get(0).keySet().forEach(headerJoiner::add);
                bw.write(headerJoiner.toString());
                bw.newLine();

                for (Map<String, String> record : records) {
                    StringJoiner recordJoiner = new StringJoiner(CSV_SEPARATOR);
                    for (String value : record.values()) {
                        recordJoiner.add(value);
                    }
                    bw.write(recordJoiner.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New readCSVPreserveCommas method

    /**
     * Reads a CSV file while preserving commas inside quoted values.
     *
     * @param filename The name of the CSV file to read.
     * @return A list of maps, where each map represents a record in the CSV file.
     */
    public static List<Map<String, String>> readCSVPreserveCommas(String filename) {
        List<Map<String, String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            String[] headers = line.split(CSV_SEPARATOR, -1);

            while ((line = br.readLine()) != null) {
                String[] values = line.split(CSV_SEPARATOR + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    String value = i < values.length ? values[i].replaceAll("^\"|\"$", "") : "";
                    row.put(headers[i], value);
                }
                records.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    // New writeCSVPreserveCommas method

    /**
     * Writes data to a CSV file while preserving commas inside values by using quotes.
     *
     * @param filename The name of the CSV file to write.
     * @param records  A list of maps representing the data to be written.
     */
    public static void writeCSVPreserveCommas(String filename, List<Map<String, String>> records) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            if (!records.isEmpty()) {
                StringJoiner headerJoiner = new StringJoiner(CSV_SEPARATOR);
                records.get(0).keySet().forEach(headerJoiner::add);
                bw.write(headerJoiner.toString());
                bw.newLine();

                for (Map<String, String> record : records) {
                    StringJoiner recordJoiner = new StringJoiner(CSV_SEPARATOR);
                    for (String value : record.values()) {
                        recordJoiner.add(value);
                    }
                    bw.write(recordJoiner.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// Helper method for quoting strings

    /**
     * Quotes a string value for proper CSV formatting.
     * @param value The string value to be quoted.
     * @return The quoted string, with special characters properly handled.
     */
    private static String quoteString(String value) {
        if (value == null) {
            return "";
        }
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
