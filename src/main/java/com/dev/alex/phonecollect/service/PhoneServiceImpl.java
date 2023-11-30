package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.model.Phone;
import com.dev.alex.phonecollect.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneCollector phoneCollector;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private ParserService parserService;

    @Autowired
    ExportService exportService;

    private String collectNumbers(OperatorEnum operator) {
        String jsonString;
        try {
            jsonString = phoneCollector.collectPhones(operator);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

    public List<Phone> collectNumbers(OperatorEnum operator, boolean multithread) {
        List<String> result = new CopyOnWriteArrayList<>();
        AtomicInteger c = new AtomicInteger(0);
        if (multithread) {
            int corePoolSize = 5; // Начальное количество потоков
            int maximumPoolSize = 50; // Максимальное количество потоков
            long keepAliveTime = 10; // Время, в течение которого лишние потоки будут ждать новые задачи
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1000); // Очередь задач
            Runnable task = () -> {
                result.add(collectNumbers(operator));
                System.out.println(Thread.currentThread().getId());
            };
            ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
            for (int i = 0; i < 100; i++) {
                // final int taskNumber = i;
                executor.execute(task);
                c.incrementAndGet();
            }
            executor.shutdown();
            // После выполнения всех задач, не забудьте завершить пул потоков
            try {
                // Дождитесь завершения всех задач или тайм-аута в 1 час
                executor.awaitTermination(1, TimeUnit.HOURS);
                System.out.println(c);
            } catch (InterruptedException e) {
                // Обработайте прерывание
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < 50; i++) {
                result.add(collectNumbers(operator));
            }
        }
        List<Phone> resultList = convertJsonToPhone(result, operator);
        return resultList.stream().distinct().toList();
    }

    @Override
    public void collectAllNumbers() {
        List<OperatorEnum> enumList = List.of(OperatorEnum.values());
        for (OperatorEnum operator : enumList) {
            List<Phone> oldList = phoneRepository.findAllByOperator(operator.getName());
            List<Phone> newList = collectNumbers(operator, false);
            if (!oldList.isEmpty()) {
                List<Phone> resultList = newList.stream()
                        .filter(p -> oldList.stream()
                                .noneMatch(p::equals)).toList();
                phoneRepository.saveAll(resultList);
            } else {
                phoneRepository.saveAll(newList);
            }
            System.out.println("Collected " + operator.getName());
        }
    }

    private List<Phone> convertJsonToPhone(List<String> jsonStrings, OperatorEnum operator) {
        List<Phone> phones = new ArrayList<>();
        for (String json : jsonStrings) {
            if (!json.isEmpty()) {
                phones.addAll(parserService.parse(json, operator));
            }
        }
        return phones;
    }

    @Override
    public File getFileForMessage(OperatorEnum operator) {
        List<Phone> phones = getPhones(operator);
        return exportService.exportToXls(phones, operator);
    }

    @Override
    public void deleteOldPhones() {
        List<Phone> listForDelete = phoneRepository.findAllByRequestDateIsBefore(LocalDateTime.now().minusDays(7));
        phoneRepository.deleteAll(listForDelete);
    }

    private List<Phone> getPhones(OperatorEnum operator) {
        return phoneRepository.findAllByOperatorAndRequestDateIsAfter(operator.getName(), LocalDateTime.now().minusDays(2));
    }
}
