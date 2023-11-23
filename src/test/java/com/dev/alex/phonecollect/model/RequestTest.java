package com.dev.alex.phonecollect.model;

import com.dev.alex.phonecollect.model.Beeline.BeelineRootDTO;
import com.dev.alex.phonecollect.service.PhoneCollector;
import com.dev.alex.phonecollect.utils.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestTest {

    //С каждым запросом сайт возвращает разное значение, если это поломалось, нужно подумать об
    // изменении логики сбора данных
    @Test
    void beelineNotEqualRequestResult() throws IOException {
        PhoneCollector phoneCollector = new PhoneCollector();
        String requestResultOne = phoneCollector.collectPhones(OperatorEnum.BEELINE);
        String requestResultSecond = phoneCollector.collectPhones(OperatorEnum.BEELINE);
        String requestResultThird = phoneCollector.collectPhones(OperatorEnum.BEELINE);
        String requestResultFour = phoneCollector.collectPhones(OperatorEnum.BEELINE);
      //  Assertions.assertNotEquals(requestResultOne, requestResultSecond);
        JsonParser jsonParser = new JsonParser();
        BeelineRootDTO firstDTO = jsonParser.parseString(requestResultOne.substring(1), BeelineRootDTO.class);
        BeelineRootDTO secondDTO = jsonParser.parseString(requestResultSecond.substring(1), BeelineRootDTO.class);
        BeelineRootDTO thirdDTO = jsonParser.parseString(requestResultThird.substring(1), BeelineRootDTO.class);
        BeelineRootDTO fourDTO = jsonParser.parseString(requestResultFour.substring(1), BeelineRootDTO.class);
        List<Phone> first = DaoPhone.beelineToEntity(firstDTO);
        List<Phone> second = DaoPhone.beelineToEntity(secondDTO);
        List<Phone> third = DaoPhone.beelineToEntity(thirdDTO);
        List<Phone> four = DaoPhone.beelineToEntity(fourDTO);

        Set<Phone> firstResultSet = Stream.of(first, second).flatMap(Collection::stream).collect(Collectors.toSet());
        Set<Phone> secondResultSet = Stream.of(third, four).flatMap(Collection::stream).collect(Collectors.toSet());
        Assertions.assertNotEquals(firstResultSet.size(),
                secondResultSet.size());

    }

    @Test
    void megafonNotRequestResult() throws IOException {
        PhoneCollector phoneCollector = new PhoneCollector();
        String requestResultOne = phoneCollector.collectPhones(OperatorEnum.MEGAFON);
        String requestResultSecond = phoneCollector.collectPhones(OperatorEnum.MEGAFON);
        Assertions.assertEquals(requestResultOne, requestResultSecond);
    }

    @Test
    void mtsNotEqualRequestResult() throws IOException {
        PhoneCollector phoneCollector = new PhoneCollector();
        String requestResultOne = phoneCollector.collectPhones(OperatorEnum.MTS);
        String requestResultSecond = phoneCollector.collectPhones(OperatorEnum.MTS);
        Assertions.assertNotEquals(requestResultOne, requestResultSecond);
    }
}