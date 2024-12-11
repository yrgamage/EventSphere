package com.oop.backend.model;

import com.oop.backend.entity.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BuyTicket  implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(BuyTicket.class);

    private int customersCount;
    private Configuration extractedParameters;
    private int ticketCount;
    Object key = new Object();
    private static int increment=0;

    public BuyTicket(int customersCount,Configuration obj,int ticketCount) {
        this.customersCount = customersCount;
        this.extractedParameters = obj;
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
                        logger.info("Pool is empty.. customer "+customerId+" is waiting for tickets");
                        key.wait();
                    }

                    for(int i=1;i<=ticketCount;i++){
                        if(increment>=extractedParameters.getCustomerRetrievalRate()){
                            logger.info("Exceeded the limit of buying tickets for this 15 seconds..  customer "+customerId+" is waiting to buy a ticket");
                            Thread.sleep(16000-(increment*1000)); // Delay to achieve  tickets per 15 second,Assumed one thread will run for one second
                            increment=0;                                  //Resetting rate
                        }else if(!TicketPool.ticketPool.isEmpty()){
                            int ticketID = TicketPool.ticketPool.poll();
                            increment++;
                            logger.info("Customer " + customersCount + " " + customerId + " bought a ticket NO : "+ticketID);
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
