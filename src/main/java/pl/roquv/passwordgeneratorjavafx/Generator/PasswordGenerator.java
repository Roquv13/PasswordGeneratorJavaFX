package pl.roquv.passwordgeneratorjavafx.Generator;

import javafx.scene.control.Alert;
import pl.roquv.passwordgeneratorjavafx.Alerts;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PasswordGenerator {

    private final Random random;

    private final Alerts alerts = new Alerts();

    public PasswordGenerator() {
        random = new Random();
    }

    public String generatePassword(int length, boolean includeLowercase, boolean includeUppercase,
                                   boolean includeNumbers, boolean includeSpecialSymbols, int maxCharFrequency) {

        StringBuilder passwordBuilder = new StringBuilder();
        Map<Character, Integer> charFrequency = new HashMap<>();

        String validCharacters = "";
        if (includeLowercase) validCharacters += GeneratorConstants.LOWERCASE_CHARACTERS;
        if (includeUppercase) validCharacters += GeneratorConstants.UPPERCASE_CHARACTERS;
        if (includeNumbers) validCharacters += GeneratorConstants.NUMBERS;
        if (includeSpecialSymbols) validCharacters += GeneratorConstants.SPECIAL_SYMBOLS;

        for(int i = 0; i < length; i++) {
            char randomChar;

            if (length > maxCharFrequency * validCharacters.length()) {
                alerts.showInfoMessage("Your password cannot be generated. Please check your parameters.");
                break;
            }

            do {
                int randomIndex = random.nextInt(validCharacters.length());
                randomChar = validCharacters.charAt(randomIndex);
            } while (charFrequency.getOrDefault(randomChar, 0) >= maxCharFrequency);

            passwordBuilder.append(randomChar);

            charFrequency.put(randomChar, charFrequency.getOrDefault(randomChar, 0) + 1);
        }

        return passwordBuilder.toString();
    }
}
