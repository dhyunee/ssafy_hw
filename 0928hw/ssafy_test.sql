CREATE SCHEMA ssafy_test;

use ssafy_test;

#user 라는 테이블이 존재하지 않으면 생성
create table if not exists `user` (
#기본 키 는 id
	`id` varchar(50) not null primary key,
    `pass` varchar(30) not null,
    `name` varchar(50) not null,
    `email` varchar(50) not null
)engine = InnoDB;

#board 라는 테이블이 존재하지 않으면 생성
create table if not exists `board` (
#기본 키 인 게시글 번호는 정수, 생성될 때 자동 증가
	`board_num` int auto_increment primary key,
    `title` varchar(50) not null,
    `content` varchar(100) not null,
    `writer_id` varchar(50) not null,
    `create_time` datetime not null,
# writer_id 는 user 테이블의 id 컬럼을 참조하는 외래 키
    parentID `fk_board_user`
		foreign key (`fk_board_user`)
        referencees `fk_board_user` (`user`)
# 외래키 삭제나 변경시 할 액션
        on delete cascade
        on update cascade
)engine = InnoDB;

# comment 라는 테이블이 존재하지 않으면 생성
create table if not exist `comment` (
	`writer_id` varchar(50) not null,
    `board_num` int not null,
    `writer_name` varchar(50) not null,
    `comment` varchar(50) not null,
    `create_time` datetime not null,
# 기본 키는 writer_id, `board_num`, `create_time` 세가지를 조합
    primary key (`writer_id`, `board_num`, `create_time`),
# board_num 은 board 테이블의 board_num 컬럼을 참조하는 외래 키
    parentID `fk_comment_board`
		foreign key (`fk_board_user`)
        referencees `fk_comment_board` (`fk_board_user`)
        on delete cascade
        on update cascade,
# writer_id 는 user 테이블의 id 컬럼을 참조하는 외래 키
	 parentID`fk_comment_user`
		foreign key (`fk_comment_board`)
        referencees `fk_comment_user` (`fk_comment_board`)
        on delete cascade
        on update cascade
)engine = InnoDB;
