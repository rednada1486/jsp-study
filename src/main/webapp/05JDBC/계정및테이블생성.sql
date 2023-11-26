-- 1. member 테이블 생성
CREATE table member(
	id varchar2(10) not null,
	pass varchar2(10) not null,
	name varchar2(30) not null,
	regidate date default sysdate not null,
	primary key(id)
);

-- 2. board 테이블 생성
CREATE TABLE board(
	num number primary key,
	title varchar2(200) not null,
	content varchar2(2000) not null,
	id varchar2(10) not null,
	postdate date default sysdate not null,
	visitcount number(6)
);

-- board 테이블과 member 테이블 사이의 관계 설정
alter table board
	add constraint board_mem_fk foreign key (id)
	references member (id);
	
-- 일련 번호용 시퀀스 객체 생성
create sequence seq_board_num
	increment by 1
	start with 1
	minvalue 1
	nomaxvalue 
	nocycle
	nocache;
	
-- 더미 데이터 생성
insert into member (id, pass, name) values ('musthave', '1234', '머스트해브');

insert into board (num, title, content, id, postdate, visitcount)
values (seq_board_num.nextval, '제목1입니다', '내용1입니다','musthave', sysdate, 0);