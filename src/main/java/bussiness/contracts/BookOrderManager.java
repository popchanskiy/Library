package bussiness.contracts;

import entyties.Book;
import entyties.Employee;

import java.util.List;

public interface BookOrderManager {
    public List<Book> getAvailableBookList();
    public List<Book>getTakenBookList();
    public void makeReservation(Employee employee, Book book);
    public void cancelReservation(Employee employee,Book book);
}
