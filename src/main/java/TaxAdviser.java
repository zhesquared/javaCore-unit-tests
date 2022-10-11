import java.util.Scanner;

public class TaxAdviser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int earnings = 0;
        int spendings = 0;
        String input;

        while (true) {
            System.out.println("Выберите операцию и введите её номер:\n " +
                    "1. Добавить новый доход\n " +
                    "2. Добавить новый расход\n " +
                    "3. Выбрать систему налогообложения");
            input = scanner.nextLine();
            if (input.equals("end")) {
                System.out.println("Программа завершена!");
                break;
            } else {
                int operation = Integer.parseInt(input);
                switch (operation) {
                    case 1 -> {
                        System.out.println("Введите сумму дохода:");
                        String moneyIncome = scanner.nextLine();
                        int moneyIn = Integer.parseInt(moneyIncome);
                        earnings += moneyIn;
                    }
                    case 2 -> {
                        System.out.println("Введите сумму расхода: ");
                        String moneyExpend = scanner.nextLine();
                        int moneyOut = Integer.parseInt(moneyExpend);
                        spendings += moneyOut;
                    }
                    case 3 -> System.out.println(taxAdviser(earnings, spendings));
                    default -> System.out.println("Такой операции нет");
                }
            }
        }

    }

    public static int taxEarnings(int earnings) {
        return (earnings * 6) / 100;
    }

    public static int taxEarningsMinusSpendings(int earnings, int spendings) {
        int tax = (earnings - spendings) * 15 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }

    public static String taxAdviser(int earnings, int spendings) {
        StringBuilder advice = new StringBuilder();

        String taxSystem = taxEarnings(earnings) > taxEarningsMinusSpendings(earnings, spendings)
                ? "УСН доходы минус расходы"
                : "УСН доходы";
        int taxMax = 0;
        int taxMin = 0;

        if (taxEarnings(earnings) > taxEarningsMinusSpendings(earnings, spendings)) {
            taxMax = taxEarnings(earnings);
            taxMin = taxEarningsMinusSpendings(earnings, spendings);
        } else {
            taxMax = taxEarningsMinusSpendings(earnings, spendings);
            taxMin = taxEarnings(earnings);
        }
        int economy = taxMax - taxMin;

        advice.append("Мы советуем вам ")
                .append(taxSystem)
                .append("\n")
                .append("Ваш налог составит: ")
                .append(taxMin)
                .append(" рублей\n")
                .append("Налог на другой системе: ")
                .append(taxMax)
                .append(" рублей\n")
                .append("Экономия: ")
                .append(economy)
                .append(" рублей\n");


        return advice.toString();
    }
}