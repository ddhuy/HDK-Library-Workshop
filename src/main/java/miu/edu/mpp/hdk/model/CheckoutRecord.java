package miu.edu.mpp.hdk.model;

import java.util.Date;

public class CheckoutRecord {

    private LibraryMember member;
    private Book book;
    private String checkoutBy;
    private Date checkoutDate;

    public CheckoutRecord() {
    }

    public CheckoutRecord(LibraryMember member, Book book, String checkoutBy) {
        this.member = member;
        this.book = book;
        this.checkoutBy = checkoutBy;
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

    public String print(){
        return "member=" + member.getMemberId() +
                ", book=" + book.getIsbn() +
                ",\ncheckoutDate=" + checkoutDate;
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
