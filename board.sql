CREATE TABLE `board` (
  `article_no` INT(11) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
  `author` VARCHAR(30) NOT NULL,
  `score` INT(11) DEFAULT '0',
  PRIMARY KEY (`article_no`)
) ENGINE=INNODB DEFAULT CHARSET=utf8