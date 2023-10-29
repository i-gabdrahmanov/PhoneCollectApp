package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.DaoDTOPhone;
import com.dev.alex.phonecollect.model.Beeline.BeelineRootDTO;
import com.dev.alex.phonecollect.model.Megafon.MegafonRootDTO;
import com.dev.alex.phonecollect.model.Mts.MtsNumbersDTO;
import com.dev.alex.phonecollect.model.Mts.MtsRootDTO;
import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.model.Phone;
import com.dev.alex.phonecollect.repository.PhoneRepository;
import com.dev.alex.phonecollect.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneCollector phoneCollector;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    private final JsonParser jsonParser = new JsonParser();

    @Override
    public void collectNumbers(OperatorEnum operatorEnum) {
        String jsonString;
        try {
            jsonString = phoneCollector.collectPhones(operatorEnum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (operatorEnum) {
            case BEELINE -> parseBeeline(jsonString, operatorEnum);
            case MEGAFON -> parseMegafon(jsonString, operatorEnum);
            case MTS -> parseMts(jsonString, operatorEnum);
            default -> throw new RuntimeException("Unsupported operator");
        }
    }

    private void parseBeeline(String json, OperatorEnum operator) {
        List<Phone> phones;
        BeelineRootDTO dto = jsonParser.parseString(json.substring(1), BeelineRootDTO.class);
        if (needUpdateReposinory(operator) && dto != null) {
            phones = DaoDTOPhone.beelineToEntity(dto);
            transactionTemplate.execute(status -> phoneRepository.saveAll(phones));
        }
    }

    private void parseMegafon(String json, OperatorEnum operator) {
        List<Phone> phones;
        MegafonRootDTO dto = jsonParser.parseString(json, MegafonRootDTO.class);
        if (needUpdateReposinory(operator) && dto != null) {
            phones = DaoDTOPhone.megafonToEntity(dto);
            transactionTemplate.execute(status -> phoneRepository.saveAll(phones));
        }
    }

    private void parseMts(String json, OperatorEnum operator) {
        List<Phone> phones;
        List<MtsNumbersDTO> dto = jsonParser.parseString(json, null);
        if (needUpdateReposinory(operator) && dto != null) {
            phones = DaoDTOPhone.mtsToEntity(dto);
            transactionTemplate.execute(status -> phoneRepository.saveAll(phones));
        }
    }

    private boolean needUpdateReposinory(OperatorEnum operator) {
        //milliseconds since January 1, 1970, 00:00:00 GMT. A negative number is the number of milliseconds before January 1, 1970, 00:00:00 GMT.
        Date result = phoneRepository.getLastUpdateDateTime(operator.getName()).orElse(new Timestamp(1));
        LocalDateTime lastUpdateDateTime = result.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        //return lastUpdateDateTime.isBefore(LocalDateTime.now().minusDays(1));
        return true;
    };

}
