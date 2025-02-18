package stack.code.formatters;

import stack.code.model.Subscriber;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvHouseFormatter implements Formatter{
    private static final String OUTPUT_FILE_PATH = "src/test/resources/fixtures/output/Начисления_дома.csv";
    private static final String CSV_HEADER = "№ строки;Улица;№ дома;Начислено";

    @Override
    public void format(List<Subscriber> subscribers) throws IOException {
        Path outputFile = Paths.get(OUTPUT_FILE_PATH);
        Path outputDir = outputFile.getParent();
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }
        Map<String, Double> houseSumMap = sumAbsoluteForHouses(subscribers);
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile.toFile()))) {
            writer.println(CSV_HEADER);
            int rowNumber = 1;
            for (Map.Entry<String, Double> entry : houseSumMap.entrySet()) {
                String key = entry.getKey();
                Double totalSum = entry.getValue();
                String[] parts = key.split(";");
                String street = parts[0];
                String houseAddress = parts[1];
                String csvLine = String.format("%d;%s;%s;%d",
                        rowNumber++,
                        street,
                        houseAddress,
                        Math.round(totalSum));
                writer.println(csvLine);
            }
        }
    }

    public Map<String, Double> sumAbsoluteForHouses(List<Subscriber> subscribers) {
        return subscribers.stream()
                .collect(Collectors.groupingBy(
                        subscriber -> subscriber.getStreet() + ";" + subscriber.getHouseAddress(),
                        Collectors.summingDouble(Subscriber::getAbsoluteSum)
                ));
    }
}
