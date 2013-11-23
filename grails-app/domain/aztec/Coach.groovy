package aztec

class Coach extends User {

    static hasMany = [teams: Team]
	static belongsTo = Team
	static constraints = {
    }
	
}

