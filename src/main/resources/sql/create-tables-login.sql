CREATE TABLE `udemy_springboot`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));

  
CREATE TABLE `udemy_springboot`.`authority` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unique_user_id_role` (`user_id` ASC, `role` ASC),
  CONSTRAINT `fk_authority_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `udemy_springboot`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

    
insert into user (username, password, enabled) values ('admin', '$2a$10$tuKj0e4UA008RvmwrfGTDe.fzpc5E42NMg6ZFIIqNaOSDorIdUby6', 1);
insert into user (username, password, enabled) values ('willy', '$2a$10$7hsYnkGdzqP9J8zFe85IfuYVrTlmVVsnuvX36ralZ01s1N1YtkmOq', 1);

insert into authority (user_id, role) values (1, 'ROLE_ADMIN');
insert into authority (user_id, role) values (1, 'ROLE_USER');
insert into authority (user_id, role) values (2, 'ROLE_USER');
 