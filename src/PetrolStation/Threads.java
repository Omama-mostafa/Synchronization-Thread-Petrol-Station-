package PetrolStation;

import java.util.Random;

public class Threads extends Thread
{
	private static Random Rand = new Random();
	private String Cust_name;
	private Semaphore Pump;
	
	Threads(Semaphore S_Pump, String name)
	{
		this.Cust_name = name;
		this.Pump = S_Pump;
	}
	
	public void run()
	{
		Main M = new Main();
		Pump.Wait(Cust_name);
		try
		{
			int pumpID = Pump.getPump();
			sleep(Rand.nextInt(7000 - 1000) + 1000);
			System.out.println("Pump " + (pumpID + 1) + " : " + Cust_name + " Occupied.");
			M.Get_String("Pump " + (pumpID + 1) + " : " + Cust_name + " Occupied.");
			sleep(Rand.nextInt(7000 - 1000) + 1000);
			System.out.println("Pump " + (pumpID + 1) + " : " + Cust_name + " Being Served.");
			M.Get_String("Pump " + (pumpID + 1) + " : " + Cust_name + " Being Served.");
			sleep(Rand.nextInt(7000 - 1000) + 1000);
			System.out.println("Pump " + (pumpID + 1) + " : " + Cust_name + " Paying.");
			M.Get_String("Pump " + (pumpID + 1) + " : " + Cust_name + " Paying.");
			sleep(Rand.nextInt(7000 - 1000) + 1000);
			System.out.println("Pump " + (pumpID + 1) + " : " + Cust_name + " Leave.");
			M.Get_String("Pump " + (pumpID + 1) + " : " + Cust_name + " Leave.");
			Pump.ReleasePump(pumpID);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			Pump.Signal();
		}
	}
}

