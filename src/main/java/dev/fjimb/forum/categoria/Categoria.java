package dev.fjimb.forum.categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
	
	@Entity
	@Table(name= "categorias")
	public class Categoria {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(nullable = false, unique = true, length = 50)
		private String nombre;
		
		@Column(nullable = false, unique = true, length = 60)
		private String slug;
		
		protected Categoria() {}
		
		public Categoria(String nombre, String slug) {
			this.nombre = nombre;
			this.slug = slug;
		}
		
		public Long getId() { return id; }
		public String getNombre() { return nombre; }
		public String getSlug() { return slug; }

}
