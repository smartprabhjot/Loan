import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loan {
	private static final int UpdatedLoan = 0;
	private static PriorityQueue<Applicant> activeList;
	private static ArrayList<Applicant> approvedList;
	private static ArrayList<Applicant> rejectedList;
	private static int budget;
	static Applicant applicant;

	public Loan() {
		activeList = new PriorityQueue<Applicant>();
		approvedList = new ArrayList<Applicant>();
		rejectedList = new ArrayList<Applicant>();
		budget = 0;
	}
	/**
	 * Calls the displan menu function 
	 * @throws IOException
	 */

	public void run() throws IOException {
	

		Scanner scan = new Scanner(System.in);

		displayMenu();
		char input = '0';
		while (input != 'x') {
			System.out.println("______________________________");
			System.out.print("\nOption:");
			input = scan.next().charAt(0);
			selectOption(input);
		}
	}
/**
 * Displays the menu for options
 */
	private void displayMenu() {
		System.out.println("********Loan Approval System***********");
		System.out.println("\n");
		System.out.println(
				"Choose the following options:\n(l)Load Applicaitons\n(s)Set the Budget\n(m)Make a decision\n(p)Print\n(u)Update the application");

	}
/**
 * Takes user input and runs the function accordingly
 * @param input
 * @throws IOException
 */
	public static void selectOption(char input) throws IOException {

		switch (input) {
		case 'l': {

			loadApplications();
			break;
		}

		case 's': {

			System.out.println("Set the Budget");
			setBudget();
			break;
		}

		case 'm': {

			
			makeDecision();
			System.out.println("Decision made sucessfuly!");
			break;
		}

		case 'p': {

			
			print();
			break;
		}

		case 'u': {

			System.out.println("Update the application");
			updateApplication();
			break;
		}
		case 'x': {

			System.out.println("Exiting...");
			
			break;
		}

		default:
			System.out.println("Please enter a valid input\n**************************************");
			break;

		}
	}
	/**
	 * Prints the 
	 * @throws IOException
	 */
	private static void print() throws IOException {

		FileWriter writer1, writer2, writer3;
		writer1 = new FileWriter("active.txt");
		
		if(!activeList.isEmpty()) {
			
		for (int i = 0; i < activeList.size()+1; i++) {
			Applicant applicant = activeList.pop();
			String score = Integer.toString(applicant.getScore());
			writer1.write(applicant.getName() + "\t" + applicant.getLoan() + "\t" + score + "\n");

			
		}
		writer1.close();
		}
		
		if(!approvedList.isEmpty()) {
			
		writer2 = new FileWriter("approved.txt");
		for (int i = 0; i < approvedList.size(); i++) {
			Applicant applicant = approvedList.get(i);
			String score = Integer.toString(applicant.getScore());
			writer2.write(applicant.getName() + "\t" + applicant.getLoan()  + "\n");

			
		}
		writer2.close();}
		
		if(!rejectedList.isEmpty()) {
			
		writer3 = new FileWriter("rejected.txt");
		for (int i = 0; i < rejectedList.size(); i++) {
			Applicant applicant = rejectedList.get(i);
			String score = Integer.toString(applicant.getScore());
			writer3.write(applicant.getName() + "\t" + applicant.getLoan() + "\t" + score + "\n");

			
		}
		writer3.close();
		}
		
		System.out.println("Enteries succefully added in the log files");
	} 

	private static void makeDecision() {

		PriorityQueue<Applicant> tempActiveList = new PriorityQueue<Applicant>();

		for (int i = 0; i <= activeList.size()+2 ; i++) {
			Applicant applicant = activeList.pop();
			
			if (applicant.getLoan() < budget) {
				approvedList.add(applicant);
				budget = budget - applicant.getLoan();
				
			}
			
			else {
		
				tempActiveList.push(applicant);
			}
		}

		activeList = tempActiveList;

	}

	private static void updateApplication() throws FileNotFoundException {
		/**
		 * Note that in the case of update command, the score of the application will be
		 * calculated again and it will be updated in the active list, or if the
		 * application was rejected before, it might change the status and add it to the
		 * active list.
		 * 
		 */
		File file = new File("C:\\Users\\singh\\eclipse-workspace\\Loan\\application.txt");
		Scanner scan = new Scanner(file);
		Scanner scanInput = new Scanner(System.in);
		System.out.println("Type the name of the person you want to update:");
		String personName = scanInput.nextLine();
		String person = "\""+personName+"\"";
		
		

		while (scan.hasNextLine()) {

			String line = scan.nextLine();
			String[] userInfo = line.split("\t");
			String userInfoString= userInfo[0].toLowerCase();
			int education = Integer.parseInt(userInfo[2]);
			int experience = Integer.parseInt(userInfo[3]);
			int sum = education + experience;
			
			int loan = Integer.parseInt(userInfo[1]);
			int score = 0;
			int[] estimatedProfit = new int[userInfo.length - 4];
			Applicant applicant = new Applicant(userInfo[0], education, experience, estimatedProfit, score, loan);
				if(userInfoString.equals(person.toLowerCase())) {
					Applicant tempApplicant = activeList.find(applicant);
					int updatedEducation;
					int updatedExperience;
					String updatedName;
					int updatedScore = 0;
					
					System.out.println("Enter the updated Name:");
					updatedName = scanInput.nextLine();
					System.out.println("Enter the updated experience:");
					updatedExperience = scanInput.nextInt();
					System.out.println("Enter the updated education:");
					updatedEducation=scanInput.nextInt();
					
					System.out.println("How many enteries do you have for estimated profit?");
					int temp =scanInput.nextInt();
					int[] updatedEstPro = new int[temp];
					for(int i=0;i<temp;i++) {
						
						System.out.println("Enter the "+(i+1)+" entry of estimated profit");
						int entry = scanInput.nextInt();
						updatedEstPro[i]=entry;
						
					}
					
					if (sum < 10) {
						applicant = new Applicant(updatedName, updatedEducation, updatedExperience, estimatedProfit, score, loan);
						rejectedList.add(applicant);
					}

					else {

						/**
						 * Calculate score
						 */
						int tmp = 0;
						for (int j = 0; j < estimatedProfit.length; j++) {
							tmp = estimatedProfit[j] / (j + 1);
							updatedScore = updatedScore + tmp;

						}

					Applicant updatedApplicant = new Applicant(updatedName, updatedEducation,updatedExperience,updatedEstPro,updatedScore,loan);
					activeList.updateKey(updatedApplicant );
				    
	
					break;
				}
				
			}
		
		
		

	} }

	/**
	 * Load the applications: Reads the information of the applications from a file
	 * and store them as active applications if they qualify (if an application does
	 * not meet the requirements it will be added to the rejected application list)
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	private static void loadApplications() throws FileNotFoundException {
		File file = new File("C:\\Users\\singh\\eclipse-workspace\\Loan\\application.txt");
		Scanner scan = new Scanner(file);

		while (scan.hasNextLine()) {

			String line = scan.nextLine();
			/*
			 * If the sum of education and experience years is smaller than 10, the
			 * application will be rejected. Otherwise the application will get some score
			 * and will be added to the active list.
			 */
			// Convert the line string into an array of strings
			String[] userInfo = line.split("\t");
			int education = Integer.parseInt(userInfo[2]);
			int experience = Integer.parseInt(userInfo[3]);
			int sum = education + experience;
			int loan = Integer.parseInt(userInfo[1]);
			int[] estimatedProfit = new int[userInfo.length - 4];
			int score = 0;

			/**
			 * To count estimated profit
			 */
			for (int i = 4; i < userInfo.length; i++) {

				estimatedProfit[i - 4] = Integer.parseInt(userInfo[i]);

			}

			/**
			 * Check if application will be rejected or to put in the active list
			 */

			if (sum < 10) {
				Applicant applicant = new Applicant(userInfo[0], education, experience, estimatedProfit, score, loan);
				rejectedList.add(applicant);
			}

			else {

				/**
				 * Calculate score
				 */
				int tmp = 0;
				for (int j = 0; j < estimatedProfit.length; j++) {
					tmp = estimatedProfit[j] / (j + 1);
					score = score + tmp;

				}

				/**
				 * Add to active list
				 */

				Applicant applicant = new Applicant(userInfo[0], education, experience, estimatedProfit, score, loan);
				activeList.push(applicant);

			}
		}
		
		System.out.println("Applicaitons Loaded Successfuly!");

	}

	/**
	 * Update the budget. Take input from the user.
	 */
	private static void setBudget() {
		System.out.println("How much is the budget:");
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		budget = input;
		System.out.println("Budget Entry successfull:"+budget);
	}

	

}
