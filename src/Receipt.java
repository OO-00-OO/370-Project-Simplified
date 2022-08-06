public class Receipt {
    public static float ReceiptTotalPrice;
    public static int ReceiptTotalQuantity;
    public static String[] ReceiptTotalItems = new String[25];

    public int ItemsCountInRecipt()
    {
        int ItemsCount = 0;
        while(ReceiptTotalItems[ItemsCount] != null)
        {
            ItemsCount++;
        }
        return ItemsCount;
    }


    public void PrintReceipt()
    {
        // print the recipt
        System.out.println("");
        System.out.println("");
        System.out.println("************************************************************************************************************************************************");
        System.out.println("Your Order:");
        System.out.println("Item Name  -  Price  -  Quantity");
        int i = 0;
        while(ReceiptTotalItems[i] != null)
        {
            System.out.println((i+1) + ". " + ReceiptTotalItems[i]);
            i++;
        }

        System.out.println("");
        System.out.println("ORDER TOTAL :  $" + ReceiptTotalPrice + " -  Q." + ReceiptTotalQuantity + " items");
        System.out.println("Please come again!");
        System.out.println("************************************************************************************************************************************************");
        System.out.println("");
        System.out.println("");
    }
}
