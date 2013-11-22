package aztec

class SessionEvaluation {

    int rating
	Date date
	Coach coach
	static belongsTo = Player
	static constraints = {
		rating(range:0..5)
    }
}
