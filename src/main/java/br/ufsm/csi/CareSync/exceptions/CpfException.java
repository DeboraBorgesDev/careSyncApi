package br.ufsm.csi.CareSync.exceptions;

public class CpfException extends RuntimeException {
    
    public CpfException(String cpf) {
        super("O CPF '" + cpf + "' já está cadastrado no sistema.");
    }
    
}