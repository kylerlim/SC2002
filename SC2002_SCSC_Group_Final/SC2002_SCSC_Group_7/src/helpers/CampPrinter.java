package helpers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import enitities.*;
import enitities.Camp;
import enitities.Staff;
import enitities.Student;
import enitities.User;
/**
 * The `CampPrinter` class provides methods to view and interact
 * with the camps based on user roles as and filters.
 */
public class CampPrinter {

	/**
	 * 	The user associated to the camps that are being printed.
	 */
	public User user;
	/**
	 * The camp to interact with.
	 */
	private Camp campToInteract;
	/**
	 * List of all Camps that are available to interact with.
	 */
	private ArrayList<Camp> campsAvailableToInteract = new ArrayList<Camp>();
	/**
	 * List of all Camps that are available.
	 */
	public ArrayList<Camp> allCamps;
	/**
	 * The search filter implemented to search for specific camp names.
	 */
	String filter;

	/**
	 * Constructs a new `CampPrinter` with the specified user and list of all camps.
	 *
	 * @param user     The user for whom camps are being printed.
	 * @param allCamps The list of all camps available.
	 */
	public CampPrinter(User user, ArrayList<Camp> allCamps){
		System.out.print("Enter the filter needed(Press enter to skip search filter): ");
        Scanner fil = new Scanner(System.in);
        String filter = fil.nextLine();
		System.out.print("\n");
		this.user = user;
		this.allCamps = allCamps;
		this.filter = filter;
	 }

	/**
	 * Views all camps based on user type and search filter.
	 */
	public void viewAllCamps(){
		// TODO - implement CampPrinter.viewAllCamps
		filter = filter.toLowerCase();
		ArrayList <Camp> visibleCamps = new ArrayList<Camp>();

		if(!filter.isEmpty())System.out.println("Here are the camps with \'" + filter + "\' contained in it."); 
		if (user instanceof Staff){
			int x = 1;
			for (int i = 0; i < allCamps.size(); i++){
				if ((allCamps.get(i).getName().toLowerCase()).contains(filter)){
					visibleCamps.add(allCamps.get(i));
					Camp temp = allCamps.get(i);
					System.out.print("(" + x + ")");
					temp.printAttributes();
					System.out.print("\n");
					x++;
				}
			}
		}

		else if (user instanceof Student){
			int x = 1;
			for (int i = 0; i < allCamps.size(); i++){
				if (allCamps.get(i).getVisibility()){
					if(allCamps.get(i).getOpenTo().equals(user.getFaculty()) || allCamps.get(i).getOpenTo().equals(Faculty.NTU)){
						visibleCamps.add(allCamps.get(i));
						Camp temp = allCamps.get(i);
						System.out.print("(" + x + ")");
						temp.printAttributes();
						System.out.print("\n");
						x++;
					}
				}
		}
	}
			campsAvailableToInteract = visibleCamps;
			// return allCamps;
	}

	/**
	 * 
	 * @param campInfoRecords
	 * @param user
	 */

	/**
	 * Views camps owned by the staff user.
	 */
	public void viewMyCamps(){
		// TODO - implement CampPrinter.viewMyCamps
		// TODO discuss viability of adding MyOwnedCamps to USER class
		ArrayList <Camp> myCamps = new ArrayList<Camp>();
		filter = filter.toLowerCase();

		if(!filter.isEmpty())System.out.println("Here are the camps with \'" + filter + "\' contained in it."); 

		if (user instanceof Staff){
			Staff s1 = (Staff) user;
			for (int i = 0; i< allCamps.size(); i ++){
				if(allCamps.get(i).getCreator().equals(s1.getNetworkID()) && allCamps.get(i).getName().toLowerCase().contains(filter)){
					myCamps.add(allCamps.get(i));
				}
			}

			System.out.println("You have " + myCamps.size() + " camps");
			for (int x =0; x< myCamps.size(); x++){
				System.out.print("(" + (x+1) + ")");
				myCamps.get(x).printAttributes();
				System.out.print("\n");
			}
		}

		else if (user instanceof Student){
			
			Student s1 = (Student) user;
			int commSize =  s1.getCampCommittee().size();
			int attendSize = s1.getCampAttendee().size();
			String campName;


			for(int i = 0; i< commSize; i++){
				campName =  s1.getCampCommittee().get(i);
				for (int j = 0; j<allCamps.size(); j++){
					if(allCamps.get(j).getName().equals(campName) && allCamps.get(j).getName().toLowerCase().contains(filter)){ 
						myCamps.add(allCamps.get(j));
					}
				}
			}

			for(int q = 0; q< attendSize; q++){
				campName =  s1.getCampAttendee().get(q);
				for (int j = 0; j<allCamps.size(); j++){
					if(allCamps.get(j).getName().equals(campName) && allCamps.get(j).getName().toLowerCase().contains(filter)){ 
						myCamps.add(allCamps.get(j));
					}
				}
			}

			System.out.println("You have " + myCamps.size() + " camps");
			int comm = 0;
			for (int x= 0; x < myCamps.size(); x++){
				System.out.print("(" + (x+1) + ")");
				myCamps.get(x).printAttributes();

					if(comm< commSize){
					System.out.print("{COMMITTEE}");
					comm++;
				}
				System.out.print("\n");
			}

			}
			
		campsAvailableToInteract = myCamps;
		
	}


