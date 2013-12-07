package aztec

import grails.plugin.springsecurity.annotation.Secured

class HomepageController extends ApplicationController {

	@Secured(['IS_AUTHENTICATED_REMEMBERED'])
	def index() { 
		def user = currentUser()
		if (user.authorities.any { it.authority == "ROLE_ADMIN" })
		  redirect(action: "admin")
		else if (user.authorities.any { it.authority == "ROLE_COACH" })
		  redirect(action: "coach")		
		else if (user.authorities.any { it.authority == "ROLE_PLAYER" })
		  redirect(action: "player")		
	}
	
	@Secured(['ROLE_ADMIN'])
	def admin() {
		model([:])
	}
	
	@Secured(['ROLE_COACH'])
	def coach() {
		model([action : params['action']])
	}
	
	@Secured(['ROLE_PLAYER'])
	def player() {
		model([:])
	}
}
