package dev.fjimb.forum.publicacion;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PublicacionDtos {

	//creacion del cliente
	public record CrearPublicacionRequest(
			@NotBlank @Size(max = 150) String titulo,
			@NotBlank String contenido,
			@NotNull TipoPublicacion tipo,
			@NotBlank String categoriaSlug
	) {}
	
	//salida del detalle de la publicacion del cliente
	public record PublicacionResponse(
			Long id,
			String titulo,
			String slug,
			String contenido,
			TipoPublicacion tipo,
			String categoria,
			Instant creadoEn,
			Instant editadoEn
	) {
		static PublicacionResponse from(Publicacion p) {
			return new PublicacionResponse(
					p.getId(), p.getTitulo(), p.getSlug(), p.getContenido(),
					p.getTipo(), p.getCategoria().getNombre(), p.getCreadoEn(), p.getEditadoEn());
		}
	}
	
	// version liviana para listados (es una salida)
	public record PublicacionResumen(
			Long id,
			String titulo,
			String slug,
			TipoPublicacion tipo,
			String categoria,
			Instant creadoEn
	) {
		static PublicacionResumen from(Publicacion p) {
			return new PublicacionResumen(
					p.getId(), p.getTitulo(), p.getSlug(), p.getTipo(), 
					p.getCategoria().getNombre(), p.getCreadoEn());
		}
	}
}
