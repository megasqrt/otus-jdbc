insert into authors(full_name) values('Толстой Лев Николаевич');
insert into authors(full_name) values('Пушкин Александр Сергеевич');
insert into genres(title) values('Роман');
insert into genres(title) values('Сказка');
insert into books (title,author_id,genre_id) values ('Война и мир','1','1');
insert into books (title,author_id,genre_id) values ('У лукоморья дуб зелёный','2','2');
insert into comments(text,book_id) values('Великолепно',1);
insert into comments(text,book_id) values('Шедеврально',1);


