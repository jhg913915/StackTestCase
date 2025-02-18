package stack.code;

import stack.code.formatters.CsvHouseFormatter;
import stack.code.formatters.CsvSubscriberFormatter;
import stack.code.formatters.Formatter;
import stack.code.model.Subscriber;
import stack.code.parsers.CsvParser;
import stack.code.parsers.Parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class App {
    public static void createAbsolutePriceReport(String filePath, Parser parser, Formatter formatter) {
        List<Map<String, String>> data = parser.parse(filePath);
        List<Subscriber> subscribers = data.stream()
                .map(Subscriber::buildSubscriberFromMap)
                .toList();
        try {
            formatter.format(subscribers);
        } catch (IOException e) {
            System.err.println("Не удалось сгенерировать файл");
        }
    }

    public static void main(String[] args)  {
        String filePath = "src/test/resources/fixtures/input/абоненты.csv";
        Parser csvParser = new CsvParser();
        Formatter csvSubscriberFormatter = new CsvSubscriberFormatter();
        Formatter csvHouseFormatter = new CsvHouseFormatter();
        createAbsolutePriceReport(filePath, csvParser, csvSubscriberFormatter);
        createAbsolutePriceReport(filePath, csvParser, csvHouseFormatter);
    }
}
