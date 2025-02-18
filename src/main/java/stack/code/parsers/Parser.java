package stack.code.parsers;

import java.util.List;
import java.util.Map;

public interface Parser {
    List<Map<String, String>> parse(String filepath);
}
