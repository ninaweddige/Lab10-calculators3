import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserInterfaceSet extends UserInterface implements ActionListener{
	
	private CalcEngineSet<String> calcSet;
	private JTextField setATextField, setBTextField, result;
	private CustomSetClass<String> setA, setB;
	
    //Constructor
    public UserInterfaceSet(CalcEngineSet<String> engine)
    {
        super(engine);
        this.calcSet = engine;
        setVisible(false);
        this.makeFrame();
        frame.setVisible(true);
    }
    
    public void makeFrame() {
    	frame = new JFrame(calc.getTitle());
    	JPanel contentPane = (JPanel)frame.getContentPane();
    	
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));
        
        JPanel setTextFields = new JPanel(new GridLayout(2, 1));
        setATextField = new JTextField();
        setBTextField = new JTextField();
        setTextFields.add(setATextField);
        setTextFields.add(setBTextField);
        
        JPanel setButtons = new JPanel(new GridLayout(2, 4));
        addButton(setButtons, "Set A");
        addButton(setButtons, "Size (A)");
        addButton(setButtons, "Union");
        addButton(setButtons, "Clear");
        addButton(setButtons, "Set B");
        addButton(setButtons, "Subtraction (A-B)");
        addButton(setButtons, "Intersection");
        setButtons.add(new JLabel(""));
        
        result = new JTextField();
        
        contentPane.add(setTextFields, BorderLayout.NORTH);
        contentPane.add(setButtons, BorderLayout.CENTER);
        contentPane.add(result, BorderLayout.SOUTH);
        
        frame.pack();
        
    }
    
    public void actionPerformed(ActionEvent event) {
    	
    	String command = event.getActionCommand();
    	String resultString = "";
    	
    	if (command == "Set A") {
   
    		if (!setATextField.getText().equals("")) {
    			setA = parseSet(setATextField.getText());
    			resultString = "Set A: " + setA.toString();
    		}else {
    			resultString = "Nothing saved in Set A";
    		}
    		
    	}
    	else if (command == "Set B") {
    		if (!setBTextField.getText().equals("")) {
    			setB = parseSet(setBTextField.getText());
    			resultString = "Set B: " + setB.toString();
    		}
    		else 
    			resultString = "Nothing saved in Set B";
    		
    	}
    	else if (command == "Size (A)") { 
    		if (setA != null) 
    			resultString = Integer.toString(calcSet.size(setA));
    		else
    			resultString = "0";
    	}
    	else if (command == "Subtraction (A-B)") {
    		if (setA != null) {
    			resultString = (calcSet.subtract(setA, setB)).toString();
    		}
    	}
    	else if (command == "Union") {
    		if (setA != null && setB != null) {
    			resultString = (calcSet.union(setA, setB)).toString();
    		}    		
    	}
    	else if (command == "Intersection") {
    		if (setA != null && setB != null) {
    			resultString = (calcSet.intersection(setA, setB)).toString();
    		}
    	// Clear
    	}
    	else {
    		if(setA != null) {
    			setA.removeAll();
        		setATextField.setText(setA.toString());
    		}
    		if(setB != null) {
        		setB.removeAll();
        		setBTextField.setText(setB.toString());
    		}
    		resultString = "";
    	}
    	
    	result.setText(resultString);
    }
    
    //takes a String (from one of the JTextFields), makes a set and adds the substrings separated by commas to it
    public CustomSetClass<String> parseSet(String aString){
    	CustomSetClass<String> set = new CustomSetClass<>();
    	if (aString.charAt(0) == '{' && aString.charAt(aString.length() - 1) == '}'){
    		aString = aString.substring(1, aString.length() - 1);
    	}
    	
    	String a = aString.replaceAll("\\s+","");
    	Scanner s = new Scanner(a);
    	s.useDelimiter(",");
    	
    	while(s.hasNext()) {
    		set.add(s.next());
    	}
    	return set;
    }
    
}
