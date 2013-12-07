package aztec

class User extends SecUser {

    String firstName
	String lastName
	String email
	String getLastNameFirst() { "${lastName}, ${firstName}" }
	static transients = ['lastNameFirst']
	static constraints = {
    }
}
