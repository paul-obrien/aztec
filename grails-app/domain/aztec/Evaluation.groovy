package aztec

class Evaluation {

    Date date	
	static belongsTo = Player
    int physicalRating;
	int psychologicalRating;
	int technicalRating;
	int tacticalRating
	String comment
	
	static constraints = {
		physicalRating(range:0..5)
		psychologicalRating(range:0..5)
		technicalRating(range:0..5)
		tacticalRating(range:0..5)
    }
	 
}
