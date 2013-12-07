package aztec

class ApplicationController {

    def springSecurityService
	
	// Add the currentUser to the model
	protected model(controllerModel) {
	  controllerModel['currentUser'] = currentUser()
	  return controllerModel	
	}
	
	protected currentUser() {
		User.get(springSecurityService.principal.id)
	}
	
	protected isCurrentUserCoach() {
		currentUser().authorities.any { it.authority == "ROLE_ADMIN" || it.authority == "ROLE_COACH" }
	}
}
