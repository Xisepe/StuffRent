package com.unnamedteam.stuffrent.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "thingsTaken")
public class ThingTaken {
    @Id
    private long thingId;
    private long idTaker;
    private class ExactDate{
        private LocalDate day;
        private LocalTime intraDay;

        ExactDate(){
        }

        ExactDate(LocalDate day, LocalTime intraDay){
            this.day = day;
            this.intraDay = intraDay;
        }

        ExactDate(Term term, int amount){
            switch (term) {
                case Hour: {
                    intraDay = LocalTime.now().plusHours(amount);
                    int days = 0;
                    while (intraDay.getHour()>24){
                        days++;
                        intraDay = intraDay.minusHours(24);
                    }
                    day = LocalDate.now().plusDays(days);
                    break;
                }
                case Day:{
                    intraDay = LocalTime.now();
                    day = LocalDate.now().plusDays(amount);
                    break;
                }
                case Week:{
                    intraDay = LocalTime.now();
                    day = LocalDate.now().plusWeeks(amount);
                    break;
                }
                case Month:{
                    intraDay = LocalTime.now();
                    day = LocalDate.now().plusMonths(amount);
                    break;
                }
            }
        }

    }
}
