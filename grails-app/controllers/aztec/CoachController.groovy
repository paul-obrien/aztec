package aztec
import grails.plugin.springsecurity.annotation.Secured

class CoachController extends ApplicationController {
	
	@Secured(['ROLE_ADMIN'])
	def search() {
		def coach = Coach.findByLastName("${params['coach_name']}")
		render(contentType: 'text/json') {
		  coach ? ['id' : coach.id] : []
		}
	}
	
	@Secured(['ROLE_ADMIN'])
	def edit() {
		model([coach : Coach.get(params['id']), doneSubmit : params['doneSubmit']])
	}
	
	@Secured(['ROLE_ADMIN'])
	def create() {
		render(view: "edit", model: model([:]))		
	}
	
	@Secured(['ROLE_ADMIN'])
	def editSubmit() {
		def coach = (params['coach_id'] ? Coach.get(params['coach_id']) : new Coach(password : 'password'))
		coach.properties = params
		coach.save()
		redirect(action: "edit", id: coach.id, params : [doneSubmit : true])
	}
}
