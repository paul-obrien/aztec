package aztec;

public class Player extends User {
	Date dateOfBirth
	SortedSet reports
	static hasMany = [reports: Report]
	static belongsTo = Team
}
