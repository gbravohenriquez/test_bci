package cl.sentra.testbci.exception;

import lombok.Getter;

@Getter
public class ControlException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String mensaje;

    public ControlException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ControlException(final String mensaje) {
        this.mensaje = mensaje;
    }
}
