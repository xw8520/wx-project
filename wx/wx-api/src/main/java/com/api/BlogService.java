package com.api;

import com.domain.Blog;

import java.util.List;

/**
 * Created by TimLin on 2016/1/6.
 */
public interface BlogService {
    Blog selectBlog(int id);

   Boolean addBlog(Blog blog);

   List<Blog> selectBlogs();
}
