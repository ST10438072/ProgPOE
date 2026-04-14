/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poe1;
import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */

public class POE1 {

    /**
     * @param args the command line arguments
     */
 public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
      // Get user details
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();
        
        System.out.println("Captured Password: " + password);

        System.out.print("Enter South African phone number (+27): ");
        String phone = input.nextLine();
        
        System.out.println("Captured Phone Number: " + phone);

       

        System.out.println("\n--- Login ---");

// Login attempt
System.out.print("Enter username: ");
String loginUsername = input.nextLine();

System.out.print("Enter password: ");
String loginPassword = input.nextLine();

// Validation
boolean validUsername = UserLoginClass.checkUserName(username);
boolean validPassword = UserLoginClass.checkPasswordComplexity(password);
boolean validPhone = UserLoginClass.checkCellPhoneNumber(phone);

if (validUsername && validPassword && validPhone) {

    // Register user
    String message = UserLoginClass.registerUser(
            firstName, lastName, username, password, phone);

    // Show registration result
    System.out.println("\n--- Registration ---");
    System.out.println(message);

    // Proceed to login ONLY if registration succeeded
    if (message.contains("success")) {   // adjust depending on your method

        boolean loginStatus = UserLoginClass.loginUser(loginUsername, loginPassword);

        System.out.println("\n--- Login Result ---");
        System.out.println(UserLoginClass.returnLoginStatus(loginStatus));

    } else {
        System.out.println("Login skipped due to registration failure.");
    }

} else {
    System.out.println("\nRegistration failed due to invalid input.");
}
        
        input.close();
    }
}

