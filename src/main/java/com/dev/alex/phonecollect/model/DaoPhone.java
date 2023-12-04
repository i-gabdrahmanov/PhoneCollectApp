package com.dev.alex.phonecollect.model;

import com.dev.alex.phonecollect.dto.Beeline.BeelineRootDTO;
import com.dev.alex.phonecollect.dto.Megafon.MegafonRootDTO;
import com.dev.alex.phonecollect.dto.Megafon.SearchSpaceListDTO;
import com.dev.alex.phonecollect.dto.Mts.MtsNumbersDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.collect;

@Component
public class DaoPhone {

    public List<Phone> beelineToEntity(BeelineRootDTO dtoList) {
        if (dtoList != null) {
            return dtoList.getNumbers().stream()
                    .flatMap(dto -> dto.getNumbers().stream()
                            .map(ex -> new Phone.Builder()
                                    .phoneNumber(ex.getValue())
                                    .operator(OperatorEnum.BEELINE.getName())
                                    .cost(dto.getPrice())
                                    .requestDate(LocalDateTime.now())
                                    .build()))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException();
        }
    }

    public List<Phone> mtsToEntity(List<MtsNumbersDTO> dtoList) {
        if (dtoList != null) {
            return dtoList.stream()
                    .map(ph -> new Phone.Builder()
                            .phoneNumber(ph.getMsisdn())
                            .cost(ph.getPriceRub().intValue())
                            .operator(OperatorEnum.MTS.getName())
                            .requestDate(LocalDateTime.now())
                            .build())
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException();
        }
    }

    public List<Phone> megafonToEntity(MegafonRootDTO dto) {
        if (dto != null) {
            Map<Integer, Integer> ssl = dto.getSearchSpaceList().stream()
                    .collect(Collectors.toMap(SearchSpaceListDTO::getNmLisClassId, SearchSpaceListDTO::getNmPrice));
            return dto.getNumbers().stream()
                    .flatMap(ex -> ex.getPhones().stream()
                            .map(ph -> new Phone.Builder()
                                    .cost(ssl.get(ex.getClassType()))
                                    .phoneNumber(ph)
                                    .operator(OperatorEnum.MEGAFON.getName())
                                    .requestDate(LocalDateTime.now())
                                    .build()))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException();
        }
    }
}
