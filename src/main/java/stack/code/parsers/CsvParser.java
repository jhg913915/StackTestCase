package stack.code.parsers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.io.input.BOMInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvParser implements Parser {
    @Override
    public List<Map<String, String>> parse(String filePath) {
        try {
            BOMInputStream bomInputStream = new BOMInputStream(new FileInputStream(filePath));
            InputStreamReader reader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8);
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).setColumnSeparator(';').build();
            MappingIterator<Map<String, String>> iterator = csvMapper
                    .readerFor(Map.class)
                    .with(csvSchema)
                    .readValues(reader);
            return iterator.readAll();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
