package net.bluepoet.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import net.bluepoet.board.model.Article;

@Repository
public interface BoardDao {
	@Insert("insert into board(article_no, author, title, content) values(#{articleNo}, #{author}, #{title}, #{content})")
	void save(Article article);

	@Update("update board set author=#{author}, title=#{title}, content=#{content}, score=#{score} where article_no=#{articleNo}")
	void update(Article article);

	@Delete("delete from board where article_no=#{articleNo}")
	void delete(int articleNo);

	@Select("select article_no as articleNo,author,title,content from board where article_no=#{articleNo}")
	Article getArticle(int articleNo);

	@Select("select article_no as articleNo,author,title from board")
	List<Article> getList();
}
