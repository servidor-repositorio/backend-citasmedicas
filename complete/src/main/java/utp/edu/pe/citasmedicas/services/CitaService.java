package utp.edu.pe.citasmedicas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utp.edu.pe.citasmedicas.assembler.ConvertidorCitas;
import utp.edu.pe.citasmedicas.entity.EntidadCita;
import utp.edu.pe.citasmedicas.model.Cita;
import utp.edu.pe.citasmedicas.repo.RepoCita;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

	@Autowired
	private RepoCita repoCita;

	@Autowired
	private ConvertidorCitas convertidorCitas;

	public Cita crear(Cita cita) {

		return convertidorCitas.convertirCita(repoCita.save(convertidorCitas.convertirEntidadCita(cita)));

	}

	public List<Cita> obtener() {

		return convertidorCitas.convertirListaCitas((repoCita.findAll()));

	}

	public List<Cita> obtenerPorPaginacion(Pageable pageable) {

		return convertidorCitas.convertirListaCitas(repoCita.findAll(pageable).getContent());

	}

	public Cita obtenerPorId(Integer id) {

		Optional<EntidadCita> optionalCita = repoCita.findById(id);
		Cita cita;

		if (optionalCita.isPresent()) {
			cita = convertidorCitas.convertirCita(optionalCita.get());

			return cita;
		} else {
			return null;
		}

	}

	public Cita obtenerPorHorarioCitaYPaciente(String horarioCita, String paciente) {


		Optional<EntidadCita> optionalCita = repoCita.findByHorarioCitaEntidadAndPacienteEntidad(horarioCita, paciente);
		Cita cita;

		if (optionalCita.isPresent()) {
			cita = convertidorCitas.convertirCita(optionalCita.get());
			return cita;
		} else {
			return null;
		}
	}

	public Cita obtenerPorHorarioCitaYMedico(String horarioCita, String medico) {

		Optional<EntidadCita> optionalCita = repoCita.findByHorarioCitaEntidadAndMedicoEntidad(horarioCita, medico);
		Cita cita;

		if (optionalCita.isPresent()) {
			cita = convertidorCitas.convertirCita(optionalCita.get());
			return cita;
		} else {
			return null;
		}

	}
}
