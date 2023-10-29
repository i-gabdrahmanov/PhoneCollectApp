package com.dev.alex.phonecollect.model.Mts;

import com.dev.alex.phonecollect.model.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties (ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MtsRootDTO extends BaseDTO {
    @JsonProperty
    private List<MtsNumbersDTO> numbers;
}
