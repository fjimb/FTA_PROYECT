package dev.fjimb.forum.publicacion;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.fjimb.forum.common.PaginaResponse;
import dev.fjimb.forum.publicacion.PublicacionDtos.CrearPublicacionRequest;
import dev.fjimb.forum.publicacion.PublicacionDtos.PublicacionResponse;
import dev.fjimb.forum.publicacion.PublicacionDtos.PublicacionResumen;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    private final PublicacionService service;

    public PublicacionController(PublicacionService service) {
        this.service = service;
    }

    // GET /api/publicaciones?categoria=guias&page=0&size=10
    @GetMapping
    public PaginaResponse<PublicacionResumen> listar(
            @RequestParam(required = false) String categoria,
            @PageableDefault(size = 10, sort = "creadoEn", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.listar(categoria, pageable);
    }

    // GET /api/publicaciones/instalar-arch-paso-a-paso
    @GetMapping("/{slug}")
    public PublicacionResponse obtener(@PathVariable String slug) {
        return service.obtener(slug);
    }

    // POST /api/publicaciones
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublicacionResponse crear(@Valid @RequestBody CrearPublicacionRequest req) {
        return service.crear(req);
    }

    // DELETE /api/publicaciones/instalar-arch-paso-a-paso
    @DeleteMapping("/{slug}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String slug) {
        service.eliminar(slug);
    }
}
