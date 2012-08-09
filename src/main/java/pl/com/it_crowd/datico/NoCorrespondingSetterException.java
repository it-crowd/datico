package pl.com.it_crowd.datico;

public class NoCorrespondingSetterException extends RuntimeException {
// --------------------------- CONSTRUCTORS ---------------------------

    public NoCorrespondingSetterException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
