package storage.impl;

import exception.PostNotFoundException;
import model.User;

public class UserStorageImpl {
    private User[] users = new User[16];
    private int userSize = 0;


    public void add(User user) {
        users[userSize++] = user;
    }

    private void extendUsers() {
        User[] tmp = new User[users.length + 10];
        System.arraycopy(users, 0, tmp, 0, users.length);
        users = tmp;
    }
    public User getUserByEmail(String email) throws PostNotFoundException {
        for (int i = 0; i < userSize; i++) {
            if (users[i].equals(email)){
                return users[i];
            }
        }throw new PostNotFoundException(String.format("User with %s email does not exist",email));

    }

    public User getUserByEmailAndPassword(String email, String password) throws PostNotFoundException {
        for (int i = 0; i < userSize; i++) {
            User user = users[i];
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new PostNotFoundException(String.format("User with %s email and %s password exist", email, password));

    }
}
