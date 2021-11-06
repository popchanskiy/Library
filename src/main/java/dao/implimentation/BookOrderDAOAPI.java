package dao.implimentation;

import dao.contracts.BookDAO;
import dao.contracts.BookOrderDAO;
import entyties.Book;
import entyties.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookOrderDAOAPI implements BookOrderDAO {

    private final String SELECT_AVAILABLE_BOOK="SELECT * FROM book WHERE book_status=0";
    private final String SELECT_TAKEN_BOOK="SELECT * FROM book WHERE book_status=1";
    private final String INSERT="INSERT INTO book_order (book_id, employee_id) VALUES (?, ?)"; // insert into DB book_order new reservation/book order
    private final String DELETE="DELETE * FROM book_order WHERE book_id= ?";// unclaim the book, delete it from
    //private final String UPDATE_BOOK_STATUS="UPDATE book SET book_status = ? WHERE book_id=?";

    private ConnectionBuilderAPI connectionBuilder=ConnectionBuilderAPI.getInstance();
    private BookDAO bookDAO;

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }
    @Override
    public List<Book> getAvailableBookList() { //TODO have to implement
        List<Book> availableBookList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(SELECT_AVAILABLE_BOOK);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Book book = new Book();
                    book.setBookID(result.getInt("book_id"));
                    book.setBookAuthor(result.getString("book_author"));
                    book.setBookName(result.getString("book_name"));
                    book.setPrice(result.getDouble("price"));
                    availableBookList.add(book);
                }

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return availableBookList;
    }



    @Override
    public List<Book> getTakenBookList() {
        List<Book> takenBookList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(SELECT_TAKEN_BOOK);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Book book = new Book();
                    book.setBookID(result.getInt("book_id"));
                    book.setBookAuthor(result.getString("book_author"));
                    book.setBookName(result.getString("book_name"));
                    book.setPrice(result.getDouble("price"));
                    takenBookList.add(book);
                }

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return takenBookList;
    }

    @Override
    public void makeReservation(Employee employee, Book book) {
        try {
            Connection connection = getConnection();
            try {
                bookDAO=new BookDAOAPI();
                bookDAO.setStatusActive(book);
                PreparedStatement statement = connection.prepareStatement(INSERT);
                statement.setInt(1,book.getBookID());
                statement.setInt(2,employee.getEmployeeId());
                statement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void cancelReservation(Employee employee, Book book) {
        try {
            Connection connection = getConnection();
            try {
                bookDAO=new BookDAOAPI();
                bookDAO.setStatusInactive(book);
                PreparedStatement statement = connection.prepareStatement(DELETE);
                statement.setInt(1,book.getBookID());
                statement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

