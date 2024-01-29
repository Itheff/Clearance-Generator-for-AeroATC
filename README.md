# Clearance Generator for AeroATC

Welcome to the Clearance Generator created by Itheff for the AeroATC community.
Current version is version 0.1.0

## Patch Notes

Initial Version

## How to Use

Releases are pushed as a .jar file and a .bat file (or similar for MacOS and Linux).
Run the .bat file and use the command line on your computer to input flight plans
and tell the program what to do with them. There are several assumtions made in the
code that I will now list:

1.) It is CRITIAL for normal operation of the program that there are no tab (ASCII 9)
characters in the input flight plan, other than those that are put there by copying
from google sheets. Best case scenario you will end up with a corrupted mess of a
flight plan, worst case scenario the program will throw an error and crash.

2.) Similarly, DO NOT delete any whitespace (tabs, spaces, ect.) in the flight plan
being input. The program will automatically deal with blank areas.

3.) The .bat files obviously assumes that you have Java installed. A secondary README is
pushed with releases that includes a link to install Java.

4.) The program is not magic, if the flight plan being put in is a jarbled mess,
the output will be so as well.
