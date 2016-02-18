package com.service;

import com.api.BlogService;
import com.data.BlogMapper;
import com.domain.Blog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TimLin on 2016/1/6.
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    BlogMapper blogMapper;

    public Blog selectBlog(int id) {
        Blog blog = blogMapper.selectBlog(id);

        return blog;
    }

    public Boolean addBlog(Blog blog) {
        Boolean bool = blogMapper.addBlog(blog);

        return bool;
    }

    public List<Blog> selectBlogs() {

        List<Blog> list = null;

        list = blogMapper.selectBlogs();

        return list;
    }

    public void setBlogMapper(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    public BlogMapper getBlogMapper() {
        return blogMapper;
    }
}
