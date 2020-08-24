import java.util.Objects;
import java.util.Scanner;

public class Login {
	
	// real password
	String realPassword = "gandalf";
	
	int loginTrials = 4;
	boolean passwordMatched;
	boolean userBlocked;
	
	Scanner sc = new Scanner(System.in);
	
	public void checkPassword() {

		String userPassword = sc.nextLine();
		
		if (Objects.equals( userPassword, realPassword) ) {
			passwordMatched = true;
		}
		else {
			passwordMatched = false;
		}
	}
	
	public void run() {
		
		System.out.println("\n***********************************");
		System.out.println("Welcome to an imaginary login page!");
		System.out.println("(the real password is \"gandalf\")");
		System.out.println("***********************************\n");
		
		System.out.println("Note: \nAfter 3 failed login attempts the\n"
				+ "account will be blocked\n");
		
		for(int i = 1; i <= 4; ++i) {
			
			
			System.out.println("[Trial " + i + "] Password: ");
			
			this.checkPassword();
			
			if ( (!passwordMatched) & (i == 4) ) {
				System.out.println("\n>> YOUR ACCOUNT IS BLOCKED <<");
				System.out.println("Incorrect password entered 4 times, ");
				System.out.println("contact the administrator to proceed!");
				userBlocked = true;
			}
			
			else if (passwordMatched) {
				System.out.println("\nCORRECT, logging in..");
				break;
			}
			else {
				System.out.println("Incorrect password, try again:\n");
			}
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login login = new Login();
		//login.checkPassword();
		login.run();
		//System.out.println( "Good password? " + login.passwordMatch);

	}

}





