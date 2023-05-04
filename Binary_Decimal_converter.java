/* Harjot Gill
 * 9/10/2022
 * This program will convert a simple binary or decimal number into the other, as 
 * well as into an octal and hexidecimal number 
 */
 
import java.util.Scanner;
import java.util.*;
import java.util.InputMismatchException;

public class Binary_Decimal_converter { 
    public static void main (String [] args) { 
        Scanner in = new Scanner(System.in);
        
        ///input number and if its binary/decimal 
        System.out.print("Please enter a number in decimal or binary: ");
        double entry = 0;
        
        ///test for valid input
        try {
            entry = in.nextDouble();
        } catch(InputMismatchException e){
        System.out.println("Your input is invalid, please try again");
        System.exit(0);
        }        
        
        System.out.print("Decimal (d) or binary (b):  ");
        char type;
        
        ///test for valid input
        do { 
            type = in.next().charAt(0); 
            type = Character.toLowerCase(type);
            
            if (type == 'b') { 
            String test = String.format("%.12f",entry);
                for (int i = 0; i < test.length(); i++) {
                    if (test.charAt(i) != '0' && test.charAt(i) != '1' && test.charAt(i) != '.') {
                        System.out.print("Invalid format for binary (or too big)");
                        System.exit(0);
                    }     
                }
            }

            if (type != 'b' & type != 'd') 
                System.out.print("Please enter d or b: ");
        } while (type != 'b' & type != 'd');
                
        ///initalize conversion placeholder variables 
        String binary = null; 
        double decimal = 0; 
        
        ///convert binary to decimal for easier conversion to octal, etc. 
        if (type == 'b') { 
            binary = Double.toString(entry);
            decimal = biToDeci(entry);
            entry = decimal; 
        } else {
            binary = convert(entry,2);
            decimal = entry;
        }
        
        ///convert to octal and hex
        String octal = convert(entry,8); 
        String hex = convert(entry,16);    
        
        ///print converted values 
        System.out.println("\nBinary: " + binary);
        System.out.println("Octal: " + octal);
        System.out.printf("Decimal: %.3f \n", decimal);
        System.out.println("Hexidecimal: " + hex);
    
    }
    
    ///helper function 
    public static String convert(double entry, int base) { 
        return convert_int(entry,base) + convert_fraction(entry,base); 
    } 
    
    
    public static String convert_int(double entry, int base) { 
        ///division method
        int quotient = (int)entry;
        Stack<Character> stack = new Stack<Character>();
        String result = "";
        int remainder = 0; 
        
        while(quotient >= base) {
            remainder = quotient % base;
            stack.push(codeAdder(remainder));
            quotient = quotient / base; 
        }
        
        if (base > quotient) 
            stack.push(codeAdder(quotient));

        while(!stack.isEmpty())
            result = result + stack.pop();

        return result;
    }
    
    public static String convert_fraction(double entry, int base) { 
        ///multiplication method 
        int wholeNumber = (int)entry;
        double decimal = entry - wholeNumber;    
        
        double tolerance = 0.009;    //limits the accuracy of the converision 
        
        if (decimal < tolerance) 
            return ""; 
            
        Queue<Character> list = new LinkedList<Character>();
        String result = "";
        int wholeNum = 0; 
        int counter = 0;    //prevents an infinte loop in case of endless conversion 
        
        while(decimal > tolerance && counter < 10) {
            decimal = decimal * base;
            wholeNum = (int)decimal;
            list.add(codeAdder(wholeNum));
            decimal = decimal - wholeNum;
            counter++;
        }
        
        while(!list.isEmpty())
            result = result + list.remove();
            
        return "." + result;
    }
    
    ///helper function for binary to decimal conversion
    public static double biToDeci(double entry) { 
        return biToDeci_int(entry) + biToDeci_fraction(entry);
    }
    
    public static double biToDeci_int(double entry) {
        int decimalRemover = (int)entry;
        String result = String.valueOf(decimalRemover); 
        int stringIterator = result.length() - 1;
        double sum = 0;
        
        for (int i = 0; i < result.length(); i++) { 
            
            if (result.charAt(stringIterator - i) == '1') {
                  sum = sum + Math.pow(2,i);
              }
        }
        return sum;
    }
    
    public static double biToDeci_fraction(double entry) {
        int wholeNumber = (int)entry;
        double decimal = entry - wholeNumber;        
        String result = String.valueOf(decimal); 
        double sum = 0;
        
        for (int i = 2; i < result.length(); i++) { 
            if (result.charAt(i) == '1') {
                  sum = sum + Math.pow(2,(-i + 1));
              }
        }
        return sum; 
    }

    ///returns appropiate charcter for mulitplication and division method 
    public static Character codeAdder(int input) {
            if (input == 0) return '0';
            if (input == 1) return '1';
            if (input == 2) return '2';
            if (input == 3) return '3';
            if (input == 4) return '4';
            if (input == 5) return '5';
            if (input == 6) return '6';
            if (input == 7) return '7';
            if (input == 8) return '8';
            if (input == 9) return '9';
            if (input == 10) return 'A';
            if (input == 11) return 'B';
            if (input == 12) return 'C';
            if (input == 13) return 'D';
            if (input == 14) return 'E';
            if (input == 15) return 'F';
        return null;
    }
    
}
