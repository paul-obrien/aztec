package aztec;

public class Player extends User {

	Date dateOfBirth
	static hasMany = [periodicEvaluations : CoachEvaluation,
		sessionEvaluations : SessionEvaluation,
		selfEvaluations : Evaluation,
		injuryReports : Injury]
	
}
