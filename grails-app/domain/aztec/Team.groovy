package aztec

class Team {

    String name
	Season season
	static hasMany = [coaches: Coach, players: Player]
	
	static constraints = {
    }
}
