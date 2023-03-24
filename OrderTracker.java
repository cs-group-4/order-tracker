import java.util.*;

public class OrderTracker{
	public static void main(String[]args){
		UserInterface ui = new UserInterface();
		ui.home();

	}
}
//Once the admin logs in, they will be able to make the needed changes to the customer's tracking page.

class UserInterface{
	Scanner tracker = new Scanner(System.in);

	public void home(){

//What a user see the moment they open the tracking site

		System.out.println("Home Page.");
		System.out.println("Press 1 to track your package");
		System.out.println("Press 2 for Admin Login");
		System.out.print("Enter here: ");

//prompt user/admin to input a value 
		int input = tracker.nextInt();

//depending on what value the user/admin enters, this is what will be displayed
		switch(input){
			case 1:{ trackProductScreen();}
			break;
			case 2: {admin();}
			break;
			default: System.out.println("Invalid Input, try again.");
		}
	}
	//user is able to see and track the progress of their product
	public void trackProductScreen(){
		System.out.println("Enter your tracking code");
		int code = tracker.nextInt();
		//check if code is valid
		TrackingBackend  trackingbackend = new TrackingBackend();

		if(TrackingBackend.isValid(code)){

			trackingDetailsScreen(TrackingBackend.getName(), TrackingBackend.getOrderNumber(), TrackingBackend.getShippingStage());
		} else{
			System.out.println("Code doesnt exist");
			trackProductScreen();
		}
		System.out.print("Enter 0 to go back to previous screen: ");
		int back = tracker.nextInt();
		if(back == 0){
			home();
		}
		
	}
	//allows the admin to make updates and changes concerning the shipment
	public void admin(){
		System.out.println("Enter Password");
		String password = tracker.next();
		//updates
		UpdateTracking updatetracking = new UpdateTracking();
		
		int trackingCode = tracker.nextInt();
		String orderNumber = tracker.next();
		String shippingStage = tracker.next();
		System.out.println("To update the shipping stage, ");
		System.out.println("Press 1 for loading");
		System.out.println("Press 2 for in transit");
		System.out.println("Press 3 for delivered");

		System.out.print("Enter 0 to go back to previous screen: ");
		int back = tracker.nextInt();
		if(back == 0){
			home();
		}else {
			updatetracking.setShippingStage(back);
			admin();
		}

	}
	//displays the details of the customer as well as their shipping details
	public void trackingDetailsScreen(String name, String orderNumber, String shippingStatge){
		System.out.printf("Fullname: %s %n Order Number: %s %n Shipping Stage: %s", name, orderNumber, shippingStage);
		System.out.print("Enter 0 to go back to previous screen: ");
		int back = tracker.nextInt();
		if(back == 0){
			trackProductScreen();
		}
	}
}
