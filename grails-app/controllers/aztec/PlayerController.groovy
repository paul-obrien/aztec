package aztec

import grails.plugin.springsecurity.annotation.Secured

class PlayerController {

    @Secured(['permitAll'])
	def reports() {
	  	println "ID = " + params["id"]
		render(view: 'reports', model: [ player : Player.findByLastName("Ryder") ])
    }
		
    def index() { 
		println "In index"
	}
}
