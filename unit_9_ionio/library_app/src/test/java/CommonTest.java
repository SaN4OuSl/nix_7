import org.example.dao.AuthorDao;
import org.example.dao.BookDao;
import org.example.dao.impl.CsvDB.AuthorDaoCsvDBImpl;
import org.example.dao.impl.CsvDB.BookDaoCsvDBImpl;
import org.example.entity.Author;
import org.example.entity.Book;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonTest {
    private final AuthorDao authorDao = new AuthorDaoCsvDBImpl();
    private final BookDao bookDao = new BookDaoCsvDBImpl();

    @Test
    public void test() {
        Author author = new Author();
        author.setName("Name");
        author.setSurname("Surname");
        author.setBirthPlace("City");
        authorDao.create(author);

        Book book = new Book();
        book.setName("Book Name");
        book.setPages(100);
        bookDao.create(book);

        Author readAuthor = authorDao.read(1);
        Book readBook = bookDao.read(1);

        authorDao.update(readAuthor);
        bookDao.update(readBook);

        authorDao.read();
        bookDao.read();

        authorDao.addBookToAuthor(readAuthor, readBook);

        authorDao.delete(readAuthor);
        bookDao.delete(readBook);
    }
}
