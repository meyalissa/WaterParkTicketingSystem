
/**
 * CSC248
 * GROUP PROJECT OCT 2023 - FEB 2024
 * WATERPARK TICKETING SYSTEM
 * 
 * Application :Queue
 */
import java.util.*;
public class AppsQueue
{
    public static void main(String args[])
    {
        //Declare and create LinkedList object
        Queue ticketQ= new Queue();
        Queue tempQ= new Queue();
        //Input
        Scanner inputS = new Scanner(System.in);
        Scanner inputN = new Scanner(System.in);
        
        Ticket tickobj;
        String nm,dt;
        int quantity;
        
        for(int i=0;i<3;i++)
        {
            System.out.println("\nEnter Name:");
            nm=inputS.nextLine();
            System.out.println("\nEnter Date:");
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
            ticketQ.enqueue(tickobj);
        }
        
        //example :
        //Display Ticket purchase
        while(!ticketQ.isEmpty())
        {
            tickobj = (Ticket) ticketQ.dequeue();
            System.out.println("\nDetails : " + tickobj.toString() +
                                "Total Price : " + tickobj.calcPrice());
            tempQ.enqueue(tickobj);
        }
        //insert back into the original Ticket obj
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
        
        //-------------------Summation-------------------
        double totalSale=0.0;
        while(!ticketQ.isEmpty())
        {
            tickobj = (Ticket) ticketQ.dequeue();
            totalSale += tickobj.calcPrice();
            tempQ.enqueue(tickobj);
        }
        System.out.println("\nTotal sale : " + totalSale);

        //insert back into the original Ticket obj
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
    
        //----------------Highest-------------------
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
        System.out.println("Customer Details that have the highest ticket purchased : \n"
                            + highCust + "Ticket Price : " + highest);
        //insert back into the original Ticket obj
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
        
        //-------------------Average-------------------
        double totalSale2023 =0.0;
        int count=0;
        String getyear;
        while(!ticketQ.isEmpty())
        {
            tickobj = (Ticket) ticketQ.dequeue();
            getyear = tickobj.getDate().substring(6,10);
            if(getyear.equalsIgnoreCase("2023"))
            {
                totalSale2023 += tickobj.calcPrice();
                count++;
            }
            tempQ.enqueue(tickobj);
        }
        double average = totalSale2023/count;
        System.out.println("\nAverage sales fot the year 2023 : " + average);
        //insert back into the original Ticket obj
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
        
        //-------------------Remove-------------------
        Queue expressLaneQ = new Queue();
        Queue normalLaneQ = new Queue();
        while(!ticketQ.isEmpty())
        {
            tickobj = (Ticket) ticketQ.dequeue();
            if(tickobj.getXpress())
                expressLaneQ.enqueue(tickobj);
            else
                normalLaneQ.enqueue(tickobj);
            tempQ.enqueue(tickobj);
        }
        //insert back into the original Ticket obj
        while(!tempQ.isEmpty())
        {
            ticketQ.enqueue(tempQ.dequeue());
        }
        System.out.println("\nCustomer Info who buy Express Lane : " + expressLaneQ );
        System.out.println("\nCustomer Info who buy Express Lane : " + normalLaneQ );
        
        //-------------------counting-------------------
        int countB, countC;
        countB = countC =0;
        //count express Lane
        while(!expressLaneQ.isEmpty())
        {
            tickobj = (Ticket) expressLaneQ.dequeue();
            countB ++;
            tempQ.enqueue(tickobj);
        }
        while(!tempQ.isEmpty())
        {
            expressLaneQ.enqueue(tempQ.dequeue());
        }
        //count normal Lane
        while(!normalLaneQ.isEmpty())
        {
            tickobj = (Ticket) normalLaneQ.dequeue();
            countB ++;
            tempQ.enqueue(tickobj);
        }
        while(!tempQ.isEmpty())
        {
            normalLaneQ.enqueue(tempQ.dequeue());
        }
        
        System.out.println("\nThe total customers book online ticket: " + countB+countC);
        System.out.println("The total customers ( Express Lane Ticket --> Yes ) : " + countB);
        System.out.println("The total customers ( Express Lane Ticket --> No ) : " + countC); 

        //-------------------Searching------------------- 
        String dateUpdate;
        System.out.println("\nDo you want to update date?: ");
        String update=inputS.nextLine();
        
        
        while(update.equalsIgnoreCase("yes"))
        {
            Ticket found =null;
            
            System.out.println("\nEnter name that you want to update: ");
            String nm1=inputS.nextLine();
            
            tickobj =null;//initialize
            while(!ticketQ.isEmpty())
            {
                
                tickobj=(Ticket) ticketQ.dequeue();
                if(tickobj.getName().equalsIgnoreCase(nm1))
                {
                    found=tickobj;
                }
                tempQ.enqueue(tickobj);
            }
            
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
    
            System.out.println("\nDo you want to update date?: ");
            update=inputS.nextLine();
            while(!tempQ.isEmpty())
            {
                ticketQ.enqueue(tempQ.dequeue());
            }
         }
         
        //-------------------Remove------------------- 
        
        System.out.println("\nDo you want to remove data: ");
        String removed=inputS.nextLine();
        
        LinkedList tempList = new LinkedList();
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
                    System.out.println("\nData have been removed ");
                    found = true;
                }
                else
                    tempQ.enqueue(tickobj);
            }
            
            while(!tempQ.isEmpty())
            {
                ticketQ.enqueue(tempQ.dequeue());
            }
            
            if (found)
            {
                System.out.println("\nUpdated data: ");
                //Display Ticket purchase
                while(!ticketQ.isEmpty())
                {
                    tickobj = (Ticket) ticketQ.dequeue();
                    System.out.println("\nDetails : " + tickobj.toString() +
                                    "Total Price : " + tickobj.calcPrice());
                    tempQ.enqueue(tickobj);
                }
            }
            else
                System.out.println("\nName not found");
            
            System.out.println("\nDo you want to removed data: ");
            removed=inputS.nextLine();
            while(!tempQ.isEmpty())
            {
                ticketQ.enqueue(tempQ.dequeue());
            }
         }
        
    }
}
