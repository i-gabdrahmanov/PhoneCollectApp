package com.dev.alex.phonecollect.dto.Beeline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class BeelineNumbersDTO {
    private String value;
    // private int price;
}
