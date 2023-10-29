package com.dev.alex.phonecollect.model.Beeline;

import com.dev.alex.phonecollect.model.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class BeelineRootDTO extends BaseDTO {

    @JsonProperty(value = "numbers")
    private List<BeelineListsDTO> numbers;

    public BeelineRootDTO() {

    }

}
