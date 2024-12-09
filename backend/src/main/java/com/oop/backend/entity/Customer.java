package com.oop.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
@Getter
@Setter
public class Customer {
        public static int numberOfCustomer=0;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int customerID;
        private String name;
        private String password;

        @Override
        public String toString() {
            return name + ":" + password;
        }

}
