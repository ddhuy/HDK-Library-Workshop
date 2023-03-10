package miu.edu.mpp.hdk.enums;

public enum MenuItem {

    LOGIN("Login"),
    CHECKOUT_BOOK("Checkout Book"),
    ADD_BOOK("Add New Book"),
    PRINT_CHECKOUT_RECORD("Print Checkout Record"),
    ;

    private final String label;

    MenuItem(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
