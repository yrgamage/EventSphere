package com.oop.backend.model;
import com.oop.backend.entity.Configuration;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    static Queue<Integer> ticketPool= new LinkedList<Integer>(); //Queue to store tickets
    public static void addingTotalTickets(Configuration obj){
        for(int i =1;i<=obj.getTotalTickets();i++){
            ticketPool.add(i);
        }

    }



}

