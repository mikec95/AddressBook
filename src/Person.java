/**
 * getters and setters for data that will be published in the address book
 * 
 * @author Michael Carbonari
 *
 */
public class Person {
	private String firstName, lastName, phoneNumber, city;

	Person(String firstName, String lastName, String phoneNumber,
			String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String toString() {
		return "First Name: " + firstName + " " + "Last Name: " + lastName
				+ " " + "Phone Number: " + phoneNumber + " " + "City: " + city;
	}
}
