import java.util.InputMismatchException;
import java.util.Scanner; 

public class Test {

    private int shift = 0;

    public static void main (String [] args) {
    

        String message = "";
        Scanner optionInput = new Scanner (System.in);
        int option = 0; 
       

        Test main = new Test ();
        String secondMessage = ""; 

        System.out.println ("Welcome to Caesar Cipher!"); 
        System.out.println ("Please select one of the following options"); 
        System.out.println ("1 - Encrypt a message"); 
        System.out.println ("2 - Decrypt a message"); 
        System.out.println ("3 - Brute Force"); 
       
        System.out.print ("\n\nEnter a choice: ");

         try {
        
            option =  optionInput.nextInt(); 
            if (!(option >= 1 && option <= 3)) {
                throw new InputMismatchException (); 
            }
         } catch (InputMismatchException e) {
             System.out.println ("Invalid Option"); 
             System.exit(-1);
         }

         System.out.print ("\nEnter you message: ");
         message = new Scanner(System.in).nextLine(); 
        

        if (option == 1) {
    
            try {
                System.out.print("\nEnter the key number (1 - 26): ");
                main.shift = optionInput.nextInt(); 
                if (!(main.shift >= 1 && main.shift <= 26)) {
                    throw new InputMismatchException();
                }
                secondMessage =  main.encrypt(message);
                System.out.println("Your translated message is: " + secondMessage + "\n");

            } catch (InputMismatchException e) {
                System.out.println("Invalid key number");
                System.exit(-1);
            }

        } else if (option == 2) {

            try {     
                if (!(main.shift >= 1 && main.shift <= 26)) {
                    throw new InputMismatchException();
                }
                System.out.print("\nEnter the key number (1 - 26): ");
                main.shift = optionInput.nextInt(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid key number");
                System.exit(-1);
            }

            secondMessage =  main.decrypt(message);
            System.out.println("Your translated message is: " + secondMessage+ "\n"); 

        } else if (option == 3) { 
            main.bruteForce(message);
        }

    }


    public String encrypt(String message) {
        String secondMessage = ""; 

        for (char mes: message.toCharArray()) {

            if ((int)mes >= 97 && (int)mes <= 122) {
               secondMessage += lowerCaseShift(mes); 
            }  else if ((int)mes >= 65 && (int)mes <= 90) {
                secondMessage += upperCaseShift(mes);             
            } else  {
                secondMessage += Character.toString (mes); 
            }
        }
        return secondMessage; 
    }

    public String decrypt (String message) {
        String secondMessage = ""; 
        for (char mes: message.toCharArray()) {
            if ((int)mes >= 97 && (int)mes <= 122) {
               secondMessage += decryptlowerCaseShift(mes); 
            }  else if ((int)mes >= 65 && (int)mes <= 90) {
                secondMessage += decryptUpperCaseShift(mes);             
            } else  {
                secondMessage += Character.toString (mes); 
            }
        }
        return secondMessage; 
    }

    public void bruteForce (String message){
        System.out.println("Your translated message is: ");
        String secondMessage = ""; 

        for (shift = 1; shift<= 26 ; shift++) {
            secondMessage =  decrypt(message);
            System.out.println(shift + " " + secondMessage+ "\n");
        }
    }

    public  String upperCaseShift(char mes) {
        int c = (int)mes + shift; 
        if (c > 90) {
            c = c - 90 + 64; 
            return Character.toString(c); 

        } else {
            return Character.toString ( (char)(char)mes + shift);
        }

    }

    public String lowerCaseShift(char mes) {
        int c = (int)mes + shift; 
        if (c > 122) {
            c = c - 122 + 96; 
            return Character.toString(c); 
        } else {
            return Character.toString ( (char)(char)mes + shift);
        }


    }

    public String decryptlowerCaseShift (char mes) {

        int c = (int)mes - shift; 
        if (c < 97) {
            c = 122 - (96 - c); 
            return Character.toString(c); 

        } else {
            return Character.toString ( (char)(char)mes - shift);
        }

    }

    public  String decryptUpperCaseShift(char mes) {
        int c = (int)mes - shift; 
        if (c < 65) {
            c = 90 - (64 - c);  
            return Character.toString(c); 

        } else {
            return Character.toString ( (char)(char)mes - shift);
        }

    }

}

