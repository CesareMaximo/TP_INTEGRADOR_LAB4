package Exceptions;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {

	}

	@Override
	public String getMessage() {
		return "Usuario inexistente o contrase�a mal ingresada";
	}

}
