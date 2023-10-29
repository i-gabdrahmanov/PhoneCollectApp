package com.dev.alex.phonecollect.model.Megafon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchSpaceListDTO {
    private int nmLisClassId;
    private int nmPrice;
}
