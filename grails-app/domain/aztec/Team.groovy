package aztec

class Team {

    String Name
	Season season
	static hasMany = [coaches: Coach, players: Player]
	static belongsTo = Coach
	
	static constraints = {
    }
}
