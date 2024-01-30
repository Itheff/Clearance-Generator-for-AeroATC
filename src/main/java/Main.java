package main.java;
import java.util.Scanner;

class Main {

    /**
     * Main loop that runs until the user inputs either q or quit
     */
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        String input;

        // Initial input for ICAO vs FAA
        input = "";
        while (!(input.equals("ICAO") || input.equals("FAA"))) {

            System.out.println("Please select a version: ICAO or FAA");
            input = scnr.nextLine();

        }

        // version = false for ICAO, true for FAA
        boolean version = input.equals("FAA");

        if (version) {
            while (true) {

                System.out.println("Please paste a full row flight plan from the flight plan sheet.");
                input = scnr.nextLine();

                // Exit condition to escape the loop and end the process
                if (input == "q" || input == "quit") break;
                
                // Initializes the flight plan
                FlightPlan localFlightPlan =  new FlightPlan(input);

                System.out.println("Selected what you want to do with the flight plan:");
                System.out.println("\t1 = Generate an oral clearance with a SID");
                System.out.println("\t2 = Generate an oral clearance without a SID");
                System.out.println("\t3 = Generate a PDC clearance");
                System.out.println("\t4 = Generate a squawk code only");
                input = scnr.nextLine();

                if (input.equals("1")) {
                    System.out.println(localFlightPlan.generateFAAIFROralSID());
                }
                else if (input.equals("2")) {
                    System.out.println(localFlightPlan.generateFAAIFROralNoSID());
                }
                else if (input.equals("3")) {
                    System.out.println(localFlightPlan.generatePDC());
                }
                else if (input.equals("4")) {
                    System.out.println(FlightPlan.genSquawk());
                }
                else {
                    System.out.println("Input invalid, please re-enter flight plan and try again");
                }
            }

        }
        
        else {
            scnr.close();
            throw new UnsupportedOperationException("ICAO not supported yet");
        }
        
        scnr.close();

    }

}