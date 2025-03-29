package iA_Work;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import javax.swing.JComboBox;
public class Table{
	private static JTable table = new JTable();
	private static String[][] fullCalender =  new String[14][8];
	private static JTextField eventField;
	private static JTextField weekOfField;
	private static JButton scheduleButton;
	private static JLabel lblNewLabel;
	private static JLabel lblDay;
	private static JLabel lblTime;
	private static JButton btnNewButton_1;
	private static JButton btnNewButton_2;
	private static JLabel lblDay_1;
	private static JComboBox comboBox;
	private static JComboBox comboBox_1;
	public static String ldString;
	
	
	private static LocalDate ldChange;
	
	String scheduleEvent = "";
	String scheduleDay = "";
	String scheduleTime = "";

	
	
	public static void main(String[] args) throws IOException {
		
	//Gets the calendar date of the Monday of the current week and sets ldChange to that date in yy/mm/dd format
	Calendar cl = Calendar.getInstance();
    int dd = cl.get(Calendar.DATE);
    int mm = cl.get(Calendar.MONTH) + 1;
    int yy = cl.get(Calendar.YEAR);
    LocalDate ld = LocalDate.of(yy, mm, dd);
    ldChange = ld.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    ldString = ldChange.toString();
    
	getCalendar();
	
	JFrame frame = new JFrame("WINDOW");
	frame.getContentPane().setBackground(new Color(81, 81, 81));
	frame.getContentPane().setForeground(Color.BLACK);
	frame.setBounds(100,100,756,700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setLocationRelativeTo(null);
	
	//Creation of the calendar table in the main window
	Object[] columns = fullCalender[0];
	table.setTableHeader(null);
	DefaultTableModel model = new DefaultTableModel();
	model.setColumnIdentifiers(columns);
	table.setModel(model);
	table.setBackground(new Color(78,78,78));
	table.setForeground(Color.WHITE);
	table.setSelectionBackground(new Color(60, 60, 60));
	table.setGridColor(new Color(97, 97, 97));
	table.setFont(new Font("Tahoma",Font.PLAIN,12));
	table.setRowHeight(30);
	table.setAutoCreateRowSorter(true);
	JScrollPane pane = new JScrollPane(table);
	pane.setBounds(17,80,718,426);
	frame.getContentPane().add(pane);
	
	
	
	//Creation of buttons and inputs from the user into the schedule
	eventField = new JTextField();
	eventField.setBounds(110, 538, 130, 26);
	frame.getContentPane().add(eventField);
	eventField.setColumns(10);
	
	weekOfField = new JTextField();
	weekOfField.setBounds(110, 564, 130, 26);
	frame.getContentPane().add(weekOfField);
	weekOfField.setColumns(10);
	
	scheduleButton = new JButton("Schedule");
	scheduleButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				bookEvent();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	scheduleButton.setBounds(110, 643, 117, 29);
	frame.getContentPane().add(scheduleButton);
	
	
	lblNewLabel = new JLabel("Event:");
	lblNewLabel.setForeground(new Color(238, 242, 255));
	lblNewLabel.setBounds(37, 543, 61, 16);
	frame.getContentPane().add(lblNewLabel);
	
	lblDay = new JLabel("Week of:");
	lblDay.setForeground(new Color(238, 242, 255));
	lblDay.setBounds(37, 569, 61, 16);
	frame.getContentPane().add(lblDay);
	
	lblTime = new JLabel("Time:");
	lblTime.setForeground(new Color(238, 242, 255));
	lblTime.setBounds(37, 620, 61, 16);
	frame.getContentPane().add(lblTime);
	
	btnNewButton_1 = new JButton("<");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ldChange = ldChange.minusWeeks(1); // Move back a week
            lblDay_1.setText("Week of: " + ldChange.toString());
            try {
				getCalendar();
				refresh();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	});
	btnNewButton_1.setBounds(267, 39, 35, 29);
	frame.getContentPane().add(btnNewButton_1);
	
	btnNewButton_2 = new JButton(">");
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ldChange = ldChange.plusWeeks(1); // Move forward a week
            lblDay_1.setText("Week of: " + ldChange.toString());
            try {
				getCalendar();
				refresh();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	});
	btnNewButton_2.setBounds(485, 39, 35, 29);
	frame.getContentPane().add(btnNewButton_2);
	
