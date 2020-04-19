package net.endy.server.auth;

public class RegisteredUserException extends RuntimeException {
    
    private static final long serialVersionUID = 3452452345124532145L;
    
    public RegisteredUserException(String email) {
        super(email + " is already registered");
    }
    
}
