
package  org.example;

import java.util.logging.Level;

public class ReleaseTicket implements Runnable{

    private  final int vendorsCount;
    private final Configuration obj;
    private  final int ticketCount;
    Object key = new Object(); //Object to lock the critical section
    private static int increment=0;
    protected   static int ticketID =1;

    public ReleaseTicket(int vendorCount, Configuration obj,int ticketCount) {
        this.vendorsCount = vendorCount;
        this.obj = obj;
        this.ticketCount = ticketCount;
    }

    @Override
    public void run() {
        {

            while (true) {
                synchronized (key) {
                    String vendorId = Thread.currentThread().getName();

                    try {
                        // Wait if the pool is full or the vendor has reached its limit
                        while (TicketPool.ticketPool.size() == obj.getMaxTicket() ) {
                            LoggerClass.log(Level.INFO,"Vendor "+vendorsCount+" "+vendorId+" is waiting for space to add tickets","YELLOW");
                            key.wait(); // Wait until space is available in the pool
                        }

                        for (int i = 1; i <=ticketCount+1 ; i++) {
                            if (TicketPool.ticketPool.size() <obj.getMaxTicket() && increment<obj.getTReleaseRate()) {
                                TicketPool.ticketPool.add(ticketID++);
                                LoggerClass.log(Level.INFO,"Vendor "+vendorsCount+" "+vendorId+" added ticket: NO " + ticketID+" pool size "+TicketPool.ticketPool.size(),"CYAN");
                                increment++;
                                key.notifyAll();
                                Thread.sleep(1000);
                            }
                            else if(increment>=obj.getTReleaseRate()){
                                LoggerClass.log(Level.INFO,"Vendor "+vendorsCount+" "+vendorId+" has reached the ticket limit for this 15 seconds.waiting to release ...","BLUE");
                                Thread.sleep(16000-(increment*1000));// Delay to achieve  tickets per second, Assumed that one thread will run for one second
                                increment=0;                            //Resetting the rate
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupt status
                        break; // Exit the loop on interruption
                    }
                }

            }
        }

    }
}
