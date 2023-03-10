package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.MemberController;
import miu.edu.mpp.hdk.controller.SystemController;
import miu.edu.mpp.hdk.model.Address;
import miu.edu.mpp.hdk.model.LibraryMember;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewMemberForm extends MainForm {
    private final MemberController memberController;
    private JPanel mainPanel;
    private JButton btnAddMember;
    private JTextField txtFistName;
    private JTextField txtLastName;
    private JTextField txtTelephone;
    private JTextField txtStreet;
    private JTextField txtCity;
    private JTextField txtState;
    private JTextField txtZipCode;
    private JLabel lblErrorMsg;

    public AddNewMemberForm(SystemController system) {
        super(system);
        memberController = new MemberController();
        btnAddMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = txtFistName.getText();
                String lastName = txtLastName.getText();
                String telephone = txtTelephone.getText();
                String street = txtStreet.getText();
                String city = txtCity.getText();
                String state = txtState.getText();
                String zipCode = txtZipCode.getText();

                Address address = new Address(street, city, state, zipCode);

                LibraryMember member = new LibraryMember(Util.generateId(), firstName, lastName, telephone, address);
                if (memberController.addNewMember(member)) {
                    clearFields();
                    lblErrorMsg.setText("Member added!");
                } else {
                    lblErrorMsg.setText("Could not add member!");
                }
            }
        });
    }

    public void clearFields() {
        txtFistName.setText("");
        txtLastName.setText("");
        txtTelephone.setText("");
        txtStreet.setText("");
        txtCity.setText("");
        txtState.setText("");
        txtZipCode.setText("");
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }

    @Override
    public void refresh() {

    }
}
