BEGIN TRANSACTION ;

DROP TABLE IF EXISTS favorite_genre, history_swipes, movie_genre, movie_actor, movie_favorite, profile, users, person, movie, genre, collection;

CREATE TABLE collection (
	collection_id serial,
	collection_name text,

	CONSTRAINT PK_collection PRIMARY KEY (collection_id)

);

CREATE TABLE users (
	user_id serial,
	username varchar(25) NOT NULL,
	password_hash text NOT NULL,
	role varchar(50) NOT NULL,

	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE person (
	person_id serial,
	person_name varchar(100) NOT NULL,
	birthday date,
	deathday date,
	biography text,
	profile_path text,
	home_page text,

	CONSTRAINT PK_person PRIMARY KEY (person_id)
);

CREATE TABLE genre (
	genre_id serial,
	genre_name varchar(50) NOT NULL,

	CONSTRAINT PK_genre PRIMARY KEY (genre_id)
);

CREATE TABLE movie (
	movie_id serial,
	title text,
	overview text,
	tagline text,
	poster_path text,
	home_page text,
	release_date date,
	length_minutes int,
	director_id int,
	collection_id int,


	CONSTRAINT PK_movie PRIMARY KEY (movie_id),
	CONSTRAINT FK_movie_person FOREIGN KEY (director_id) REFERENCES person(person_id),
	CONSTRAINT FK_movie_collection FOREIGN KEY (collection_id) REFERENCES collection(collection_id)
);

CREATE TABLE profile (
	profile_id serial,
	user_id int,
	first_name varchar(50),
	last_name varchar(60),
	email varchar(100),

	CONSTRAINT PK_profile_id PRIMARY KEY (profile_id),
	CONSTRAINT FK_profile_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE favorite_genre (
	user_id int,
	genre_id int,

	CONSTRAINT PK_favorite_genre PRIMARY KEY (genre_id, user_id),
	CONSTRAINT FK_genre_user_genre FOREIGN KEY (genre_id) REFERENCES genre(genre_id),
	CONSTRAINT FK_genre_user_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);


CREATE TABLE movie_actor (
	movie_id int NOT NULL,
	actor_id int NOT NULL,

	CONSTRAINT PK_movie_person PRIMARY KEY (movie_id, actor_id),
	CONSTRAINT FK_movie_person_movie FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_movie_person_person FOREIGN KEY (actor_id) REFERENCES person(person_id)
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

CREATE TABLE history_swipes (
	user_id int,
	movie_1_id int,
	movie_2_id int,
	movie_3_id int,
	movie_4_id int,
	movie_5_id int,
	movie_6_id int,
	movie_7_id int,
	movie_8_id int,
	movie_9_id int,
	movie_10_id int,

	CONSTRAINT PK_history_swipes PRIMARY KEY (user_id),
	CONSTRAINT FK_user_movie_1 FOREIGN KEY (movie_1_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_user_movie_2 FOREIGN KEY (movie_2_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_user_movie_3 FOREIGN KEY (movie_3_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_user_movie_4 FOREIGN KEY (movie_4_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_user_movie_5 FOREIGN KEY (movie_5_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_user_movie_6 FOREIGN KEY (movie_6_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_user_movie_7 FOREIGN KEY (movie_7_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_user_movie_8 FOREIGN KEY (movie_8_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_user_movie_9 FOREIGN KEY (movie_9_id) REFERENCES movie(movie_id),
	CONSTRAINT FK_user_movie_10 FOREIGN KEY (movie_10_id) REFERENCES movie(movie_id)
);




COMMIT;