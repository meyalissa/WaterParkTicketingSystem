/**
 * CSC248
 * GROUP PROJECT OCT 2023 - FEB 2024
 * WATERPARK TICKETING SYSTEM
 * 
 * Application : Queue with Input File
 * 
 * List of Processing : 
 * 1. Calculate and display the total sales for all of the customer's ticket purchased.
 * 2. Search & Update customer ticket's date.
 * 3. Find and display the highest ticket price purchase by the customers.
 * 4. Calculate and display the average of total sales of the ticket of the year based on user input.
 * 5. Remove the existing data stored in the Ticket object by searching the customer's name input by user.
 * 6. Count and display the total number of customers who buy and do nut buy express lane tickets.
 */
import java.util.*;
import java.io.*;
public class AppsQueuewtFile
{
    public static void main(String args[]) throws IOException
    {
        //Declare and create Queue object
        Queue ticketQ= new Queue();
        
        Ticket tickobj;
        Scanner input= new Scanner(System.in);
        insertFile(ticketQ);
        int choice=0; 
        while(choice!=5){
            menuScreen();
            System.out.print("\n Enter option: ");
            choice = input.nextInt();
            if(choice==1){
                Choice1(ticketQ);//function call
            }
            else if (choice==2){
                Choice2(ticketQ);//function call
            }
            else if(choice==3){
                Choice3(ticketQ);//function call
            }
            else if(choice==4){
                Choice4(ticketQ);//function call
            }
            else if (choice==5){
                break;
            }
            else
                System.out.println("\n Option not available");
        }
        System.out.println("System Terminated");
    }
    public static void menuScreen()
    {
        System.out.println("\n==========WATERPARK ADMIN SYSTEM=========\n");
        System.out.println("\t1. Insert Data");
        System.out.println("\t2. Search Ticket");
        System.out.println("\t3. Customer Management");//view details, update, remove
        System.out.println("\t4. Report & Analytics");//count,sum,average,highest,
        System.out.println("\t5. Exit");
    }
    public static void insertFile(Queue ticketQ)
    {
        try{
             //Input file
             FileReader fr=new FileReader("ticket.dat.txt");//***
             BufferedReader br = new BufferedReader(fr);//***
         
             Ticket tickobj;
             String nm,dt;
             int quantity;
         
             StringTokenizer st =null;//***
             String data= br.readLine();//***
             while(data != null )
             {
                 st = new StringTokenizer (data,"*");
                 
                 nm = st.nextToken();
                 dt = st.nextToken();
                 quantity = Integer.parseInt(st.nextToken());
                 int[]age=new int[quantity];
                 for(int p=0;p<quantity;p++)
                 {
                     age[p]=Integer.parseInt(st.nextToken());
                 }
                 String yesno=st.nextToken();
                 boolean xpress= false;
                 if(yesno.equalsIgnoreCase("yes"))
                     xpress =true;
                     
                 tickobj=new Ticket(nm,dt,quantity,age,xpress);
                 ticketQ.enqueue(tickobj);
                 
                 data = br.readLine();
             }
             br.close();
        }
            catch(EOFException eof) //to display a message if an error related to file occur
            {    System.out.println("Problem: "+eof.getMessage());}
            catch(FileNotFoundException e)
            {    System.out.println("Problem: "+e.getMessage());}
            catch(IOException ioe)
            {    System.out.println("Problem: "+ioe.getMessage());}
    }
    public static void Choice1(Queue ticketQ){
        //Inserting Data
        System.out.println("\n==========INSERT NEW DATA=========\n");
        Scanner inputS = new Scanner(System.in);
        Scanner inputN = new Scanner(System.in);
        
        Ticket tickobj;
        String nm,dt;
        int quantity;
        
        System.out.println("\nEnter Name:");
        nm=inputS.nextLine();
        System.out.println("Enter Ticket Date:");
        dt=inputS.nextLine();
        System.out.println("Enter ticket quantity:");
        quantity=inputN.nextInt();
        int[]age=new int[quantity];
        for(int p=0;p<quantity;p++)
        {
             System.out.println("Enter age(s):");
             age[p]=inputN.nextInt();
        }
        System.out.println("Include Xpress lane?:");
        String yesno=inputS.nextLine();
        boolean xpress=false;
        if(yesno.equalsIgnoreCase("yes"))
            xpress= true;
            
        tickobj=new Ticket(nm,dt,quantity,age,xpress);
        ticketQ.addLast(tickobj);
        
        System.out.println("Data have been inserted");
    }
    public static void Choice2(Queue ticketQ){
        //Search Customer Name
        System.out.println("\n==========SEARCH CUSTOMER=========\n");
        Scanner inputS = new Scanner(System.in);
        Ticket found=null;
        
        String search = "yes";
        while (search.equalsIgnoreCase("yes"))
        {
            
            System.out.println("\nEnter name that you want to search: ");
            found = Searching(ticketQ, found); // function call
            if(found!=null)
            {    
                System.out.println("\nCustomer ticket details :" + found.toString());      
            }
            else
                System.out.println("\nSorry name is not found");
                
            System.out.println("\nSearch another name?: ");
            search=inputS.next();
        }
    }
    public static void Choice3(Queue ticketQ){
        Scanner inputS= new Scanner(System.in);
        int choice=0;
        Ticket tickobj;
        
        while(choice !=4)
        {
            System.out.println("\n==========CUSTOMER MANAGEMENT=========\n");
            System.out.println("\t1. View Ticket Details");
            System.out.println("\t2. Update Ticket Date");
            System.out.println("\t3. Remove Ticket Data");
            System.out.println("\t4. Back");
            choice= inputS.nextInt();
            if(choice==1){
                //view Ticket Details
                Display(ticketQ); //function call
            }
            else if(choice==2){
                //Update Ticket Date
                Update(ticketQ);
            }
            else if(choice==3){
                //Remove Ticket Data
                Remove(ticketQ);//function call
            }
            else if(choice==4){
                break;//will go back to Menu
            }
            else 
                System.out.println("\n Option not available");
        }
    }
    public static void Choice4(Queue ticketQ){
        //Reports and Analytics
        Scanner input= new Scanner(System.in);
        int choice=0;
        Ticket tickobj;
        while(choice != 5){
            System.out.println("\n==========REPORTS AND ANALYTICS=========\n");
            System.out.println("\t1. Total Sales");
            System.out.println("\t2. Average Sales");
            System.out.println("\t3. Highest Sales");
            System.out.println("\t4. Number of Express Ticket");
            System.out.println("\t5. Back");
            choice= input.nextInt();
        
            if(choice==1){
                //Total Sales
                Summation(ticketQ);//function call
            }
            else if(choice==2){
                //Average Sales
                Average(ticketQ);//function call
            }
            else if(choice==3){
                //Highest Sales
                Highest(ticketQ);//function call
            }
            else if(choice==4){
                //Display number of Express & Non Xpress Ticket
                Counting(ticketQ);//function call
            }
            else if(choice==5){
                 break;//will go back to Menu
            }
            else 
                System.out.println("\n Option not available");
        }
    }
    public static void Display(Queue ticketQ){
        Ticket tickobj;
        Queue tempQ= new Queue();
        int i=1;
        
        while(!ticketQ.isEmpty())
        {
            tickobj = (Ticket) ticketQ.dequeue();
            System.out.println("\n*******Details customer " + i + " *******"
                            + tickobj.toString());
            tempQ.enqueue(tickobj);
            i++;
        }
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
    }
    public static void Summation(Queue ticketQ){
        Ticket tickobj;
        Queue tempQ= new Queue();
        double totalSale =0.0;
        while(!ticketQ.isEmpty())
        {
            tickobj = (Ticket) ticketQ.dequeue();
            totalSale += tickobj.calcPrice();
            tempQ.enqueue(tickobj);
        }
        //insert back into the original Ticket obj
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
        System.out.println("\nTotal Sales : " + totalSale);
    }
    public static void Update(Queue ticketQ){
        String update = "yes",dateUpdate;
        Scanner inputS = new Scanner(System.in);
        Scanner inputN = new Scanner(System.in);
        Ticket tickobj =null;
        while(update.equalsIgnoreCase("yes"))
            {
                Ticket found=null;//
            
                System.out.println("\nEnter name that you want to update: ");
                found = Searching(ticketQ, found);
                tickobj = found;
                if(found!=null)
                {
                    System.out.println("\nEnter new date: ");
                    dateUpdate=inputS.nextLine();
                    System.out.println("\nTicket date sucessfully updated!!\n");
                    System.out.println("Before update: " +tickobj.getDate());
                    tickobj.setDate(dateUpdate);
                    System.out.println("After update: " +tickobj.getDate());
                }
                else
                {
                    System.out.println("\nSorry name is not found");
                }
    
                System.out.println("\nDo you want to update another ticket date?: ");
                update=inputS.nextLine();
        }
    }
    public static Ticket Searching(Queue ticketQ, Ticket found){
        Scanner inputS = new Scanner(System.in);
        Queue tempQ= new Queue();
        Ticket tickobj;
        found=null;//
        
        String nm1=inputS.nextLine();
            
        while(!ticketQ.isEmpty())
        {
            tickobj=(Ticket) ticketQ.dequeue();
            if(tickobj.getName().equalsIgnoreCase(nm1))
            {
                found =tickobj;                
            }
            tempQ.enqueue(tickobj);
        }
        //insert back into the original Ticket obj
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
        return found;
    }
    public static void Highest(Queue ticketQ){
        Queue tempQ= new Queue();
        Ticket tickobj;
        double highest=0.0;
        String highCust="";
        while(!ticketQ.isEmpty())
        {
            tickobj = (Ticket) ticketQ.dequeue();
            if(tickobj.calcPrice() > highest)
            {
                highest = tickobj.calcPrice();
                highCust = tickobj.toString();
            }
            tempQ.enqueue(tickobj);
        }
        System.out.println("\nCustomer Details that have the highest ticket purchased : \n"
                            + highCust + "Ticket Price : " + highest);
        //insert back into the original Ticket obj
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
    }
    public static void Average(Queue ticketQ){
        Ticket tickobj;
        Queue tempQ= new Queue();
        Scanner input = new Scanner(System.in);
        double totalSaleYear =0.0;
        int count=0;
        String getyear;
        
        System.out.println("\nEnter year :");
        String yearavg=input.next();
        
        
        while(!ticketQ.isEmpty())
        {
            tickobj = (Ticket) ticketQ.dequeue();
            getyear = tickobj.getDate().substring(6,10);
            if(getyear.equalsIgnoreCase(yearavg)) //compare with the year
            {
                totalSaleYear += tickobj.calcPrice();
                count++;
            }
            tempQ.enqueue(tickobj);
        }
        double average = totalSaleYear/count;
        System.out.println("\nAverage sales for the year " + yearavg +" : " + String.format("%.2f",average));
        //insert back into the original Ticket obj
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
    }
    public static void Remove(Queue ticketQ){
        Scanner inputS = new Scanner(System.in);
        Queue tempQ= new Queue();
        Ticket tickobj;
        
        String removed = "yes";
        
        while(removed.equalsIgnoreCase("yes"))
        {
            boolean found=false;//
            
            System.out.println("\nEnter name that you want to removed: ");
            String nm1=inputS.nextLine();
            
            while(!ticketQ.isEmpty())
            {
                tickobj=(Ticket) ticketQ.dequeue();//initialize
                if(tickobj.getName().equalsIgnoreCase(nm1))
                {
                    found = true;
                }
                else
                    tempQ.enqueue(tickobj);
            }
            
            while(!tempQ.isEmpty())  //insert back to the original Queue
            {
                ticketQ.enqueue(tempQ.dequeue());
            }            
            
            if (found)
            {
                System.out.println("\nData have been removed ");
            }
            else
                System.out.println("\nName not found");
            System.out.println("\nDo you want to remove another data?: ");
            removed=inputS.nextLine();
         }
    }
    public static void Counting(Queue ticketQ){
        Ticket tickobj;
        Queue tempQ = new Queue();
        Queue expressLaneList = new Queue();
        Queue normalLaneList = new Queue();
        while(!ticketQ.isEmpty())
        {
            tickobj = (Ticket) ticketQ.dequeue();
            if(tickobj.getXpress())
                expressLaneList.enqueue(tickobj);
            else
                normalLaneList.enqueue(tickobj);
            tempQ.enqueue(tickobj);
        }
        while(!tempQ.isEmpty()) //insert back to the original Queue
        {
                ticketQ.enqueue(tempQ.dequeue());
        }   
        int countX, countnonX;
        countX = countnonX =0;
        while(!expressLaneList.isEmpty())
        {
            tickobj = (Ticket) expressLaneList.dequeue();
            countX ++;
            tempQ.enqueue(tickobj);
        }
        while(!tempQ.isEmpty()) //insert back to the original Queue
        {
            expressLaneList.enqueue(tempQ.dequeue());
        }  
        
        while(!normalLaneList.isEmpty())
        {
            tickobj = (Ticket) normalLaneList.dequeue();
            countnonX ++;
            tempQ.enqueue(tickobj);
        }
        while(!tempQ.isEmpty()) //insert back to the original Queue
        {
            normalLaneList.enqueue(tempQ.dequeue());
        }  
        
        System.out.print("\nThe total customers purchase online ticket: " + (countX+countnonX));
        System.out.print("\nThe total customers purchase express lane: " + countX);
        System.out.println("\nThe total customers does not purchase express lane : " + countnonX);
        
    }
}
