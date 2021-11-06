package dao.contracts;

import entyties.Book;

import java.util.List;

public interface BookDAO {
    public List<Book> getBooksList();
    public Book getBook(int book_id);
    public Integer addBook(Book book);
    public void updateBook(Book book);
    public void setStatusActive(Book book);
    public void setStatusInactive(Book book);
}
