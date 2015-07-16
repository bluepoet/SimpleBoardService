package net.bluepoet.board.service;

import java.util.List;

import javax.annotation.Resource;

import net.bluepoet.board.model.Article;
import net.bluepoet.board.repository.BoardDao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {
	@Resource(name = "boardDao")
	private BoardDao boardDao;

	public void save(Article article) {
		boardDao.save(article);
	}

	public void update(Article article) {
		giveScore(article);
		boardDao.update(article);
	}

	private void giveScore(Article article) {
		String author = article.getAuthor();
		
		if(author.equals("bluepoet1004")) {
			article.setScore(100);
		}else if(author.equals("rucy")) {
			article.setScore(30);
		}else {
			article.setScore(10);
		}
	}

	public void delete(int articleNo) {
		boardDao.delete(articleNo);
	}

	@Transactional(readOnly = true)
	public Article getArticle(int articleNo) {
		return boardDao.getArticle(articleNo);
	}

	@Transactional(readOnly = true)
	public List<Article> getList() {
		return boardDao.getList();
	}
}
