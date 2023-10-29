package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;

import java.io.File;
import java.time.LocalDateTime;

public interface ExportService {
    File exportToXls(OperatorEnum operator, LocalDateTime requestDate);
}
