package aztec

class Report implements Comparable {

    Date date	
	Coach coach
	Team team
	Season season
	Player player
	static belongsTo = Player
	String reportType // "session", "coach", "self", "injury"
    int overallRating
	int physicalRating
	int psychologicalRating
	int technicalRating
	int tacticalRating
	int injurySeverity
	String comment
	
	static constraints = {
		coach(nullable:true)
		overallRating(nullable:true,range:0..5)
		physicalRating(nullable:true,range:0..5)
		psychologicalRating(nullable:true,range:0..5)
		technicalRating(nullable:true,range:0..5)
		tacticalRating(nullable:true,range:0..5)
		injurySeverity(nullable:true,range:0..1)
		comment(nullable:true)
    }
	
	int compareTo(obj) {
		date.compareTo(obj.date)
	}
	 
}
