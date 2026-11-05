package dev.fjimb.forum.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaDtos {

	public record CrearCategoriaRequest(
		@NotBlank @Size(max = 50) String nombre
	) {}
	
	public record CategoriaResponse(Long id, String nombre, String slug) {
		static CategoriaResponse from (Categoria c) {
			return new CategoriaResponse(c.getId(), c.getNombre(), c.getSlug());
		}
	}
}
