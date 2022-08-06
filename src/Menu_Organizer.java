import java.io.*;
import java.util.*;

public class Menu_Organizer {
    static Receipt receipt = new Receipt();
    public static void CallMenu() {
        CallMainMenu();
        MenuSelection();
    }


    private static void CallMainMenu() {
        System.out.println("Welcome to Our Restaurant");
        System.out.println("");
        System.out.println("Plese select an option");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.println("4. Desert");
        System.out.println("5. Drinks");
        System.out.println("");
        System.out.println("");
        System.out.println("0. Place Order");
        System.out.println("");
        System.out.println("0. Exit");

    }

    // The Five Variables That the .txt File Will be Read Into
    static int [] Item_Index = new int[5];
    static String [] Item_Name = new String[5];
    static float [] Item_Price = new float[5];
    static String [] Item_Description = new String[5];
    static String [] Item_INFO_Combined= new String[5];
    // Write a method to read menu from a file and store it in an array
    
    // Stores all the data in a single pritable format
    private static String Items_INFO_Combined_Format(int itemIndex, String itemName, Float itemPrice , String itemDescription)
    {
        String item = itemIndex + ". " + itemName + " - " + itemPrice + " - " + itemDescription;
                 
         
        return item;
    }

    static void ReadFromFile(String FileName){
        String SectionReader = "";
        try
        {
            //Scanner input = new Scanner(new File("."+System.getProperty("path.separator")+"Breakfast.txt"));
            Scanner input = new Scanner(new File(FileName));
            input.useDelimiter(";");
            
            for (int Line = 0; Line < 5 ; Line++)
            {
                for(int Section = 0; Section < 4; Section++)
                {
                    if (Section == 0)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Index : "  + SectionReader);
                        Item_Index[Line] = Integer.parseInt(SectionReader);
                    }
                    else if (Section == 1)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Name: "  + SectionReader);
                        Item_Name[Line] = SectionReader;
                    }
                    else if (Section == 2)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Price : "  + SectionReader);
                        Item_Price[Line] = Float.parseFloat(SectionReader);
                    }
                    else if (Section == 3)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Description : "  + SectionReader);
                        Item_Description[Line] = SectionReader;
                    }
                    Item_INFO_Combined[Line] = Items_INFO_Combined_Format(Item_Index[Line], Item_Name[Line], Item_Price[Line], Item_Description[Line]);
                }
                input.nextLine();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        PrintMenu();
    }
    // Prints the Menu


    // Write a method to display the menu
    private static void PrintMenu()
    {
        for (int i = 0; i < Item_INFO_Combined.length; i++)
        {
            System.out.println(Item_INFO_Combined[i]);
        }
        System.out.println("Press 9 : To Return to Category Order Menu");
        System.out.println("Press 0 : To Exit");
        System.out.println("");
        ItemSelection();
        
    }

    private static String Receipt_Format(String ItemName, float ItemPrice, int ItemQuantity)
    {
        String Receipt_Format_Item = "";
        Receipt_Format_Item += ItemName + " - " + "$"+ ItemPrice + " - "  + "Q. "+ ItemQuantity + "\n";
        return Receipt_Format_Item;
    }
    static float TotalPrice = 0.0f;
    static int TotalQuantity = 0;
    static String[] Recipt_ItemsList = new String[10];

    private static void Send_Items_To_Recipt()
    {
        // Check How many items in the recipt array
       int Items_in_Recipt_Counter = receipt.ItemsCountInRecipt();
       int initalCounter = 0;

       //If the item is not in the recipt, add it to the recipt start from 0
       if(Items_in_Recipt_Counter == 0)
       {
        initalCounter = 0;
           while(Recipt_ItemsList[initalCounter] != null)
           {
               receipt.ReceiptTotalItems[initalCounter] = Recipt_ItemsList[initalCounter];
               initalCounter++;
           }
       }
       // if there are items already on the recipt, add the new item to the next empty slot
       else
       {
            initalCounter = 0;
            while(Recipt_ItemsList[initalCounter] != null)
            {
                receipt.ReceiptTotalItems[Items_in_Recipt_Counter] = Recipt_ItemsList[initalCounter];
                Items_in_Recipt_Counter++;
                initalCounter++;
            }
       }
    }
    
    private static void ItemSelection()
    {
        TotalPrice = 0.0f;
        TotalQuantity = 0;
        Recipt_ItemsList = new String[10];

        System.out.println("This is what in Array[0] : " + Recipt_ItemsList[0]);


        int ItemsSlected = 0;
        Scanner Reader = new Scanner (System.in);  // Reading from System.in
        System.out.print("Please Enter The Digit that correspondence to what you want: ");
        int b = Reader.nextInt();

        while (b != 0)
        {

            if (b== 9)
            {
                System.out.println("");
                System.out.println("Returning to Category Order Menu");
                receipt.ReceiptTotalPrice += TotalPrice;
                receipt.ReceiptTotalQuantity += TotalQuantity;
                Send_Items_To_Recipt();
                CallMenu();

            }
            // Printers whatever the User ask and the quantity of it. Then it asks again
            else if (b >= 1 && b <= 5)
            {

                float selectedItem_Price = 0.0f;
                System.out.println("You have selected " + Item_Name[b-1]);
        
                // quantity of the item
                System.out.print("Please Enter The Quantity: ");
                int quantity = Reader.nextInt();

                System.out.println("");
                System.out.println("You have selected " + quantity + " " + Item_Name[b-1] + "(s)");
                
                // Append Price To Class Total Price
                selectedItem_Price = Item_Price[b-1] * quantity;
                TotalPrice += selectedItem_Price;
                TotalQuantity += quantity;
               
               
                // Add Items to Receipt Format
                Recipt_ItemsList[ItemsSlected] = Receipt_Format(Item_Name[b-1], Item_Price[b-1], quantity);
                ItemsSlected++;

                System.out.println("");
            }
            else
            {
                System.out.println("You have selected an invalid option");
                System.out.println("");
            }
            System.out.print("Please Enter The Digit that correspondence to what you want: ");
            b = Reader.nextInt();
        }
     

    }

    private static void MenuSelection()
    {
        Scanner menuScanner = new Scanner(System.in);
        System.out.println("Please Select an Option");
        int selection = menuScanner.nextInt();
        while (selection != 0)
        {
            if (selection == 1)
            {
                System.out.println("You have selected Breakfast");
                System.out.println("");
                ReadFromFile("Breakfast_Menu.txt");
                
            }
            else if (selection == 2)
            {
                System.out.println("You have selected Lunch");
                System.out.println("");
                ReadFromFile("Lunch_Menu.txt");
            }
            else if (selection == 3)
            {
                System.out.println("You have selected Dinner");
                System.out.println("");
                ReadFromFile("Dinner_Menu.txt");
            }
            else if (selection == 4)
            {
                System.out.println("You have selected Drinks");
                System.out.println("");
                ReadFromFile("Drinks_Menu.txt");
            }
            else if (selection == 5)
            {
                System.out.println("You have selected Desserts");
                System.out.println("");
                ReadFromFile("Desserts_Menu.txt");
            }
            else  if ( selection == 0)
            {
                break;
            }
            else 
            {
                System.out.println("You have selected an invalid option");
                System.out.println("");
            }
            System.out.println("Please Select an Option");
            selection = menuScanner.nextInt();

        }
        if ( selection == 0)
        {
            System.out.println("Your Order Has Been Confirmed");
            receipt.PrintReceipt();
        }
        if (selection == 9)
        {
            System.out.println("Your Order Has Been Cancelled");
             System.out.println("We Hope You Come Back Soon");

        }

    }


}
