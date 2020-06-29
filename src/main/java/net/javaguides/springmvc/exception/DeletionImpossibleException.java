package net.javaguides.springmvc.exception;

public class DeletionImpossibleException extends Exception {
    private static final long serialVersionUID = 1L;

    public DeletionImpossibleException(Object resourId) {
        super(resourId != null ? resourId.toString() : null);
    }
}
