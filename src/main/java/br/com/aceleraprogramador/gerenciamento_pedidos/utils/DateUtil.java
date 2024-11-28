package br.com.aceleraprogramador.gerenciamento_pedidos.utils;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@UtilityClass
public class DateUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate toLocalDate(String dateTimeString) {
        try {
            if (StringUtils.isNotBlank(dateTimeString)) {
                return LocalDate.parse(dateTimeString, FORMATTER);
            } else {
                return null;
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("A data deve estar no formato dd/MM/yyyy.", e);
        }
    }

    public static String toString(LocalDate date) {
        if (Objects.isNull(date)) {
            throw new IllegalArgumentException("O objeto LocalDateTime não pode ser nulo.");
        }
        return date.format(FORMATTER);
    }
}
