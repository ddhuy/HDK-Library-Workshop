package miu.edu.mpp.hdk.model;

import java.util.Date;

public class CheckoutRecord {

    private LibraryMember member;
    private Book book;
    private Date checkoutDate;

    public CheckoutRecord() {
    }

    public CheckoutRecord(LibraryMember member, Book book) {
        this.member = member;
        this.book = book;
        this.checkoutDate = new Date();
    }

    public LibraryMember getMember() {
        return member;
    }

    public void setMember(LibraryMember member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return "CheckoutRecord{" +
                "member=" + member +
                ", book=" + book +
                ", checkoutDate=" + checkoutDate +
                '}';
    }
}
