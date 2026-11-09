package dev.fjimb.forum.common;

import java.text.Normalizer;

public final class Slugs {
	
	private Slugs() {}
	
	public static String of(String texto) {
		return Normalizer.normalize(texto, Normalizer.Form.NFD)
				.replaceAll("\\p{M}", "") // <- quita tildes
				.toLowerCase() // <- minusculas - Guias -> guias
				.replaceAll("[^a-z0-9]+", "-") // <- espacios y simbolos -> "-"
				.replaceAll("(^-|-$)", ""); // <- quita guiones al inicio / final
	}

}
