package pl.roquv.passwordgeneratorjavafx.Generator;

import java.util.Random;

public class PasswordGenerator {

    private final Random random;

    public PasswordGenerator() {
        random = new Random();
    }

    public String generatePassword(int length, boolean includeLowercase, boolean includeUppercase,
                                   boolean includeNumbers, boolean includeSpecialSymbols) {

        StringBuilder passwordBuilder = new StringBuilder();

        String validCharacters = "";
        if (includeLowercase) validCharacters += GeneratorConstants.LOWERCASE_CHARACTERS;
        if (includeUppercase) validCharacters += GeneratorConstants.UPPERCASE_CHARACTERS;
        if (includeNumbers) validCharacters += GeneratorConstants.NUMBERS;
        if (includeSpecialSymbols) validCharacters += GeneratorConstants.SPECIAL_SYMBOLS;

        for(int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validCharacters.length());

            char randomChar = validCharacters.charAt(randomIndex);

            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();
    }
}