	lblDay_1 = new JLabel("Week of: " + ldString);
	lblDay_1.setForeground(new Color(238, 242, 255));
	lblDay_1.setBounds(328, 44, 145, 16);
	frame.getContentPane().add(lblDay_1);
	
	
	comboBox = new JComboBox();
	//Limited options for user in comboBoxes to restrict the creation of unnecessary inputs
	comboBox.addItem("--");
    comboBox.addItem("9AM");
    comboBox.addItem("10AM");
    comboBox.addItem("11AM");
    comboBox.addItem("12PM");
    comboBox.addItem("1PM");
    comboBox.addItem("2PM");
    comboBox.addItem("3PM");
    comboBox.addItem("4PM");
    comboBox.addItem("5PM");
    comboBox.addItem("6PM");
    comboBox.addItem("7PM");
    comboBox.addItem("8PM");
    comboBox.addItem("9PM");
	comboBox.setBounds(110, 616, 130, 27);
	frame.getContentPane().add(comboBox);
	
	JLabel lblDay_3 = new JLabel("Day:");
	lblDay_3.setForeground(new Color(238, 242, 255));
	lblDay_3.setBounds(37, 592, 61, 16);
	frame.getContentPane().add(lblDay_3);
	
	comboBox_1 = new JComboBox();
	comboBox_1.addItem("--");
    comboBox_1.addItem("MONDAY");
    comboBox_1.addItem("TUESDAY");
    comboBox_1.addItem("WEDNESDAY");
    comboBox_1.addItem("THURSDAY");
    comboBox_1.addItem("FRIDAY");
    comboBox_1.addItem("SATURDAY");
    comboBox_1.addItem("SUNDAY");
	comboBox_1.setBounds(110, 588, 130, 27);
	frame.getContentPane().add(comboBox_1);
	
	
	//Buttons to open other windows of application
	JButton btnNewButton = new JButton("Study Mode");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			StudyMode.OpenStudyMode();
		}
	});
	btnNewButton.setBounds(410, 556, 117, 29);
	frame.getContentPane().add(btnNewButton);
	
	JButton btnNewButton_3 = new JButton("Study Algorithm");
	btnNewButton_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			StudyAlgorithm.studyWindow();

		}
	});
	btnNewButton_3.setBounds(410, 592, 145, 29);
	frame.getContentPane().add(btnNewButton_3);
	
	//Sets the first table to be displayed for the jTable when the application is started
	String[] dates = {"", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
	for (int l = 0; l < 8; l++)
	{
		fullCalender[0][l] = dates[l];
	}
	String[] times = {"", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM"};
	for (int r = 0; r < 14; r++)
	{
		fullCalender[r][0] = times[r];
	}
	for (int i = 0; i < times.length; i++)
		{
			model.addRow(fullCalender[i]);
		}	
	
	frame.setVisible(true);
	
	}
	
	//This method searches through the csv file that holds the information for every week in the calendar. The method then looks through the lines
	//until it finds a line that matches the current week the user is looking for and updates the jTable accordingly. If the week that the user is
	//looking for is not visible inside the csv file, the method will add a new calendar week to the bottom of the file, matching the user's demands
	//and will show up as an empty week (bufferedWriter append mode)
	public static void getCalendar() throws IOException {
	    boolean validDate = false;
	    String[][] updateCalendar =  new String[14][8];
	    String fileName = "Week_of_2025-01-13.csv";
	    String line = null;
	    FileReader fileReader = new FileReader(fileName); 
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    int lineCount = 0;
	    while((line = bufferedReader.readLine()) != null) { 
	        lineCount++;
	        if (line.contains(ldChange.toString())) {
	            // If the date exists in the file, load the corresponding data
	            for (int i = 0; i < 14; i++) {
	                String[] blocks = bufferedReader.readLine().split(",");
	                for (int r = 0; r < 8; r++) {
	                    updateCalendar[i][r] = blocks[r];
	                }
	            }
	            validDate = true;
	        }
	    }   
	    bufferedReader.close();

	    if (!validDate) {
	        // If the date doesn't exist, write a new entry to the CSV file
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
	            writer.newLine();
	            writer.write(ldChange.toString());
	            writer.newLine();
	            writer.write(",Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday");
	            writer.newLine();
	            writer.write("9:00 AM, , , , , , , ");
	            writer.newLine();
	            writer.write("10:00 AM, , , , , , , ");
	            writer.newLine();
	            writer.write("11:00 AM, , , , , , , ");
	            writer.newLine();
	            writer.write("12:00 PM, , , , , , , ");
	            writer.newLine();
	            writer.write("1:00 PM, , , , , , , ");
	            writer.newLine();
	            writer.write("2:00 PM, , , , , , , ");
	            writer.newLine();
	            writer.write("3:00 PM, , , , , , , ");
	            writer.newLine();
	            writer.write("4:00 PM, , , , , , , ");
	            writer.newLine();
	            writer.write("5:00 PM, , , , , , , ");
	            writer.newLine();
	            writer.write("6:00 PM, , , , , , , ");
	            writer.newLine();
	            writer.write("7:00 PM, , , , , , , ");
	            writer.newLine();
	            writer.write("8:00 PM, , , , , , , ");
	            writer.newLine();
	            writer.write("9:00 PM, , , , , , , ");
	            System.out.println("Data written successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Update the fullCalender array with the new data
	    for (int i = 0; i < 14; i++) {
	        for (int j = 0; j < 8; j++) {
	            fullCalender[i][j] = updateCalendar[i][j];
	        }
	    }

	    // Refresh the JTable model
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // Remove existing rows
	    for (int i = 0; i < 14; i++) {
	        model.addRow(fullCalender[i]); // Add updated rows
	    }
	    model.fireTableDataChanged();  // Notify the table to refresh the data
	}
	
	
	//This method takes in a user's input of a date and time they would like to schedule the event for and then reads the file looking for the
	//associated week and then copies that week to a 2D-array once found. The method then inserts the event the user inputed into the schedule and 
	//adds the event into the correct date and time in the 2D-array. The JTable is then refreshed so that the new data is displayed in the table
	//if the user is viewing the correct week.
	public static void bookEvent() throws IOException {
	    String event = eventField.getText(); // Get the event from the text field
	    String weekOf = weekOfField.getText(); // Get the week of from the text field
	    String day = (String) comboBox_1.getSelectedItem(); // Get the selected day from the combo box
	    String time = (String) comboBox.getSelectedItem(); // Get the selected time from the combo box
	    
	    if (event.isEmpty() || day.equals("--") || time.equals("--")) {
	        System.out.println("Please fill in all fields.");
	        return; // Ensure all fields are filled before proceeding
	    }

	    // Map day and time to the correct position in the fullCalender array
	    int dayIndex = getDayIndex(day);
	    int timeIndex = getTimeIndex(time);
	    if (dayIndex == -1 || timeIndex == -1) {
	        System.out.println("Invalid day or time selected.");
	        return;
	    }

	    // Open the CSV file to load the calendar data
	    String fileName = "Week_of_2025-01-13.csv";
	    String line = null;
	    FileReader fileReader = new FileReader(fileName);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);

	    String[][] updatedCalendar = new String[14][8]; // Temp array to hold the updated calendar data
	    boolean foundWeek = false;

	    // Read the CSV file to find the correct week
	    while ((line = bufferedReader.readLine()) != null) {
	        if (line.contains(weekOf)) {
	            foundWeek = true;
	            for (int i = 0; i < 14; i++) {
	                String[] blocks = bufferedReader.readLine().split(",");
	                for (int j = 0; j < 8; j++) {
	                    updatedCalendar[i][j] = blocks[j];
	                }
	            }
	            break;
	        }
	    }
	    bufferedReader.close();

	    if (!foundWeek) {
	        System.out.println("Week not found in the CSV file.");
	        return;
	    }

	    // Place the event in the correct position in the updated calendar (in-memory update)
	    updatedCalendar[timeIndex][dayIndex] = event;

	    // Update the fullCalender with the new data
	    for (int i = 0; i < 14; i++) {
	        for (int j = 0; j < 8; j++) {
	            fullCalender[i][j] = updatedCalendar[i][j];
	        }
	    }

	    // Step 2: Refresh the JTable model with updated data
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // Remove existing rows
	    for (int i = 0; i < 14; i++) {
	        model.addRow(fullCalender[i]); // Add updated rows
	    }
	    returntofile();
	    model.fireTableDataChanged(); // Notify the table to refresh the data

	    System.out.println("Event scheduled successfully.");
	}
	//This method converts the 2D Array fullCalnder into an Array List with each element as a string which is a row of the original fullCalender
	//2D-Array
	public static ArrayList<String> fullCalendarList ()
	{
		ArrayList<String> stringFullCalendar = new ArrayList<String>();
		for (int i = 0; i < 14; i++)
		{
			String tempString = "";
			for (int r = 0; r < 8; r++)
			{
				tempString += (fullCalender[i][r] + ",");
			}
			tempString = tempString.substring(0, tempString.length()-1);
			stringFullCalendar.add(tempString);
			
		}
		return stringFullCalendar;
			
	}
	
	//This method takes each line of the csv file that contains every week of the user's calendar and adds them to an Array List the method.
	//The method then takes the Array List generated in fullCalendarList() and replaces all elements of the appropriate weekly calendar with
	//the elements of fullCalendar. The csv file is then rewritten with the new adjusted Array List of lines.
	public static void returntofile () throws IOException
	{
		String weekOf = weekOfField.getText();
		String fileName = "Week_of_2025-01-13.csv";
		String line = null;
		ArrayList<String> infoList = new ArrayList<String>();
		ArrayList<String> stringedCalendar = fullCalendarList();
		FileReader fileReader = new FileReader(fileName);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
					infoList.add(line);
		}
		int n = 0;
		while (n < infoList.size()) {
		    if (infoList.get(n).contains(weekOf))
		    {
		    	infoList.set(n+1, stringedCalendar.get(0));
		    	infoList.set(n+2, stringedCalendar.get(1));
		    	infoList.set(n+3, stringedCalendar.get(2));
		    	infoList.set(n+4, stringedCalendar.get(3));
		    	infoList.set(n+5, stringedCalendar.get(4));
		    	infoList.set(n+6, stringedCalendar.get(5));
		    	infoList.set(n+7, stringedCalendar.get(6));
		    	infoList.set(n+8, stringedCalendar.get(7));
		    	infoList.set(n+9, stringedCalendar.get(8));
		    	infoList.set(n+10, stringedCalendar.get(9));
		    	infoList.set(n+11, stringedCalendar.get(10));
		    	infoList.set(n+12, stringedCalendar.get(11));
		    	infoList.set(n+13, stringedCalendar.get(12));
		    	
		    }
		    n++;
		}
	    bufferedReader.close();
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    int i = 0;
	    while (i < infoList.size()) {
	        writer.write(infoList.get(i));
	        writer.newLine();
	        i++;
	    }
	    writer.close();
	    
	}

	//Method to get the column index based on the selected day
	public static int getDayIndex(String day) {
	    switch (day.toUpperCase()) {
	        case "MONDAY":
	            return 1;
	        case "TUESDAY":
	            return 2;
	        case "WEDNESDAY":
	            return 3;
	        case "THURSDAY":
	            return 4;
	        case "FRIDAY":
	            return 5;
	        case "SATURDAY":
	            return 6;
	        case "SUNDAY":
	            return 7;
	        default:
	            return -1; //If the day is invalid
	    }
	}

	//Method to get the row index based on the selected time
	public static int getTimeIndex(String time) {
	    switch (time) {
	        case "9AM":
	            return 1;
	        case "10AM":
	            return 2;
	        case "11AM":
	            return 3;
	        case "12PM":
	            return 4;
	        case "1PM":
	            return 5;
	        case "2PM":
	            return 6;
	        case "3PM":
	            return 7;
	        case "4PM":
	            return 8;
	        case "5PM":
	            return 9;
	        case "6PM":
	            return 10;
	        case "7PM":
	            return 11;
	        case "8PM":
	            return 12;
	        case "9PM":
	            return 13;
	        default:
	            return -1; //Invalid time
	    }
	}
	
	//Refreshes the displayed week in the calendar by checking the displayed week and then converting the lines containing the information on that
	//week from the csv file to a 2D-array that can be displayed in the JTable
	public static void refresh() throws IOException {
		String fileName = "Week_of_2025-01-13.csv";
	    String line = null;
	    FileReader fileReader = new FileReader(fileName);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);

	    String[][] updatedCalendar = new String[14][8]; //Temp array to hold the updated calendar data
	    boolean foundWeek = false;

	    //Read the CSV file to find the correct week
	    while ((line = bufferedReader.readLine()) != null) {
	        if (line.contains(ldChange.toString())) {
	            foundWeek = true;
	            for (int i = 0; i < 14; i++) {
	                String[] blocks = bufferedReader.readLine().split(",");
	                for (int j = 0; j < 8; j++) {
	                    updatedCalendar[i][j] = blocks[j];
	                }
	            }
	            break;
	        }
	    }
	    bufferedReader.close();

	    if (!foundWeek) {
	        System.out.println("Week not found in the CSV file.");
	        return;
	    }

	    // Update the fullCalender with the new data
	    for (int i = 0; i < 14; i++) {
	        for (int j = 0; j < 8; j++) {
	            fullCalender[i][j] = updatedCalendar[i][j];
	        }
	    }

	    //Refresh the JTable model with updated data
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // Remove existing rows
	    for (int i = 0; i < 14; i++) {
	        model.addRow(fullCalender[i]); // Add updated rows
	    }
	    model.fireTableDataChanged(); // Notify the table to refresh the data
	}
	
	//Allows the day to be accessed by other files without making the the date public and changeable
	public static String getDay () {
		ldString = ldChange.toString();
		return ldString;
	}
}



