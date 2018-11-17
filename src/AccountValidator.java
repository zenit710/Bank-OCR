public class AccountValidator {

    public boolean validate(Account account) {
        return validateAccountNumber(account);
    }

    private boolean validateAccountNumber(Account account) {
        int sum = 0;
        int charIndex = account.getNumber().length() - 1;
        int position = 1;

        while(charIndex >= 0) {
            int value = Integer.parseInt("" + account.getNumber().charAt(charIndex));
            sum += (value * position);
            charIndex--;
            position++;
        }

        return sum % 11 == 0;
    }
}
