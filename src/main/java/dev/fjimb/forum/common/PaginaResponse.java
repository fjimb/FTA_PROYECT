package dev.fjimb.forum.common;

import java.util.List;

import org.springframework.data.domain.Page;

public record PaginaResponse<T>(
		
		List<T> contenido,
		int pagina,
		int tamano,
		long total,
		int totalPaginas
) {
	
	public static <T> PaginaResponse<T> from(Page<T> p) {
		
		return new PaginaResponse<>(
				p.getContent(),
				p.getNumber(),
				p.getSize(),
				p.getTotalElements(),
				p.getTotalPages()
		);
	}
}

