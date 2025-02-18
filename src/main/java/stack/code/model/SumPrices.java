package stack.code.model;

import lombok.Getter;

@Getter
public enum SumPrices {
    STANDARD("СтНорматив", 301.26),
    PER_COUNTER("СтСчетчик", 1.52);

    private final String description;
    private final double price;

    SumPrices(String description, double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return description + " = " + price + " рублей";
    }
}
