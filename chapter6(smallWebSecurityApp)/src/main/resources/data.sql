INSERT IGNORE INTO `chapter6`.`user` (`id`, `username`, `password`,
`algorithm`) VALUES ('1', 'john', '$2y$05$pybjIYO8PBlwFlonDvwcY.pKuH/sMQDBq7JL60J4X9IQjryax55Zi', 'BCRYPT');
INSERT IGNORE INTO `chapter6`.`authority` (`id`, `name`, `user`) VALUES ('1',
'READ', '1');
INSERT IGNORE INTO `chapter6`.`authority` (`id`, `name`, `user`) VALUES ('2',
'WRITE', '1');
INSERT IGNORE INTO `chapter6`.`product` (`id`, `name`, `price`, `currency`)
VALUES ('1', 'Chocolate', '10', 'USD');