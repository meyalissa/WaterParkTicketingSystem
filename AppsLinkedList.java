/**
 * CSC248
 * GROUP PROJECT OCT 2023 - FEB 2024
 * WATERPARK TICKETING SYSTEM
 * 
 * Application : LinkedList
 */
import java.util.*;
public class AppsLinkedList
{
    public static void main(String args[])
    {
        //Declare and create LinkedList object
        LinkedList ticketList= new LinkedList();
        
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
            ticketList.addFirst(tickobj);
        }
        
        //Display Ticket purchase
        tickobj = (Ticket) ticketList.getFirst();
        while(tickobj !=null)
        {
            System.out.println("\nDetails : " + tickobj.toString()+
                                "Total Price : " + tickobj.calcPrice());
            tickobj = (Ticket) ticketList.getNext();
        }
        
        //-------------------summation------------------- 
        double totalSale =0.0;
        tickobj = (Ticket) ticketList.getFirst();
        while(tickobj !=null)
        {
            totalSale += tickobj.calcPrice();
            tickobj = (Ticket) ticketList.getNext();
        }
        System.out.println("\nTotal Sales : " + totalSale);
        
        //-------------------Searching------------------- 
        String dateUpdate;
        System.out.println("\nDo you want to update date?: ");
        String update=inputS.nextLine();
        
        
        while(update.equalsIgnoreCase("yes"))
        {
            Ticket found=null;//
            
            System.out.println("\nEnter name that you want to update: ");
            String nm1=inputS.nextLine();
            
            tickobj=(Ticket) ticketList.getFirst();//initialize
            while(tickobj!=null)
            {
                if(tickobj.getName().equalsIgnoreCase(nm1))
                {
                    found=tickobj;
                    break;
                }
                else
                    tickobj = (Ticket) ticketList.getNext();
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
         }
        
        
        
        //-------------------Highest------------------- 
        double highest=0.0;
        String highCust="";
        tickobj = (Ticket) ticketList.getFirst();
        while(tickobj !=null)
        {
            if(tickobj.calcPrice() > highest)
            {
                highest = tickobj.calcPrice();
                highCust = tickobj.toString();
            }
            tickobj = (Ticket) ticketList.getNext();
        }
        System.out.println("\nCustomer Details that have the highest ticket purchased : \n"
                            + highCust + "Ticket Price : " + highest);
        
        //-------------------Average-------------------
        double totalSale2023 =0.0;
        int count=0;
        String getyear;
        tickobj = (Ticket) ticketList.getFirst();
        while(tickobj !=null)
        {
            getyear = tickobj.getDate().substring(6,10);
            if(getyear.equalsIgnoreCase("2023"))
            {
                totalSale2023 += tickobj.calcPrice();
                count++;
            }
            tickobj = (Ticket) ticketList.getNext();
        }
        double average = totalSale2023/count;
        System.out.println("\nAverage sales fot the year 2023 : " + average);
    
        
        //-------------------Remove------------------- 
        
        System.out.println("\nDo you want to remove data: ");
        String removed=inputS.nextLine();
        
        LinkedList tempList = new LinkedList();
        while(removed.equalsIgnoreCase("yes"))
        {
            boolean found=false;//
            
            System.out.println("\nEnter name that you want to removed: ");
            String nm1=inputS.nextLine();
            
            tickobj=(Ticket) ticketList.removeFirst();//initialize
            while(tickobj!=null)
            {
                if(tickobj.getName().equalsIgnoreCase(nm1))
                {
                    System.out.println("\nData have been removed ");
                    found = true;
                }
                else
                    tempList.addFirst(tickobj);
                tickobj=(Ticket) ticketList.removeFirst();
            }
            
            tickobj=(Ticket) tempList.removeFirst();//initialize
            while(tickobj!=null)
            {
                ticketList.addFirst(tickobj);
                tickobj=(Ticket) tempList.removeFirst();
            }
            
            if (found)
            {
                System.out.println("\nUpdated data: ");
                //Display Ticket purchase
                tickobj = (Ticket) ticketList.getFirst();
                while(tickobj !=null)
                {
                    System.out.println("\nDetails : " + tickobj.toString()+
                                    "Total Price : " + tickobj.calcPrice());
                    tickobj = (Ticket) ticketList.getNext();
                }
            }
            else
                System.out.println("\nName not found");
            System.out.println("\nDo you want to removed data: ");
            removed=inputS.nextLine();
         }
        
        
        //-------------------counting------------------- 
        LinkedList expressLaneList = new LinkedList();
        LinkedList normalLaneList = new LinkedList();
        tickobj = (Ticket) ticketList.getFirst();
        while(tickobj !=null)
        {
            if(tickobj.getXpress())
                expressLaneList.addFirst(tickobj);
            else
                normalLaneList.addFirst(tickobj);
            tickobj = (Ticket) ticketList.getNext();
        }
        //System.out.println("\nCustomer details who purchase Express Lane : " + expressLaneList);
        //System.out.println("\nCustomer details who does not purchase Express Lane : " + normalLaneList);
        
        int countB, countC;
        countB = countC =0;
        tickobj = (Ticket) expressLaneList.getFirst();
        while(tickobj != null)
        {
            countB ++;
            tickobj = (Ticket) expressLaneList.getNext();
        }
        tickobj = (Ticket) normalLaneList.getFirst();
        while(tickobj != null)
        {
            countC ++;
            tickobj = (Ticket) normalLaneList.getNext();
        }

        
        System.out.print("\nThe total customers purchase online ticket: " + countB+countC);
        System.out.print("\nThe total customers purchase express lane: " + countB);
        System.out.println("\nThe total customers does not purchase express lane : " + countC); 

    }
}
