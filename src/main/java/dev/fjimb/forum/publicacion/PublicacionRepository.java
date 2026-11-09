package dev.fjimb.forum.publicacion;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

	@Override
	@EntityGraph(attributePaths = "categoria")
	Page<Publicacion> findAll(Pageable pageable);
	
	@EntityGraph(attributePaths = "categoria")
	Page<Publicacion> findByCategoriaSlug(String slug, Pageable pageable);
	
	@EntityGraph(attributePaths = "categoria")
	Optional<Publicacion> findBySlug(String slug);
	
	boolean existsBySlug(String slug);
}
