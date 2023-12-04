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

    /*public static List<Phone> beelineToEntity(BeelineRootDTO dtoList) {
        List<Phone> phones = new ArrayList<>();
        if (dtoList != null) {
            dtoList.getNumbers().forEach(dto -> {
                int costTemp = dto.getPrice();
                dto.getNumbers().forEach(ex -> {
                    Phone phone = new Phone();
                    phone.setCost(costTemp);
                    phone.setPhoneNumber(ex.getValue());
                    phone.setOperator(OperatorEnum.BEELINE.getName());
                    phone.setRequestDate(LocalDateTime.now());
                    phones.add(phone);
                });
            });
            return phones;
        } else {
            throw new RuntimeException();
        }
    }
*/

    public List<Phone> beelineToEntity(BeelineRootDTO dtoList) {
        if (dtoList != null) {
            return dtoList.getNumbers().stream()
                    .flatMap(dto -> dto.getNumbers().stream()
                            .map(ex -> {
                                Phone phone = new Phone();
                                phone.setCost(dto.getPrice());
                                phone.setPhoneNumber(ex.getValue());
                                phone.setOperator(OperatorEnum.BEELINE.getName());
                                phone.setRequestDate(LocalDateTime.now());
                                return phone;
                            }))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException();
        }
    }

    public List<Phone> mtsToEntity(List<MtsNumbersDTO> dtoList) {
        if (dtoList != null) {
            return dtoList.stream()
                    .map(ph -> {
                        Phone phone = new Phone();
                        phone.setCost(ph.getPriceRub().intValue());
                        phone.setPhoneNumber(ph.getMsisdn());
                        phone.setOperator(OperatorEnum.MTS.getName());
                        phone.setRequestDate(LocalDateTime.now());
                        return phone;
                    })
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException();
        }
    }

    public List<Phone> megafonToEntity(MegafonRootDTO dto) {
        /*Map<Integer, Integer> ssl = dtoList.stream().map(MegafonRootDTO::getGetSearchSpaceList)
               .collect(Collectors.toMap(SearchSpaceListDTO::getNmLisClassId, SearchSpaceListDTO::getNmPrice));*/
     /*   Map<Integer, Integer> ssl = dtoList.stream()
                .flatMap(dto -> dto.getGetSearchSpaceList().stream())
                .collect(Collectors.toMap(SearchSpaceListDTO::getNmLisClassId, SearchSpaceListDTO::getNmPrice));*/
        if (dto != null) {
            Map<Integer, Integer> ssl = dto.getSearchSpaceList().stream()
                    .collect(Collectors.toMap(SearchSpaceListDTO::getNmLisClassId, SearchSpaceListDTO::getNmPrice));

            return dto.getNumbers().stream()
                    .flatMap(ex -> ex.getPhones().stream()
                            .map(ph -> {
                                Phone phone = new Phone();
                                phone.setCost(ssl.get(ex.getClassType()));
                                phone.setPhoneNumber(ph);
                                phone.setOperator(OperatorEnum.MEGAFON.getName());
                                phone.setRequestDate(LocalDateTime.now());
                                return phone;
                            }))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException();
        }
    }
}
