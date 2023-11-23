package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.model.Phone;

import java.util.List;

public interface ParserService {
    List<Phone> parse(String json, OperatorEnum operator);
}
