package service;


import mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Book;


import java.util.List;

/**
 * Created by jiangxiaoqiang(jiangtingqiang@gmail.com) on 2017-08-04.
 */
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> getBooks() {
        return bookMapper.selectAll();
    }
}
