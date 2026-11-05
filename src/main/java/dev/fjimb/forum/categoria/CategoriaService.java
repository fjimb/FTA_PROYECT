package dev.fjimb.forum.categoria;

import java.text.Normalizer;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.fjimb.forum.categoria.CategoriaDtos.CategoriaResponse;
import dev.fjimb.forum.categoria.CategoriaDtos.CrearCategoriaRequest;

@Service
public class CategoriaService {
	
	private final CategoriaRepository repo;
	
	public CategoriaService(CategoriaRepository repo) {
		this.repo = repo;
	}
	
	@Transactional(readOnly = true)
	public List<CategoriaResponse> listar() {
		return repo.findAll().stream().map(CategoriaResponse::from).toList();
	}
	
	@Transactional
	public CategoriaResponse crear(CrearCategoriaRequest req) {
		String slug = slugify(req.nombre());
		if (repo.existsBySlug(slug)) {
			throw new IllegalArgumentException("La categoria ya existe");
		}
		return CategoriaResponse.from(repo.save(new Categoria(req.nombre().trim(), slug)));
	}
	
	private static String slugify(String texto) {
		return Normalizer.normalize(texto, Normalizer.Form.NFD)
				.replaceAll("\\p{M}", "")
				.toLowerCase()
				.replaceAll("[^a-z0-9]+", "-")
				.replaceAll("(^-|-$)", "");
	}
}