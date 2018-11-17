public class AccountValidator {

    private String number;
    private boolean isValid;

    public AccountValidator(String number) {
        this.number = number;
        validateAccountNumber(number);
    }

    public String getNumber() {
        return number;
    }

    public boolean getIsValid() { return isValid; }

    private void validateAccountNumber(String numberString) {
        int sum = 0;
        for (int i = numberString.length() - 1, position = 1; i > -1; i--, position++) {
            int value = Integer.parseInt(numberString.substring(i, i+1));
            sum += (value * position);
        }

        if (sum % 11 != 0) {
            isValid = false;
        } else {
            isValid = true;
        }
    }
}
