package stack.code.formatters;

import stack.code.model.Subscriber;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvSubscriberFormatter implements Formatter {

    private static final String OUTPUT_FILE_PATH = "src/test/resources/fixtures/output/Начисления_абоненты.csv";
    private static final String CSV_HEADER = "№ строки;Фамилия;Улица;№ дома;№ Квартиры;Тип начисления;Предыдущее;Текущее;Начислено";

    @Override
    public void format(List<Subscriber> subscribers) throws IOException {
        Path outputFile = Paths.get(OUTPUT_FILE_PATH);
        Path outputDir = outputFile.getParent();
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile.toFile()))) {
            writer.println(CSV_HEADER);
            for (Subscriber subscriber : subscribers) {
                String csvLine = String.format("%d;%s;%s;%s;%d;%d;%d;%d;%d",
                        subscriber.getRow(),
                        subscriber.getSurname(),
                        subscriber.getStreet(),
                        subscriber.getHouseAddress(),
                        subscriber.getApartmentNumber(),
                        subscriber.getSumType(),
                        Math.round(subscriber.getPreviousSum()),
                        Math.round(subscriber.getCurrentSum()),
                        Math.round(subscriber.getAbsoluteSum()));
                writer.println(csvLine);
            }
        }
    }
}
