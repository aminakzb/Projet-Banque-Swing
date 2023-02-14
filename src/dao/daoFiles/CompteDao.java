package dao.daoFiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import dao.IDao;
import presentation.modele.Client;
import presentation.modele.Compte;

public class CompteDao implements IDao<Compte, Long> {

	@Override
	public List<Compte> findAll() {
		List<Compte> comptes = new ArrayList<>();

		try {
			List<String> lines = Files.readAllLines(FileBasePaths.ACCOUNT_TABLE, StandardCharsets.UTF_8);
			lines.remove(0);

			if (!lines.isEmpty())
				comptes = lines.stream().map(line -> {
					Compte ctp = null;
					StringTokenizer st = new StringTokenizer(line, "\t");

					String num = st.nextToken();
					LocalDateTime date = LocalDateTime.parse(st.nextToken());
					Double solde = Double.parseDouble(st.nextToken());
					Long idClient = Long.parseLong(st.nextToken());

					Client proprietaire = new ClientDao().findById(idClient);

					ctp = new Compte(num, date, solde, proprietaire);
					return ctp;
				}).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return comptes;
	}

	@Override
	public Compte findById(Long idCompte) {
		return findAll().stream().filter(compte -> compte.getNumeroCompte().equals("b-co00" + idCompte)).findFirst()
				.orElse(null);
	}

	public Compte findByNum(String numCompte) {
		return findAll().stream().filter(compte -> compte.getNumeroCompte().equals(numCompte)).findFirst().orElse(null);
	}

	public Compte findCompteByIdClient(long idClient) {
		return findAll().stream().filter(compte -> compte.getPropriétaire().getId().equals(idClient)).findFirst()
				.orElse(null);
	}

	public long getIncrementedIdByIndexFile(Path path) {
		String idStr = null;
		Long id = 1L;
		try {
			idStr = Files.readString(path);
			if (idStr == "")
				id = 1L;
			else {
				id = Long.parseLong(idStr);
				id++;
			}
		} catch (IOException e) {
			id = 1L;
		}

		try {
			Files.writeString(path, id.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public Compte save(Compte compte) {

		String compteStr = compte.getNumeroCompte() + "\t\t\t" + compte.getDateCreation() + "\t" + compte.getSolde()
				+ "\t\t" + (compte.getPropriétaire() != null ? compte.getPropriétaire().getId() : "NULL") + "\t\t\n";

		try {
			Files.writeString(FileBasePaths.ACCOUNT_TABLE, compteStr, StandardOpenOption.APPEND);
			System.out.println("Compte n ° " + compte.getNumeroCompte() + " a été ajouté avec succès ^_^");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return compte;
	}

	@Override
	public List<Compte> saveAll(List<Compte> listeComptes) {
		return listeComptes.stream().map(compte -> save(compte)).collect(Collectors.toList());
	}

	@Override
	public Compte update(Compte newCompte) {
		List<Compte> comptessUpdated = findAll().stream().map(compte -> {
			if (compte.getNumeroCompte().equals(newCompte.getNumeroCompte()))
				return newCompte;
			else
				return compte;
		}).collect(Collectors.toList());

		try {
			Files.deleteIfExists(FileBasePaths.INDEX_ACCOUNT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);

		saveAll(comptessUpdated);

		return newCompte;
	}

	@Override
	public Boolean delete(Compte comptesToDelete) {

		var comptes = findAll();
		boolean deleted = comptes.remove(comptesToDelete);

		if (deleted) {

			FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);
			saveAll(comptes);
		}

		return deleted;
	}

	public Boolean deleteByNumCompte(String numCompte) {
		var comptes = findAll();
		boolean deleted = comptes.remove(findByNum(numCompte));

		if (deleted) {

			FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);
			saveAll(comptes);
		}

		return deleted;

	}

	public List<Compte> mesComptes(Long id) {
		List<Compte> comptes = new CompteDao().findAll().stream()
				.filter(compte -> compte.getPropriétaire().getId().equals(id)).toList();
		return comptes;
	}

	public void saveArchive(Compte compte) {

		String compteStr = compte.getNumeroCompte() + "\t\t\t" + compte.getDateCreation() + "\t\t\t" + LocalDate.now()
				+ "\t\t" + compte.getPropriétaire().getNomComplet() + "\t\t\n";

		try {
			Files.writeString(FileBasePaths.ARCHIVES_TABLE, compteStr, StandardOpenOption.APPEND);
			System.out.println("Compte n ° " + compte.getNumeroCompte() + " du client "
					+ compte.getPropriétaire().getNomComplet() + " archivé avec succès ^_^");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
