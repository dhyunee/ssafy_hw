use ssafy_user;

select * from user;
select * from board;

#1.	user 테이블과 board 테이블을 full outer 조인하면 몇개의 행이 조회되는시 출력하시오.
select count(*) as '행수' from user join board;

#2.	모든 board가 누가 작성했는지 알 수 있도록 작성자의 id와 title을 출력하시오.
select user.id, board.title from user  join board on board.id =user.id;

#3.	board의 title에 ‘헐크’ 가 포함된 게시글의 작성자의 id를 와 title을 출력하시오.
select user.id, board.title from user inner join board on user.id = board.id where title like '헐크';

#4. 2018년 이전에 작성된 board를 작성한 user의 email과 createtime을 출력하시오.
select user.email, board.createtime from user inner join board on user.id = board.id where year(board.createtime) < '2018';

#5. 각 년도에 몇명의 사용자들이 board를 작성했는지 출력하시오. 한명이 어떤 년도에 2개이상 작성했다면 1번만 센다. ( 중복 제거 )
select count(distinct user.id) as '사용자 수', year(board.createtime) as '년도' from user inner join board on board.id =user.id group by year(board.createtime);

#6. 2016년 이후 각 사용자들이 몇개의 board를 작성했는지 알 수 있도록 id와 작성한 board의 개수를 출력하시오.
select user.id , count(board.title) as '개수' from user inner join board on user.id = board.id where year(board.createtime) > '2016' group by user.id;

#7. id가 'BlackWidow'인 사용자 보다 나이가 많은 user의 id와 age를 출력하시오.
select id, age from user where age > (select age from user where id = 'BlackWidow');

#8 2019년에 board를 작성한 적이 있는 사용자의 id를 출력하시오.
select id from user where name = ( select writername from board where year(board.createtime) = '2019');

#9. age가 30부터 60 사이인 사람들이 작성한 board의 title을 출력하시오.
select board.title from (select age, name from user where age between 30 and 60) temp inner join board on temp.id = board.id;

#10. age가 60보다 큰 사람이 board를 작성한 적이 있다면 그 사람의 name을 출력하시오.
select name from user where write_name=(select write_name from board where board.writername = user.name and user.age > 60);
