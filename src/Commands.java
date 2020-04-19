public interface Commands {
    //main commands
    int EXIT=0;
    int LOGIN=1;
    int REGISTER=2;
    int SEARCH_POST=3;
    int POSTS_BY_CATEGORY=4;

    // user commands
    int LOGOUT=0;
    int ADD_POST=1;
    static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + LOGIN + " for LOGIN");
        System.out.println("Please input " + REGISTER + " for REGISTER");
        System.out.println("Please input " + SEARCH_POST + " for SEARCH_POST");
        System.out.println("Please input " + POSTS_BY_CATEGORY + " for POST_BY_CATEGORY");

    }
    static void printUserCommands(){
        System.out.println("Please input " + LOGOUT + " for LOGOUT");
        System.out.println("Please input " + ADD_POST + " for ADD_POST");
    }

}
