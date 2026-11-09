package dev.fjimb.forum.publicacion;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import dev.fjimb.forum.categoria.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 150)
	private String titulo;
	
	@Column(nullable = false, unique = true, length = 180)
	private String slug;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String contenido;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private TipoPublicacion tipo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Instant creadoEn;
	
	@UpdateTimestamp
	private Instant editadoEn;
	
	protected Publicacion() {}
	
	public Publicacion(String titulo, String slug, String contenido, TipoPublicacion tipo, Categoria categoria) {
		this.titulo = titulo;
		this.slug = slug;
		this.contenido = contenido;
		this.tipo = tipo;
		this.categoria = categoria;
	}
	
	public Long getId() { return id; }
	public String getTitulo() { return titulo; }
	public String getSlug() { return slug; }
	public String getContenido() { return contenido; }
	public TipoPublicacion getTipo() { return tipo; }
	public Categoria getCategoria() { return categoria; }
	public Instant getCreadoEn() { return creadoEn; }
	public Instant getEditadoEn() { return editadoEn; }
}
