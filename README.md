# Clearance Generator for AeroATC

Welcome to the Clearance Generator created by Itheff for the AeroATC community.
Current version is version 0.2.0

## Disclaimer

I am at best an amatuer at Java. This project was basically created as practice for 
my university classes. This could have been coded in python much more easily and it 
likely would be better. But alas, here we are, so enjoy.

## Patch Notes

- Changed entire file structure.  
- Added new airport database
- Added automatic departure frequency selection using the new database.
  - This feature is not perfect and will always assume there is a departure controller online.
  - If no departure frequency is available for the airport, it will default to the center controller.
- Added ability to launch program in ICAO mode.
  -  **This is non functional.**  It is only for further expansion in the future.


## How to Use

Releases are pushed as a .jar file and a .bat file (or similar for MacOS and Linux).
Run the .bat file and use the command line on your computer to input flight plans
and tell the program what to do with them. There are several assumtions made in the
code that I will now list:

1. It is CRITIAL for normal operation of the program that there are no tab (ASCII 9)
characters in the input flight plan, other than those that are put there by copying
from google sheets. Best case scenario you will end up with a corrupted mess of a
flight plan, worst case scenario the program will throw an error and crash.
2. Similarly, DO NOT delete any whitespace (tabs, spaces, ect.) in the flight plan
being input. The program will automatically deal with blank areas.
3. The .bat files obviously assumes that you have Java installed. A secondary README is
pushed with releases that includes a link to install Java.
4. The program is not magic, if the flight plan being put in is a jarbled mess,
the output will be so as well.