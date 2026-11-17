package projetos.pigbank.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.stereotype.Component;

@Component("dateUtils")
public class DateUtils {
    private final PrettyTime prettyTime = new PrettyTime(Locale.of("pt", "BR"));

    public String tempoRelativo(LocalDateTime data) {
        if (data == null) {
            return "";
        }

        try {
            return prettyTime.format(data);
        } catch (Exception e) {
            return data.toString();
        }
    }

    public String tempoNormal(LocalDateTime data) {
        if (data == null) {
            return "";
        }

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yy");
        return data.format(formatador);
    }
}