BEGIN TRANSACTION ;

DROP TABLE IF EXISTS users, person, genre, movies, movies_person, movies_genre;

CREATE TABLE users (
	user_id serial,
	username varchar(25) NOT NULL,
	password text NOT NULL,
	role varchar(10) NOT NULL,

	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE person (
	person_id serial,
	name varchar(50) NOT NULL,
	birthday date NOT NULL,

	CONSTRAINT PK_person PRIMARY KEY (person_id)
);

CREATE TABLE genre (
	genre_id serial,
	name varchar(50) NOT NULL,

	CONSTRAINT PK_genre PRIMARY KEY (genre_id)
);

CREATE TABLE movies (
	movie_id serial,
	title varchar(50),
	overview text,
	poster text,
	director_id int,
	runtime time,
	date_released date,

	CONSTRAINT PK_movies PRIMARY KEY (movie_id),
	CONSTRAINT FK_movies_director FOREIGN KEY (director_id) REFERENCES person(person_id)
);

CREATE TABLE movies_person (
	movie_id int NOT NULL,
	person_id int NOT NULL,

	CONSTRAINT PK_movies_person PRIMARY KEY (movie_id, person_id),
	CONSTRAINT FK_movies_person_movies FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
	CONSTRAINT FK_movies_person_person FOREIGN KEY (person_id) REFERENCES person(person_id)
);

CREATE TABLE movies_genre (
	movie_id int NOT NULL,
	genre_id int NOT NULL,

	CONSTRAINT PK_movies_genre PRIMARY KEY (movie_id, genre_id),
	CONSTRAINT FK_movies_genre_movies FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
	CONSTRAINT FK_movies_genre_genre FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);

CREATE TABLE movies_favorite (
	user_id int NOT NULL,
	movie_id int NOT NULL,

	CONSTRAINT PK_movies_favorite PRIMARY KEY (user_id, movie_id),
	CONSTRAINT FK_movies_user_movies FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
	CONSTRAINT FK_movies_user_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

COMMIT;
