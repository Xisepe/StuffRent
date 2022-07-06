package com.unnamedteam.stuffrent.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ThingTaken {
    private Thing thing;
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

        @Override
        public boolean equals(Object obj){
            if (obj == null)
                return false;
            if (obj == this)
                return true;
            if (obj.getClass() != this.getClass())
                return false;
            ExactDate that = (ExactDate) obj;
            return this.day.equals(that.day)&&this.intraDay.equals(that.intraDay);
        }

        @Override
        public int hashCode(){
            return day.getYear() + day.getMonthValue()*2 + day.getDayOfMonth()*3 +
                    intraDay.getHour()*11 + intraDay.getMinute()*7 + intraDay.getSecond()*5;
        }
    }
}
