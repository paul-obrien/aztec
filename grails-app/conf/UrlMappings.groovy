class UrlMappings {

	static mappings = {
        "/login/$action"(controller: "login")
		"/logout/$action"(controller: "logout")
		"/coaches"(controller: "coach", action: "index")
		"/player/$id/reports"(controller: "player", action:"reports")
		"/"(controller:"homepage", action:"index")
		"/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
