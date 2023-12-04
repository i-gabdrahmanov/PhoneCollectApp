package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.dto.Beeline.BeelineRootDTO;
import com.dev.alex.phonecollect.model.DaoPhone;
import com.dev.alex.phonecollect.dto.Megafon.MegafonRootDTO;
import com.dev.alex.phonecollect.dto.Mts.MtsNumbersDTO;
import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.model.Phone;
import com.dev.alex.phonecollect.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParserServiceImpl implements ParserService {

    @Autowired
    JsonParser jsonParser;

    @Autowired
    DaoPhone dao;
    @Override
    public List<Phone> parse(String json, OperatorEnum operator) {
        List<Phone> phones = new ArrayList<>();
        switch (operator) {
            case BEELINE -> {
                BeelineRootDTO dtoBeeline = jsonParser.parseString(json.substring(1), BeelineRootDTO.class);
                phones = dao.beelineToEntity(dtoBeeline);
            }
            case MEGAFON -> {
                MegafonRootDTO dtoMegafon = jsonParser.parseString(json, MegafonRootDTO.class);
                phones = dao.megafonToEntity(dtoMegafon);
            }
            case MTS -> {
                List<MtsNumbersDTO> dtoMts = jsonParser.parseString(json, null);
                phones = dao.mtsToEntity(dtoMts);
            }
        }
        return phones;
    }

  /*  private boolean needUpdateReposinory(OperatorEnum operator) {
        //milliseconds since January 1, 1970, 00:00:00 GMT. A negative number is the number of milliseconds before January 1, 1970, 00:00:00 GMT.
        Date result = phoneRepository.getLastUpdateDateTime(operator.getName()).orElse(new Timestamp(1));
        LocalDateTime lastUpdateDateTime = result.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        //return lastUpdateDateTime.isBefore(LocalDateTime.now().minusDays(1));
        return true;
    };*/
}