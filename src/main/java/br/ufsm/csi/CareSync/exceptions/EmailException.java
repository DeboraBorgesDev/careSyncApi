package br.ufsm.csi.CareSync.exceptions;

public class EmailException extends RuntimeException {
    
    public EmailException(String email) {
        super("O email '" + email + "' já está cadastrado no sistema.");
    }
    
}
