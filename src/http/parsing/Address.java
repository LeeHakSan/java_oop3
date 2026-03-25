package http.parsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipCode;
    private Geo geo;

}
