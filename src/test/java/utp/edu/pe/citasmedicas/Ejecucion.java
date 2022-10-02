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
class Ejecucion {

	@Autowired
	private ConvertidorMedicos convertidorMedicos;
	@Autowired
	private ConvertidorPacientes convertidorPacientes;
	@Autowired
	private ConvertidorCitas convertidorCitas;

	@Autowired
	private RepoMedico repoMedico;

	@Autowired
	private RepoPaciente repoPaciente;

	@Autowired
	private RepoCita repoCita;

	@Test
	@Sql(scripts = { "/insertarMedicos.sql", "/insertarPacientes.sql",
			"/insertarCitas.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	void insersionRegistrosEntidades() {
		List<Medico> medicos = convertidorMedicos.convertirListaMedicos(repoMedico.findAll());

		assertTrue((medicos.size() == 6));

		List<Paciente> pacientes = convertidorPacientes.convertirListaPacientes(repoPaciente.findAll());

		assertTrue(pacientes.size() == 6);

		List<Cita> citas = convertidorCitas.convertirListaCitas(repoCita.findAll());

		assertTrue(citas.size() == 1);

	}

}
