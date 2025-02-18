import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stack.code.model.Subscriber;
import stack.code.model.SumPrices;
import stack.code.parsers.Parser;
import stack.code.parsers.CsvParser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberTest {
    private List<Map<String, String>> csvData;

    @BeforeEach
    void setUp() throws IOException {
        Parser csvParser = new CsvParser();
        String filePath = "src/test/resources/fixtures/input/абоненты.csv";
        csvData = csvParser.parse(filePath);
    }

    @Test
    void testBuildSubscriberFromMapType2() {
        Map<String, String> data = csvData.stream()
                .filter(row -> "1".equals(row.get("\uFEFF№ строки")))
                .findFirst()
                .orElse(null);
        assertNotNull(data, "Строка с номером 1 не найдена: № строки");

        Subscriber subscriber = Subscriber.buildSubscriberFromMap(data);

        assertNotNull(subscriber);
        assertEquals("Абаимов", subscriber.getSurname());
        assertEquals("Ленина", subscriber.getStreet());
        assertEquals("1", subscriber.getHouseAddress());
        assertEquals(1, subscriber.getApartmentNumber());
        assertEquals(381, subscriber.getPreviousSum());
        assertEquals(470, subscriber.getCurrentSum());
        assertEquals(SumPrices.PER_COUNTER.getPrice() * (470 - 381), subscriber.getAbsoluteSum(), 0.001);
    }

    @Test
    void testBuildSubscriberFromMapType1() {
        Map<String, String> data = csvData.stream()
                .filter(row -> "2".equals(row.get("\uFEFF№ строки")))
                .findFirst()
                .orElse(null);
        assertNotNull(data, "Строка с номером 2 не найдена");

        Subscriber subscriber = Subscriber.buildSubscriberFromMap(data);

        assertNotNull(subscriber);
        assertEquals("Багаев", subscriber.getSurname());
        assertEquals("Ленина", subscriber.getStreet());
        assertEquals("1", subscriber.getHouseAddress());
        assertEquals(2, subscriber.getApartmentNumber());
        assertEquals(0, subscriber.getPreviousSum());
        assertEquals(0, subscriber.getCurrentSum());
        assertEquals(SumPrices.STANDARD.getPrice(), subscriber.getAbsoluteSum(), 0.001);
    }
}

