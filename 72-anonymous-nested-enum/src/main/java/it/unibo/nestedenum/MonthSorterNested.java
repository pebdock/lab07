package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

import it.unibo.nestedenum.MonthSorterNested.Month.SortByDate;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    private static final int MAXMONTHDAYS = 31;
    private static final int MEDIUMMONTHDAYS = 30;
    private static final int MINMONTHDAYS = 28;

    @Override
    public Comparator<String> sortByDays() {
        return new Month.SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Month.SortByMonthOrder();
    }

    public enum Month {
    JANUARY("January",MAXMONTHDAYS),
    FEBRUARY("February",MINMONTHDAYS),
    MARCH("March",MAXMONTHDAYS),
    APRIL("April",MEDIUMMONTHDAYS),
    MAY("May",MAXMONTHDAYS),
    JUNE("June",MEDIUMMONTHDAYS),
    JULY("July",MAXMONTHDAYS),
    AUGUST("August",MAXMONTHDAYS),
    SEPTEMBER("September",MEDIUMMONTHDAYS),
    OCTOBER("October",MAXMONTHDAYS),
    NOVEMBER("November",MEDIUMMONTHDAYS),
    DECEMBER("December",MAXMONTHDAYS);
        
        private final String monthName;
        private final int monthDays;

        private Month(final String monthName,final int monthDays) {
            this.monthName = monthName;
            this.monthDays = monthDays;
        }

        public static Month fromString(final String monthRequest) {
            if (monthRequest == null || monthRequest.trim().isEmpty()) {
            throw new IllegalArgumentException("The String argument is not valid");
            }

            Month found = null;

            for(Month indexMonth : Month.values()) {
                if(indexMonth.monthName.toLowerCase().startsWith(monthRequest.toLowerCase())) {
                   if (found != null) {
                    throw new IllegalArgumentException("The String argument is ambiguos");
                   }
                   found = indexMonth;
                }
            }
            
            if(found == null) {
                throw new IllegalArgumentException("The String argument is not valid");
            }

            return found;
        }

        public static class SortByMonthOrder implements Comparator<String> {

            @Override
            public int compare(String arg0, String arg1) {

                if (arg0 == null && arg1 == null) return 0;
                if (arg0 == null) return -1;
                if (arg1 == null) return 1;

                final Month m0 = fromString(arg0);
                final Month m1 = fromString(arg1);

                if(m0.ordinal() > m1.ordinal()) {
                    return 1;
                } else if(m0.ordinal() == m1.ordinal()) {
                    return 0;
                } else {
                    return -1;
                }
            }

        }

        public static class SortByDate implements Comparator<String> {

            @Override
            public int compare(String arg0, String arg1) {

                if (arg0 == null && arg1 == null) return 0;
                if (arg0 == null) return -1;
                if (arg1 == null) return 1;

                final Month m0 = fromString(arg0);
                final Month m1 = fromString(arg1);
                if(m0.monthDays > m1.monthDays) {
                    return 1;
                } else if(m0.monthDays == m1.monthDays) {
                    return new Month.SortByMonthOrder().compare(arg0,arg1);
                } else {
                    return -1;
                }
            }

        }

    }
}
