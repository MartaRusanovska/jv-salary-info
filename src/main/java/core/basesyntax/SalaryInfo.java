package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate start = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalaryForEmployee = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                LocalDate recordDate = LocalDate.parse(parts[0], FORMATTER);
                String employeeName = parts[1];

                if (employeeName.equals(name)
                        && !recordDate.isBefore(start)
                        && !recordDate.isAfter(end)) {
                    int hours = Integer.parseInt(parts[2]);
                    int moneyPerHour = Integer.parseInt(parts[3]);
                    totalSalaryForEmployee += hours * moneyPerHour;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalaryForEmployee);
        }

        return stringBuilder.toString();
    }
}
