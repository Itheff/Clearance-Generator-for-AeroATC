package main.java;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class FlightPlan {
    
    private String[] flightPlan = new String[10];

    /**
     * This constructor method takes a raw flight plan from the flight plans sheet and 
     * turns it into an array for the rest of the methods to read from.
     */
    FlightPlan(String rawFP) {
        /* The indicies for the flightPlan array are as follows:
         * 0 : Timestamp
         * 1 : Game callsign
         * 2 : ATC callsign
         * 3 : Stand
         * 4 : Aircraft type
         * 5 : Flight rules
         * 6 : Departure ICAO
         * 7 : Arrival ICAO
         * 8 : Route
         * 9 : Flight level
         * 10 : Remarks
         * 
         * Many of these are non-functional but still included for the sake of expanability
         * and simplicity
         */

        flightPlan = rawFP.split("\t");
    }

    /**
     * This method takes no parameters and returns a squawk code
     * 
     * @return a random octal number from 0000 to 7777, with leading zeros
     */
    public static String genSquawk() {
        Random rand = new Random();
        int min = 0;
        int max = 4096;
        int randomNumberInRange = rand.nextInt(max - min) + min;
        String octalNumber = String.format("%04o",randomNumberInRange);
        return octalNumber;
    }

    /**
     * This method takes no parameters and returns a full oral IFR clearance for aircraft with a SID
     * @return a string containing an oral clearance for the flight plan
     */
    public String generateFAAIFROralSID() {

        // This object is a way to concatenate many strings into a single string efficiently and clearly
        StringBuilder clearanceBuilder = new StringBuilder();

        clearanceBuilder.append(flightPlan[2]);
        clearanceBuilder.append(" cleared to ");
        clearanceBuilder.append(flightPlan[7]);
        clearanceBuilder.append(" via the ");
        // This pulls the SID from the route. It assumes the SID is 6 characters long
        clearanceBuilder.append(flightPlan[8].substring(0,7));
        clearanceBuilder.append(" departure, ");
        // This pulls the transition from the route. It assumes that the transition and SID will have a character in between them
        clearanceBuilder.append(flightPlan[8].substring(8,13));
        clearanceBuilder.append(" transition, then as filed. Climb via the SID and expect FL");
        clearanceBuilder.append(flightPlan[9]);
        clearanceBuilder.append(" 10 minutes after departure. Departure frequency on ");
        // This makes a BufferedReader object which is read by the loop below
        InputStream inputStream = FlightPlan.class.getResourceAsStream("/resources/airport_database.csv");
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String frequency = "N/A";

        // For some reason the whole thing crashes if it's not in a try block
        try {
        // This checks all the ICAO codes in the aircraft database against the departure ICAO code in the flight plan
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] splitLine = line.split(",");
            if (splitLine[0].equals(flightPlan[6])) {
                // When a matching ICAO code is found, we then check if there is a departure frequency in the database
                // If there isn't, then we use the center frequency for departure
                if (splitLine[6].equals("None")) frequency = splitLine[7];
                else frequency = splitLine[6];

            }

        }

        // Honestly, I don't know why this try block is here, this never raises any exceptions so it pretty useless
        // But it doesn't work otherwise, ask ChatGPT I guess because I did, and this is what it spat out and it worked
        } catch (IOException e) {
            e.printStackTrace();
        }

        clearanceBuilder.append(frequency);
        clearanceBuilder.append(", squawk ");
        clearanceBuilder.append(genSquawk());
        clearanceBuilder.append(".");

        // This assembles the built string and returns it
        return (clearanceBuilder.toString());

    }

    /**
     * This method takes no parameters and returns a full oral IFR clearance for aircraft without a SID
     * @return a string containing an oral clearance for the flight plan
     */
    public String generateFAAIFROralNoSID() {

        // This object is a way to concatenate many strings into a single string efficiently and clearly
        StringBuilder clearanceBuilder = new StringBuilder();

        clearanceBuilder.append(flightPlan[2]);
        clearanceBuilder.append(" cleared to ");
        clearanceBuilder.append(flightPlan[7]);
        // FIXME: Allow for different altitudes and times and perhaps allow for route readback
        clearanceBuilder.append(" as filed. Initial altitude 4000, expect FL");
        clearanceBuilder.append(flightPlan[9]);
        // FIXME: Frequencies need implementing
        clearanceBuilder.append(" 10 minutes after departure. Departure frequency on N/A, squawk ");
        clearanceBuilder.append(genSquawk());
        clearanceBuilder.append(".");

        // This assembles the built string and returns it
        return (clearanceBuilder.toString());

    }

    /**
     * This method takes no parameters and returns a full PDC clearance
     * @return a string containing a PDC clearance for the flight plan
     */
    public String generatePDC() {

        // This object is a way to concatenate many strings into a single string efficiently and clearly
        StringBuilder clearanceBuilder = new StringBuilder();

        clearanceBuilder.append("CLR ");
        clearanceBuilder.append(flightPlan[2].toUpperCase());
        clearanceBuilder.append(" TO  ARR ");
        clearanceBuilder.append(flightPlan[7].toUpperCase());
        clearanceBuilder.append(". RT ");
        // This pulls only the route after the SID and transition, assuming they are 12 characters long
        clearanceBuilder.append(flightPlan[8].substring(12));
        clearanceBuilder.append(". RMK CLRD ");
        // This pulls the SID from the route. It assumes that the SID is 6 characters long
        clearanceBuilder.append(flightPlan[8].substring(0,6));
        clearanceBuilder.append(" DEPARTURE, ");
        // This pulls the transition from the route. It assumes that the transition and SID will have a character in between them
        clearanceBuilder.append(flightPlan[8].substring(7,12));
        clearanceBuilder.append("TRANSITION, CLIMB VIA SID EXP ");
        clearanceBuilder.append(flightPlan[9]);
        // FIXME: Frequencies need implementing
        clearanceBuilder.append(" 10 MINS AFTER DPT, DPT FREQ N/A, SQUAWK ");
        clearanceBuilder.append(genSquawk());
        clearanceBuilder.append(".");

        // This assembles the built string and returns it
        return (clearanceBuilder.toString());

    }

}
