package pho.teach.functional.functions.users;

import pho.teach.functional.commons.entities.with.User;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserCreator {

    private static final char[] VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*!@#$&%".toCharArray();
    private static final String[] names = new String[]{
        "john", "jack", "peter", "paul", "george",
        "richard", "park", "noah", "lorenzo", "tom",
        "vanessa", "samantha", "sandra", "martha", "iris",
        "paula", "martina", "sofia", "catarina", "victoria"};
    private static final String[] surnames = new String[]{
        "smith", "johnson", "williams", "brown", "lee",
        "kim", "patel", "nguyen", "garcia", "martinez",
        "santos", "lopes", "muller", "davies", "yang",
        "rossi", "dubois", "moreau", "bianchi", "singh"};

    private static final Random RANDOM_ENGINE = new SecureRandom();

    protected static String generatePassword(int length) {
        return IntStream
            .range(0, length)
            .map(i -> RANDOM_ENGINE.nextInt(VALID_CHARACTERS.length))
            .mapToObj(i -> String.valueOf(VALID_CHARACTERS[i]))
            .collect(Collectors.joining());
    }

    protected String generateUserName() {
        return String.format(
            "%s.%s",
            RANDOM_ENGINE.nextInt(names.length),
            RANDOM_ENGINE.nextInt(surnames.length));
    }

    public List<User> createUserList(int amount, int passwordSize) {
        return IntStream
            .range(0, amount)
            .mapToObj(i -> User
                .builder()
                .name(generateUserName())
                .password(generatePassword(passwordSize))
                .build())
            .toList();
    }
}
