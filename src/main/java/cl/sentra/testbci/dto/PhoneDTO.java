package cl.sentra.testbci.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String number;
    private String citycode;
    private String countrycode;
}
