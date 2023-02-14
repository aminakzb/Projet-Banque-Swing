package dao.daoFiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import dao.IDao;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.TypeLog;

public class LogDao implements IDao<Log, Long> {

	@Override
	public List<Log> findAll() {
		List<Log> logs = new ArrayList<>();

		try {
			List<String> lines = Files.readAllLines(FileBasePaths.LOGSFOLDER, StandardCharsets.UTF_8);
			lines.remove(0);

			if (!lines.isEmpty())
				logs = lines.stream().map(line -> {
					Log lg = null;
					StringTokenizer st = new StringTokenizer(line, "\t");
					String compteAssocie = (String) (st.nextToken());
					LocalDate date = LocalDate.parse(st.nextToken());
					TypeLog type = TypeLog.valueOf((st.nextToken()));
					String message = (String) (st.nextToken());

					lg = new Log(compteAssocie, date, type, message);
					return lg;
				}).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return logs;
	}

	public List<Log> findLogsOfUser(Client user) {
		List<Log> logs = new ArrayList<>();
		List<Compte> comptes = new CompteDao().findAll().stream()
				.filter(compte -> compte.getPropriétaire().getId().equals(user.getId())).toList();
		for (int i = 0; i < comptes.size(); i++) {
			String numCompte = comptes.get(i).getNumeroCompte();
			logs.addAll(findAll().stream().filter(log -> log.getNumCompteLog().equals(numCompte))
					.collect(Collectors.toList()));
		}
		return logs;
	}

	public List<Log> findLogsOfLastWeek(Client user) {
		List<Log> logs = new ArrayList<>();
		List<Compte> comptes = new CompteDao().findAll().stream()
				.filter(compte -> compte.getPropriétaire().getId().equals(user.getId())).toList();
		for (int i = 0; i < comptes.size(); i++) {
			String numCompte = comptes.get(i).getNumeroCompte();
			logs.addAll(findAll().stream()
					.filter(log -> log.getNumCompteLog().equals(numCompte)
							&& log.getDate().until(LocalDate.now(), ChronoUnit.DAYS) <= 7)
					.collect(Collectors.toList()));
		}
		return logs;
	}

	public List<Log> findLogsOfLastMonth(Client user) {
		List<Log> logs = new ArrayList<>();
		List<Compte> comptes = new CompteDao().findAll().stream()
				.filter(compte -> compte.getPropriétaire().getId().equals(user.getId())).toList();
		for (int i = 0; i < comptes.size(); i++) {
			String numCompte = comptes.get(i).getNumeroCompte();
			logs.addAll(findAll().stream()
					.filter(log -> log.getNumCompteLog().equals(numCompte)
							&& log.getDate().until(LocalDate.now(), ChronoUnit.DAYS) <= 30)
					.collect(Collectors.toList()));
		}
		return logs;
	}

	@Override
	public Log findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log save(Log log) {
		String logStr = log.getNumCompteLog() + "\t\t\t" + log.getDate() + "\t" + log.getType() + "\t\t"
				+ log.getMessage() + "\t\t\n";

		try {
			Files.writeString(FileBasePaths.LOGSFOLDER, logStr, StandardOpenOption.APPEND);
			System.out.println("Log du compte " + log.getNumCompteLog() + " ajouté avec succès ^_^");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return log;
	}

	@Override
	public List<Log> saveAll(List<Log> liste) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log update(Log t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Log t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
