select *from users;

select distinct count(id) from users;

select count(id) from users;


select * from users;

select count(*) as borrowedBooks from users u
 inner join book_borrow b on u.id = b.user_id where is_returned = 0;

select name from book_categories;

select name,isbn,author,description,year from books where name='Clean Code' and description = 'this book is awesome';


select bc.name,count(*) from book_borrow bb
inner  join books b on bb.book_id = b.id
inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;


select isbn,name,author from books
where name = 'Head First Java' and author = 'Kathy Sierra' and isbn = '10112028';

select isbn,name,author from books
where name = 'The Scrum Field Guide' and author = 'Mitch Lacey' and isbn = '11112028';

select isbn,name,author from books
where isbn in ('11112028','10112028');