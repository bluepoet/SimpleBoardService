package net.bluepoet.board.service;

import java.util.List;

import net.bluepoet.board.model.Article;

public interface BoardService {
	void save(Article article);
	void update(Article article);
	void delete(int articleNo);
	Article getArticle(int articleNo);
	List<Article> getList();
}
