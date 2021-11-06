package bussiness.implimentation;

import bussiness.contracts.BookManager;
import bussiness.contracts.BookOrderManager;
import dao.contracts.BookDAO;
import dao.contracts.BookOrderDAO;
import entyties.Book;
import entyties.BookOrder;
import entyties.Employee;

import java.util.List;

public class BookOrderManagerAPI implements BookOrderManager {

   private BookOrderDAO dao; //TODO после реализиации заполнить имплементацию
   public void setBookOrderDAO (BookOrderDAO dao){
       this.dao=dao;
   }

    @Override
    public List<Book> getAvailableBookList() {
        return dao.getAvailableBookList();
    }

    @Override
    public List<Book> getTakenBookList() {
        return dao.getTakenBookList();
    }

    @Override
    public void makeReservation(Employee employee, Book book) {
       dao.makeReservation(employee,book);

    }

    @Override
    public void cancelReservation(Employee employee, Book book) {
       dao.makeReservation(employee,book);

    }
}

