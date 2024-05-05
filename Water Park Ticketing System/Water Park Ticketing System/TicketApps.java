/**
 * CSC305
 * GROUP PROJECT OCT 2023 - FEB 2024
 * WATERPARK TICKETING SYSTEM :
 * SUNWAY LAGOON
 * Application
 */
import java.util.*;
import java.text.DecimalFormat;
public class TicketApps
{
    public static void main(String args[])
    {
        int size = 3;
        Ticket[] tickobj= new Ticket[size];
        Scanner inputS = new Scanner(System.in);
        Scanner inputN = new Scanner(System.in);
        
        String nm,dt;
        int quantity;
        
        //Input
        System.out.println("-------------------------INSERT DATA-------------------------");
        for(int i=0;i<size;i++)
        {
            System.out.print("Enter Name: ");
            nm=inputS.nextLine();
            System.out.print("Enter Date: ");
            dt=inputS.nextLine();
            System.out.print("Enter ticket quantity: ");
            quantity=inputN.nextInt();
            int[]age=new int[quantity];
            for(int p=0;p<quantity;p++)
            {
                
                System.out.print("Enter age(s): ");
                age[p]=inputN.nextInt();
                
            }
            System.out.print("Include Xpress lane?: ");
            String yesno=inputS.nextLine();
            boolean xpress=false;
            if(yesno.equalsIgnoreCase("yes"))
                xpress= true;
            System.out.println("*************************************");
            tickobj[i]=new Ticket(nm,dt,quantity,age,xpress);
        }
        System.out.println("\n-------------------------DISPLAY-------------------------");
        for(int i=0;i<size;i++)
        {
            System.out.println("\n*******Details customer " + (i+1) + " *******" + tickobj[i].toString() + 
                            "Total Price : RM" + tickobj[i].calcPrice());
 
        }
        System.out.println("\n------------------------SUMMATION-------------------------");
        //-------------------summation------------------- 
        double totalSale =0.0;//declaration (summation totalSale)
        for(int i=0;i<size;i++)
        {
            totalSale += tickobj[i].calcPrice();
        }
        
        System.out.println("\nTotal Sales : RM"+totalSale);
        System.out.println("\n-------------------------COUNTING-------------------------");
        //-------------------counting------------------- 
        int countA, countB, countC;
        countA = countB = countC = 0;
        
        for(int i=0;i<size;i++)
        {
            countA ++;
            if ( tickobj[i].getXpress()== true )
            {
                countB ++;
            }
            else
            {
                countC ++;
            }
        }
        
        System.out.print("\nThe total customers purchase online ticket: " + countA);
        System.out.print("\nThe total customers purchase express lane: " + countB);
        System.out.println("\nThe total customers does not purchase express lane : " + countC); 
        
        System.out.println("\n-------------------------MAXIMUM-------------------------");
        //-------------------Maximum------------------- 
        double highest= 0.0;
        int highInd=-1;
        for(int i=0; i<size; i++)
        {
            if(tickobj[i].calcPrice() > highest)
            {
                highest = tickobj[i].calcPrice();
                highInd = i;
            }
        }
        System.out.println("\nCustomer Details that have the highest ticket purchased : \n"
                            + tickobj[highInd].toString() + "Ticket Price : RM" + highest);
        System.out.println("\n-------------------------AVERAGE-------------------------");
        //-------------------Average-------------------
        double totalSale2023=0.0;
        int count2023=0;
        String getyear;
        for(int i=0; i<size; i++)
        {
            getyear = tickobj[i].getDate().substring(6,10);
            if(getyear.equalsIgnoreCase("2023"))
            {
                totalSale2023 += tickobj[i].calcPrice();
                count2023++;
            }
         }
        double average = totalSale2023/count2023;
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("\nAverage sales for the year 2023 : RM" + df.format(average));
        System.out.println("\n-------------------SEARCHING & UPDATE--------------------");
        //-------------------Searching & Update------------------- 
        String update;
        System.out.println("\nDo you want to update date(yes/no)?: ");
        update=inputS.nextLine();
        String dateUpdate, nameSearch;
    
        while(update.equalsIgnoreCase("yes"))
        {
            int found=-1;
            System.out.println("\nEnter name that you want to update: ");
            nameSearch=inputS.nextLine();
            
            for(int i=0;i<size;i++)
            {
                if(tickobj[i].getName().equalsIgnoreCase(nameSearch))
                {
                    found=i;
                    break;
                }
            }

            if(found==-1)
            {
                System.out.println("\nSorry name is not found");
            }
            else
            {
                System.out.println("\nEnter new date: ");
                dateUpdate=inputS.nextLine();
                System.out.println("\nTicket date sucessfully updated!!\n");
                System.out.println("Before update: " +tickobj[found].getDate());
                tickobj[found].setDate(dateUpdate);//
                System.out.println("After update: " + tickobj[found].getDate());//
            }
            System.out.println("\n*******************************");
            System.out.println("\nDo you want to update date(yes/no)?: ");
            update=inputS.nextLine();
        }
        System.out.println("\n-------------------SEARCHING & DELETE--------------------");
        
        //-------------------Searching & Delete------------------- 
        System.out.println("\nDo you want to delete data(yes/no)?: ");
        String delete=inputS.nextLine();
    
        while(delete.equalsIgnoreCase("yes"))
        {
            int found=-1;
            System.out.println("\nEnter name that you want to delete: ");
            String nm2=inputS.nextLine();
            
            for(int i=0;i<3;i++)
            {
                if(tickobj[i]!=null && tickobj[i].getName().equalsIgnoreCase(nm2))
                {
                    found=i;
                    break;
                }
            }

            if(found==-1)
            {
                System.out.println("\nSorry name is not found");
            }
            else
            {
                tickobj[found]=null;
                System.out.println("\nData sucessfully deleted!!\n");
                System.out.println("\nData after deletion: ");
                for(int i=0;i<3;i++)
                {
                    if(tickobj[i]!=null)
                    System.out.println("\n*******Details customer " + (i+1) + "********\n" + tickobj[i].toString() + 
                                "\nTotal Price : " + tickobj[i].calcPrice());
                }
                break;
            }
            System.out.println("\nDo you want to delete data(yes/no)?: ");
            delete=inputS.nextLine();
                
        }
        System.out.println("\n----------------------END OF PROGRAM----------------------");
    }
}
