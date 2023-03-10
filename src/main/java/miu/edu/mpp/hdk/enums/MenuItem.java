package miu.edu.mpp.hdk.enums;

public enum MenuItem {

    LOGIN_MENU("Login"),
    TITLES_MENU("View Titles"),
    ADD_BOOK_MENU("Add Book");

    private final String label;

    MenuItem(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
