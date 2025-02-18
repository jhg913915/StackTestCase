package stack.code.formatters;

import stack.code.model.Subscriber;

import java.io.IOException;
import java.util.List;

public interface Formatter {
    void format(List<Subscriber> subscribers) throws IOException;
}
