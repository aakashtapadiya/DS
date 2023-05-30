
import java.util.Scanner;

public class Bully {

    static boolean state[]  = new boolean[5];
    static int coordinator=5;
    
    public static void up(int up)
    {
        if(state[up-1]==true)
        {
            System.out.println("process "+up+" is already up !!");
        }
        else
        {
            state[up-1] = true;
            System.out.println("process "+up+" held election");
            for(int i=up;i<5;i++)
            {
                System.out.println("Election message sent from process "+up+" to process"+(i+1));
            }
            System.out.println("");
            for(int i=up+1;i<=5;i++)
            {
                if(state[i-1]==true)
                {
                    System.out.println("Alive message send from process "+i+" to process"+up);
                    break;
                }
            }
        }
    }
    
    public static void down(int down)
    {
        if(state[down-1]==false)
        {
            System.out.println("process "+down+" is already dowm.");
        }
        else
        {
            state[down-1] = false;
            System.out.println("process "+down+" is now dowm.");
        }
    }

    
    public static void mess(int mess)
    {
        if(state[mess-1]==true)
        {
            if(state[4]==true)
            {
                System.out.println("Message send successfully from Process "+mess);
            }
            else
            {
                if(state[4]==false)
                {
                    System.out.println("Co-ordinator is down, Process "+mess+" is helding election");
                    for(int i=mess;i<5;i++)
                    {
                        System.out.println("Election send from process "+mess+" to process "+(i+1));
                    }

                    for(int i=5;i>=mess;i--)
                    {
                        if(state[i-1]==true)
                        {
                        	System.out.println("\nNew Coordinator Assigned :"+i);
                        	coordinator=i;
                            System.out.println("Coordinator message send from process"+i+"to all");
                            break;
                        }
                    }
                }
            }
        }
        else
        {
            System.out.println("Prccess "+mess+" is down");
        }
    }
    
    public static void display() {
    	System.out.println("Co-ordinator is :"+coordinator);
    	
    	for(int i=0;i<4;i++) {
    		String str="";
        	if(state[i]) {
        		str="Up";
        	}else {
        		str="Down";
        	}
    		System.out.println("Process "+(i+1)+" is "+str);
    	}
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        for(int i=0;i<5;i++)
        {
            state[i] = true;
        }
        System.out.println("5 active process are:");
        System.out.println("Process up  = p1 p2 p3 p4 p5");
        System.out.println("Process 5 is coordinator");

        do
        {
            System.out.println("===================");
            System.out.println("1 up a process.");
            System.out.println("2.down a process");
            System.out.println("3 send a message");
            System.out.println("4 Display");
            System.out.println("5 Exit");
            System.out.println("===================");
            System.out.print("\nEnter your Choice :");
            choice = sc.nextInt();
            System.out.println("");
            switch(choice)
            {
                case 1:
                {
                    System.out.print("Enter Process to brring proces up");
                    int up = sc.nextInt();
                    if(up==5)
                    {
                        System.out.println("process 5 is co-ordinator");
                        state[4] = true;

                    }
                    else
                    {
                        up(up);
                    }
                }
                break;
                case 2:
                {
                    System.out.print("Enter Process to Bring down :");
                    int down = sc.nextInt();
                    down(down);
                }
                break;
                case 3:
                {
                    System.out.println("Enter Process which will Send a message :");
                    int mess = sc.nextInt();
                    mess(mess);
                }
                break;
                
                case 4:
                {
                    display();
                }
                break;
                
                default:
                {
                	System.out.println("Worng Input, Enter Choice Again");
                }

            }

        }
        while(choice!=5);
        sc.close();
    }        
}

