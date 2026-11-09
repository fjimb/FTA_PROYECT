package dev.fjimb.forum.publicacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.fjimb.forum.categoria.Categoria;
import dev.fjimb.forum.categoria.CategoriaRepository;
import dev.fjimb.forum.common.NotFoundException;
import dev.fjimb.forum.common.PaginaResponse;
import dev.fjimb.forum.common.Slugs;
import dev.fjimb.forum.publicacion.PublicacionDtos.CrearPublicacionRequest;
import dev.fjimb.forum.publicacion.PublicacionDtos.PublicacionResponse;
import dev.fjimb.forum.publicacion.PublicacionDtos.PublicacionResumen;

@Service
public class PublicacionService {

    private final PublicacionRepository repo;
    private final CategoriaRepository categoriaRepo;

    public PublicacionService(PublicacionRepository repo, CategoriaRepository categoriaRepo) {
        this.repo = repo;
        this.categoriaRepo = categoriaRepo;
    }

    // Listar: todas, o filtradas por categoría si viene el parámetro
    @Transactional(readOnly = true)
    public PaginaResponse<PublicacionResumen> listar(String categoria, Pageable pageable) {
        Page<Publicacion> page = (categoria == null)
                ? repo.findAll(pageable)
                : repo.findByCategoriaSlug(categoria, pageable);

        return PaginaResponse.from(page.map(PublicacionResumen::from));
    }

    // Ver una por su slug
    @Transactional(readOnly = true)
    public PublicacionResponse obtener(String slug) {
        return repo.findBySlug(slug)
                .map(PublicacionResponse::from)
                .orElseThrow(() -> new NotFoundException("Publicación no encontrada"));
    }

    // Crear
    @Transactional
    public PublicacionResponse crear(CrearPublicacionRequest req) {
        Categoria categoria = categoriaRepo.findBySlug(req.categoriaSlug())
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        Publicacion p = new Publicacion(
                req.titulo().trim(),
                slugUnico(req.titulo()),
                req.contenido(),
                req.tipo(),
                categoria);

        return PublicacionResponse.from(repo.save(p));
    }

    // Eliminar
    @Transactional
    public void eliminar(String slug) {
        Publicacion p = repo.findBySlug(slug)
                .orElseThrow(() -> new NotFoundException("Publicación no encontrada"));
        repo.delete(p);
    }

    // Genera "mi-guia", y si ya existe: "mi-guia-2", "mi-guia-3"...
    private String slugUnico(String titulo) {
        String base = Slugs.of(titulo);
        String slug = base;
        int i = 2;
        while (repo.existsBySlug(slug)) {
            slug = base + "-" + i;
            i++;
        }
        return slug;
    }
}