package com.example.tfg.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "rutinas")
public class Rutina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column (name = "repeticiones")
	private int repeticiones;
	
	@ManyToOne
	private Dia dia;
	
	@ManyToOne
	private Ejercicio ejercicio;
	
	public Rutina() {
		
	}

	public Rutina(long id, int repeticiones, Dia dia, Ejercicio ejercicio) {
		super();
		this.id = id;
		this.repeticiones = repeticiones;
		this.dia = dia;
		this.ejercicio = ejercicio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public Dia getDia() {
		return dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	public Ejercicio getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}	
}
