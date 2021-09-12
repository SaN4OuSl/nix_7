import org.example.dao.AuthorDao;
import org.example.dao.BookDao;
import org.example.dao.impl.CsvDB.AuthorDaoCsvDBImpl;
import org.example.dao.impl.CsvDB.BookDaoCsvDBImpl;
import org.example.entity.Author;
import org.example.entity.Book;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorDaoTest {
    private final AuthorDao authorDao = new AuthorDaoCsvDBImpl();
    private final BookDao bookDao = new BookDaoCsvDBImpl();

    @Test
    @Order(1)
    public void createTest() {
        for (int i = 1; i <= 10; i++) {
            Author author = new Author();
            author.setName("Name " + i);
            author.setSurname("Surname " + i);
            author.setBirthPlace("City " + i);
            authorDao.create(author);
        }
    }

    @Test
    @Order(2)
    public void readTest() {
        createTest();
        for (int i = 1; i <= 10; i++) {
            Author author = authorDao.read(i);
        }
    }

    @Test
    @Order(3)
    public void updateTest() {
        createTest();
        for (int i = 1; i <= 10; i++) {
            Author author = authorDao.read(i);
            author.setName("Updated Name " + i);
            author.setSurname("Updated Surname " + i);
            author.setBirthPlace("Updated City " + i);
            authorDao.update(author);
        }
    }

    @Test
    @Order(4)
    public void deleteTest() {
        updateTest();
        for (int i = 1; i <= 10; i++) {
            Author author = authorDao.read(i);
            authorDao.delete(author);
        }
    }

    @Test
    @Order(5)
    public void addBookTest() {
        updateTest();
        for (int i = 1; i <= 10; i++) {
            Book book = new Book();
            book.setName("Book Name " + i);
            book.setPages(100 + i);
            bookDao.create(book);

            for (int j = 1; j <= 10; j++) {
                Author author = authorDao.read(j);
                authorDao.addBookToAuthor(author, bookDao.read(i));
            }
        }
    }
}