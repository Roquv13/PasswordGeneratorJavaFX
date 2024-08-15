package pl.roquv.passwordgeneratorjavafx;

import java.util.Random;

public class PasswordGenerator {

    private final Random random;

    public PasswordGenerator() {
        random = new Random();
    }

    public String generatePassword(int length, boolean includeUppercase, boolean includeLowercase,
                                   boolean includeNumbers, boolean includeSpecialSymbols) {

        StringBuilder passwordBuilder = new StringBuilder();

        String validCharacters = "";
        if (includeUppercase) validCharacters += Constants.UPPERCASE_CHARACTERS;
        if (includeLowercase) validCharacters += Constants.LOWERCASE_CHARACTERS;
        if (includeNumbers) validCharacters += Constants.NUMBERS;
        if (includeSpecialSymbols) validCharacters += Constants.SPECIAL_SYMBOLS;

        for(int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validCharacters.length());

            char randomChar = validCharacters.charAt(randomIndex);

            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();
    }
}
