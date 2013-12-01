package aztec

class Report implements Comparable {

    Date date	
	Coach coach
	Team team
	Season season
	Player player
	static belongsTo = Player
	String reportType // "sessionCoach", "periodicCoach", "self", "injury"
    int overallRating
	int physicalRating
	String physicalComment
	int psychologicalRating
	String psychologicalComment
	int technicalRating
	String technicalComment
	int tacticalRating
	String tacticalComment
	int injurySeverity
	String comment
	
	static SESSION_COACH_REPORT_TYPE = 'sessionCoach'
	static PERIODIC_COACH_REPORT_TYPE = 'periodicCoach'
	static SELF_REPORT_TYPE = 'self'
	static INJURY_REPORT_TYPE = 'injury'
	static RATINGS  = [4 : 'Excellent', 3 : 'Strong', 2 : 'Average', 1 : 'Needs Improvement', 0 : 'Not Applicable']
	static SESSION_RATINGS = [0 : 'Did Not Attend', 4 : 'Excellent', 3 : 'Strong', 2 : 'Average', 1 : 'Needs Improvement']
	
	static constraints = {
		coach(nullable:true)
		overallRating(nullable:true,range:0..5)
		physicalRating(nullable:true,range:0..5)
		physicalComment(nullable:true)
		psychologicalRating(nullable:true,range:0..5)
		psychologicalComment(nullable:true)
		technicalRating(nullable:true,range:0..5)
		technicalComment(nullable:true)
		tacticalRating(nullable:true,range:0..5)
		tacticalComment(nullable:true)
		injurySeverity(nullable:true,range:0..1)
		comment(nullable:true)
    }
	
    static mapping = {
       physicalComment type: 'text'
	   psychologicalComment type: 'text'
	   technicalComment type: 'text'
	   tacticalComment type: 'text'
	   comment type: 'text'
    }
	
	int compareTo(obj) {
       obj.date.compareTo(date)
    }
	
	def typeDisplayName() {
		switch (reportType) {
			case SESSION_COACH_REPORT_TYPE:
			 	return 'Training Session'
			case PERIODIC_COACH_REPORT_TYPE:
			    return 'Coach Evaluation'
		    case SELF_REPORT_TYPE:
			    return 'Self Evaluation'
		    case INJURY_REPORT_TYPE:
			    return 'Injury'	 
		}
	}
	
	def reportedBy() {
		if (!coach) return ""
		return "${coach.firstName} ${coach.lastName}"
	}
	
}
