package pho.teach.functional.functions.users;

import pho.teach.functional.commons.entities.with.User;

import java.util.List;
import java.util.Random;
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

    private static final Random RANDOM_ENGINE = new Random();

    protected static String generatePassword(int length) {
        Object[] newPassword = IntStream
            .range(0, length)
            .map(i -> RANDOM_ENGINE.nextInt(VALID_CHARACTERS.length))
            .boxed()
            .map(p -> VALID_CHARACTERS[p])
            .toArray();

        StringBuilder builder = new StringBuilder();

        for (Object c : newPassword) {
            builder.append(c);
        }

        return builder.toString();
    }

    protected String generateUserName() {
        int name = RANDOM_ENGINE.nextInt(names.length);
        int surname = RANDOM_ENGINE.nextInt(surnames.length);
        return String.format("%s.%s", names[name], surnames[surname]);
    }

    public List<User> createUserList(int amount, int passwordSize) {
        return IntStream
            .range(0, amount)
            .boxed()
            .map(i -> User
                .builder()
                .name(generateUserName())
                .password(generatePassword(passwordSize))
                .build())
            .toList();
    }
}
