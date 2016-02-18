package com.test;

import com.api.BlogService;
import com.domain.Blog;
import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Admin on 2016/2/18.
 */
public class TestFrame extends TestCase {
    ClassPathXmlApplicationContext context = null;

    public void setUp() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

        context.start();
    }

    public void testMybatis() {

        BlogService blogService = (BlogService) context.getBean("blogService");
        Blog blog = blogService.selectBlog(1);
        System.out.println(blog.getTitle());
    }
}