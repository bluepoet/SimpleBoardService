package net.bluepoet.board.service;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import javax.annotation.Resource;

import net.bluepoet.board.config.DbConfig;
import net.bluepoet.board.config.WebConfig;
import net.bluepoet.board.model.Article;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@ContextConfiguration(classes = { DbConfig.class, WebConfig.class }, loader = AnnotationConfigWebContextLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup(value = "config/INIT_BOARD.xml", type = DatabaseOperation.CLEAN_INSERT)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class BoardServiceTest {
	@Resource(name = "boardService")
	private BoardService boardService;

	@Test
	@DatabaseSetup(value = "config/EXPECTED_ADD_BOARD.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void getList() throws Exception {
		// Given
		// When
		List<Article> articles = boardService.getList();

		// Then
		assertThat(articles.size()).isEqualTo(2);
	}

	@Test
	public void getArticle() throws Exception {
		// Given
		// When
		Article article = boardService.getArticle(1);

		// Then
		assertThat(article.getArticleNo()).isEqualTo(1);
		assertThat(article.getTitle()).isEqualTo("테스트");
		assertThat(article.getContent()).isEqualTo("테스트내용입니다.");
		assertThat(article.getAuthor()).isEqualTo("bluepoet");
	}

	@Test
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "config/EXPECTED_ADD_BOARD.xml")
	public void save() throws Exception {
		// Given
		Article article = createArticle(2, "게시판", "내용입니다.", "rucy");

		// When
		boardService.save(article);
	}

	@Test
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "config/EXPECTED_UPDATE_BOARD_AUTHOR_TOM.xml")
	public void update_수정한사람이tom_점수확인() throws Exception {
		// Given
		Article article = createArticle(1, "수정된 게시판", "수정된 내용입니다", "tom");

		// When
		boardService.update(article);
	}

	@Test
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "config/EXPECTED_UPDATE_BOARD_AUTHOR_BLUEPOET1004.xml")
	public void update_수정한사람이bluepoet1004_점수확인() throws Exception {
		// Given
		Article article = createArticle(1, "수정된 게시판", "수정된 내용입니다", "bluepoet1004");

		// When
		boardService.update(article);
	}

	@Test
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "config/EXPECTED_UPDATE_BOARD_AUTHOR_RUCY.xml")
	public void update_수정한사람이rucy_점수확인() throws Exception {
		// Given
		Article article = createArticle(1, "수정된 게시판", "수정된 내용입니다", "rucy");

		// When
		boardService.update(article);
	}

	@Test
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "config/EXPECTED_EMPTY_BOARD.xml")
	public void delete() throws Exception {
		// Given
		// When
		boardService.delete(1);
	}

	private Article createArticle(int articleNo, String title, String content, String author) {
		Article article = new Article();
		article.setArticleNo(articleNo);
		article.setTitle(title);
		article.setContent(content);
		article.setAuthor(author);
		return article;
	}
}
