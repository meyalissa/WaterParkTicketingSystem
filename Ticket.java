/**
 * CSC305
 * GROUP PROJECT OCT 2023 - FEB 2024
 * WATERPARK TICKETING SYSTEM :
 * SUNWAY LAGOON
 * CLASS
 */
public class Ticket
{
    private String name;
    private String date;
    private int quantity;
    private int[] age;
    private boolean xpress;
    
    public Ticket(String name, String date, int quantity, int[] age, boolean xpress) 
    {
        this.name = name;
        this.date = date;
        this.quantity = quantity;
        this.age = new int[quantity];
        for(int i=0;i<quantity;i++)
        {
            this.age[i] = age[i];
        }
        this.xpress = xpress;
    }
    
    public void setName(String name){this.name=name;}
    public void setDate(String date){this.date=date;} 
    public void setQuantity(int quantity){this.quantity=quantity;}
    public void setAge(int[] age)
    {
        for(int i=0;i<quantity;i++)
        {
            this.age[i]=age[i];
        }
    }
    public void setXpress(boolean xpress){this.xpress=xpress;}
     
    public String getName(){return name;}
    public String getDate(){return date;}
    public int getQuantity(){return quantity;}
    public int getAge(int ind){return age[ind];}
    public boolean getXpress(){return xpress;}
    
    public String category()
    {
        int child=0,adult=0,senior=0;
        for(int i=0;i<quantity;i++)
        {
            if(age[i]<12)
                child++;
            else if(age[i]>=12 && age[i]<60)
                adult++;
            else
                senior++;
        }
        String dpChild="",dpAdult="",dpSenior="";
        if(child!=0)
            dpChild = "\n" +child + "x child";
        if (adult!=0)
            dpAdult = "\n" +adult + "x Adult";
        if (senior!=0)
            dpSenior= "\n" +senior + "x Senior Citizen";
        return ( dpChild +
                 dpAdult +
                 dpSenior);
    }
    public String xpressLane()
    {
        String value;
        if(xpress)
            return value = "yes";
        else
            return value = "no";
    }
    public double calcPrice()
    {
        double priceSum=0;
        for(int i=0; i<quantity; i++)
        {
            if(age[i]<12) //child
                priceSum += 135;
            else if(age[i]>=12 && age[i]<60) //adult
                priceSum += 160;
            else // senior citizen
                priceSum += 135;
        }
        if(xpress)
            priceSum += 80;
        return priceSum;
    }
    public String toString()
    {
        return ("\nName:" +name + 
                "\nTicket Date:" +date +
                "\nQuantity: " + category() +
                "\nTotal Quantity : " + quantity +
                "\nExpress Lane:" + xpressLane() +"\n");
    }
}