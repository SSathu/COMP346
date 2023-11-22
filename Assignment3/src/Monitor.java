/** 
 * Class Monitor 
 * To synchronize dining philosophers. 
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca  
 */
public class Monitor   
{
	/*
	 * ------------    
	 * Data members 
	 * ------------
	 */
	private int numberOfPhilosophers;
    private boolean[] chopsticks;
    private boolean isTalking;

	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{	
		// Number of philo = nb of chopsticks
		numberOfPhilosophers = piNumberOfPhilosophers;
        chopsticks = new boolean[numberOfPhilosophers];

		// Setting all chopsticks to be usable
        for (int i = 0; i < numberOfPhilosophers; i++) {
            chopsticks[i] = true;
        }

		// no philosoper is talking
        isTalking = false; 
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{
		// getting indexes of left and right chopsticks
		int leftChopstick = (piTID - 1) % numberOfPhilosophers; 
        int rightChopstick = (piTID) % numberOfPhilosophers; 

		//if either left or right chopsticks is not usable, wait()
		while (!chopsticks[leftChopstick] || !chopsticks[rightChopstick]) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		// Both  are available so set availability of those chopsticks to false   
		chopsticks[leftChopstick] = false;
        chopsticks[rightChopstick] = false;

	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{	
		//index of left/right chopsticks
		int leftChopstick = (piTID - 1) % numberOfPhilosophers; 
        int rightChopstick = (piTID) % numberOfPhilosophers; 

        //Making used chopsticks available
        chopsticks[leftChopstick] = true;
        chopsticks[rightChopstick] = true;

        // Notify
        notifyAll();
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		// ...
	}
}

// EOF

