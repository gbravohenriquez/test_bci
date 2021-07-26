package cl.sentra.testbci.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum UserError {
    E_EMAIL_REGISTRADO("50", "Email ya se encuentra registrado"),
    E_SIN_REGISTROS("51", "No existen registros"),
    E_SIN_REGISTROS_ACTUALIZAR("52", "No hay registros para actualizar");

    private String codigo;
    private String descripcion;

}
