BEGIN TRANSACTION ;

DROP TABLE IF EXISTS movie_genre, movie_person, movie_favorite, users, person, movie, genre;

CREATE TABLE users (
	user_id serial,
	username varchar(25) NOT NULL,
	password_hash text NOT NULL,
	role varchar(50) NOT NULL,

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
	genre_name varchar(50) NOT NULL,

	CONSTRAINT PK_genre PRIMARY KEY (genre_id)
);

CREATE TABLE movie (
	movie_id serial,
	title varchar(50),
	overview text,
	poster text,
	director_id int,
	runtime time,
	date_released date,

	CONSTRAINT PK_movie PRIMARY KEY (movie_id),
	CONSTRAINT FK_movie_person FOREIGN KEY (director_id) REFERENCES person(person_id)
);

CREATE TABLE movie_person (
	movie_id int NOT NULL,
	person_id int NOT NULL,

	CONSTRAINT PK_movie_person PRIMARY KEY (movie_id, person_id),
	CONSTRAINT FK_movie_person_movie FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_movie_person_person FOREIGN KEY (person_id) REFERENCES person(person_id)
);

CREATE TABLE movie_genre (
	movie_id int NOT NULL,
	genre_id int NOT NULL,

	CONSTRAINT PK_movie_genre PRIMARY KEY (movie_id, genre_id),
	CONSTRAINT FK_movie_genre_movie FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_movie_genre_genre FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);

CREATE TABLE movie_favorite (
	user_id int NOT NULL,
	movie_id int NOT NULL,

	CONSTRAINT PK_movie_favorite PRIMARY KEY (user_id, movie_id),
	CONSTRAINT FK_movie_user_movie FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_movie_user_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);



COMMIT;