package utp.edu.pe.citasmedicas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.citasmedicas.model.Cita;
import utp.edu.pe.citasmedicas.model.Response;
import utp.edu.pe.citasmedicas.services.CitaService;
import utp.edu.pe.citasmedicas.services.MedicoService;
import utp.edu.pe.citasmedicas.services.ValidandoDatos;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "/citas")
public class ControladorCitas {

	@Autowired
    CitaService citaService;
	@Autowired
    MedicoService medicoService;

	@Autowired
	ValidandoDatos validadorRegistros;

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<Cita> cargarCitas() {

		return citaService.obtener();
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cita> cargarCita(@PathVariable("id") Integer id) {

		return new ResponseEntity<>(citaService.obtenerPorId(id), HttpStatus.OK);
	}

	@PostMapping 
	public Response insertarCita(@RequestBody @Valid Cita cita) {

		return validadorRegistros.validarRegistro(cita);
	}

}
