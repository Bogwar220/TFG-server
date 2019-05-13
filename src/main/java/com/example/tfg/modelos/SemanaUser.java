package com.example.tfg.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "semana_user")
public class SemanaUser {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Semana semana;
	
	@ManyToOne
	private User user;
	
	@Column(name = "seleccionado")
	private int seleccionado = 0;
	
	public SemanaUser() {
		
	}

	public SemanaUser(long id, Semana semana, User user, int seleccionado) {
		super();
		this.id = id;
		this.semana = semana;
		this.user = user;
		this.seleccionado = seleccionado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Semana getSemana() {
		return semana;
	}

	public void setSemana(Semana semana) {
		this.semana = semana;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(int seleccionado) {
		this.seleccionado = seleccionado;
	}	
}