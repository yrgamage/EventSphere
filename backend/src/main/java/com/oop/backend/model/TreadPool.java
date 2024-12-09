package com.oop.backend.model;

import com.oop.backend.controller.EventController;
import com.oop.backend.controller.TicketController;
import com.oop.backend.entity.Configuration;
import com.oop.backend.entity.Event;
import com.oop.backend.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TreadPool {
    private volatile boolean stopRequested = false;
    private static  List<Thread> activeThreads = new ArrayList<>();

    // Start the thread pool
    public static void startThreadPool(Configuration obj) {
        TicketPool.addingTotalTickets(obj);

        // Create and start vendor threads for releasing tickets
        for (int i = 1; i <= Event.numberOfVendor; i++) {
            ReleaseTicket releaseTicket = new ReleaseTicket(i, obj, EventController.ticketCount);
            Thread customerThread = new Thread(releaseTicket);
            activeThreads.add(customerThread); // Add thread to active threads list
            customerThread.start();
        }

        // Create and start customer threads for buying tickets
        for (int i = 1; i <= Ticket.numberOfCustomer; i++) {
            BuyTicket buyTicket = new BuyTicket(i, obj, TicketController.ticketPerCustomer);
            Thread vendorThread = new Thread(buyTicket);
            activeThreads.add(vendorThread); // Add thread to active threads list
            vendorThread.start();
        }
    }

    // Method to stop all threads
    public void stopAllThreads() {
        stopRequested = true;

        for (Thread thread : activeThreads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();  // Interrupt the thread to stop execution
            }
        }
    }

    // Method to check if the stop flag is set and stop the threads
    public boolean shouldStop() {
        return stopRequested;
    }
}
