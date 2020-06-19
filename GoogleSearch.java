package googlesearch;
import java.util.Scanner;
public class GoogleSearch {
        public static void main(String[] args){
            String choice = "";
            boolean exit = false;
            
            //Keeps the program running until the exit option is chosen.
            while (exit == false){
                
                //Catches errors.
                try {
                    
                    //Initializes the scanner.
                    Scanner scanner = new Scanner(System.in);
                    
                    //Prints out the options/UI.
                    System.out.println("Google Search - Andreas Sukardi Teja - 2301900416");
                    System.out.println("1. Search");
                    System.out.println("2. Help");
                    System.out.println("3. Exit");
                    
                    //Requests and accepts input for the option the user would like to run.
                    System.out.print("What would you like to do? ");
                    String ch = scanner.nextLine();
                    System.out.print("\n");
                    
                    choice = ch;
                    if (choice.equalsIgnoreCase("search") || choice.equalsIgnoreCase("s") || choice.equals("1")){
                        
                        //Prints out and accepts input for the Search Term and Result Amount.
                        System.out.print("Search Term: ");
                        String search = scanner.nextLine();
                        System.out.print("Result Amount (0 for default): ");
                        int num = scanner.nextInt();
                        
                        //Checks for the input and decides which constructor is used.
                        if (num == 0){
                            Search obj = new Search(search);
                            obj.search();
                        }
                        else if (num > 0){
                            Search obj = new Search(search, num);
                            obj.search();
                        }
                        else{
                            System.out.println("Invalid input, please only input 0 or above for the Result Amount.\n");
                        }
                    }
                    else if (choice.equalsIgnoreCase("help") || choice.equalsIgnoreCase("h") || choice.equals("2")){
                        System.out.println("1/search/s - This option will launch the search and ask for the search term and how many results you'd like.");
                        System.out.println("2/help/h - This option will launch the help.");
                        System.out.println("3/exit/e - This option will exit the program.\n");
                    }
                    
                    //Tried putting this condition in the while but it seems to fail to exit.
                    else if (choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("e") || choice.equals("3")){
                        exit = true;
                    }
                    else {
                        System.out.println("Invalid input, please type 2/help/h for the help.\n");
                    }
                }
                catch(Exception e) {
                    System.out.println("Invalid input, please type 2/help/h for the help.\n");
                }
            }
            System.out.println("Program Exited.");
        }
}