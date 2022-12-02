--INSERT INTO BLOG(blog_id,blog_name) VALUES (1,'prvi');
INSERT INTO POST(title,content,author,created_at) VALUES ('naslov','sadržaj', 'ja', now());
INSERT INTO COMMENT(post_id, text, author, created_at) VALUES (1,'komentar', 'komentator', now());
INSERT INTO POST(title,content,author,created_at) VALUES ('naslov2','sadržaj2', 'OpetJa', now());
INSERT INTO POST(title,content,author,created_at) VALUES ('naslov3','sadržaj3', 'OpetJa3', now());
INSERT INTO COMMENT(post_id, text, author, created_at) VALUES (2,'komentar2', 'komentator2', now());