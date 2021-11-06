package dao.implimentation;

import dao.contracts.BookDAO;
import dao.contracts.ConnectionBuilder;
import entyties.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOAPI implements BookDAO {
    private final String SELECT_ALL="SELECT* FROM book";
    private final String SELECT_BOOK= "SELECT * FROM book WHERE book_id =?";
    private final String UPDATE_BOOK= "UPDATE book SET book_name=?,book_author=?,price=? WHERE book_id=?";
    private final String INSERT= "INSERT INTO book (book_name, book_author, price) VALUES (?,?,?)";
    private final String UPDATE_STATUS_ACTIVE="UPDATE book book_status SET book_status=1 WHERE book_id= ?";
    private final String UPDATE_STATUS_INACTIVE="UPDATE book book_status SET book_status=0 WHERE book_id= ?";
    private ConnectionBuilder connectionBuilder=ConnectionBuilderAPI.getInstance();

    public Connection getConnection() throws SQLException {// можно обосраться, если забыть закрыть connection
        return connectionBuilder.getConnection();
    }

    @Override
    public List<Book> getBooksList() {
        List<Book> bookList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Book book = new Book();
                    book.setBookID(result.getInt("book_id"));
                    book.setBookAuthor(result.getString("book_author"));
                    book.setBookName(result.getString("book_name"));
                    book.setPrice(result.getDouble("price"));
                    bookList.add(book);
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
        return bookList;

    }

    @Override
    public Book getBook(int book_id) {
        Book book=new Book();
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(SELECT_BOOK);
                statement.setInt(1,book_id);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    book.setBookID(result.getInt("book_id"));
                    book.setBookAuthor(result.getString("book_author"));
                    book.setBookName(result.getString("book_name"));
                    book.setPrice(result.getDouble("price"));
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
        return book;
    }

    @Override
    public Integer addBook(Book book) {
        Integer result =-1;
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(INSERT,new String[]{"book_id"});
                statement.setString(1,book.getBookName());
                statement.setString(2,book.getBookAuthor());
                statement.setDouble(3,book.getPrice());
                statement.executeUpdate();
                ResultSet resultSet=statement.getGeneratedKeys();
                while (resultSet.next()){
                    result=resultSet.getInt(1);
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
        return result;
    }


    @Override
    public void updateBook(Book book) {
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK);
                statement.setString(1,book.getBookName());
                statement.setString(2,book.getBookAuthor());
                statement.setDouble(3,book.getPrice());
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
    public void setStatusActive(Book book) {
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_ACTIVE);
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

    @Override
    public void setStatusInactive(Book book) {
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_INACTIVE);
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
