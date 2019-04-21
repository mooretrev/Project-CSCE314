import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumMap;
import java.awt.event.*;
import javax.sound.midi.VoiceStatus;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


// Bonus points: Create an icon (or find a public domain icon. Keep in mind federal Copyright law and TAMU's plagiarism policy and add it to the home screen window.
public class MainWindow implements ActionListener {
  
  private final JFrame mainFrame = new JFrame(Config.APPLICATIONNAME);
  private final JPanel panel = new JPanel();
  private WorkoutsPanel p;
  private final JDialog selectWorkout = new JDialog(mainFrame, "Select Workout");
  private JComboBox<String> cboType, cboGoal;
  private JSpinner spnDuration;
  private final Workouts workouts;
  private final EnumMap<Config.MuscleGroup, ArrayList<Config.Muscle>> muscleGroups;
  private JButton lowerBodyButton = new JButton("Lower Body");
  private JButton upperBodyButton = new JButton("Upper Body");
  private JButton fullBodyButton = new JButton("Full Body");
  

  MainWindow(Workouts workouts, EnumMap<Config.MuscleGroup, ArrayList<Config.Muscle>> muscleGroups) {
    this.workouts = workouts;
    this.muscleGroups = muscleGroups;
    mainFrame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT); 
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    configButtonandPanel();
    launchHomeScreen();
  }
  
  private void configButtonandPanel() {
  	//constrants
  	GridBagLayout layout = new GridBagLayout();
  	layout.rowWeights = new double[]{.33, .33, .33};
  	layout.columnWeights = new double[] {1.0};
  	GridBagConstraints gbc_cboNames = new GridBagConstraints();
    gbc_cboNames.fill = GridBagConstraints.BOTH;
    gbc_cboNames.gridx = 0;
    gbc_cboNames.gridy = 0;
  	GridBagConstraints gbc_cboNames2 = new GridBagConstraints();
    gbc_cboNames2.fill = GridBagConstraints.BOTH;
    gbc_cboNames2.gridy = 1;
  	GridBagConstraints gbc_cboNames3 = new GridBagConstraints();
    gbc_cboNames3.fill = GridBagConstraints.BOTH;
    gbc_cboNames3.gridy = 2;
    
    panel.setLayout(layout);
  	
    panel.add(lowerBodyButton, gbc_cboNames2);
    panel.add(upperBodyButton, gbc_cboNames);
    panel.add(fullBodyButton, gbc_cboNames3);
    lowerBodyButton.addActionListener(this);
    lowerBodyButton.setActionCommand("Lower");
    upperBodyButton.addActionListener(this);
    upperBodyButton.setActionCommand("Upper");
    fullBodyButton.addActionListener(this);
    fullBodyButton.setActionCommand("Full");
    
    
  }
  
  public void backToHomeScreen() {
  	p.setVisible(false);
  	launchHomeScreen();
  }
  
  private void launchHomeScreen() { 
    mainFrame.add(panel);
    mainFrame.setVisible(true);
    panel.setVisible(true);
   
  }
  
  // This is the method your actionlistener should call. It should create and display a WorkoutsPanel.
  private void showWorkouts(ArrayList<Config.Muscle> muscles) {
    
  // Code goes here.
  	p = new WorkoutsPanel(muscles, workouts);
  	p.setMainWindow(this);
  	mainFrame.add(p);
  	p.setVisible(true);
  	panel.setVisible(false);
  }

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Lower")) showWorkouts(muscleGroups.get(Config.MuscleGroup.LOWERBODY));
		else if (action.equals("Upper")) showWorkouts(muscleGroups.get(Config.MuscleGroup.UPPERBODY));
		else showWorkouts(muscleGroups.get(Config.MuscleGroup.WHOLEBODY));
		
		
		
	}
  
}
