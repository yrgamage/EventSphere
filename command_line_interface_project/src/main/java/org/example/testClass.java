package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class testClass {
    public static void main(String[] args) {
        ReturningHomePage();

    }

    public static void ReturningHomePage(){
        int returnVal;
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning){
            try{
                System.out.print("Want to return to Home page ? Enter 1\nExit ? Enter 0 : ");
                returnVal = scanner.nextInt();
                if(returnVal==1 ){
                    isRunning = false;
                    Main.startMethod();
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
}
