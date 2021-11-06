import bussiness.contracts.BookManager;
import bussiness.implimentation.BookManagerAPI;
import dao.contracts.BookDAO;
import dao.implimentation.BookDAOAPI;
import dao.implimentation.ConnectionBuilderAPI;

public class
Run {
    public static void main(String[] args) {

        BookDAOAPI bookDAOAPI=new BookDAOAPI();
        BookManager bookManager= new BookManagerAPI();
        bookManager.setDao(bookDAOAPI);


        System.out.println(bookManager.getBooksList().toString());

    }
}
