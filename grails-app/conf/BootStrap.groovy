import aztec.*

class BootStrap {
	def springSecurityService
	
    def init = { servletContext ->
		def adminRole = SecRole.findByAuthority("ROLE_ADMIN") ?: new SecRole(authority: "ROLE_ADMIN").save()
		def coachRole = SecRole.findByAuthority("ROLE_COACH") ?: new SecRole(authority: "ROLE_COACH").save()
		def playerRole = SecRole.findByAuthority("ROLE_PLAYER") ?: new SecRole(authority: "ROLE_PLAYER").save()
		
		def sampleAdmins = [ 'admin@example.com' : [ password: 'admin', firstName: 'Andy', lastName: 'Administrator'] ]
		def sampleCoaches = [ 'coach@example.com' : [ password: 'coach', firstName: 'Chris', lastName: 'Coach' ],
			'coach2@example.com' : [password: 'coach', firstName: 'Jon', lastName: ''] ]

		def users = User.list() ?: []
		def admins, coaches, players, seasons, teams, coachEvaluations, selfEvaluations, sessionEvaluations, injuryReports
		if (!users) {
			admins = createAdmins(sampleAdmins, adminRole)
			coaches = createCoaches()
			players = createPlayers()
			seasons = createSeasons()
			teams = createTeams()
			coachEvaluations = createCoachEvaluations()
			selfEvaluations = createSelfEvaluations()
			sessionEvaluations = createSessionEvaluations()
			injuryReports = createInjuryReports()
		}
	}
	
	def finishCreateObject(object, role=null) {
		if (object.validate()) {
			object.save(flush:true)
			if (role)
			  SecUserSecRole.create object, role
			return true
		}
		else {
			println("\n\n\nError in account bootstrap for ${object}!\n\n\n")
			object.errors.each { err ->
				println err
			}
			return false
		}
	}

	def createAdmins(adminData, adminRole) {
		def admins = []
		adminData.each { username, profileAttrs ->
			def admin = new User(
				username: username,
				password: profileAttrs.password,
				firstName: profileAttrs.firstName,
				lastName: profileAttrs.lastName,
				email: username)
			
			if (finishCreateObject(admin, adminRole))
				admins << admin
		}
		
		return admins
	}
	
	def createCoaches() {
		def role = SecRole.findByAuthority("ROLE_COACH") 
		def coaches = []
		new File('exampleData/coaches.csv').eachLine { line ->
			def fields = line.split(',')
			def coach = new Coach(
				username: fields[0],
				password: 'password',
				firstName: fields[1],
				lastName: fields[2],
				email: fields[0])

			
			if (finishCreateObject(coach, role))
			  coaches << coach
		}
		
		return coaches
	}

	def createPlayers() {
		def role = SecRole.findByAuthority("ROLE_PLAYER") 
		def players = []
		new File('exampleData/players.csv').eachLine { line ->
			def fields = line.split(',')
			def player = new Player(
				username: fields[0],
				password: 'password',
				firstName: fields[1],
				lastName: fields[2],
				dateOfBirth: Date.parse('y-M-d', fields[3]),
				email: fields[0])

			if (finishCreateObject(player, role))
				players << player	
		}
		
		return players
	}

	def createSeasons() {
		def seasons = []
		new File('exampleData/seasons.csv').eachLine { line ->
			def fields = line.split(',')
			def season = new Season(
				name: fields[0],
				startDate: Date.parse('y-M-d', fields[1]),
				endDate: Date.parse('y-M-d', fields[2]))

			if (finishCreateObject(season))
				seasons << season	
		}
		
		return seasons
	}

	def createTeams() {
		def teams = []
		new File('exampleData/teams.csv').eachLine { line ->
			def fields = line.split(',')
			def season = Season.findByName(fields[1])
			def team = new Team(
				name: fields[0],
				season: season
		    )
			def coaches = fields[2].split(':')
			coaches.each { lastName ->
				def coach = Coach.findByLastName(lastName)
				team.addToCoaches(coach)				
			}
			def players = fields[3].split(':')
			players.each { lastName ->
				def player = Player.findByLastName(lastName)
				team.addToPlayers(player)
			}

			if (finishCreateObject(team))
				teams << team
		}
		
		return teams
	}

	def createCoachEvaluations() {
		def coachEvaluations = []
		new File('exampleData/coachEvaluations.csv').eachLine { line ->
			def fields = line.split(',')
			def team = Team.findByName(fields[1])
			def evaluation = new Report (
				reportType: 'periodicCoach',
				coach: Coach.findByLastName(fields[0]),
				team: team,
				season: team.season,
				date: Date.parse('y-M-d', fields[2]),
				physicalRating: fields[4],
				psychologicalRating: fields[5],
				technicalRating: fields[6],
				tacticalRating: fields[7],
				comment: fields[8]
		    )

			def player = Player.findByLastName(fields[3])
			player.addToReports(evaluation)

			if (finishCreateObject(evaluation))
				coachEvaluations << evaluation
		}
		
		return coachEvaluations
	}

	def createSelfEvaluations() {
		def selfEvaluations = []
		new File('exampleData/evaluations.csv').eachLine { line ->
			def fields = line.split(',')
			def team = Team.findByName(fields[0])
			def evaluation = new Report(
				reportType: 'self',
				team: team,
				season: team.season,
				date: Date.parse('y-M-d', fields[1]),
				physicalRating: fields[3],
				psychologicalRating: fields[4],
				technicalRating: fields[5],
				tacticalRating: fields[6],
				comment: fields[7]
		    )

			def player = Player.findByLastName(fields[2])
			player.addToReports(evaluation)

			if (finishCreateObject(evaluation))
				selfEvaluations << evaluation
				
		}
		
		return selfEvaluations
	}

	def createSessionEvaluations() {
		def sessionEvaluations = []
		new File('exampleData/sessionEvaluations.csv').eachLine { line ->
			def fields = line.split(',')
		    def team = Team.findByName(fields[1])
			def evaluation = new Report (
				reportType: 'sessionCoach',
				coach: Coach.findByLastName(fields[0]),
				team: team,
				season: team.season,
				date: Date.parse('y-M-d', fields[2]),
				rating: fields[4]
		    )

			def player = Player.findByLastName(fields[3])
			player.addToReports(evaluation)

			if (finishCreateObject(evaluation))
				sessionEvaluations << evaluation
		}
		
		return sessionEvaluations
	}
	
	def createInjuryReports() {
		def injuries = []
		new File('exampleData/injuries.csv').eachLine { line ->
			def fields = line.split(',')
		    def team = Team.findByName(fields[1])
			def injury = new Report(
				reportType: 'injury',
				coach: Coach.findByLastName(fields[0]),
				team: team,
				season: team.season,
				date: Date.parse('y-M-d', fields[2]),
				injurySeverity: fields[4],
				comment: fields[5]
		    )

			def player = Player.findByLastName(fields[3])
			player.addToReports(injury)

			if (finishCreateObject(injury))
				injuries << injury
		}
		
		return injuries
	}

    def destroy = {
    }
	
}
