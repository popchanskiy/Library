package bussiness.implimentation;

import bussiness.contracts.BookManager;
import dao.contracts.BookDAO;
import entyties.Book;

import java.util.List;

public class BookManagerAPI implements BookManager{
    private BookDAO dao;

    public void setDao(BookDAO dao){
        this.dao=dao;
    }

    @Override
    public List<Book> getBooksList() {
        return dao.getBooksList();
    }

    @Override
    public Book getBook(int book_id) {
        return dao.getBook(book_id);
    }

    @Override
    public Integer addBook(Book book) {return  dao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        dao.updateBook(book);
    }

    @Override
    public void setStatusActive(Book book) {
        dao.setStatusActive(book);
    }

    @Override
    public void setStatusInactive(Book book) {
        dao.setStatusInactive(book);
    }
}
