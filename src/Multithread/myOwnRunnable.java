package Multithread;
import java.lang.*;
import java.util.*;

public class myOwnRunnable implements Runnable{

	public void run(){
		try {
			for (int i=0; i<this.getId(); i++) {
			
				Thread.sleep(1000);
		
				System.out.println("Hello from thread " + i);
			}
		} catch (InterruptedException e) {
			System.out.println("I wasn't done yet");
		}
	}
	
	private int id;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public static void main(String args[]) throws InterruptedException {
		myOwnRunnable runnable = new myOwnRunnable();
/*
		runnable.setId(10);
		Thread thread1 = new Thread(runnable);
		thread1.start();
		
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread1.join();
*/

		runnable.setId(20);
		Thread thread2 = new Thread(runnable);
		thread2.start();

        long patience = 1000 * 2;
		long startTime = System.currentTimeMillis();
		while (thread2.isAlive()) {
            System.out.println("Still waiting...");
            // Wait maximum of 1 second
            // for MessageLoop thread
            // to finish.
            thread2.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience)
                  && thread2.isAlive()) {
                System.out.println("Tired of waiting!");
                thread2.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
                thread2.join();
            }
        }
/*		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		System.out.println("--------");
		System.out.println("Finished!!!");
		
	}
}
