package org.example;

import java.util.*;


public class Main {
    static Configuration data,config;
    private static int vendorCount, count, customerCount;

    public static void main(String[] args) {
        System.out.println("*************************************************************");
        System.out.println("***********WELCOME TO THE EVENT TICKETING SYSTEM*************");
        System.out.println("*************************************************************");
        String[] statuses = {
                "Initializing...",
                "Loading components...",
                "Setting up environment...",

        };
        for (String status : statuses) {
            System.out.print(status);
            try {
                Thread.sleep(1000);  // 1-second delay for each status message
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\r");  // Move the cursor back to overwrite the line
        }
        int prompt = startMethod();
        caseSwitching(prompt);

    }
    public static void caseSwitching(int prompt){
        Scanner input = new Scanner(System.in);


        switch (prompt){
            case 1:
                startingSystem();
                break;
            case 2:
                config = param();
                DatabaseConnector.databaseUpdater(config);
                ReturningHomePage();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input");
        }

    }
    public static int startMethod() {

        Scanner input = new Scanner(System.in);

        System.out.println("USE FOLLOWING NUMBERS TO RUN THE SYSTEM ");
        System.out.print("1 - Start\n2 - Enter Parameters\n0 - Exit\nEnter :");


        int prompt1;
        while (true) {
            try {
                prompt1 = input.nextInt();
                if (prompt1 >= 0 && prompt1 < 3) {
                    break;
                } else {
                    throw new IllegalArgumentException("\n");
                }
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Enter a valid number");
                input.nextLine();
            }
        }
        return prompt1;
    }

    public  static Configuration param() {
        boolean condition = true;
        int TTickets = 0,MaxTicket = 0;
        float TReleaseRate=0,CustomerRetrievalRate=0;
        Scanner input = new Scanner(System.in);
        while (condition) {
            try {
                System.out.print("Enter maximum ticket capacity : ");
                MaxTicket = input.nextInt();
                if (MaxTicket < 3) {
                    System.out.println("Maximum ticket count is not enough to sell on the system.");
                    throw new IllegalArgumentException("\n");
                }
                System.out.print("Enter number of total tickets : ");
                TTickets = input.nextInt();
                if (TTickets > MaxTicket) {
                    System.out.println("Total ticket count cannot exceed the maximum ticket count");
                    throw new IllegalArgumentException("\n");
                }else{condition = false;}

            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid input.Try again\nEnter");
                input.nextLine();
            }
        }
        boolean condition1 = true;
        while (condition1) {
            try {
                System.out.print("Enter ticket release rate per 15 seconds : ");
                TReleaseRate = input.nextFloat();
                if (TReleaseRate <16) {
                    condition1 = false;
                    
                }else{
                    System.out.println("Maximum capasity is ticket per second.");
                    throw new IllegalArgumentException("\n");
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid input.");
            }
        }
        boolean condition2 = true;
        while (condition2) {
            try {
                System.out.print("Enter customer retrieval rate per 15 seconds: ");
                CustomerRetrievalRate = input.nextFloat();
                if (CustomerRetrievalRate>16) {
                    System.out.println("Maximum capasity is ticket per second.");
                    throw new IllegalArgumentException("\n");
                }else {
                    condition2= false;
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }
        return new Configuration(TTickets,TReleaseRate,CustomerRetrievalRate,MaxTicket);
    }

    public static void ReturningHomePage(){
        int returnVal;
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning){
            System.out.print("Want to return to Home page ? Enter 1\nExit ? Enter 0 : ");
            try{
                returnVal = scanner.nextInt();
                if(returnVal==1 ){
                    isRunning = false;
                    int prompt = Main.startMethod();
                    Main.caseSwitching(prompt);
                }else if(returnVal==0){
                    isRunning = false;
                    System.exit(0);
                }
                else{
                    throw new IllegalArgumentException();
                }
            }catch (InputMismatchException | IllegalArgumentException e){
                System.out.println("Invalid input.");
                scanner.next();
            }

        }
    }


    public static void startingSystem(){
        Scanner input =  new Scanner(System.in);
        System.out.println("Starting system ...........");
        data = DataExtractor.dataExtractor();
        TicketPool.addingTotalTickets(data);
        int ticketVendorCount,ticketCustomerCount;
        while (true) {
            try {
                System.out.print("Enter number of customers : ");
                customerCount = input.nextInt();
                System.out.print("Enter number of tickets per customer ");
                ticketCustomerCount = input.nextInt();
                if (customerCount > 0 && ticketCustomerCount > 0) {
                    break;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid input");
                input.next();
            }
        }

        while (true) {
            try {
                System.out.print("Enter number of vendors : ");
                vendorCount = input.nextInt();
                System.out.print("Enter number of tickets per vendor : ");
                ticketVendorCount = input.nextInt();
                if (vendorCount > 0 && ticketVendorCount > 0) {
                    break;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid input");
                input.next();
            }
        }
        System.out.println("Enter 0 to stop : ");

        Thread stopButton = new Thread(()->{
            boolean isRunning = true;
            while (isRunning) {
                if (input.hasNextInt()) { // Check if user has entered input
                    int command = input.nextInt();
                    if (command == 0) {
                        isRunning = false; // Set flag to stop all threads
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                }
            }
        });
        stopButton.start();

        for(int i=1;i<=vendorCount;i++){

            ReleaseTicket releaseTicket = new ReleaseTicket(i,data,ticketVendorCount);
            Thread customerThread = new Thread(releaseTicket);
            customerThread.start();
        }

        for(int i=1;i<=customerCount;i++){

            BuyTicket buyTicket = new BuyTicket(i,data,ticketCustomerCount);
            Thread vendorThread = new Thread(buyTicket);
            vendorThread.start();
        }
    }





}
