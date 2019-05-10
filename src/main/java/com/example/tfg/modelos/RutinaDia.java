package com.example.tfg.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "rutina_dia")
public class RutinaDia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Rutina rutina;
	
	@ManyToOne
	private Dia dia;
	
	public RutinaDia() {
		
	}

	public RutinaDia(long id, Rutina rutina, Dia dia) {
		super();
		this.id = id;
		this.rutina = rutina;
		this.dia = dia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Rutina getRutina() {
		return rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

	public Dia getDia() {
		return dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}	
}
