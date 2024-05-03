package helpers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import enitities.Camp;
import enitities.Student;
import enitities.User;

/**
 * The `CampGenerator` class generates a list
 * of camps based on a user's role as a camp attendee or camp
 * committee for the particular camp .
 */
public class CampGenerator {

	/**
	 * The user that is associated to the camps that will be generated.
	 */
	public User user;
	/**
	 * List of all the available and possible camps.
	 */
	public ArrayList<Camp> allCamps;
	// public ArrayList<Camp> myCamps = new ArrayList<Camp>();

	/**
	 * Constructs a new `CampGenerator` with the specified user and list of all camps.
	 *
	 * @param user     The user for whom the camps are generated.
	 * @param allCamps The list of all camps available.
	 */
	public CampGenerator(User user, ArrayList<Camp> allCamps){
		this.user = user;
		this.allCamps = allCamps;
	 }

	/**
	 * Generates a list of camps where the user is either a camp committee or camp attendee.
	 *
	 * @return An ArrayList of camps where the user is involved as a camp committee or camp attendee.
	 */
	public ArrayList<Camp> generateMyCamps(){

		ArrayList <Camp> myCamps = new ArrayList<Camp>();

			Student s1 = (Student) user;
			int commSize =  s1.getCampCommittee().size();
			int attendSize = s1.getCampAttendee().size();
			String campName;


			for(int i = 0; i< commSize; i++){
				campName =  s1.getCampCommittee().get(i);
				for (int j = 0; j<allCamps.size(); j++){
					if(allCamps.get(j).getName().equals(campName)){ 
						myCamps.add(allCamps.get(j));
					}
				}
			}

			for(int q = 0; q< attendSize; q++){
				campName =  s1.getCampAttendee().get(q);
				for (int j = 0; j<allCamps.size(); j++){
					if(allCamps.get(j).getName().equals(campName)){ 
						myCamps.add(allCamps.get(j));
					}
				}
			}

			
		 return myCamps;
		
	}

	/**
	 * Generates a list of camps where the user is an attendee.
	 *
	 * @return An ArrayList of camps where the user is involved as an attendee.
	 */
	public ArrayList<Camp> generateMyAttendeeCamps(){

		ArrayList <Camp> myCamps = new ArrayList<Camp>();

			Student s1 = (Student) user;
			int attendSize = s1.getCampAttendee().size();
			String campName;


			for(int q = 0; q< attendSize; q++){
				campName =  s1.getCampAttendee().get(q);
				for (int j = 0; j<allCamps.size(); j++){
					if(allCamps.get(j).getName().equals(campName)){ 
						myCamps.add(allCamps.get(j));
					}
				}
			}
			
		return myCamps;
	}

	/**
	 * Generates a list of camps where the user is a committee member.
	 *
	 * @return An ArrayList of camps where the user is involved as a committee member.
	 */
	public ArrayList<Camp> generateMyCommitteeCamps(){

		ArrayList <Camp> myCamps = new ArrayList<Camp>();

			Student s1 = (Student) user;
			int commSize = s1.getCampCommittee().size();
			String campName;


			for(int q = 0; q< commSize; q++){
				campName =  s1.getCampCommittee().get(q);
				for (int j = 0; j<allCamps.size(); j++){
					if(allCamps.get(j).getName().equals(campName)){ 
						myCamps.add(allCamps.get(j));
					}
				}
			}
			
		return myCamps;
	}
}

	

