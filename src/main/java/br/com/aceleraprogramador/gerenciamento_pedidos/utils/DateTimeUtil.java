package br.com.aceleraprogramador.gerenciamento_pedidos.utils;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@UtilityClass
public class DateTimeUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static LocalDateTime toLocalDateTime(String dateTimeString) {
        try {
            if (StringUtils.isNotBlank(dateTimeString)) {
                return LocalDateTime.parse(dateTimeString, FORMATTER);
            } else {
                return null;
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("A data deve estar no formato dd/MM/yyyy HH:mm:ss.", e);
        }
    }

    public static String toString(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) {
            return null;
        }
        return dateTime.format(FORMATTER);
    }
}
