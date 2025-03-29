package iA_Work;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DraftGameDevelopment.Map;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import java.awt.Color;

public class StudyAlgorithm {
	private static JTextField startingWeek;
	private static JTextField hoursDesired;
	private static JTextField testWeek;
	private static JComboBox startDay;
	private static JComboBox testDay;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		studyWindow();
	}
	
	//Generates a window for the user to put in their algorithmic study schedule inputs
	public static void studyWindow() {
		JFrame studyAlgorithmWindow = new JFrame("Study Algorithm Generator");
		studyAlgorithmWindow.getContentPane().setBackground(new Color(81, 81, 81));
		studyAlgorithmWindow.setSize(700, 300);
		studyAlgorithmWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		studyAlgorithmWindow.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Starting week of:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(72, 19, 111, 16);
		studyAlgorithmWindow.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Starting day:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(98, 47, 79, 16);
		studyAlgorithmWindow.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("# of hours desired:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(57, 181, 119, 16);
		studyAlgorithmWindow.getContentPane().add(lblNewLabel_3);
		
		startingWeek = new JTextField();
		startingWeek.setBounds(189, 14, 130, 26);
		studyAlgorithmWindow.getContentPane().add(startingWeek);
		startingWeek.setColumns(10);
		
		startDay = new JComboBox();
		startDay.addItem("--");
	    startDay.addItem("MONDAY");
	    startDay.addItem("TUESDAY");
	    startDay.addItem("WEDNESDAY");
	    startDay.addItem("THURSDAY");
	    startDay.addItem("FRIDAY");
	    startDay.addItem("SATURDAY");
	    startDay.addItem("SUNDAY");
		startDay.setBounds(189, 39, 130, 27);
		studyAlgorithmWindow.getContentPane().add(startDay);
		
		hoursDesired = new JTextField();
		hoursDesired.setBounds(189, 176, 130, 26);
		studyAlgorithmWindow.getContentPane().add(hoursDesired);
		hoursDesired.setColumns(10);
		
		JButton btnNewButton = new JButton("Generate Schedule");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					algorithmicScheduling();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			});
		btnNewButton.setBounds(171, 209, 165, 29);
		studyAlgorithmWindow.getContentPane().add(btnNewButton);
		
		testWeek = new JTextField();
		testWeek.setColumns(10);
		testWeek.setBounds(507, 14, 130, 26);
		studyAlgorithmWindow.getContentPane().add(testWeek);
		
		JLabel lblWeekOfTest = new JLabel("Week of test:");
		lblWeekOfTest.setForeground(new Color(255, 255, 255));
		lblWeekOfTest.setBounds(408, 19, 87, 16);
		studyAlgorithmWindow.getContentPane().add(lblWeekOfTest);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last day:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(434, 43, 61, 16);
		studyAlgorithmWindow.getContentPane().add(lblNewLabel_1_1);
		
		testDay = new JComboBox();
		testDay.addItem("--");
		testDay.addItem("MONDAY");
		testDay.addItem("TUESDAY");
		testDay.addItem("WEDNESDAY");
		testDay.addItem("THURSDAY");
		testDay.addItem("FRIDAY");
		testDay.addItem("SATURDAY");
		testDay.addItem("SUNDAY");
		testDay.setBounds(507, 39, 130, 27);
		studyAlgorithmWindow.getContentPane().add(testDay);
		studyAlgorithmWindow.setVisible(true);
	}
	
	
	//This method takes in input from the user to determine the number of hours they want to study for their desired week and adds in appropriate study blocks into
	//the schedule for the desired number of hours. The blocks which are scheduled with "study" are determined by data gathered with the study questions testing
	//reaction time. The lower the average reaction time of the user during that hour, the more likely the user is to have an event scheduled during that time.
	//The method additionally ignores blocks in the schedule that already have an event and searches for the next best space.
	public static void algorithmicScheduling() throws IOException {
	    String testWeekStr = testWeek.getText();
	    int hoursNeeded = Integer.parseInt(hoursDesired.getText());
	    
	    //Read StudyResults.csv and rank times by lowest reaction time (last column)
	    String fileName = "studyAvg.csv";
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    String line;
	    ArrayList<Integer> studyData = new ArrayList<>();
	    while ((line = reader.readLine()) != null) {
	        studyData.add(Integer.parseInt(line));
	    }
	    reader.close();
	    
	    ArrayList<Integer>times = new ArrayList<>();
	    times.add(2);
	    times.add(3);
	    times.add(4);
	    times.add(5);
	    times.add(6);
	    times.add(7);
	    times.add(8);
	    times.add(9);
	    times.add(10);
	    times.add(11);
	    times.add(12);
	    times.add(13);
	    times.add(14);
	    
	    
	    int n = studyData.size();
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (studyData.get(j) > studyData.get(j + 1)) {
                    // Swap elements
                    Collections.swap(studyData, j, j + 1);
                    Collections.swap(times, j, j + 1);
                    swapped = true;
                }
            }
            //If no elements were swapped, the list is already sorted
            if (!swapped) break;
        }
        for (int t = 0; t < studyData.size(); t++)
        {
        	System.out.println(studyData.get(t));
        	System.out.println(times.get(t));
        }
	    
	    List<String> calendarLines = new ArrayList<>();
	    BufferedReader weekReader = new BufferedReader(new FileReader("Week_of_2025-01-13.csv"));
	    while ((line = weekReader.readLine()) != null) {
	        calendarLines.add(line);
	    }
	    weekReader.close();
	    
	    //Assign study blocks based on availability and ranking
	    int slotsFilled = 0;
	    for (int f = 0; f < studyData.size(); f++)
	    {
	    	if (slotsFilled >= hoursNeeded) break;
	    	int timeIndex = times.get(f);
	    	
	    	for (int dayIndex = Table.getDayIndex(startDay.getSelectedItem().toString()); dayIndex <= Table.getDayIndex(testDay.getSelectedItem().toString()); dayIndex++) { // Monday-Sunday
	            if (slotsFilled >= hoursNeeded) break;
	            
	            // Find correct week in the calendar
	            for (int i = 0; i < calendarLines.size(); i++) {
	                if (calendarLines.get(i).contains(Table.getDay())) {
	                    String[] daySlots = calendarLines.get(i + timeIndex).split(",");
	                    
	                    if (daySlots[dayIndex].equals(" ")) { // Check if empty
	                        daySlots[dayIndex] = "study";
	                        calendarLines.set(i + timeIndex, String.join(",", daySlots));
	                        slotsFilled++;
	                    }
	                }
	            }
	        }
	    }
	    
	    //Save updated calendar back to the file
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Week_of_2025-01-13.csv"));
	    for (String updatedLine : calendarLines) {
	        writer.write(updatedLine);
	        writer.newLine();
	    }
	    writer.close();
	    Table.refresh();
	}

}
