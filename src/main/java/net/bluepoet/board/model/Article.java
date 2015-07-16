package net.bluepoet.board.model;

public class Article {
	private int articleNo;
	private String author;
	private String title;
	private String content;
	private int score;
	
	public Article() {}
	
	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	private Article(Builder builder) {
		this.articleNo = builder.articleNo;
		this.author = builder.author;
		this.title = builder.title;
		this.content = builder.content;
	}

	public static class Builder {
		private int articleNo;
		private String author;
		private String title;
		private String content;

		public Builder(int articleNo, String author, String title) {
			this.articleNo = articleNo;
			this.author = author;
			this.title = title;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Article build() {
			return new Article(this);
		}
	}
}
