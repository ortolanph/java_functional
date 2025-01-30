package pho.teach.functional.functions.users;

import pho.teach.functional.commons.entities.with.User;
import pho.teach.functional.commons.integrations.IntensiveIntegration;

import java.util.List;

public class UserProcessor {

    public static void main(String[] args) {
        UserCreator userCreator = new UserCreator();
        IntensiveIntegration intensiveIntegration = new IntensiveIntegration();

        List<User> users = userCreator.createUserList(10, 20);

        System.out.println(users);

        List<User> modifiedUsers = users
            .parallelStream()
            .map(u -> u.withActivated(true))
            .peek(intensiveIntegration::performIntegration)
            .toList();

        System.out.println(modifiedUsers);
    }
}
