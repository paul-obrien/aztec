class UrlMappings {

	static mappings = {
        "/login/$action"(controller: "login")
		"/logout/$action"(controller: "logout")
		"/coaches"(controller: "coach", action: "index")
		"/"(controller:"homepage", action:"index")
		"/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "500"(view:'/error')
	}
}
