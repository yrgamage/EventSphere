
package org.example;

import java.util.logging.Level;

public class BuyTicket  implements Runnable{

    private int customersCount;
    private Configuration obj;
    private int ticketCount;
    Object key = new Object();
    private static int increment=0;

    public BuyTicket(int customersCount,Configuration obj,int ticketCount) {
        this.customersCount = customersCount;
        this.obj = obj;
        this.ticketCount = ticketCount;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (key) {

                try {
                    String customerId = Thread.currentThread().getName();// Use thread name as customer ID
                    // Wait if no tickets are available
                    while (TicketPool.ticketPool.isEmpty()) {
                        LoggerClass.log(Level.INFO,"Pool is empty.. customer "+customerId+" is waiting for tickets","YELLOW");
                        key.wait();
                    }

                    for(int i=1;i<=ticketCount+1;i++){
                        if(increment>=obj.getCustomerRetrievalRate()){
                            LoggerClass.log(Level.INFO,"Exceeded the limit of buying tickets for this 15 seconds..  customer "+customerId+" is waiting to buy a ticket","BLUE");
                            Thread.sleep(16000-(increment*1000)); // Delay to achieve  tickets per 15 second,Assumed one thread will run for one second
                            increment=0;                                  //Resetting rate
                        }else if(!TicketPool.ticketPool.isEmpty() && increment<obj.getCustomerRetrievalRate()){
                            int ticketID = TicketPool.ticketPool.poll();
                            increment++;
                            LoggerClass.log(Level.INFO,"Customer " + customersCount + " " + customerId + " bought a ticket NO : "+ticketID+" pool size "+TicketPool.ticketPool.size(),"CYAN");
                            key.notifyAll(); // Notify other threads
                            Thread.sleep(1000); // Delay to print the message
                        }


                    }
                } catch (InterruptedException | RuntimeException e ){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }


    }





}
