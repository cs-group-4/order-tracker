import java.util.*;

public class OrderTracker {

    public static void main(String[]args){
		UserInterface ui = new UserInterface();
		ui.home();

	}
}

//Once the admin logs in, they will be able to make the needed changes to the customer's tracking page.

// @author Gain Nambeye
class UserInterface extends UpdateTracking{
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
			case 1 -> { trackProductScreen();}
			case 2 -> {admin();}
			default -> System.out.println("Invalid Input, try again.");
		}
	}
	//user is able to see and track the progress of their product
	public void trackProductScreen(){

		System.out.println("Enter your tracking code,or enter 0 to go back to previous screen");
		long code = tracker.nextInt();
		//check if code is valid
                String shippingStage;
                if(code == 0){
                    home();
                }
		if(isValid(code)){
                    int currentShippingStage = getShippingStage();
                    shippingStage = switch (currentShippingStage) {
                        case 1 -> "Loading";
                        case 2 -> "In transit";
                        default -> "Delivered";
                    };
                    trackingDetailsScreen(getCustomerName(), getOrderNumber(), shippingStage);
		} else{
			System.out.println("Code doesnt exist");
			trackProductScreen();
		}
		
		
	}
	//allows the admin to make updates and changes concerning the shipment
	public void admin(){
//		System.out.println("Enter Password");
//		String password = tracker.next();
		//updates
		
//		int trackingCode = tracker.nextInt();
                System.out.println("Customer name: " + getCustomerName());
		System.out.println("Order numer: " + getOrderNumber());
                String shipStage = switch (getShippingStage()) {
                        case 1 -> "Loading";
                        case 2 -> "In transit";
                        default -> "Delivered";
                    };
		System.out.println("Shipping stage: " + shipStage);
		System.out.println("To update the shipping stage, ");
		System.out.println("Press 1 for loading");
		System.out.println("Press 2 for in transit");
		System.out.println("Press 3 for delivered");

		System.out.print("Enter 0 to go back to previous screen: ");
		int back = tracker.nextInt();
		if(back == 0){
			home();
		}else {
			setShippingStage(back);
			admin();
		}

	}
	//displays the details of the customer as well as their shipping details
	public void trackingDetailsScreen(String name, String orderNumber, String shippingStage){
		System.out.printf("Fullname: %s %n Order Number: %s %n Shipping Stage: %s", name, orderNumber, shippingStage);
                System.out.println("");
		System.out.println("Enter 0 to go back to previous screen: ");
		int back = tracker.nextInt();
		if(back == 0){
			trackProductScreen();
		}
	}
}

//@ author: Mwiza Chiwale
class UpdateTracking extends TrackingBackEnd{
    int shippingStage = 1;
    
    public void setShippingStage(int stage){
        shippingStage = stage;
    }
     public int getShippingStage(){
        return this.shippingStage;
    }
    
    
}

//@ author: Mwiza Chiwale
class TrackingBackEnd {
    String customerName = "Jane Doe";    
    String orderNumber = "ABC12345";

    long trackingCode = 123456;
    boolean valid = false;
    public boolean isValid(long code){
        if(code == trackingCode){
            valid = true;
        }
        return valid;
    }
    public  String getCustomerName(){
        return this.customerName;
    }
    public String getOrderNumber(){
        return this.orderNumber;
    }
}

