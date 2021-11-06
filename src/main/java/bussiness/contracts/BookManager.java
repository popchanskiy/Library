package bussiness.contracts;

import dao.contracts.BookDAO;
import entyties.Book;

import java.util.List;

public interface BookManager {
    public void setDao(BookDAO dao);
    public List<Book> getBooksList();
    public Book getBook(int book_id);
    public Integer addBook(Book book);
    public void updateBook(Book book);
    public void setStatusActive(Book book);
    public void setStatusInactive(Book book);
}
