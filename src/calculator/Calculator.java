package calculator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calculator extends Frame {
	public static class Global {
		public static double sum=0;
		public static double broj=0;
		public static int flag=1;
		public static double proizvod=1;
		public static int flag1;
		public static double kolichnik;
		public static double prv;
	}
	private Button[] btnNumbers = new Button[10];
	private Button btnPlus ,btnEquals, btnMinus, btnMult, btnDivide, btnReset;
	private Label lblInput;
	private TextField tfDisplay;
	
	public Calculator() {
		Panel panelResult = new Panel(new BorderLayout(0,5));
		lblInput = new Label("Result: ");
		panelResult.add(lblInput, BorderLayout.NORTH);
		tfDisplay = new TextField("",20);
		panelResult.add(tfDisplay, BorderLayout.CENTER);
		tfDisplay.setEditable(false);
		
		Panel panelButtons = new Panel(new GridLayout(6,3));
		btnListener listener = new btnListener();
		PlusListener listener1 = new PlusListener();
		EqualsListener listener2 = new EqualsListener();
		MinusListener listener3 = new MinusListener();
		MultiplyListener listener4 = new MultiplyListener();
		DivideListener listener5 = new DivideListener();
		for(int i=1; i<10; i++){
			btnNumbers[i]= new Button(i+"");
			panelButtons.add(btnNumbers[i]);
			btnNumbers[i].addActionListener(listener);
		}
		btnMinus = new Button("-");
		panelButtons.add(btnMinus);
		btnMinus.addActionListener(listener3);
		btnNumbers[0] = new Button("0");
		panelButtons.add(btnNumbers[0]);
		btnNumbers[0].addActionListener(listener);
		btnPlus = new Button("+");
		panelButtons.add(btnPlus);
		btnPlus.addActionListener(listener1);
		btnMult = new Button("*");
		panelButtons.add(btnMult);
		btnMult.addActionListener(listener4);
		btnEquals = new Button("=");
		panelButtons.add(btnEquals);
		btnEquals.addActionListener(listener2);
		btnDivide = new Button("/");
		panelButtons.add(btnDivide);
		btnDivide.addActionListener(listener5);
		
		setLayout(new BorderLayout(0,10));
		add(panelResult, BorderLayout.NORTH);
		add(panelButtons, BorderLayout.CENTER);
		
		Button btnReset = new Button("Reset");
		panelButtons.add(btnReset);
		btnReset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				tfDisplay.setText("");
				Global.sum=0;
				Global.proizvod=1;
				Global.broj=0;
				Global.flag=1;
				Global.flag1=1;
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		setTitle("Calculator");
		setSize(400,400);
		setVisible(true);
	}
	public static void main(String[] args){
		new Calculator();
	}
	private class btnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(Global.flag==2) {
				tfDisplay.setText("");
				Global.flag=1;
				Global.broj=0;
				Global.proizvod=1;
				Global.sum=0;
			}
			String btnLabel=e.getActionCommand();
			for(int i=0; i<10; i++){
			if(btnLabel.equals(i+"")) {
				tfDisplay.setText(tfDisplay.getText()+i+"");
				// tfDisplay1.setText(tfDisplay.getText()+"");
				break;
			}
			//if(Global.flagPlus==1){
			//	tfDisplay1.setText(tfDisplay.getText()+"+");
			//	Global.flagPlus=0;
			//}
			}
			long broj=Long.parseLong(tfDisplay.getText());
			Global.broj=broj;
		}
	}
	private class PlusListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(tfDisplay.getText().equals("")) {
				if(Global.flag==0 || Global.flag==1 || Global.flag==2 || Global.flag==3 || Global.flag==4){
					JOptionPane.showMessageDialog(btnPlus,"Cannot input two operators next to eachother or operator without entering a number first!\n Please enter your expression again!"
							, "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
				}
				tfDisplay.setText("");
				Global.sum=0;
				Global.proizvod=1;
				Global.broj=0;
				Global.flag=1;
				Global.flag1=1;
			}
			else {
			if(Global.flag==0) 
			Global.sum=Global.sum-Global.broj;
			else if(Global.flag==3 && Global.flag1==1) {
				Global.proizvod=Global.proizvod*Global.broj;
				Global.sum=Global.sum+Global.proizvod;
			}
			else if(Global.flag==3 && Global.flag1==0) {
				Global.proizvod=Global.proizvod*Global.broj;
				Global.sum=Global.sum-Global.proizvod;
			}
			else if(Global.flag==4 && Global.flag1==1) {
				Global.kolichnik=Global.kolichnik/Global.broj;
				Global.sum=Global.sum+Global.kolichnik;
			}
			else if(Global.flag==4 && Global.flag1==0) {
				Global.kolichnik=Global.kolichnik/Global.broj;
				Global.sum=Global.sum-Global.kolichnik;
			}
			else
			Global.sum=Global.sum+Global.broj;
			tfDisplay.setText("");
			Global.flag=1;
			Global.proizvod=1;}	
		}
	}
	private class EqualsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(Global.flag==1)
			Global.sum=Global.sum+Global.broj;
			else if(Global.flag==3 && Global.flag1==1) {
				Global.proizvod=Global.proizvod*Global.broj;
				Global.sum=Global.sum+Global.proizvod;
			}
			else if(Global.flag==3 && Global.flag1==0) {
				Global.proizvod=Global.proizvod*Global.broj;
				Global.sum=Global.sum-Global.proizvod;
			}
			else if(Global.flag==4 && Global.flag1==1) {
				Global.kolichnik=Global.kolichnik/Global.broj;
				Global.sum=Global.sum+Global.kolichnik;
			}
			else if(Global.flag==4 && Global.flag1==0) {
				Global.kolichnik=Global.kolichnik/Global.broj;
				Global.sum=Global.sum-Global.kolichnik;
			}
			else
			Global.sum=Global.sum-Global.broj;
			tfDisplay.setText(Global.sum+"");
			Global.broj=0;
			Global.flag=2;
		}
	}
	private class MinusListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			if(tfDisplay.getText().equals("")) {
				if(Global.flag==0 || Global.flag==1 || Global.flag==2 || Global.flag==3 || Global.flag==4){
					JOptionPane.showMessageDialog(btnMinus, "Cannot input two operators next to eachother or operator without entering a number first!\n Please enter your expression again!"
							, "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
				}
				tfDisplay.setText("");
				Global.sum=0;
				Global.proizvod=1;
				Global.broj=0;
				Global.flag=1;
				Global.flag1=1;
			}
			else {
			if(Global.flag==1){
				Global.sum=Global.sum+Global.broj;
			}
			else if(Global.flag==3 && Global.flag1==1) {
				Global.proizvod=Global.proizvod*Global.broj;
				Global.sum=Global.sum+Global.proizvod;
			}
			else if(Global.flag==3 && Global.flag1==0) {
				Global.proizvod=Global.proizvod*Global.broj;
				Global.sum=Global.sum-Global.proizvod;
			}
			else if(Global.flag==4 && Global.flag1==1) {
				Global.kolichnik=Global.kolichnik/Global.broj;
				Global.sum=Global.sum+Global.kolichnik;
			}
			else if(Global.flag==4 && Global.flag1==0) {
				Global.kolichnik=Global.kolichnik/Global.broj;
				Global.sum=Global.sum-Global.kolichnik;
			}
			else
			Global.sum=Global.sum-Global.broj;
			tfDisplay.setText("");
			Global.flag=0; 
			Global.proizvod=1;}
	}
	}
	private class MultiplyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(tfDisplay.getText().equals("")) {
				if(Global.flag==0 || Global.flag==1 || Global.flag==2 || Global.flag==3 || Global.flag==4){
					JOptionPane.showMessageDialog(btnMult, "Cannot input two operators next to eachother or operator without entering a number first!\n Please enter your expression again!"
							, "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
				}
				tfDisplay.setText("");
				Global.sum=0;
				Global.proizvod=1;
				Global.broj=0;
				Global.flag=1;
				Global.flag1=1;
			}
			else {
				if(Global.flag==1)
					Global.flag1=1;
				else if(Global.flag==0)
					Global.flag1=0;
			if(Global.flag==4){
					Global.kolichnik=Global.kolichnik/Global.broj;
					Global.proizvod=Global.kolichnik;}
			else if (Global.flag==3){
			Global.proizvod=Global.proizvod*Global.broj;}
			else if(Global.flag==2){
				Global.flag1=1;
				Global.proizvod=Global.sum;
				Global.sum=0;
			}
			tfDisplay.setText("");
			Global.flag=3;
			}
		}
	}
	private class DivideListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(tfDisplay.getText().equals("")) {
				if(Global.flag==0 || Global.flag==1 || Global.flag==2 || Global.flag==3 || Global.flag==4){
					JOptionPane.showMessageDialog(btnDivide, "Cannot input two operators next to eachother or operator without entering a number first!\n Please enter your expression again!"
							, "INPUT ERROR!", JOptionPane.ERROR_MESSAGE);
				}
				tfDisplay.setText("");
				Global.sum=0;
				Global.proizvod=1;
				Global.broj=0;
				Global.flag=1;
				Global.flag1=1;
			}
			else {
				if(Global.flag==1)
					{Global.prv=Global.broj;
					Global.kolichnik=Global.broj;
					Global.flag1=1;}	
				else if(Global.flag==0) {
					Global.kolichnik=Global.broj;
					Global.flag1=0; }
			if(Global.flag==3) {
					Global.proizvod=Global.proizvod*Global.broj;
					Global.kolichnik=Global.proizvod;}
			else if(Global.flag==4){
				if(Global.prv==Global.broj){
					Global.kolichnik=Global.prv;
					Global.prv=0;}
				else
				Global.kolichnik=Global.kolichnik/Global.broj;
				}
			else if(Global.flag==2){
				Global.flag1=1;
				Global.kolichnik=Global.sum;
				Global.sum=0;
			}
			tfDisplay.setText("");
			Global.flag=4;
		}
	}
	}
}