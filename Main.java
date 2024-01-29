import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        boolean running = true;
        Scanner scnr = new Scanner(System.in);

        while (running) {
            
            System.out.println("Please paste a full row flight plan from the flight plan sheet.");
            String input = scnr.nextLine();
            if (input == "q" || input == "quit") {
                running = false;
                break;
            }

            FlightPlan localFlightPlan =  new FlightPlan(input);

            System.out.println("Selected what you want to do with the flight plan:");
            System.out.println("\t1 = Generate an oral clearance with a SID");
            System.out.println("\t2 = Generate an oral clearance without a SID");
            System.out.println("\t3 = Generate a PDC clearance");
            System.out.println("\t4 = Generate a squawk code only");
            input = scnr.nextLine();

            if (input.equals("1")) {
                System.out.println(localFlightPlan.generateIFROralSID());
            }
            else if (input.equals("2")) {
                System.out.println(localFlightPlan.generateIFROralNoSID());
            }
            else if (input.equals("3")) {
                System.out.println(localFlightPlan.generateIFRPDC());
            }
            else if (input.equals("4")) {
                System.out.println(FlightPlan.genSquawk());
            }
            else {
                System.out.println("Input invalid, please re-enter flight plan and try again");
            }
        }

        scnr.close();

    }

}