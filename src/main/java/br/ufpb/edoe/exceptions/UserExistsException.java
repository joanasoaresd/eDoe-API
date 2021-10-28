package br.ufpb.edoe.exceptions;

public class UserExistsException extends IllegalArgumentException {

    private String message;

    public UserExistsException(String msg){
        super();
        this.message = msg;
    }

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}
