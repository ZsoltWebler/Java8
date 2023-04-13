package org.webler.zsolt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private Country country;
    private String street;
    private String houseNumber;

}
