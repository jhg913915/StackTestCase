import Parsers.CsvParser;
import Parsers.Parser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCsvParser {
    final private String FILE_PATH = "src/test/resources/fixtures/input/абоненты.csv";

    @Test
    void testParseCsvParameterizedByFile() {
        Parser parser = new CsvParser();
        List<Map<String, String>> result = parser.parse(FILE_PATH);
        Map<String, String> firstRow = result.get(0);
        Map<String, String> fifthRow = result.get(4);

        assertNotNull(result);
        assertEquals(29, result.size());
        assertEquals("Абаимов", firstRow.get("Фамилия"));
        assertEquals("Ленина", firstRow.get("Улица"));
        assertEquals("470", firstRow.get("Текущее"));
        assertEquals("Багин", fifthRow.get("Фамилия"));
        assertEquals("Пушкина", fifthRow.get("Улица"));
        assertEquals("462", fifthRow.get("Текущее"));
    }
}
