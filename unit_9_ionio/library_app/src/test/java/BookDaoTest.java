import org.example.dao.AuthorDao;
import org.example.dao.BookDao;
import org.example.dao.impl.CsvDB.AuthorDaoCsvDBImpl;
import org.example.dao.impl.CsvDB.BookDaoCsvDBImpl;
import org.example.entity.Author;
import org.example.entity.Book;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDaoTest {
    private final AuthorDao authorDao = new AuthorDaoCsvDBImpl();
    private final BookDao bookDao = new BookDaoCsvDBImpl();

    @Test
    @Order(1)
    public void createTest() {
        for (int i = 1; i <= 10; i++) {
            Book book = new Book();
            book.setName("Book Name " + i);
            book.setPages(100 + i);
            bookDao.create(book);
        }
    }

    @Test
    @Order(2)
    public void readTest() {
        createTest();
        for (int i = 1; i <= 10; i++) {
            Book book = bookDao.read(i);
        }
    }

    @Test
    @Order(3)
    public void updateTest() {
        createTest();
        for (int i = 1; i <= 10; i++) {
            Book book = bookDao.read(i);
            book.setName("Updated Book Name " + i);
            book.setPages(200 + i);
            bookDao.update(book);
        }
    }

    @Test
    @Order(4)
    public void deleteTest() {
        updateTest();
        for (int i = 1; i <= 10; i++) {
            Book book = bookDao.read(i);
            bookDao.delete(book);
        }
    }

    @Test
    @Order(5)
    public void addAuthorTest() {
        updateTest();
        for (int i = 1; i <= 10; i++) {
            Author author = new Author();
            author.setName("Author name" + i);
            author.setSurname("Author surname" + i);
            author.setBirthPlace("Author birth place" + i);
            authorDao.create(author);

            for (int j = 1; j <= 10; j++) {
                Book book = bookDao.read(j);
                bookDao.addAuthorToBook(book, authorDao.read(i));
            }
        }
    }
}
