package aztec

class Injury {

    Date date
	Coach coach
	static belongsTo = Player
	int severity
	String comment
	
	static constraints = {
		severity(range:0..1)
    }
}
