package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Book;
import service.BookService;

import java.util.List;

/**
 * Created by jiangxiaoqiang(jiangtingqiang@gmail.com) on 2017-08-04.
 */
@RestController
@RequestMapping(path = "/api/book/")
public class BookController {

   @Autowired
    private BookService bookService;

    @GetMapping("all")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

}
