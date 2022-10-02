package utp.edu.pe.citasmedicas;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import utp.edu.pe.citasmedicas.assembler.ConvertidorCitas;
import utp.edu.pe.citasmedicas.assembler.ConvertidorMedicos;
import utp.edu.pe.citasmedicas.assembler.ConvertidorPacientes;
import utp.edu.pe.citasmedicas.model.Cita;
import utp.edu.pe.citasmedicas.model.Medico;
import utp.edu.pe.citasmedicas.model.Paciente;
import utp.edu.pe.citasmedicas.repo.RepoCita;
import utp.edu.pe.citasmedicas.repo.RepoMedico;
import utp.edu.pe.citasmedicas.repo.RepoPaciente;

@SpringBootTest
class Preparacion {

	@Autowired
	private RepoMedico repoMedico;

	@Autowired
	private RepoPaciente repoPaciente;

	@Autowired
	private RepoCita repoCita;

	@Autowired
	private ConvertidorMedicos convertidorMedicos;
	@Autowired
	private ConvertidorPacientes convertidorPacientes;
	@Autowired
	private ConvertidorCitas convertidorCitas;

	@Test
	@Sql(scripts = "/formatearEntidades.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	void validarPreparacion() {
		List<Medico> medicos = convertidorMedicos.convertirListaMedicos(repoMedico.findAll());

		assertTrue(medicos.isEmpty());

		List<Paciente> pacientes = convertidorPacientes.convertirListaPacientes(repoPaciente.findAll());

		assertTrue(pacientes.isEmpty());

		List<Cita> citas = convertidorCitas.convertirListaCitas(repoCita.findAll());

		assertTrue(citas.isEmpty());

	}

}
