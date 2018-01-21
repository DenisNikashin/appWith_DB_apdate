CREATE DATABASE db_app;
CREATE TABLE user_account
(
  id       INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name     VARCHAR(20) DEFAULT ''        NOT NULL,
  email    VARCHAR(20) DEFAULT ''        NOT NULL,
  country  VARCHAR(20) DEFAULT 'Ukraine' NULL,
  password VARCHAR(20) DEFAULT ''        NOT NULL
)
  ENGINE = InnoDB;

INSERT INTO user_account (id, name, email, country, password)
                            VALUES (DEFAULT, 'Bom', 'Bom@ukr.net', DEFAULT, 1111);
INSERT INTO user_account (id, name, email, country, password)
                            VALUES (DEFAULT, 'Ddd', 'Ddd@ukr.net', DEFAULT, 2222);
