package hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception;

public class DoNotExistObjectException extends Exception {
    public DoNotExistObjectException(String errorMessage) {
        super(errorMessage);
    }
}
