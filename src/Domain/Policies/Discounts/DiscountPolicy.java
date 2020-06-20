package Domain.Policies.Discounts;

import Domain.Policies.BasePolicy;
import Domain.Store.Product;
import Domain.Store.Product_boundle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;


/*
    //      TODO: Discounts: refactor out
    this class get string in the following format that represent Discount
    and parse it in order to create discounts objects.
    Note: the formats are without spaces

    simple discount format:
        <type>#<product-name>#<percentage>#<exp-date>  [#<condition>]
        where:
        - <type>: 0 - visible
                  1 - conditional amount
                  2 - conditional price
        - <product-name>: the name of the product
        - <percentage>: integer between 0 to 100
        - <exp-date>: date in format DD/MM/YYYY
        - <condition>: only for conditional (amount if 1 and price if 2)

    Composite discount format:
        <type>#<number> (#<discount>)*
        where:
        - <type>: 10 - And
                  11 - Or
                  12 - Xor
        - <discount>: discount in valid format
        - <number>: how many discounts are
        - * means repetition
 */

public class DiscountPolicy extends BasePolicy {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private List<Discount> discounts;

    public DiscountPolicy() {
        this.discounts = new LinkedList<>();
    }

    //      TODO: Discounts: refactor out
    private Discount discountFactory(Stack<String> params) throws Exception {
        int type = Integer.parseInt(params.pop());
        switch (type) {
            case 0: //visible
                return new VisibleDiscount(params.pop(), Integer.parseInt(params.pop()), toDate(params.pop()));

            case 1: //conditional amount
                return new ConditionalAmountDiscount(params.pop(), Integer.parseInt(params.pop()), toDate(params.pop()),
                        Integer.parseInt(params.pop()));

            case 2: //conditional price
                return new ConditionalPriceDiscount(params.pop(), Integer.parseInt(params.pop()), toDate(params.pop()),
                        Double.parseDouble(params.pop()));

            case 10: //composite and
                return new AndDiscount(parseDiscountList(params));

            case 11: //composite or
                return new OrDiscount(parseDiscountList(params));

            case 12: //composite xor
                return new XorDiscount(parseDiscountList(params));

            default:
                throw new Exception();
        }
    }

    //      TODO: Discounts: refactor out
    private List<Discount> parseDiscountList(Stack<String> params) throws Exception {
        List<Discount> discountList = new LinkedList<>();
        int n = Integer.parseInt(params.pop());
        for (int i = 0; i < n; i++) {
            discountList.add(discountFactory(params));
        }
        return discountList;
    }

    //      TODO: Discounts:  refactor out
    private LocalDate toDate(String date) {
        return LocalDate.parse(date, format);
    }

    public boolean addDiscount(Discount discount) {
//      TODO: Discounts:  refactor out
//        Discount d;
//        try {
//            d = discountFactory(stringSplitToStack(discount, REGEX));
//        } catch (Exception e) {
//            ErrorLogger.GetInstance().Add_Log("IN Discount" + " got wrong discount format");
//            return false;
//        }
        discounts.add(discount);
        return true;
    }

    public boolean hasDiscounts(List<Product_boundle> products) {
        return discounts.stream()
                .map(d -> d.hasDiscount(products))
                .reduce(false, Boolean::logicalOr);
    }

    public double applyDiscounts(List<Product_boundle> products) {
        double totalPrice =
                products.stream()
                        .map((entry) -> entry.item.getPrice() * entry.size())   //price * amount
                        .reduce(0.0, Double::sum);
        return totalPrice -
                discounts.stream()
                        .filter(d -> d.hasDiscount(products))
                        .map(d -> d.applyDiscount(products))
                        .reduce(0.0, Double::sum);
    }

    public boolean removeDiscount(int disNum) {
        if (disNum >= 0 && disNum < discounts.size()) {
            discounts.remove(disNum);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        AtomicInteger i = new AtomicInteger();
        return "Discounts{" +
                discounts.stream()
                        .map(Discount::toString)
                        .reduce("", (acc, cur) -> acc + "\n\t" + (i.getAndIncrement()) + ". " + cur) +
                "\n}";
    }

}
