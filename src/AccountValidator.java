public class AccountValidator {

    private String number;
    private boolean isValid;

    public AccountValidator(Account account) {
        this.number = account.getNumber();
    }

    public boolean Validate() {
        isValid = validateAccountNumber(number);
        return isValid;
    }

    private boolean validateAccountNumber(String numberString) {
        int sum = 0;
        int charIndex = numberString.length() - 1;
        int position = 1;

        while(charIndex >= 0) {
            int value = Integer.parseInt("" + numberString.charAt(charIndex));
            sum += (value * position);
            charIndex--;
            position++;
        }

        return sum % 11 == 0;
    }
}
