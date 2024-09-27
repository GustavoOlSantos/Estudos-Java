package services;

import exceptions.DomainException;

public class TelefoneMask {

	private static final String REGEX_TELEFONE = "([\\d]{4})([\\d]{4})";
	private static final String REPLACE_TELEFONE = "$1\\-$2";
	public static final int LENGTH_TELEFONE = 8;

	private static final String REGEX_CELULAR = "([\\d]{5})([\\d]{4})";
	private static final String REPLACE_CELULAR = "$1\\-$2";
	public static final int LENGTH_CELULAR = 9;

	public static String Mask(String telefone) throws DomainException {

		if (telefone.length() == LENGTH_CELULAR) {
			return telefone.replaceAll(REGEX_CELULAR, REPLACE_CELULAR);
		} else if (telefone.length() == LENGTH_TELEFONE) {
			return telefone.replaceAll(REGEX_TELEFONE, REPLACE_TELEFONE);
		} else {
			throw new DomainException("Telefone Inválido.");
		}
	}

	public static String Unmask(String telefone) {
		return telefone.replace("\\.", "").replace("\\/", "").replace("-", "");
	}

}
