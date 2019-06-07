package PetrolStation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Main
{
	private JFrame frame1;
	private JFrame frame2;
	private JTextArea textArea_1;
	
	private int PumpsNum = 0;
	private int CustNum = 0;
	private ArrayList<String> CustName = new ArrayList<>();
	
	void Get_Main()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Main window = new Main();
					window.frame1.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Get_String(String Str)
	{
		textArea_1.append(Str);
	}
	
	Main()
	{
		initialize_Form1();
		initialize_Form2();
	}
	
	private void initialize_Form1()
	{
		frame1 = new JFrame("Start");
		frame1.setBounds(50, 50, 600, 600);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		JLabel lblWel = new JLabel("WelCome To Petrol Station");
		lblWel.setBounds(200, 70, 200, 80);
		frame1.getContentPane().add(lblWel);
		
		JLabel lblP = new JLabel("Enter Pumps Number : ");
		lblP.setBounds(50, 150, 200, 80);
		frame1.getContentPane().add(lblP);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(210, 180, 200, 25);
		frame1.getContentPane().add(textField_1);
		
		JLabel lblC = new JLabel("Enter Customer Number : ");
		lblC.setBounds(50, 250, 200, 80);
		frame1.getContentPane().add(lblC);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(210, 280, 200, 25);
		frame1.getContentPane().add(textField_2);
		
		JLabel lblN = new JLabel("Enter Customers Name : ");
		lblN.setBounds(50, 350, 200, 80);
		frame1.getContentPane().add(lblN);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(210, 380, 200, 25);
		frame1.getContentPane().add(textField_3);
		
		JButton btnSubmit1 = new JButton("Continue...");
		btnSubmit1.setBounds(440, 420, 100, 30);
		frame1.getContentPane().add(btnSubmit1);
		
		btnSubmit1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PumpsNum = Integer.parseInt(textField_1.getText());
				CustNum = Integer.parseInt(textField_2.getText());
				
				String[] S;
				String Cust = textField_3.getText();
				S = Cust.split(" ");
				for(int i = 0; i < CustNum; i++)
				{
					CustName.add(S[i]);
				}
				frame2.setVisible(true);
				frame1.setVisible(false);
			}
		});
	}
	
	private void initialize_Form2()
	{
		frame2 = new JFrame("Customers Status");
		frame2.setBounds(50, 50, 600, 600);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		JButton btnSubmit1 = new JButton("Show Status.");
		btnSubmit1.setBounds(50, 50, 150, 30);
		frame2.getContentPane().add(btnSubmit1);
	
		textArea_1 = new JTextArea();
		textArea_1.setBounds(100, 100, 400, 400);
		frame2.getContentPane().add(textArea_1);
	
		btnSubmit1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Semaphore P = new Semaphore(PumpsNum);
				for(int i = 0; i < CustNum; i++)
				{
					Threads th = new Threads(P, CustName.get(i));
					th.start();
				}
			}
		});
	}
}
