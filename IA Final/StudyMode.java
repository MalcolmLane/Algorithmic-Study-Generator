package iA_Work;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class StudyMode {
	private static String selectedTime;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OpenStudyMode();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void OpenStudyMode() {
		JFrame newWindow = new JFrame("New Window");
		newWindow.getContentPane().setBackground(new Color(81, 81, 81));
        newWindow.setSize(300, 200);
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        newWindow.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Time of Study Mode:");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(69, 18, 129, 16);
        newWindow.getContentPane().add(lblNewLabel);
        
        JComboBox comboBox = new JComboBox();
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
        comboBox.setBounds(69, 46, 154, 27);
        newWindow.getContentPane().add(comboBox);
        
        JButton btnNewButton = new JButton("Start");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                
                // Create a Runnable task
                Runnable task = new Runnable() {
                    public void run() {
                    	selectedTime = comboBox.getSelectedItem().toString();
                    	StudyQuestion.StudyQuestionWindow(selectedTime);
                    }
                };
                
                scheduler.scheduleAtFixedRate(task, 0, 15, TimeUnit.MINUTES);
                //Displays a new study question window every 15 minutes
        	}
        });
        btnNewButton.setBounds(69, 85, 117, 29);
        newWindow.getContentPane().add(btnNewButton);
        newWindow.setVisible(true);
	}
	
	//Makes the user's selected time accessible by other classes but prevents changes from being made if it were public
	public static String getSelectedTime()
	{
		return selectedTime;
	}
	
	
	
	

}
