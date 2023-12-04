package com.dev.alex.phonecollect.dto.Beeline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class BeelineListsDTO {
    private int price;
    List<BeelineNumbersDTO> numbers;

}