	/**
	 * Views camps where the student user is a committee member.
	 */
	public void viewMyCommitteeCamps() {
		ArrayList <Camp> myCommitteeCamps = new ArrayList<Camp>();

		filter = filter.toLowerCase();
		if(!filter.isEmpty())System.out.println("Here are the camps with \'" + filter + "\' contained in it."); 

		Student s1 = (Student) user;
			int commSize =  s1.getCampCommittee().size();


			for(int i = 0; i< commSize; i++){
				String campName =  s1.getCampCommittee().get(i);
				for (int j = 0; j<allCamps.size(); j++){
					if(allCamps.get(j).getName().equals(campName) && allCamps.get(j).getName().toLowerCase().contains(filter)){ 
						myCommitteeCamps.add(allCamps.get(j));
					}
				}
			}


			for (int x= 0; x < myCommitteeCamps.size(); x++){
						System.out.print("(" + (x+1) + ")");
						myCommitteeCamps.get(x).printAttributes();
						System.out.print("\n");
					}

		campsAvailableToInteract = myCommitteeCamps;
	}

	/**
	 * Views camps where the student user is a camp attendee.
	 */
	public void viewMyAttendeeCamps() {
		ArrayList <Camp> myAttendeeCamps = new ArrayList<Camp>();

		filter = filter.toLowerCase();
		if(!filter.isEmpty())System.out.println("Here are the camps with \'" + filter + "\' contained in it.");

		Student s1 = (Student) user;
			int attendSize =  s1.getCampAttendee().size();


			for(int i = 0; i< attendSize; i++){
				String campName =  s1.getCampAttendee().get(i);
				for (int j = 0; j<allCamps.size(); j++){
					if(allCamps.get(j).getName().equals(campName) && allCamps.get(j).getName().toLowerCase().contains(filter)){ 
						myAttendeeCamps.add(allCamps.get(j));
					}
				}
			}


			for (int x= 0; x < myAttendeeCamps.size(); x++){
						System.out.print("(" + (x+1) + ")");
						myAttendeeCamps.get(x).printAttributes();
						System.out.print("\n");
					}

		campsAvailableToInteract = myAttendeeCamps;
	}

	/**
	 * Sets the camp to interact with based on user input.
	 */
	public void setCampToInteract(){
		if (campsAvailableToInteract.isEmpty()){
			return;
		}
		int userInput = 1;
		do{
			if (userInput>campsAvailableToInteract.size() || userInput < 0){
				System.out.println("Not a valid input");
				return;
			}
			else if (userInput == 0 ) return;

			System.out.println("Select camp: ");
			Scanner scan = new Scanner(System.in);
			userInput =  scan.nextInt();
		}while (userInput>campsAvailableToInteract.size() || userInput < 1);

		campToInteract =  campsAvailableToInteract.get(userInput - 1);
	}

	/**
	 * Gets the camp to interact.
	 *
	 * @return The camp to interact.
	 */
	public Camp getCampToInteract(){
		return campToInteract;
	}

	/**
	 * Gets the list of camps available to interact.
	 *
	 * @return The list of camps available to interact.
	 */
	public ArrayList<Camp> getCampsAvailable(){
		return campsAvailableToInteract;
	}
}

