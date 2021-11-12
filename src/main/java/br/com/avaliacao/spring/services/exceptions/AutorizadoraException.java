package br.com.avaliacao.spring.services.exceptions;

public class AutorizadoraException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AutorizadoraException(String msg) {
		super(msg);
	}

	public AutorizadoraException(String msg, Throwable cause) {
		super(msg, cause);
	}

}