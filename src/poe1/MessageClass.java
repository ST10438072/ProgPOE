/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe1;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
/**
 *
 * @author lab_services_student
 */
public class MessageClass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        String correctUsername = "admin";
        String correctPassword = "1234";

        // LOGIN
        System.out.println("===== LOGIN =====");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        // CHECK LOGIN
        if (username.equals(correctUsername) && password.equals(correctPassword)) {

            System.out.println("\nWelcome " + username + "!");
            System.out.println("Login successful.\n");
            
             // Ask user how many messages they want to send
            System.out.print("Enter the number of messages you want to send: ");
            int maxMessages = input.nextInt();
            input.nextLine(); // clear buffer

            int sentMessages = 0;
            int menuOption = 0;

            do {

                // MAIN MENU
                System.out.println("===== MAIN MENU =====");
                System.out.println("1. Send Message");
                System.out.println("2. Show Recently Sent Messages");
                System.out.println("3. Show JSON File");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                menuOption = input.nextInt();
                input.nextLine(); // clear buffer

                // OPTION 1: SEND MESSAGE
                if (menuOption == 1) {

                    // Check if limit reached
                    if (sentMessages >= maxMessages) {

                        System.out.println("\nYou have reached your message limit.");
                        System.out.println("No more messages can be sent.\n");

                    } else {

                        // Generate unique message ID
                        int messageID = 100000 + random.nextInt(900000);

                        // Recipient
                        System.out.print("Enter recipient name: ");
                        String recipient = input.nextLine();

                        // Message
                        System.out.print("Enter your message (max 250 characters): ");
                        String message = input.nextLine();

                        // Validate message length
                        if (message.length() > 250) {

                            System.out.println("Message exceeds 250 characters.\n");

                        } else {

                            // Display details
                            System.out.println("\n===== MESSAGE DETAILS =====");
                            System.out.println("Message ID: " + messageID);
                            System.out.println("Recipient: " + recipient);
                            System.out.println("Message: " + message);

                            // Send or store menu
                            System.out.println("\nChoose an option:");
                            System.out.println("1. Send Message Now");
                            System.out.println("2. Store Message To Send Later");

                            int messageOption = input.nextInt();
                            input.nextLine(); // clear buffer

                            // SEND MESSAGE
                            if (messageOption == 1) {

                                sentMessages++;

                                System.out.println("\nMessage successfully sent.");
                                System.out.println("Messages sent: " 
                                        + sentMessages + "/" + maxMessages + "\n");

                            }

                            // STORE MESSAGE
                            else if (messageOption == 2) {

                                try {

                                    FileWriter writer = new FileWriter("messages.json", true);

                                    writer.write("{");
                                    writer.write("\"MessageID\":\"" + messageID + "\",");
                                    writer.write("\"Recipient\":\"" + recipient + "\",");
                                    writer.write("\"Message\":\"" + message + "\"");
                                    writer.write("}\n");

                                    writer.close();

                                    sentMessages++;

                                    System.out.println("\nMessage successfully stored.");
                                    System.out.println("Saved to messages.json");
                                    System.out.println("Messages processed: " 
                                            + sentMessages + "/" + maxMessages + "\n");

                                } catch (IOException e) {

                                    System.out.println("Error saving message.");
                                }

                            }

                            // INVALID OPTION
                            else {

                                System.out.println("\nInvalid option.\n");
                            }
                        }
                    }

                }

                // OPTION 2: SHOW RECENT MESSAGES
                else if (menuOption == 2) {

                    System.out.println("\nFeature not available yet.");
                    System.out.println("Coming soon :)\n");

                }

                // OPTION 3: SHOW JSON FILE
                else if (menuOption == 3) {

                    try {

                        File file = new File("messages.json");

                        if (file.exists()) {

                            Scanner fileReader = new Scanner(file);

                            System.out.println("\n===== JSON FILE CONTENT =====");

                            while (fileReader.hasNextLine()) {

                                System.out.println(fileReader.nextLine());
                            }

                            fileReader.close();
                            System.out.println();

                        } else {

                            System.out.println("\nmessages.json file not found.\n");
                        }

                    } catch (IOException e) {

                        System.out.println("Error opening JSON file.");
                    }
                }

                // OPTION 4: EXIT
                else if (menuOption == 4) {

                    System.out.println("\nExiting program...");
                    System.out.println("Goodbye!");
                }

                // INVALID OPTION
                else {

                    System.out.println("\nInvalid option. Try again.\n");
                }

            } while (menuOption != 4);

        } else {

            System.out.println("\nIncorrect username or password.");
            System.out.println("Access denied.");
        }

        input.close();
    }
}
