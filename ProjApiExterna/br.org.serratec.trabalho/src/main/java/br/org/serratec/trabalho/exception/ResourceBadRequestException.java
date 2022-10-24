package br.org.serratec.trabalho.exception;

public class ResourceBadRequestException extends RuntimeException {

	public ResourceBadRequestException(String mensagem) {
		super(mensagem);
	}
}
