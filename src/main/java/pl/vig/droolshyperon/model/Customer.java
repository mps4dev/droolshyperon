package pl.vig.droolshyperon.model;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Customer {

    public static List<String> getRules() {
        return Arrays.asList("Discount.xls");
    }

    private CustomerType type;

    private int years;

    private int discount;

    public Customer(CustomerType type, int numOfYears) {
        super();
        this.type = type;
        this.years = numOfYears;
    }

    public enum CustomerType {
        INDIVIDUAL, BUSINESS;
    }
}