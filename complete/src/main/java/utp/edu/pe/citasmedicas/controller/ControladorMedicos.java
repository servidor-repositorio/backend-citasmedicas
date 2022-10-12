package utp.edu.pe.citasmedicas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.citasmedicas.model.Medico;
import utp.edu.pe.citasmedicas.services.MedicoService;
import utp.edu.pe.citasmedicas.services.ValidandoDatos;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "/medicos")
public class ControladorMedicos {

	@Autowired
    MedicoService medicoService;

	@Autowired
	ValidandoDatos validadorRegistros;

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<Medico> cargarMedicos() {

		return medicoService.obtener();

	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Medico cargarMedico(@PathVariable("id") String id) {

		return medicoService.obtenerPorId(id);
	}

	@DeleteMapping(value = "/{id}")
	public Medico eliminarMedico(@PathVariable("id") String id) {

		return medicoService.borrar(id);

	}

	@PutMapping(path = "/{id}")
	public Medico actualizarMedico(@RequestBody @Valid Medico medicoActualizar,@PathVariable("id") String id) {

		medicoActualizar.setIdentificacion(id);
		return medicoService.crear(medicoActualizar);
		

	}

	@PostMapping
	public Medico insertarMedico(@RequestBody @Valid  Medico medicoActualizar) {

		return medicoService.crear(medicoActualizar);
		

	}

}
