package com.example.tfg.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tfg.modelos.Ejercicio;
import com.example.tfg.repositorios.EjercicioRepository;

@Controller
public class ControladorEjercicio {

	@Autowired
	private EjercicioRepository repEj;

	@PostMapping("/ej")
	@ResponseBody Object postEj(@RequestBody Ejercicio ejercicio) {
		repEj.save(ejercicio);
		return ejercicio;
	}
	
	@GetMapping(path = "/ej")
	@ResponseBody Object getCand() {
		
		if(repEj.findAll().size() < 1) {
			anadirEj();
		}
		return repEj.findAll();
	}
	
	private void crear(String nombre, String descripcion) {
		Ejercicio ejercicio = new Ejercicio();
		ejercicio.setNombre(nombre);
		ejercicio.setDescripcion(descripcion);
		postEj(ejercicio);
	}
	
	private void anadirEj() {
		crear("Flexiones", "En posición de cúbito ventral, sosténte con la punta de los pies y la palma de las manos. Comienza a flexionar los brazos hasta casi rozar el piso con la nariz, y vuelve a la posición inicial estirando los brazos.");
		crear("Sentadillas", "Desde una posición erguida, flexiona rodillas y cadera como si quisieras sentarte en una silla imaginaria. Para hacer más intenso el trabajo puedes agregar mancuernas, o usar tu propio peso colocando las manos detrás de la nuca.");
		crear("Remo para brazos","De pie, con las rodillas ligeramente flexionadas, inclina el tronco a 45 grados dejando la espalda bien recta. Usa mancuernas o una barra sostenida a una distancia algo mayor que el ancho de hombros, y los brazos extendidos.");
		crear("Dippings", "Comienza sentándote en un escalón con las manos a los lados de la cadera. Avanza el cuerpo hasta dejarlo en el vacío con los brazos flexionados y los codos hacia atrás.");
		crear("Curl de biceps", "De pie con las piernas ligeramente separadas, toma las mancuernas con los codos pegados a los lados del cuerpo. Luego sube y baja lentamente las mancuernas hasta estirar completamente los brazos.");
		crear("Desplantes", "Comienza de pie con las piernas ligeramente separadas, y si usas mancuernas, mantenlas a los costados del cuerpo. Da un paso largo manteniendo el tronco bien erguido, tratando de que la rodilla de atrás quede casi tocando el piso.");
		crear("Press de hombros", "Sentado con la espalda bien recta, toma las mancuernas y colócalas a la altura de los hombros. Empuja las mancuernas hasta estirar los brazos, apuntando con los puños al techo, y regresa a la posición de inicio.");
		crear("Elevacion de pies", "Realiza una extensión de pies o flexión plantar, intentando bajar los talones lo más que puedas. Luego elévate sobre los dedos hasta quedar \"en punta de pie\", y trata de mantener siempre las rodillas ligeramente flexionadas.");
		crear("Abdominales", "Realiza una contracción que acerque el tórax hacia el pubis, como si quisieras mirarte el ombligo. Esta flexión debe ser de unos 30 grados, no hace falta elevarte por completo.");
		crear("Planchas para abdominales", "Comienza boca abajo, con los codos y antebrazos debajo de tu pecho. Forma con el cuerpo una especie de puente, sosteniéndote solo con los antebrazos y la punta de los pies.");
		crear("Descanso", "Descansar");
	}
}
