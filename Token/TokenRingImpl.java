import java.util.*;


public class TokenRingImpl {

	public TokenRingImpl() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Num of Nodes :");
		int n=sc.nextInt();
		
		System.out.println();
		System.out.print("Initializing Ring :");
		for(int i=0;i<n;i++) {
			System.out.print(i+" -> ");
		}
		System.out.print(0);
		
		int sender, reciever;
        String message;
        
        System.out.println("");
        System.out.print("Enter sender : ");
        sender = sc.nextInt();
        System.out.print("Enter reciever : ");
        reciever = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter message : ");
        message = sc.nextLine();
       
        System.out.print("Passing token from 0 -> ");
        for(int i = 1; i <= sender; i++) {
            System.out.print(i+ " -> ");                        
        }
        
        System.out.println("");
        System.out.println("Sender "+sender+" Sending Data :"+message);
        System.out.println("");
        
        for(int i=sender; i!=reciever; i=(i+1)%n) {
        	System.out.println("Data Forwaded by :"+i);
        }
        System.out.println("Data "+message+" recieved by :"+reciever);

	}

}
