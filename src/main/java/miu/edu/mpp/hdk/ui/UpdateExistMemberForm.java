package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.MemberController;
import miu.edu.mpp.hdk.controller.SystemController;
import miu.edu.mpp.hdk.model.LibraryMember;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateExistMemberForm extends MainForm {
    private final MemberController memberController;

    private JPanel mainPanel;
    private JButton btnSaveMember;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtTelephone;
    private JTextField txtStreet;
    private JTextField txtCity;
    private JTextField txtState;
    private JTextField txtZipCode;
    private JComboBox<LibraryMember> cbbMemberList;
    private JLabel lblErrorMsg;

    public UpdateExistMemberForm(SystemController system) {
        super(system);

        memberController = new MemberController();

        this.refresh();
        btnSaveMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibraryMember member = (LibraryMember) cbbMemberList.getSelectedItem();
                if (member == null)
                    return;

                member.setFirstName(txtFirstName.getText());
                member.setLastName(txtLastName.getText());
                member.setTelephone(txtTelephone.getText());
                member.getAddress().setStreet(txtStreet.getText());
                member.getAddress().setCity(txtCity.getText());
                member.getAddress().setState(txtState.getText());
                member.getAddress().setZip(txtZipCode.getText());

                if (memberController.updateMember(member)) {
                    lblErrorMsg.setText("Member updated!");
                    system.refresh();
                } else {
                    lblErrorMsg.setText("Could not update member!");
                }
            }
        });

        cbbMemberList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibraryMember member = (LibraryMember) cbbMemberList.getSelectedItem();
                if (member == null)
                    return;

                txtFirstName.setText(member.getFirstName());
                txtLastName.setText(member.getLastName());
                txtTelephone.setText(member.getTelephone());
                txtStreet.setText(member.getAddress().getStreet());
                txtCity.setText(member.getAddress().getCity());
                txtState.setText(member.getAddress().getState());
                txtZipCode.setText(member.getAddress().getZip());
            }
        });
    }

    public void clearFields() {
        txtFirstName.setText("");
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
        cbbMemberList.removeAllItems();
        List<LibraryMember> members = memberController.getListMember();
        members.forEach(cbbMemberList::addItem);
    }
}
