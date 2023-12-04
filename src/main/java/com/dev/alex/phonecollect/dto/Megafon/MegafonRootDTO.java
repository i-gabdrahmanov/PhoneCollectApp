package com.dev.alex.phonecollect.dto.Megafon;

import com.dev.alex.phonecollect.model.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class MegafonRootDTO extends BaseDTO {
    @JsonProperty(value = "getSearchSpaceList")
    private List<SearchSpaceListDTO> searchSpaceList;
    private List<MegafonNumbersDTO> numbers;
}
