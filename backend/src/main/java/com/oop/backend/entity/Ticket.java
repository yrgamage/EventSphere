package com.oop.backend.entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buy_ticket")

public class Ticket {
    public static int numberOfCustomer=0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buyID;
    @Positive
    @Min(1)
    @Max(10)
    private int ticketCount;
    private String eventName;
    private String name;



}