package pho.teach.functional.commons.integrations;

import pho.teach.functional.commons.entities.with.User;

import java.util.Random;

public class IntensiveIntegration {

    private static final Random RANDOM = new Random();

    private static final int ONE_SECOND_IN_MILLIS = 1000;

    public IntensiveIntegration() {

    }

    public void performIntegration(User user) {
        try {
            int interval = RANDOM.nextInt(10 * ONE_SECOND_IN_MILLIS, 45 * ONE_SECOND_IN_MILLIS);

            System.out.printf("Taking %d seconds to processs user %s %n", interval / ONE_SECOND_IN_MILLIS, user.getName());

            Thread.sleep(interval);
        } catch (InterruptedException ignored) {

        }
    }
}
