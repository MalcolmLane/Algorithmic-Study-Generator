package iA_Work;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JButton;
import java.awt.Color;

public class StudyQuestion {
	private static JTextField textField;
	//Indexes each time slot in the 2D array (similar to getTimeIndex() in Table.java)
	private static int _9AMrow = 1;
	private static int _10AMrow = 2;
	private static int _11AMrow = 3;
	private static int _12PMrow = 4;
	private static int _1PMrow = 5;
	private static int _2PMrow = 6;
	private static int _3PMrow = 7;
	private static int _4PMrow = 8;
	private static int _5PMrow = 9;
	private static int _6PMrow = 10;
	private static int _7PMrow = 11;
	private static int _8PMrow = 12;
	private static int _9PMrow = 13;
	
	private static long startTime; // Variable to store the start time of the stopwatch
	private static long elapsedTime; // Variable to store elapsed time in seconds
	
	private static int newAvg;
	

	
	public static void main(String[] args) {
		

	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	//Opens up the JFrame containing the math question of 2 random numbers from 100 to 500
	public static void StudyQuestionWindow (String time) {
		int num1 = 100 + (int)(Math.random() * 401);
		int num2 = 100 + (int)(Math.random() * 401);
		int numsum = num1 + num2;
		
		//Takes the time at which the window was opened
		startTime = System.currentTimeMillis();
		
		JFrame questionWindow = new JFrame("Question");
		questionWindow.getContentPane().setBackground(new Color(81, 81, 81));
		questionWindow.setSize(500, 300);
		questionWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		questionWindow.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("What is " + num1 + " + " + num2 + "?");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(131, 45, 216, 16);
		questionWindow.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(175, 62, 130, 26);
		questionWindow.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		 btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		if(Integer.parseInt(textField.getText()) == numsum)
	        		{
	        			elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Calculate elapsed time in seconds
	        			//Creates a case for each time index and defines variables and directories for the readLineAndArray() writeBackToFile() method
	        			if (StudyMode.getSelectedTime().equals("9AM"))
	        			{
	        				try {
								String[] studyValues = readLineAndArray(_9AMrow).split(",");
								String questionCounter = studyValues[1];
			        			String questionTimes = studyValues[2];
			        			
			        			writeBackToFile("StudyResults.csv", _9AMrow,studyCalculations("9AM", questionCounter, questionTimes));
			        			writeBackToFile("StudyAvg.csv", _9AMrow, String.valueOf(newAvg));
			        			
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	        			}
	        			if (StudyMode.getSelectedTime().equals("10AM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_10AMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _10AMrow, studyCalculations("10AM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _10AMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("11AM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_11AMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _11AMrow, studyCalculations("11AM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _11AMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("12PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_12PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _12PMrow, studyCalculations("12PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _12PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("1PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_1PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _1PMrow, studyCalculations("1PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _1PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("2PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_2PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _2PMrow, studyCalculations("2PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _2PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("3PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_3PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _3PMrow, studyCalculations("3PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _3PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("4PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_4PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _4PMrow, studyCalculations("4PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _4PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("5PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_5PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _5PMrow, studyCalculations("5PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _5PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("6PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_6PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _6PMrow, studyCalculations("6PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _6PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("7PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_7PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _7PMrow, studyCalculations("7PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _7PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("8PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_8PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _8PMrow, studyCalculations("8PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _8PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}

	        			if (StudyMode.getSelectedTime().equals("9PM")) {
	        			    try {
	        			        String[] studyValues = readLineAndArray(_9PMrow).split(",");
	        			        String questionCounter = studyValues[1];
	        			        String questionTimes = studyValues[2];

	        			        writeBackToFile("StudyResults.csv", _9PMrow, studyCalculations("9PM", questionCounter, questionTimes));
	        			        writeBackToFile("StudyAvg.csv", _9PMrow, String.valueOf(newAvg));

	        			    } catch (IOException e1) {
	        			        e1.printStackTrace();
	        			    }
	        			}
	
	        			questionWindow.dispose();
	        		}
	        	}
	       });
		btnNewButton.setBounds(185, 89, 117, 29);
		questionWindow.getContentPane().add(btnNewButton);
	
		questionWindow.setVisible(true);
	}
	

	
	
	//Method reads each line of the study mode results csv file and checks if the line number is equal to the index of the date it is looking for
	public static String readLineAndArray(int lineNumber) throws IOException {
		String fileName = "StudyResults.csv";
		if (lineNumber < 1) {
            throw new IllegalArgumentException("Line number must be greater than 0.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int currentLine = 1;

            // Read each line until we reach the specified line number
            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    return line;
                    
                }
                currentLine++;
            }
        }
        // If the line number is greater than the number of lines in the file, return null
        throw new IOException("Line number exceeds the total number of lines in the file.");
    }
	//Calculates the total time taken, total number of times and average time for the user to respond to the question and condenses the results to a string for the study mode csv file
	public static String studyCalculations(String timeslot, String count, String time) {
		int newCount = Integer.parseInt(count) + 1;
		int newTime = Integer.parseInt (time) + (int)elapsedTime;
		newAvg = newTime / newCount;
		return timeslot + "," + newCount + "," + newTime + "," + newAvg;
	}
	
	//Method creates a list contain each time slot in the study mode csv file and only replaces the line with the appropriate index in the list with the updated data. The file is then rewritten with the
	//updated information
	public static void writeBackToFile(String fileName, int timeslot, String rewrite) {
	    File file = new File(fileName);
	    
	    // Create a new list to store the updated lines
	    List<String> lines = new ArrayList<>();
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        int currentLine = 1;

	        // Read each line and update the necessary one
	        while ((line = reader.readLine()) != null) {
	            if (currentLine == timeslot) {
	                lines.add(rewrite);  // Add the updated line
	            } else {
	                lines.add(line);  // Keep the other lines the same
	            }
	            currentLine++;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Write all lines back to the file
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	        for (String line : lines) {
	            writer.write(line);
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
