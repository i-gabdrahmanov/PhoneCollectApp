package com.dev.alex.phonecollect.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

public class PhoneTest {

    @BeforeEach
    void setUp() {

    }
    @Test
    void testEquals(){
        Phone first = new Phone();
        Phone second = new Phone();
        first.setPhoneNumber("333333333");
        second.setPhoneNumber("333333333");
        Assertions.assertEquals(first, second);
    }

    @Test
    void testNotEquals() {
        Phone first = new Phone();
        Phone second = new Phone();
        first.setPhoneNumber("333333332");
        second.setPhoneNumber("333333333");
        Assertions.assertNotEquals(first, second);
    }

    @Test
    void distinctListTest() {
        Phone first = new Phone();
        Phone second = new Phone();
        Phone third = new Phone();
        first.setPhoneNumber("333333333");
        second.setPhoneNumber("333333333");
        third.setPhoneNumber("333333334");
        List<Phone> phones = List.of(first, second, third);
        Assertions.assertEquals(2, phones.stream().distinct().toList().size());
        Set<Phone> uniquePhones = new HashSet<>(phones);
        Assertions.assertEquals(2, uniquePhones.size(), "Размер множества уникальных телефонов не равен 2");
    }

    @Test
    void hasCodeEquals () {
        Phone first = new Phone();
        Phone second = new Phone();
        first.setPhoneNumber("333333333");
        second.setPhoneNumber("333333333");
        Assertions.assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void hasNotCodeEquals () {
        Phone first = new Phone();
        Phone second = new Phone();
        first.setPhoneNumber("333333332");
        second.setPhoneNumber("333333333");
        Assertions.assertNotEquals(first.hashCode(), second.hashCode());
    }
}
