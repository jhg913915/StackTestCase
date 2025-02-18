package Parsers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvParser implements Parser {

    @Override
    public List<Map<String, String>> parse(String filePath) {
        try {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).setColumnSeparator(';').build();
            File file = new File(filePath);
            MappingIterator<Map<String, String>> iterator = csvMapper
                    .reader(Map.class)
                    .with(csvSchema)
                    .readValues(file);
            return iterator.readAll();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
