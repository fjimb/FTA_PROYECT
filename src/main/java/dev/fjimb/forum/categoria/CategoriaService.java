package dev.fjimb.forum.categoria;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.fjimb.forum.categoria.CategoriaDtos.CategoriaResponse;
import dev.fjimb.forum.categoria.CategoriaDtos.CrearCategoriaRequest;
import dev.fjimb.forum.common.Slugs;

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
		String slug = Slugs.of(req.nombre());
		if (repo.existsBySlug(slug)) {
			throw new IllegalArgumentException("La categoria ya existe");
		}
		return CategoriaResponse.from(repo.save(new Categoria(req.nombre().trim(), slug)));
	}
	
}