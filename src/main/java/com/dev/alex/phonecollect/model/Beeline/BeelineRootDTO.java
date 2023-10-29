package com.dev.alex.phonecollect.model.Beeline;

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
@JsonIgnoreProperties (ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class BeelineRootDTO extends BaseDTO {

    @JsonProperty(value = "numbers")
    private List<BeelineListsDTO> numbers;
}
