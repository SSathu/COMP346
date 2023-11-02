import CharStackExceptions.*;


public class StackManager {
              // The Stack
              private static CharStack stack = new CharStack();
              private static final int NUM_ACQREL = 4; // Number of Producer/Consumer threads
              private static final int NUM_PROBERS = 1; // Number of threads dumping stack
              private static int iThreadSteps = 3; // Number of steps they take
              // Semaphores for Sync
              private static Semaphore stackSem = new Semaphore(1);
              private static Semaphore orderSem = new Semaphore(0);
              

             // The main()
             public static void main(String[] argv)
             {
                       // Some initial stats...
                       try
                       {
                                 System.out.println("Main thread starts executing.");
                                 System.out.println("Initial value of top = " + stack.getTop() + ".");
                                 System.out.println("Initial value of stack top = " + stack.pick() + ".");
                                 System.out.println("Main thread will now fork several threads.");
                       }
                       catch(CharStackEmptyException e)
                       {
                                 System.out.println("Caught exception: StackCharEmptyException");
                                 System.out.println("Message : " + e.getMessage());
                                 System.out.println("Stack Trace : ");
                                 e.printStackTrace();
                        }
                       /*
                      * The birth of threads
                       */
                      Consumer ab1 = new Consumer();
                      Consumer ab2 = new Consumer();
                      System.out.println ("Two Consumer threads have been created.");
                     Producer rb1 = new Producer();
                     Producer rb2 = new Producer();
                     System.out.println ("Two Producer threads have been created.");
                     CharStackProber csp = new CharStackProber();
                     System.out.println ("One CharStackProber thread has been created.");
                     /*
                    * start executing
                     */
                    ab1.start();
                    rb1.start();
                    ab2.start();
                    rb2.start();
                    csp.start();
                    /*
                     * Wait by here for all forked threads to die
                    */
                   try
                   {
                              ab1.join();
                              ab2.join();
                              rb1.join();
                              rb2.join();
                             csp.join();
                             // Some final stats after all the child threads terminated...
                             System.out.println("System terminates normally.");
                             System.out.println("Final value of top = " + stack.getTop() + ".");
                             System.out.println("Final value of stack top = " + stack.pick() + ".");
                             System.out.println("Final value of stack top-1 = " + stack.getAt(stack.getTop() - 1) + ".");
                             System.out.println("Stack access count = " + stack.getAccessCounter());
                   }
                  catch(InterruptedException e)
                  {
                         System.out.println("Caught InterruptedException: " + e.getMessage());
                              System.exit(1);
                  }
                 catch(Exception e)
                 {
                              System.out.println("Caught exception: " + e.getClass().getName());
                              System.out.println("Message : " + e.getMessage());
                             System.out.println("Stack Trace : ");
                             e.printStackTrace();
                  }
           } // main()
           /*
           * Inner Consumer thread class
           */
           static class Consumer extends BaseThread
           {
                    private char copy;
                    public void run()
<<<<<<< HEAD
                    {            
                                while(orderSem.value != 2){System.out.print("");}
                               
                                 System.out.println ("Consumer thread [TID=" + this.iTID + "] starts executing.");
                                 for (int i = 0; i < 3; i++)  {
                                        try {
                                            stackSem.Wait();
                                            char topStack = CharStack.pop(); 
                                            copy = topStack;
                                        } catch (CharStackEmptyException e) {
                                            e.printStackTrace();
                                        }finally{
                                            stackSem.Signal();
                                        }

=======
                    {
                                 try {
                                  // Wait for both producers to complete
                                  producerSemaphore.acquire();
                                  producerSemaphore.acquire();
                  
                                  for (int i = 0; i < 6; i++) {
                                      mutex.acquire();
                                      char c = stack.pop();
                                      System.out.println("Consumer thread [TID=" + this.iTID + "] pops character = " + c);
                                      mutex.release();
                  
                                      // Signal that a consumer has completed
                                      consumerSemaphore.release();
                                  }
                              } catch (Exception e) {
                                  e.printStackTrace();
                              }
>>>>>>> 0e43ad25412a90b752e32ff4c789352e6184ac03
                                         System.out.println("Consumer thread [TID=" + this.iTID + "] pops character =" + this.copy);
                                         
                                 }
                                 System.out.println ("Consumer thread [TID=" + this.iTID + "] terminates.");
                                
                        }
             } // class Consumer
              /*
             * Inner class Producer
              */
             static class Producer extends BaseThread
             {          
                        
                        private char block; 
                        public void run()
                        {          

                                   System.out.println ("Producer thread [TID=" + this.iTID + "] starts executing.");
                                   stackSem.Wait();

                                   for (int i = 0; i < 3; i++)  {
                                    
                                    try {
<<<<<<< HEAD
                                        char topStack = CharStack.pick();
                                        char nextChar = (char)(topStack+1);
                                        block = nextChar;
                                        CharStack.push(nextChar);
=======
                                        mutex.acquire();
                                          char topChar = stack.pick();
                                          char nextChar = (char)(topChar + 1);
                                          stack.push(nextChar);
                                          System.out.println("Producer thread [TID=" + this.iTID + "] pushes character = " + nextChar);
                                          mutex.release();
                                          producerSemaphore.release(); 

>>>>>>> 0e43ad25412a90b752e32ff4c789352e6184ac03
                                    } catch (CharStackEmptyException csee) {
                                        csee.getMessage();
                                    }catch (CharStackFullException csfe) {
                                        csfe.getMessage();
                                    }finally{
                                        
                                    }
                                    
                                    
                                   System.out.println("Producer thread [TID=" + this.iTID + "] pushes character =" + this.block);
                                   }
                                   stackSem.Signal();
                                  System.out.println("Producer thread [TID=" + this.iTID + "] terminates.");
                                  orderSem.Signal();
                        }
             } // class Producer
               /*
              * Inner class CharStackProber to dump stack contents
               */
              static class CharStackProber extends BaseThread
              {
                        public void run()
                        {         
                                  
                                  System.out.println("CharStackProber thread [TID=" + this.iTID + "] starts executing.");
                                  stackSem.Wait();
                                  for (int i = 0; i < 6; i++) {
                                    System.out.print("Stack S = (");
                                    for (int j = 0; j < stack.getSize(); j++) {
                                        char value;
                                        try {
                                            value = stack.getAt(j);
                                        } catch (CharStackInvalidAceessException e) {
                                            e.getMessage();
                                            break;
                                        }
                                        System.out.print("[" + value + "]");
                                        if (j != stack.getSize() - 1) {
                                            System.out.print(",");
                                        }
                                    }
                                    System.out.println(")");
                        }
                        stackSem.Signal();
              } // class CharStackProber
}
}
