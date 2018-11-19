public class Account {
    private String number;

    public Account(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public boolean isIllegalNumber() {
        return false;
    }

    public boolean isValidNumber() {
        int sum = 0;
        int charIndex = number.length() - 1;
        int position = 1;

        while(charIndex >= 0) {
            int value = Integer.parseInt("" + number.charAt(charIndex));
            sum += (value * position);
            charIndex--;
            position++;
        }

        return sum % 11 == 0;
    }
}
