package aztec

class Team {

    String name
	Season season
	static hasMany = [coaches: Coach, players: Player]
	
	static constraints = {
    }
	
	def playersByLastName() {
		players.asList().sort({ it.lastName });
	}
}
