package stack.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class Subscriber {
    private final int row;
    private final String surname;
    private final String street;
    private final String houseAddress;
    private final int apartmentNumber;
    private final int sumType;
    private final double previousSum;
    private final double currentSum;
    private final double absoluteSum;

    private Subscriber(SubscriberBuilder builder) {
        this.row = builder.row;
        this.surname = builder.surname;
        this.street = builder.street;
        this.houseAddress = builder.houseAddress;
        this.apartmentNumber = builder.apartmentNumber;
        this.sumType = builder.sumType;
        this.previousSum = builder.previousSum;
        this.currentSum = builder.currentSum;
        this.absoluteSum = builder.absoluteSum;
    }

    public static Subscriber buildSubscriberFromMap(Map<String, String> data) {
        int sumType = Integer.parseInt(data.get("Тип начисления"));
        double previousSum = Double.parseDouble(data.get("Предыдущее"));
        double currentSum = Double.parseDouble(data.get("Текущее"));
        try {
            return new Subscriber.SubscriberBuilder()
                    .row(Integer.parseInt(data.get("\uFEFF№ строки")))
                    .surname(data.get("Фамилия"))
                    .street(data.get("Улица"))
                    .houseAddress(data.get("№ дома"))
                    .apartmentNumber(Integer.parseInt(data.get("№ Квартиры")))
                    .sumType(sumType)
                    .previousSum(previousSum)
                    .currentSum(currentSum)
                    .absoluteSum(calculateAbsoluteSum(sumType, previousSum, currentSum))
                    .build();
        } catch (NumberFormatException e) {
            throw new RuntimeException("Ошибка при парсинге числовых значений: " + data);
        }
    }

    public static double calculateAbsoluteSum(int sumType, double previousSum, double currentSum) {
        return switch (sumType) {
            case 1 -> SumPrices.STANDARD.getPrice();
            case 2 -> SumPrices.PER_COUNTER.getPrice() * (Math.abs(currentSum - previousSum));
            default -> throw new RuntimeException("Не существует такого типа начисления как: " + sumType);
        };
    }

    public static class SubscriberBuilder {
        private int row;
        private String surname;
        private String street;
        private String houseAddress;
        private int apartmentNumber;
        private int sumType;
        private double previousSum;
        private double currentSum;
        private double absoluteSum;

        public SubscriberBuilder row(int row) {
            this.row = row;
            return this;
        }

        public SubscriberBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public SubscriberBuilder street(String street) {
            this.street = street;
            return this;
        }

        public SubscriberBuilder houseAddress(String houseAddress) {
            this.houseAddress = houseAddress;
            return this;
        }

        public SubscriberBuilder apartmentNumber(int apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public SubscriberBuilder sumType(int sumType) {
            this.sumType = sumType;
            return this;
        }

        public SubscriberBuilder previousSum(double previousSum) {
            this.previousSum = previousSum;
            return this;
        }

        public SubscriberBuilder currentSum(double currentSum) {
            this.currentSum = currentSum;
            return this;
        }

        public SubscriberBuilder absoluteSum(double absoluteSum) {
            this.absoluteSum = absoluteSum;
            return this;
        }

        public Subscriber build() {
            return new Subscriber(this);
        }
    }
}
