package br.org.serratec.trabalho.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}
}