package dao.daoFiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import dao.IDao;
import presentation.modele.Agence;

public class AgenceDao implements IDao<Agence, Long> {

	@Override
	public List<Agence> findAll() {
		List<Agence> agences = new ArrayList<>();

		try {
			List<String> lines = Files.readAllLines(FileBasePaths.BANK_AGENCIES_TABLE, StandardCharsets.UTF_8);
			lines.remove(0);

			if (!lines.isEmpty())
				agences = lines.stream().map(line -> {
					Agence ag = null;
					StringTokenizer st = new StringTokenizer(line, "\t");

					long id = Long.parseLong(st.nextToken());
					String nom = st.nextToken();
					String email = st.nextToken();
					String tel = st.nextToken();
					String adresse = st.nextToken();

					ag = new Agence(nom, email, tel, adresse);
					ag.setId(id);
					return ag;
				}).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return agences;

	}

	@Override
	public Agence findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agence save(Agence t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Agence> saveAll(List<Agence> liste) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agence update(Agence t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Agence t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
