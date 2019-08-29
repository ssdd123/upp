insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Editor', 21, 'q', 'e1', 'Novi Sad', 'Srbija', 'editor1@gmail.com', 'EDITOR');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Editor', 22, 'q', 'e2', 'Novi Sad', 'Srbija', 'editor2@gmail.com', 'EDITOR');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Reviewer', 23, 'q', 'rev1', 'Novi Sad', 'Srbija', 'reviewer1@gmail.com', 'REVIEWER');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Reviewer', 24, 'q', 'rev2', 'Novi Sad', 'Srbija', 'reviewer2@gmail.com', 'REVIEWER');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Reviewer', 25, 'q', 'rev3', 'Novi Sad', 'Srbija', 'reviewer3@gmail.com', 'REVIEWER');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Reviewer', 26, 'q', 'rev4', 'Novi Sad', 'Srbija', 'reviewer4@gmail.com', 'REVIEWER');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Reviewer', 27, 'q', 'rev5', 'Novi Sad', 'Srbija', 'reviewer5@gmail.com', 'REVIEWER');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Reviewer', 28, 'q', 'rev6', 'Novi Sad', 'Srbija', 'reviewer6@gmail.com', 'REVIEWER');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Registered', 29, 'q', 'reg1', 'Novi Sad', 'Srbija', 'registered1@gmail.com', 'REGISTERED');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Registered', 30, 'q', 'reg2', 'Novi Sad', 'Srbija', 'registered2@gmail.com', 'REGISTERED');
insert into user_table (DTYPE, id, password, name, city, country, email, role) values ('Registered', 31, 'q', 'reg3', 'Novi Sad', 'Srbija', 'registered3@gmail.com', 'REGISTERED');
	
insert into scientific_field (id, name) values (21, 'chemistry');
insert into scientific_field (id, name) values (22, 'biology');
insert into scientific_field (id, name) values (23, 'mathematics');

insert into reviewer_scientific_field (id, id_reviewer, id_scientific_field) values (21, 23, 21);
insert into reviewer_scientific_field (id, id_reviewer, id_scientific_field) values (22, 24, 21);
insert into reviewer_scientific_field (id, id_reviewer, id_scientific_field) values (23, 25, 21);

insert into magazine (id, title, issn, id_editor, open_access) values (21, 'Magazine chem bio', 'asdf', 21, false);
insert into magazine (id, title, issn, id_editor, open_access) values (22, 'Magazine chem math', 'sdfg', 21, false);
insert into magazine (id, title, issn, id_editor, open_access) values (23, 'Magazine bio math', 'dfgh', 22, false);
insert into magazine (id, title, issn, id_editor, open_access) values (24, 'Magazine bio', 'fghj', 22, false);

insert into magazine_scientific_field (id, id_magazine, id_scientific_field) values (21, 21, 21);
insert into magazine_scientific_field (id, id_magazine, id_scientific_field) values (22, 21, 22);
insert into magazine_scientific_field (id, id_magazine, id_scientific_field) values (23, 22, 21);
insert into magazine_scientific_field (id, id_magazine, id_scientific_field) values (24, 22, 23);
insert into magazine_scientific_field (id, id_magazine, id_scientific_field) values (25, 23, 22);
insert into magazine_scientific_field (id, id_magazine, id_scientific_field) values (26, 23, 23);
insert into magazine_scientific_field (id, id_magazine, id_scientific_field) values (27, 24, 22);

insert into magazine_scientific_field_editor (id, id_editor, id_magazine_scientific_field) values (21, 21, 21);
insert into magazine_scientific_field_editor (id, id_editor, id_magazine_scientific_field) values (22, 21, 22);
insert into magazine_scientific_field_editor (id, id_editor, id_magazine_scientific_field) values (23, 22, 21);

insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (21, 'bio1 abstract', 'SUBMITTED_FOR_EDITOR', 'bio1 content', 'bio1 key words', 'Title bio1', 29, 21, 22, null);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (22, 'chem1 abstract', 'SUBMITTED_FOR_EDITOR', 'chem1 content', 'chem1 key words', 'Title chem1', 30, 21, 21, null);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (23, 'chem2 abstract', 'SUBMITTED_FOR_EDITOR', 'chem2 content', 'chem2 key words', 'Title chem2', 30, 21, 21, null);
	
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (24, 'bio2 abstract', 'NEEDS_FORMATTING', 'bio2 content', 'bio2 key words', 'Title bio2', 29, 21, 22, null);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (25, 'bio3 abstract', 'NEEDS_FORMATTING', 'bio3 content', 'bio3 key words', 'Title bio3', 30, 21, 22, null);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (26, 'bio4 abstract', 'NEEDS_FORMATTING', 'bio4 content', 'bio4 key words', 'Title bio4', 30, 21, 22, null);
	
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (27, 'math1 abstract', 'FOR_FIRST_REVIEW', 'math1 content', 'math1 key words', 'Title math1', 29, 22, 23, null);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (28, 'math2 abstract', 'FOR_FIRST_REVIEW', 'math2 content', 'math2 key words', 'Title math2', 30, 22, 23, null);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (29, 'chem3 abstract', 'FOR_FIRST_REVIEW', 'chem3 content', 'chem3 key words', 'Title chem3', 30, 22, 21, null);
insert into article_reviewer (id, id_article, id_reviewer) values (21, 27, 23);
insert into article_reviewer (id, id_article, id_reviewer) values (22, 27, 24);
insert into article_reviewer (id, id_article, id_reviewer) values (23, 28, 24);
insert into article_reviewer (id, id_article, id_reviewer) values (24, 28, 25);
insert into article_reviewer (id, id_article, id_reviewer) values (25, 29, 25);
insert into article_reviewer (id, id_article, id_reviewer) values (26, 29, 23);

insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (30, 'math3 abstract', 'FOR_SECOND_REVIEW', 'math3 content', 'math3 key words', 'Title math3', 29, 22, 23, 23);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (31, 'math4 abstract', 'FOR_SECOND_REVIEW', 'math4 content', 'math4 key words', 'Title math4', 30, 22, 23, 23);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (32, 'chem4 abstract', 'FOR_SECOND_REVIEW', 'chem4 content', 'chem4 key words', 'Title chem4', 30, 22, 21, 24);
	
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (33, 'bio5 abstract', 'REVIEWED_FOR_EDITOR_FIRST', 'bio5 content', 'bio5 key words', 'Title bio5', 29, 24, 22, null);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (34, 'bio6 abstract', 'REVIEWED_FOR_EDITOR_FIRST', 'bio6 content', 'bio6 key words', 'Title bio6', 30, 24, 22, null);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (35, 'chem5 abstract', 'REVIEWED_FOR_EDITOR_FIRST', 'chem5 content', 'chem5 key words', 'Title chem5', 30, 21, 21, null);
	
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (36, 'chem6 abstract', 'REVIEWED_FOR_EDITOR_SECOND', 'chem6 content', 'chem6 key words', 'Title chem6', 29, 21, 21, 27);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (37, 'chem7 abstract', 'REVIEWED_FOR_EDITOR_SECOND', 'chem7 content', 'chem7 key words', 'Title chem7', 30, 21, 21, 27);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (38, 'math5 abstract', 'REVIEWED_FOR_EDITOR_SECOND', 'math5 content', 'math5 key words', 'Title math5', 30, 22, 23, 27);
	
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (39, 'bio7 abstract', 'NEEDS_CORRECTION', 'bio7 content', 'bio7 key words', 'Title bio7', 29, 21, 22, null);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (40, 'chem8 abstract', 'NEEDS_CORRECTION', 'chem8 content', 'chem8 key words', 'Title chem8', 30, 22, 21, 27);
insert into article (id, article_abstract, article_status, content, key_words, title, id_author, id_magazine, id_scientific_field, id_second_reviewer) 
	values (41, 'math6 abstract', 'NEEDS_CORRECTION', 'math6 content', 'math6 key words', 'Title math6', 30, 23, 23, null);
