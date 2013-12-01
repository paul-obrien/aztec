package aztec

import java.util.Date;

import grails.plugin.springsecurity.annotation.Secured

class PlayerController {

   def springSecurityService
	
    @Secured(['ROLE_ADMIN', 'ROLE_COACH', 'ROLE_PLAYER'])
	def reports() {
	  	render(view: 'reports', model: [ player : Player.get(params['id']), currentUser : currentUser() ])
    }
		
    @Secured(['ROLE_ADMIN', 'ROLE_COACH'])
    def search() {
		def player = Player.findByLastName("${params['player_name']}")
		render(contentType: 'text/json') {
	      player ? ['id' : player.id] : []
		}
    }

    def getPlayer() {
      if (isCurrentUserCoach())
		return Player.get(params['player_id'])
	  else
		return currentUser()
    }
	
	@Secured(['ROLE_ADMIN', 'ROLE_COACH', 'ROLE_PLAYER'])
	def evaluate() {
		def team = isCurrentUserCoach() ? Team.get(params['team_id']) : currentUser().currentTeam
		[player : getPlayer(), currentUser : currentUser(), team : team]
	}
	
    @Secured(['ROLE_ADMIN', 'ROLE_COACH', 'ROLE_PLAYER'])
	def submitEvaluation() {
      def team = Team.get(params['team_id'])
	  def player = getPlayer()
	  def reportType = isCurrentUserCoach() ? "periodicCoach" : "self"
	  def coach = (isCurrentUserCoach() ? currentUser() : null)
	  def evaluation = new Report(date: new Date(), coach: coach, team: team, season: team.season,
		  player: player, reportType: reportType, psychologicalRating: params['psychological_rating'],
		  psychologicalComment: params['psychological_comment'], physicalRating: params['physical_rating'],
		  physicalComment: params['physical_comment'], technicalRating: params['technical_rating'],
		  technicalComment: params['technical_comment'], tacticalRating: params['tactical_rating'],
		  tacticalComment: params['tactical_comment'], comment: params['overall_comment'])
	  evaluation.save()
	  redirect(action: "reports", id: params['player_id'])
	}
	
	private currentUser() {
		User.get(springSecurityService.principal.id)
	}
	
	private isCurrentUserCoach() {
		currentUser().authorities.any { it.authority == "ROLE_ADMIN" || it.authority == "ROLE_COACH" }
	}
}
