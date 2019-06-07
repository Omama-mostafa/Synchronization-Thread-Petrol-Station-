package PetrolStation;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

class Semaphore
{
	private int value;
	private static Random Rand = new Random();
	private boolean FreePump[];
	private Lock PumpLock;
	Semaphore(int PumpsNum)
	{
		value = PumpsNum;
		
		FreePump = new boolean[PumpsNum];
		for(int i = 0; i< PumpsNum; i++)
			FreePump[i] = true;
		PumpLock = new ReentrantLock();
	}
	
	public int getPump()
	{
		int foundPump = -1;
		try
		{
			PumpLock.lock();
			for(int i=0; i<FreePump.length; i++)
			{
				if(FreePump[i])
				{
					foundPump = i;
					FreePump[i] = false;
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			PumpLock.unlock();
		}
		return foundPump;
	}
	
	public void ReleasePump(int i)
	{
		PumpLock.lock();
		FreePump[i] = true;
		PumpLock.unlock();
	}
	
	synchronized void Wait(String CustName)
	{
		boolean BeWait = false;
		Main M = new Main();
		while(value <= 0)
		{
			try
			{
				sleep(Rand.nextInt(7000 - 1000) + 1000);
				System.out.println(CustName + " Arrived and waiting.");
				M.Get_String(CustName + " Arrived and waiting.");
				BeWait = true;
				wait();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		if(!BeWait)
		{
			System.out.println(CustName + " Arrived.");
			M.Get_String(CustName + " Arrived.");
		}
		value--;
	}
	
	synchronized void Signal()
	{
		if(value <= 0)
		{
			notify();
		}
		value++;
	}
}
