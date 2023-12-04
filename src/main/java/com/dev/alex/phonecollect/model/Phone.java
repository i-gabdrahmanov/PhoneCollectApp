package com.dev.alex.phonecollect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Table(name = "phones")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "phone_number", nullable = false, unique = true)
    @JsonProperty("value")
    private final  String phoneNumber;

    @Column(name = "operator", nullable = false)
    private final  String operator;

    @Column(name = "cost")
    private final Integer cost;

    @Column(name = "request_date")
    private final LocalDateTime requestDate;

    private Phone(Long id, String phoneNumber, String operator, Integer cost, LocalDateTime requestDate) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.operator = operator;
        this.cost = cost;
        this.requestDate = requestDate;
    }

    public Phone() {
        this.phoneNumber = "";
        this.operator = "";
        this.cost = 0;
        this.requestDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return getPhoneNumber();
    }

    public static class Builder {
        private Long id;
        private String phoneNumber;
        private String operator;
        private Integer cost;
        private LocalDateTime requestDate;

        public Builder phoneNumber (String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder operator (String operator) {
            this.operator = operator;
            return this;
        }

        public Builder cost (Integer cost) {
            this.cost = cost;
            return this;
        }

        public Builder requestDate (LocalDateTime requestDate) {
            this.requestDate = requestDate;
            return this;
        }

        public Phone build () {
            return new Phone(id, phoneNumber, operator, cost, requestDate);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Phone that = (Phone) o;
        return getPhoneNumber() != null && Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber());
    }
}
