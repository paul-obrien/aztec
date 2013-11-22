import aztec.*

class BootStrap {
	def springSecurityService
	
    def init = { servletContext ->
		def adminRole = Role.findByAuthority("ROLE_ADMIN") ?: new Role(authority: "ROLE_ADMIN").save()
		def coachRole = Role.findByAuthority("ROLE_COACH") ?: new Role(authority: "ROLE_COACH").save()
		def playerRole = Role.findByAuthority("ROLE_PLAYER") ?: new Role(authority: "ROLE_PLAYER").save()
		
		def sampleUsers = [
			'admin@example.com' : [ password: 'admin', role: adminRole ],
			'coach@example.com' : [ password: 'coach', role: coachRole ],
			'player@example.com' : [ password: 'player', role: playerRole] ]

		def users = User.list() ?: []
		if (!users) {
			sampleUsers.each { username, profileAttrs ->
				def user = new User(
					username: username,
					password: profileAttrs.password)
				
				if (user.validate()) {
					user.save(flush:true)
					UserRole.create user, profileAttrs.role
					users << user
				}
				else {
					println("\n\n\nError in account bootstrap for ${username}!\n\n\n")
					user.errors.each { err ->
						println err
					}
				}
			}
		}
    }
    def destroy = {
    }
}
