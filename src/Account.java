public class Account {
    public final static String INVALID_CHARACTER_MARK = "?";
    private String number;

    public Account(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public boolean isIllegalNumber() {
        return number.contains(INVALID_CHARACTER_MARK);
    }

    public boolean isValidNumber() {
        if (isIllegalNumber()) {
            return false;
        }

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
