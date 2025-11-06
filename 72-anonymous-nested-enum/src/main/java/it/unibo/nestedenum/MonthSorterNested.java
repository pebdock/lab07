package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    private static final int MAXMONTHDAYS = 31;
    private static final int MEDIUMMONTHDAYS = 30;
    private static final int MINMONTHDAYS = 28;

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }

    public enum Month {
        GENNAIO("Gennaio",MAXMONTHDAYS),
        FEBBRAIO("Febbraio",MINMONTHDAYS),
        MARZO("Marzo",MAXMONTHDAYS),
        APRILE("Aprile",MEDIUMMONTHDAYS),
        MAGGIO("Maggio",MAXMONTHDAYS),
        GIUGNO("Giugno",MEDIUMMONTHDAYS),
        LUGLIO("Luglio",MAXMONTHDAYS),
        AGOSTO("Agosto",MAXMONTHDAYS),
        SETTEMBRE("Settembre",MEDIUMMONTHDAYS),
        OTTOBRE("Ottobre",MAXMONTHDAYS),
        NOVEMBRE("Novembre",MEDIUMMONTHDAYS),
        DICEMBRE("Dicembre",MAXMONTHDAYS);
        private final String monthName;
        private final int monthDays;
        private Month(final String monthName,final int monthDays) {
            this.monthName = monthName;
            this.monthDays = monthDays;
        }

        public static Month fromString(final String monthRequest) {
            for(Month indexMonth : Month.values()) {
                String testMonth = indexMonth.monthName.substring(monthRequest.length());
                if(testMonth.equalsIgnoreCase(monthRequest)) {
                    return indexMonth;
                }
            }
            return null;
        }

        public static class SortByMonthOrder implements Comparator<String> {

            @Override
            public int compare(String arg0, String arg1) {
                if(fromString(arg0).ordinal() > fromString(arg1).ordinal()) {
                    return 1;
                } else if(fromString(arg0).ordinal() == fromString(arg1).ordinal()) {
                    return 0;
                } else {
                    return 1;
                }
            }

        }

        public static class SortByDate implements Comparator<String> {

            @Override
            public int compare(String arg0, String arg1) {
                if(fromString(arg0).monthDays > fromString(arg1).monthDays) {
                    return 1;
                } else if(fromString(arg0).monthDays == fromString(arg1).monthDays) {
                    return 0;
                } else {
                    return 1;
                }
            }

        }

    }
}
