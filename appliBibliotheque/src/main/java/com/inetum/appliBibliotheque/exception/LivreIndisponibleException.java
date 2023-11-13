package com.inetum.appliBibliotheque.exception;

public class LivreIndisponibleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LivreIndisponibleException() {
		this("livre indisponible");
	}

	public LivreIndisponibleException(String message) {
		super(message);
	}

	public LivreIndisponibleException(String message, Throwable cause) {
		super(message, cause);
	}

}
