package dao.contracts;

import entyties.Book;
import entyties.Employee;

import java.util.List;

public interface BookOrderDAO {
    public List<Book> getAvailableBookList();
    public java.util.List<Book> getTakenBookList();
    public void makeReservation(Employee employee, Book book);
    public void cancelReservation(Employee employee,Book book);
}
