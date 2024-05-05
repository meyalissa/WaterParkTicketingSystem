/**
 * CSC248
 * GROUP PROJECT OCT 2023 - FEB 2024
 * WATERPARK TICKETING SYSTEM
 * 
 * Application : LinkedList
 */
import java.util.*;
import java.io.*;
public class AppsOutput
{
    public static void main(String args[]) throws IOException
    {
        //Declare and create LinkedList object
        LinkedList ticketList= new LinkedList();
        Ticket tickobj;
        Scanner input= new Scanner(System.in);
        insertFile(ticketList);
        
         try{
         FileWriter fw = new FileWriter("output.txt");
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter pw= new PrintWriter(bw);
         
         pw.println("Data has been saved into output.txt");
         tickobj = (Ticket) ticketList.getFirst();
         while(tickobj!=null)
         {
            pw.println(tickobj.toString());
            tickobj = (Ticket) ticketList.getNext();
         }   
         
         
         pw.close();
        }
         catch(EOFException eof) //to display a message if an error related to file occur
        {    System.out.println("Problem: "+eof.getMessage());}
        catch(FileNotFoundException e)
        {    System.out.println("Problem: "+e.getMessage());}
        catch(IOException ioe)
        {    System.out.println("Problem: "+ioe.getMessage());}
        finally
        { System.out.println("System Terminated");}
    }
    public static void insertFile(LinkedList ticketList)
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
                 ticketList.addFirst(tickobj);
                 
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
}