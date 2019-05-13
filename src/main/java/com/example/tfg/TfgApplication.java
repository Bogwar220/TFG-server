package com.example.tfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.example.tfg.controladores.ControladorEjercicio;

@SpringBootApplication
@EntityScan( basePackages = {"com.example.tfg.modelos"})
public class TfgApplication {

	public static void main(String[] args) {
		SpringApplication.run(TfgApplication.class, args);
	}

}
