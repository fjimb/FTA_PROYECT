package dev.fjimb.forum.categoria;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.fjimb.forum.categoria.CategoriaDtos.CategoriaResponse;
import dev.fjimb.forum.categoria.CategoriaDtos.CrearCategoriaRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	private final CategoriaService service;
	
	public CategoriaController(CategoriaService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<CategoriaResponse> Listar() {
		return service.listar();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaResponse crear(@Valid @RequestBody CrearCategoriaRequest req) {
		return service.crear(req);
	}

}
