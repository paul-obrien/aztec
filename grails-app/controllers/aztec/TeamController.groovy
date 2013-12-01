package aztec

import java.util.Date;

import grails.plugin.springsecurity.annotation.Secured

class TeamController {

   def springSecurityService
	
    @Secured(['ROLE_COACH'])
	def sessionReport() {
		[team : Team.get(params['id']), currentUser : currentUser()]
	}
	
    @Secured(['ROLE_COACH'])
	def submitSessionReport() {
	   def team = Team.get(params['team_id'])
	   team.players.each() { player ->
		   def report = new Report(date	: new Date(), coach : currentUser(), team : team, season : team.season, player : player, 
			   reportType : Report.SESSION_COACH_REPORT_TYPE, overallRating : params["player_${player.id}"])
		   report.save()		   
	   }
			   
	   if (params['injury_severity'] != -1) {
	     def report = new Report(date : new Date(), coach : currentUser(), team : team, season : team.season,
			 player : Player.get(params['injured_player_id']), reportType : Report.INJURY_REPORT_TYPE, 
			 injurySeverity : params['injury_severity'], comment: params['injury_comment'])
		 report.save()
	   }
	   
	  redirect(controller: "homepage", action: "coach", params: [action : 'sessionReport'])
	}
	
	private currentUser() {
		User.get(springSecurityService.principal.id)
	}
}
