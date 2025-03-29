package iA_Work;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterTest {

    /**
     * Writes a 2D array (week calendar) to a CSV file.
     *
     * @param weekData the 2D array representing the calendar week
     * @param weekStart the start date of the week in "yyyy-MM-dd" format
     */
    public static void saveWeekToCSV(Object[][] weekData, String weekStart) {
        String fileName = "Week_of_" + weekStart + ".csv"; // Name the file based on the week's start date

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Object[] row : weekData) {
                if (row == null) continue;

                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    if (row[i] != null) {
                        line.append(row[i]);
                    }
                    if (i < row.length - 1) {
                        line.append(","); // Add a comma delimiter
                    }
                }
                writer.write(line.toString());
                writer.newLine(); // Move to the next line
            }
            System.out.println("Week data saved to: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Sample week data
        Object[][] sampleWeek = {
            {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"},
            {"9:00 AM", "", "", "Meeting", "", "Workout", "", ""},
            {"10:00 AM", "", "Call", "", "", "", "", ""},
            {"11:00 AM", "", "", "", "", "", "Brunch", ""},
            {"12:00 PM", "Lunch", "", "", "", "", "", ""},
            // ... Add other time slots
        };

        // Example usage
        String weekStartDate = "2025-01-13"; // Replace with your dynamic start date logic
        saveWeekToCSV(sampleWeek, weekStartDate);
    }
}