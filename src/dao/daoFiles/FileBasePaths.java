package dao.daoFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface FileBasePaths {
	Path FILEBASEFOLDER = Paths.get("myFileBase");
	Path LOGSFOLDER = Paths.get(FILEBASEFOLDER.toString(), "operationsLogs");
	Path CLIENT_TABLE = Paths.get(FILEBASEFOLDER.toString(), "clients.txt");
	Path INDEX_CLIENT = Paths.get(FILEBASEFOLDER.toString(), "clientsLastIndex.txt");
	Path INDEX_ACCOUNT = Paths.get(FILEBASEFOLDER.toString(), "accountsLastIndex.txt");
	Path ACCOUNT_TABLE = Paths.get(FILEBASEFOLDER.toString(), "accounts.txt");
	Path ARCHIVES_TABLE = Paths.get(FILEBASEFOLDER.toString(), "archives.txt");
	Path BANK_AGENCIES_TABLE = Paths.get(FILEBASEFOLDER.toString(), "agencies.txt");

	private static void createFileOrDirectory(Path path, boolean isFile, String header) {

		if (!isFile) {
			if (!path.toFile().exists()) {
				path.toFile().mkdir();
				System.out.println(path.getFileName() + " a été créé avec succès");
			} else
				System.out.println(path.getFileName() + " existe déjà");

		} else {
			if (!path.toFile().exists()) {
				try {
					path.toFile().createNewFile();
					Files.writeString(path, header);
					System.out.println(path.getFileName() + " a été créé avec succès");

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else
				System.out.println(path.getFileName() + " existe déjà");

		}

	}

	static void changeFile(Path path, String header) {

		if (path.toFile().exists()) {
			try {
				path.toFile().delete();
				path.toFile().createNewFile();
				Files.writeString(path, header);
				System.out.println(path.getFileName() + " a été changé avec succès");

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			System.out.println(path.getFileName() + " n'existe pas");

	}

	static void createFileBase() {

		createFileOrDirectory(FILEBASEFOLDER, false, "NULL");
		createFileOrDirectory(LOGSFOLDER, true, LOG_TABLE_HEADER);
		createFileOrDirectory(CLIENT_TABLE, true, CLIENT_TABLE_HEADER);
		createFileOrDirectory(ACCOUNT_TABLE, true, ACCOUNT_TABLE_HEADER);
		createFileOrDirectory(ARCHIVES_TABLE, true, ARCHIVES_TABLE_HEADER);
		createFileOrDirectory(BANK_AGENCIES_TABLE, true, AGENCY_TABLE_HEADER);
		createFileOrDirectory(INDEX_ACCOUNT, true, null);

	}

	String CLIENT_TABLE_HEADER = "ID\t\t\tNOM\t\t\tPRENOM\t\t\tLOGIN\t\t\tMOT DE PASS\t\t\tCIN\t\t\tEMAIL\t\t\tTELEPHONE\t\t\tSEXE\t\t\tID_AGENCE\n";
	String ACCOUNT_TABLE_HEADER = "ID\t\t\tDATE_CREATION\t\t\tSOLDE\t\t\tID_CLIENT\n";
	String AGENCY_TABLE_HEADER = "ID\t\t\tNOM\t\t\t\t\t\tEMAIL\t\t\t\t\t\tTELEPHONE\t\tADRESSE\n";
	String LOG_TABLE_HEADER = "NUM_COMPTE\t\t\tDATE\t\t\tTYPE\t\t\tMESSAGE\n";
	String ARCHIVES_TABLE_HEADER = "NUM_COMPTE\t\t\tDATE_CREATION\t\t\tDATE_SUPPRESSION\t\t\tCLIENT\n";

	public static void main(String[] args) {
		createFileBase();

	}
}
