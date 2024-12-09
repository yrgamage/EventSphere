package org.example;
import java.util.LinkedList;
import java.util.Queue;
import org.example.Configuration;

public class TicketPool {
    static Queue<Integer> ticketPool= new LinkedList<Integer>(); //Queue to store tickets
    public static void addingTotalTickets(Configuration obj){
        for(int i =1;i<=obj.getTTickets();i++){
            ticketPool.add(i);
        }

    }



}

