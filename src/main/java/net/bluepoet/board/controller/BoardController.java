package net.bluepoet.board.controller;

import java.util.List;

import javax.annotation.Resource;

import net.bluepoet.board.model.Article;
import net.bluepoet.board.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name = "boardService")
	private BoardService boardService;

	@RequestMapping(value = "/{articleNo}", method = RequestMethod.GET)
	public String getArticle(@PathVariable int articleNo, ModelMap modelMap) {
		Article article = boardService.getArticle(articleNo);
		modelMap.addAttribute("article", article);
		return "view";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(ModelMap modelMap) {
		List<Article> articles = boardService.getList();
		modelMap.addAttribute("articles", articles);
		return "list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public void save(Article article) {
		boardService.save(article);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public void update(Article article) {
		boardService.update(article);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestParam("articleNo") int articleNo) {
		boardService.delete(articleNo);
	}
}
