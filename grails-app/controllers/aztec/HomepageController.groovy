package aztec

import grails.plugin.springsecurity.annotation.Secured

class HomepageController {

    def springSecurityService
	
	@Secured(['IS_AUTHENTICATED_REMEMBERED'])
	def index() { 
		user = currentUser()
		if (user.hasRole('ROLE_ADMIN'))
		  render(template: "admin")
		else if (user.hasRole('ROLE_COACH'))
		  render(template: "coach")		
		else if (user.hasRole('ROLE_PLAYER'))
		  render(template: "player")		
	}
	
	private currentUser() {
		User.get(springSecurityService.principal.id)
	}
}
