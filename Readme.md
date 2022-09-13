## To get a big data we will have 2 ways:

 - divide into smaller piece and query it
 - use Stream in JpaRepository combine with QueryHints

```text
   GET http://localhost:8080/get      // use Stream with @QueryHints

   GET http://localhost:8080/getAll  // normal crud repository
   
   => faster ~ 89%

// get one use @QueryHints + Stream
    GET http://localhost:8080/getOnce // stream
   
   GET http://localhost:8080/getOne   // normal
   
   because data is very small so we can see the different. but in the bigger data, we can see the bigger surprise
```

SQL
```sql
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('1', 'asdf');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('2', 'asdf');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('3', 'sdfhgfd');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('4', 'gdfg');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('5', 'hjg');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('6', 'hgfh');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('7', 'j');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('8', 'hj');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('9', 'ghjhj');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('10', 'g');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('11', 'jl');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('12', 'kl');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('13', ';jhdfh');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('14', 'sdfgds');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('15', 'sdfgjyer');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('16', 'rtwe');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('17', 'yutr');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('18', 'eew');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('19', 'vvhdt');
INSERT INTO `db_example`.`user` (`id`, `name`) VALUES ('20', 'nd');

```