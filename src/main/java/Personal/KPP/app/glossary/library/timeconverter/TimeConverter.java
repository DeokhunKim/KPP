package Personal.KPP.app.glossary.library.timeconverter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {

    private static String timeNumberToString(String time) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date(Long.parseLong(time)));
    }
}
