package aztec
import grails.plugin.springsecurity.annotation.Secured

class CoachController {

    @Secured(['ROLE_COACH'])
	def index() { 
		[]
	}
}
