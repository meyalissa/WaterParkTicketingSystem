// Group project
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
struct Ticket
{
    char name[30];
    char date[10]; //date format : 00/00/2000
    int quantity;
    int* age;
    bool xpress;
};

void display(struct Ticket t);
float calcPrice(struct Ticket t);

int main()
{
    int size = 3;
    struct Ticket tickobj[3];
    char yesno[4];
    int i,p;
    printf("-----------------------INSERT DATA-----------------------\n");
    for(i=0;i<size;i++)
    {
       printf("Enter Name : ");
       scanf(" %[^\n]", tickobj[i].name);
       printf("Enter Date : ");
       scanf("%s", tickobj[i].date);
       printf("Enter ticket quantity: ");
       scanf("%d", &tickobj[i].quantity);
       
       tickobj[i].age = (int*) malloc(tickobj[i].quantity * sizeof(int));
       for(p=0;p<tickobj[i].quantity;p++)
       {
            printf("Enter age(s): ");
            scanf("%d", &tickobj[i].age[p]);
       }
       printf("Include Express Lane(yes/no)?: ");
       scanf("%s", yesno);
        if(strcmp(yesno, "yes")==0)
            tickobj[i].xpress = 1;
        else
            tickobj[i].xpress = 0;
            
        printf("****************************\n");
    }
    
    printf("\n-------------------------DISPLAY-------------------------\n");
    for(i=0;i<size;i++)
    {
        printf("\n********Details customer %d ********", i+1);
        display(tickobj[i]);
        printf("Total Price : %.2f\n", calcPrice(tickobj[i]));
    }
    printf("\n------------------------SUMMATION-------------------------\n");
     //-------------------summation------------------- 
    float totalSale =0.0;//declaration (summation totalSale)
    for(i=0;i<size;i++)
    {
        totalSale += calcPrice(tickobj[i]);
    }
    
    printf("\nTotal Sales : RM%.2f\n", totalSale);
    printf("\n-------------------------COUNTING-------------------------\n");
    //-------------------counting------------------- 
    int countA, countB, countC;
    countA = countB = countC = 0;
    
    for(i=0;i<size;i++)
    {
        countA++;
        if ( tickobj[i].xpress == true )
            {
                countB ++;
            }
            else
            {
                countC ++;
            }
    }
    printf("\nThe total customers book online ticket: %d", countA);
    printf("\nThe total customers purchase express lane : %d", countB);
    printf("\nThe total customers does not purchase express lane : %d", countC); 
    printf("\n-------------------------MAXIMUM-------------------------\n");
    //-------------------Maximum------------------- 
    float highest= 0.0;
    int highInd=-1;
    for(i=0;i<size;i++)
    {
        if(calcPrice(tickobj[i]) > highest)
        {
            highest = calcPrice(tickobj[i]);
            highInd = i;
        }
    }
    //printf("\nCustomer Details that have the highest ticket purchased : \n %s \nTicket Price : RM%f",
    printf("\nCustomer Details that have the highest ticket purchased : \n");
    display(tickobj[highInd]);
    printf("\nTicket Price : RM%.2f\n",highest);
    printf("\n-------------------------AVERAGE-------------------------\n");
    //-------------------Average-------------------
    float totalSale2023=0.0;
    int count2023=0;
    char getyear[5]; 
    for(i=0;i<size;i++)
    {
        strncpy(getyear, &tickobj[i].date[6], 4);
        
        
        if(strcmp(getyear, "2023") == 0)
        {
            totalSale2023 += calcPrice(tickobj[i]);
            count2023++;
        }
        
    }
    float average = totalSale2023/count2023;
    printf("\nAverage sales for the year 2023 : RM%.2f\n", average); //**
    printf("\n-------------------SEARCHING & UPDATE--------------------\n");
    //-------------------Searching------------------- 
    char update[4];
    printf("Do you want to update date(yes/no)?: ");
    scanf("%s", update);
    char dateUpdate[9], nameSearch[30];
    
    while(strcmp(update, "yes")==0)
    {
        int found=-1;
        printf("\nEnter name that you want to update date: ");
        scanf(" %[^\n]", nameSearch);
        
        int i;
        for(i=0;i<3;i++)
    	{
	    	if(strcmp(tickobj[i].name, nameSearch)==0)
		    {
    			found=i;
	    		break;
		    }
	    }
	    if(found==-1)
	    {
		    printf("\nSorry name is not found");
    	}
	    else
	    {
            printf("\nEnter new date: ");
            scanf("%s", dateUpdate);
            printf("\nTicket date succesfully updated!!\n");
            printf("Before update: %s\n", tickobj[found].date);
            strcpy(tickobj[found].date, dateUpdate);
            printf("After update: %s\n", tickobj[found].date);
        }
            printf("\nDo you want to update date(yes/no)?: ");
            scanf("%s", update);
    }
    
    printf("\n-------------------------DELETE--------------------------\n");
	char delete[4];
    printf("Do you want to delete data(yes/no)?: ");
    scanf("%s", delete);
    char nameSearch1[30];
    
    while(strcmp(delete, "yes")==0)
    {
        int found=0;
        printf("\nEnter name that you want to delete data: ");
        scanf(" %[^\n]", nameSearch1);
        
        int i;
        for(i=0;i<3;i++)
    	{
	    	if(strcmp(tickobj[i].name, nameSearch1)==0)
		    {
    			found=1;
	    		break;
		    }
	    }
	    if(found==0)
	    {
		    printf("\nSorry name is not found");
    	}
	    else
	    {
    
            int j;
        	for (j = i; j < (size - 1); j++)
        	{
            	tickobj[j] = tickobj[j + 1];
        	}

        	size--;
            	printf("\nData succesfully deleted!!\n");
            	
            	printf("\nData after deletion : \n");
           		for(i=0;i<size;i++)
    			{
        			printf("\n********Details customer %d ********", i+1);
        			display(tickobj[i]);
        			printf("Total Price : %.2f\n", calcPrice(tickobj[i]));
    			}
    			
    		
        }
            	printf("\nDo you want to delete data(yes/no)?: ");
            	scanf("%s", delete);
    }
    
    printf("\n----------------------END OF PROGRAM----------------------");
    return 0;
}

void display(struct Ticket t)
{
    
    
    printf("\nName: %s" , t.name);
    printf("\nTicket Date: %s",t.date);
    printf("\nQuantity: ");
    
    //print quantity base on age
    int child=0,adult=0,senior=0;
    int i;
    for(i=0; i<t.quantity;i++)
    {
        if(t.age[i]<12)
            child++;
        else if(t.age[i]>=12 && t.age[i] <60)
            adult++;
        else 
            senior++;
    }
    
    if (child>0)
        printf("\n%d x child",child);
    if (adult>0)
        printf("\n%d x adult",adult);
    if (senior>0)
        printf("\n%d x senior citizen",senior);
    
    printf("\nTotal Quantity: %d" , t.quantity);
    printf("\nExpress Lane: %s\n", (t.xpress) ? "yes" : "no");
    
}

float calcPrice(struct Ticket t){
    float priceSum=0;
    int i;
    for(i=0; i<t.quantity; i++)
    {
        if(t.age[i]<12)
            priceSum+=135;
        else if(t.age[i]>=12 && t.age[i] <60)
            priceSum+=160;
        else 
            priceSum+=135;
    }
    if(t.xpress)
        priceSum+=80;
    return priceSum;
}

