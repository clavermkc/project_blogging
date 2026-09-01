package com.claver.bloggingPlatform.repository;

import com.claver.bloggingPlatform.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("""
            SELECT b FROM Blog b
            WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%'))
               OR LOWER(b.content) LIKE LOWER(CONCAT('%', :term, '%'))
               OR LOWER(b.category) LIKE LOWER(CONCAT('%', :term, '%'))
            """)
    List<Blog> search(@Param("term") String term);
}
