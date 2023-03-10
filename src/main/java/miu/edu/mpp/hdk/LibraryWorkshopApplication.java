package miu.edu.mpp.hdk;

import miu.edu.mpp.hdk.controller.SystemController;
import miu.edu.mpp.hdk.enums.Auth;
import miu.edu.mpp.hdk.ui.AddBookForm;
import miu.edu.mpp.hdk.ui.DetailForm;
import miu.edu.mpp.hdk.ui.LoginForm;
import miu.edu.mpp.hdk.view.Util;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.Serial;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static miu.edu.mpp.hdk.enums.MenuItem.ADD_BOOK_MENU;
import static miu.edu.mpp.hdk.enums.MenuItem.LOGIN_MENU;
import static miu.edu.mpp.hdk.enums.MenuItem.TITLES_MENU;

public class LibraryWorkshopApplication extends JFrame {

    @Serial
    private static final long serialVersionUID = -1L;

    JPanel cardDeck;
    JList<String> linkList;
    JSplitPane splitPane;
    JLabel message = new JLabel("Welcome to the Library workshop!");
    Map.Entry<Integer, String> currentMenu = Map.entry(0, LOGIN_MENU.getLabel());
    JPanel footer;
    SystemController controller;

    LibraryWorkshopApplication() {
        controller = SystemController.INSTANCE;
        controller.setApplication(this);
        setTitle("Library Workshop");
        initializeWindow();
        setSize(600, 450);
        setUpCards();
        initLeftMenu();
        splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,    //Orientation
                linkList,                       //left side
                cardDeck                        //right side
        );
        splitPane.setDividerLocation(200);
        splitPane.setBorder(BorderFactory.createCompoundBorder());
        this.add(splitPane, BorderLayout.CENTER);
        this.initFooter();
        centreOnDesktop(this);
    }

    private void initLeftMenu() {
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement(LOGIN_MENU.getLabel());
        model.addElement(TITLES_MENU.getLabel());
        model.addElement(ADD_BOOK_MENU.getLabel());
        this.linkList = new JList<>(model);
        this.linkList.setCellRenderer(this.renderLeftMenuList(Auth.ANONYMOUS));
        this.linkList.addListSelectionListener((event) -> {
            this.message.setText("");
            String item = linkList.getSelectedValue();
//            selectMenu(item);
            if (authMenu(controller.getUser().getAuthorization()).contains(item)) {
                currentMenu = Map.entry(linkList.getLeadSelectionIndex(), item);
                selectMenu(item);
            } else {
                linkList.setSelectedIndex(currentMenu.getKey());
                selectMenu(currentMenu.getValue());
            }
        });
    }

    private DefaultListCellRenderer renderLeftMenuList(Auth role) {
        return new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof String item) {
                    this.setText(item);
                    if (authMenu(role).contains(item)) {
                        this.setForeground(Util.LINK_AVAILABLE);
                    } else {
                        this.setForeground(Util.LINK_NOT_AVAILABLE);
                    }
                    if (isSelected) {
                        this.setForeground(Color.BLACK);
                        this.setBackground(new Color(236, 243, 245));
                    }
                }
                return component;
            }
        };
    }

    private Set<String> authMenu(Auth role) {
        Set<String> menus = new HashSet<>();
        menus.add(LOGIN_MENU.getLabel());
        if (role.equals(Auth.BOTH) || role.equals(Auth.ADMIN)) {
            menus.add(ADD_BOOK_MENU.getLabel());
        }
        if (role.equals(Auth.BOTH) || role.equals(Auth.LIBRARIAN)) {
            menus.add(TITLES_MENU.getLabel());
        }
        return menus;
    }

    private void initFooter() {
        this.footer = new JPanel();
        this.message.setForeground(Util.DARK_BLUE);
        this.message.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.footer.add(message);
        this.footer.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(footer, BorderLayout.SOUTH);
    }

    public void error(String message) {
        this.message.setForeground(Util.ERROR_MESSAGE_COLOR);
        this.message.setText(message);
    }

    public void info(String message) {
        this.message.setForeground(Util.INFO_MESSAGE_COLOR);
        this.message.setText(message);
    }

    public void auth(Auth role) {
        this.linkList.setCellRenderer(this.renderLeftMenuList(role));
        this.updateCards();
    }

    private void centreOnDesktop(Component component) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        component.setLocation(
                (toolkit.getScreenSize().width - component.getWidth()) / 2,
                (toolkit.getScreenSize().height - component.getHeight()) / 2
        );
    }

    private void setUpCards() {
        cardDeck = new JPanel(new CardLayout());
        cardDeck.add(new LoginForm().getContent(), LOGIN_MENU.getLabel());
        cardDeck.add(new DetailForm().getContent(), TITLES_MENU.getLabel());
        cardDeck.add(new AddBookForm().getContent(), ADD_BOOK_MENU.getLabel());
    }

    private void updateCards() {

    }

    public void selectMenu(String name) {
        CardLayout cl = (CardLayout) (cardDeck.getLayout());
        cl.show(this.cardDeck, name);
    }

    private void initializeWindow() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(LibraryWorkshopApplication::new);
    }
}