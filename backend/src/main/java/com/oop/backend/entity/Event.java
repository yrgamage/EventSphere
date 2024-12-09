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
@Table(name = "event")

public class Event {
    public static int  numberOfVendor=0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)



    private int eventID;
    private String eventName;
    @Positive
    @Min(3)
    @Max(100)
    private int ticketCount;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    @Positive
    private int ticketPrice;

    @Lob
    @Column(name = "eventImage")
    private byte[] eventImage;

}
