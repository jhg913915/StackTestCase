import stack.code.parsers.CsvParser;
import stack.code.parsers.Parser;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCsvParser {

    @Test
    void testParseCsvParameterizedByFile() {
        Parser csvParser = new CsvParser();
        String filePath = "src/test/resources/fixtures/input/абоненты.csv";
        List<Map<String, String>> result = csvParser.parse(filePath);
        Map<String, String> firstRow = result.get(0);
        Map<String, String> fifthRow = result.get(4);

        assertNotNull(result);
        assertEquals(29, result.size());
        assertEquals("Абаимов", firstRow.get("Фамилия"));
        assertEquals("Ленина", firstRow.get("Улица"));
        assertEquals("1", firstRow.get("№ строки"));
        assertEquals("470", firstRow.get("Текущее"));
        assertEquals("Багин", fifthRow.get("Фамилия"));
        assertEquals("Пушкина", fifthRow.get("Улица"));
        assertEquals("462", fifthRow.get("Текущее"));
    }
}
