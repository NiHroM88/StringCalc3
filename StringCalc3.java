import java.util.Scanner;

public class StringCalc3 {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();
        char action = getAction(exp);
        String[] data = getData(exp, action);

        if (action == '*' || action == '/') {
            validateMultiplicationOrDivision(data[1]);
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }

        String result = calculateResult(action, data);
        printInQuotes(result);
    }


    static char getAction(String exp) throws Exception {
        if (exp.contains(" + ")) {
            return '+';
        } else if (exp.contains(" - ")) {
            return '-';
        } else if (exp.contains(" * ")) {
            return '*';
        } else if (exp.contains(" / ")) {
            return '/';
        } else {
            throw new Exception("Некорректный знак действия");
        }
    }

    static String[] getData(String exp, char action) {
        String[] data;
        switch (action) {
            case '+':
                data = exp.split(" \\+ ");
                break;
            case '-':
                data = exp.split(" - ");
                break;
            case '*':
                data = exp.split(" \\* ");
                break;
            case '/':
                data = exp.split(" / ");
                break;
            default:
                data = new String[0]; // technically unreachable
        }
        return data;
    }

    static void validateMultiplicationOrDivision(String data) throws Exception {
        if (data.contains("\"")) {
            throw new Exception("Строчку можно делить или умножать только на число");
        }
    }


    static String calculateResult(char action, String[] data) throws Exception {
        String result = "";
        if (data[0].length() > 10)
            throw new Exception("Введено больше 10 символов");
        if (data[1].length() > 10)
            throw new Exception("Введено больше 10 символов");
        switch (action) {
            case '+':
                result = data[0] + data[1];
                break;
            case '*':
                int multi = Integer.parseInt(data[1]);
                if (multi > 10 )
                    throw new Exception("Числа должны быть от 1 до 10 включительно");
                if (multi < 1 )
                    throw new Exception("Числа должны быть от 1 до 10 включительно");

                for (int i = 0; i < multi; i++) {
                    result += data[0];
                }
                break;
            case '-':
                int index = data[0].indexOf(data[1]);
                if (index == -1) {
                    result = data[0];
                } else {
                    result = data[0].substring(0, index) + data[0].substring(index + data[1].length());
                }
                break;
            case '/':
                int newLen = data[0].length() / Integer.parseInt(data[1]);
                if (newLen > 10 )
                    throw new Exception("Числа должны быть от 1 до 10 включительно");
                if (newLen < 1 )
                    throw new Exception("Числа должны быть от 1 до 10 включительно");
                result = data[0].substring(0, newLen);
                break;
        }
        return result;
    }

    static void printInQuotes(String text) {
        if (text.length() > 40) {
            System.out.println("\"" + text.substring(0, 40) + "...\"");
        } else {
            System.out.println("\"" + text + "\"");
        }
    }
}
