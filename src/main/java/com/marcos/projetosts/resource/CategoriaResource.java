package com.marcos.projetosts.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public String list() {
		return "Rest está funcionando";
	}

	
}
