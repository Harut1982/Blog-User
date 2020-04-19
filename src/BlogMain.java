import exception.PostNotFoundException;
import model.Post;
import model.User;
import storage.PostStorage;
import storage.impl.PostStorageImpl;
import storage.impl.UserStorageImpl;

import java.util.Date;
import java.util.Scanner;

public class BlogMain implements Commands {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final PostStorage POST_STORAGE = new PostStorageImpl();
    private static final UserStorageImpl USER_STORAGE = new UserStorageImpl();
    private static User currentUser = null;

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            POST_STORAGE.printAllPosts();
            Commands.printCommands();
            String commandStr = SCANNER.nextLine();
            int command;
            try {
                command = Integer.parseInt(commandStr);
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                case SEARCH_POST:
                    searchPost();
                    break;
                case POSTS_BY_CATEGORY:
                    postsByCategory();
                    break;


                default:
                    System.out.println("Invalid command,Please try again");
            }
        }


    }

    private static void register() {
        System.out.println("Please input name,surname,email,password");
        try {
            String userStr = SCANNER.nextLine();
            String[] userData = userStr.split(",");
            User user = new User();
            user.setName(userData[0]);
            user.setSurname(userData[1]);
            user.setEmail(userData[2]);
            user.setPassword(userData[3]);
            try {
                USER_STORAGE.getUserByEmail(userData[2]);
                System.out.println("Email already exist");
            } catch (PostNotFoundException e) {
                USER_STORAGE.add(user);
                System.out.println("Thank you user added");
            }
        } catch (Exception e) {
            System.out.println("Please input valid data: ");
            register();
        }


    }

    private static void login() {
        System.out.println("Please input email,password");
        try {
            String userStr = SCANNER.nextLine();
            String[] userData = userStr.split(",");
            currentUser = USER_STORAGE.getUserByEmailAndPassword(userData[0], userData[1]);
            loginUser();
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    private static void postsByCategory() {
        System.out.println("Please enter category");
        String category = SCANNER.nextLine();
        POST_STORAGE.printPostsByCategory(category);
    }

    private static void searchPost() {
        System.out.println("Please enter keyword");
        String keyword = SCANNER.nextLine();
        POST_STORAGE.searchPostsByKeyword(keyword);
    }

    private static void addPost() {
        System.out.println("Please enter title,text,category");
        String postDataStr = SCANNER.nextLine();
        String[] postData = postDataStr.split(",");
        try {
            Post post = new Post();
            post.setTitle(postData[0]);
            post.setText(postData[1]);
            post.setCategory(postData[2]);
            post.setCreatedDate(new Date());
            post.setUser(currentUser);
            POST_STORAGE.add(post);
            System.out.println("post added!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid date");
            addPost();

        }

    }

    private static void loginUser() {
        boolean isRun = true;
        while (isRun) {
            POST_STORAGE.printAllPosts();
            Commands.printUserCommands();
            String commandStr = SCANNER.nextLine();
            int command;
            try {
                command = Integer.parseInt(commandStr);
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    isRun = false;
                    break;
                case ADD_POST:
                    addPost();
                    break;
                default:
                    System.out.println("invalid command");
                    loginUser();

            }
        }


    }


}
