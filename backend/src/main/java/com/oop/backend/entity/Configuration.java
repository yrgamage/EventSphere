package com.oop.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "configuration")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Configuration {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long configID;
    @Positive
    @Max(100)
    @Min(0)
    private int totalTickets;
    @Positive
    @Max(100)
    @Min(0)
    private float ticketReleaseRate;
    @Positive
    @Max(100)
    @Min(0)
    private float customerRetrievalRate;
    @Positive
    @Max(100)
    @Min(3)
    private int maxCapacityTickets;

    /*
     * param int tTickets: tickets currently in the pool
     * param float tReleaseRate:  Vendor ticket release Rate
     * Response: {vendor: Vendot, timeoutt: int }
     */

    /**
     *
     * @param tTickets
     * @param tReleaseRate
     * @param tCustomerRetrivalRate
     * @param tMaxTicket
     */




}
