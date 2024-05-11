package src.Project.CodeClasure.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CalculatorFrame implements ActionListener{
    JFrame frame;
//    JTextField textField;
//    JButton []numbers=new JButton[10];
//    JButton []function=new JButton[8];
//    JButton add,sub,mul,div;
//    JButton dec,equ,delete,clear;
    JPanel panel=new JPanel();
    JPanel mainpanel=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
    private JTextField textField1;
    private JButton addButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton divButton;
    private JButton decButton;
    private JButton equButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a10Button;
    private JButton a0Button;
    private JButton a7Button;
    private JButton a6Button;
    private JButton a5Button;
    private JButton a4Button;
    private JButton a3Button;
    private JButton a1Button;
    private JButton a2Button;

    private JButton Button;
    Font font=new Font("SANS_SERIF",Font.ITALIC,30);

    double num1=0,num2=0,result=0;
    char operator;


    public void initComp(){
        //panel 1
        panel.setLayout(null);
        panel.setBounds(30,30,300,50);
        textField1.setBounds(0,0,300,50);
        textField1.setFont(font);
        textField1.setEditable(false);
        panel.add(textField1);
//panel 2
        panel2.setLayout(null);
        panel2.setBounds(30,100,300,350);
        panel2.setLayout(new GridLayout(4,4,10,10));
//        panel2.setBackground(Color.RED);
        panel2.add(a0Button);
        panel2.add(a1Button);
        panel2.add(a2Button);
        panel2.add(a3Button);
        panel2.add(addButton);
        panel2.add(a4Button);
        panel2.add(a5Button);
        panel2.add(a6Button);
        panel2.add(subButton);
        panel2.add(a7Button);
        panel2.add(a8Button);
        panel2.add(a9Button);
        panel2.add(mulButton);
        panel2.add(decButton);
        panel2.add(a0Button);
        panel2.add(equButton);
        panel2.add(divButton);

//panel 3
        panel3.setLayout(null);
        panel3.setBounds(30,460,300,40);
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        deleteButton.setBounds(10, 5, 120, 30);
        clearButton.setBounds(160, 5, 130, 30);
        panel3.add(deleteButton);
        panel3.add(clearButton);
//main panel
        mainpanel.setLayout(null);
        mainpanel.setBackground(Color.CYAN);
        mainpanel.add(panel);
        mainpanel.add(panel2);
        mainpanel.add(panel3);

        // Add action listener for number buttons
        ActionListener numberButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    JButton clickedButton = (JButton) e.getSource();
                    String buttonText = clickedButton.getText();
                    String currentText = textField1.getText();
                    textField1.setText(currentText + buttonText);
                }
            }
        };
        a0Button.addActionListener(numberButtonListener);
        a1Button.addActionListener(numberButtonListener);
        a2Button.addActionListener(numberButtonListener);
        a3Button.addActionListener(numberButtonListener);
        a4Button.addActionListener(numberButtonListener);
        a5Button.addActionListener(numberButtonListener);
        a6Button.addActionListener(numberButtonListener);
        a7Button.addActionListener(numberButtonListener);
        a8Button.addActionListener(numberButtonListener);
        a9Button.addActionListener(numberButtonListener);
        decButton.addActionListener(numberButtonListener);
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        decButton.addActionListener(this);
        equButton.addActionListener(this);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField1.setText(""); // Clear the text field
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = textField1.getText();
                if (!currentText.isEmpty()) {
                    // Remove the last character from the text field
                    textField1.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
        });
}

    public CalculatorFrame(){
        frame=new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,550);
        initComp();
        frame.setContentPane(mainpanel);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            String buttonText = clickedButton.getText();
            String currentText = textField1.getText();

            // If the clicked button is an operator, and the text field is not empty
            if (e.getSource() == addButton || e.getSource() == subButton ||
                    e.getSource() == mulButton || e.getSource() == divButton) {
                if (!currentText.isEmpty()) {
                    num1 = Double.parseDouble(currentText);
                    operator = buttonText.charAt(0); // Get the operator from the button text
                    textField1.setText(""); // Clear the text field for the next number input
                }
            } else if (e.getSource() == equButton) { // If the equal button is clicked
                if (!currentText.isEmpty()) {
                    num2 = Double.parseDouble(currentText);
                    // Perform the operation based on the operator
                    switch (operator) {
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                        case '*':
                            result = num1 * num2;
                            break;
                        case '/':
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                // Handle division by zero
                                textField1.setText("Error");
                                return;
                            }
                            break;
                    }
                    textField1.setText(String.valueOf(result)); // Display the result
                    num1 = result; // Store the result for further operations
                }
            } else { // For number buttons and decimal point button
                textField1.setText(currentText + buttonText);
            }
        }
    }

    public static void main(String[] args) {
        CalculatorFrame calculatorFrame=new CalculatorFrame();
    }
}
