import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
        System.out.println();
        // Вывести список неповторяющихся городов, в которых работают трейдеры.
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        System.out.println();
        // Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(tr -> tr.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
        System.out.println();
        // Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .collect(Collectors.joining(", ")));
        System.out.println();
        // Выяснить, существует ли хоть один трейдер из Милана.
        System.out.println(transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(tr -> tr.getCity().equals("Milan")));
        System.out.println();
        // Вывести суммы всех транзакций трейдеров из Кембриджа.
        System.out.println(transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.toMap(Transaction::getTrader, Transaction::getValue, Integer::sum))
        );
        System.out.println();
        // Какова максимальная сумма среди всех транзакций?
        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare)
                .get()
        );
        System.out.println();
        // Найти транзакцию с минимальной суммой.
        System.out.println(transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .get()
        );
    }
}