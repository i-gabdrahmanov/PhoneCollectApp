package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.repository.PhoneRepository;
import com.dev.alex.phonecollect.utils.JsonParser;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneCollector phoneCollector;

    @Override
    public String collectNumbers(OperatorEnum operator) {
        String jsonString;
        try {
            jsonString = phoneCollector.collectPhones(operator);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

    public List<String> collectNumbers (OperatorEnum operator, boolean multithread) {
        List<String> result = new CopyOnWriteArrayList<>();
        AtomicInteger c = new AtomicInteger(0);
        if(multithread) {
            int corePoolSize = 5; // Начальное количество потоков
            int maximumPoolSize = 50; // Максимальное количество потоков
            long keepAliveTime = 10; // Время, в течение которого лишние потоки будут ждать новые задачи
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1000); // Очередь задач
            Runnable task = () -> {
                result.add(collectNumbers(operator));
                System.out.println(Thread.currentThread().getId());
                /*try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
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
            result.add(collectNumbers(operator));
        }
        return result;
    }
}
