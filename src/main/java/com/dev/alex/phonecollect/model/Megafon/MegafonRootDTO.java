package com.dev.alex.phonecollect.model.Megafon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class MegafonRootDTO {
    @JsonProperty(value = "getSearchSpaceList")
    private List<SearchSpaceListDTO> searchSpaceList;
    private List<MegafonNumbersDTO> numbers;
}
