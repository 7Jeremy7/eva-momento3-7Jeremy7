CREATE TABLE IF NOT EXISTS film(
    id SERIAL,
    title VARCHAR (100),
    director VARCHAR (100),
    duration INT,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS scene(
    id SERIAL,
    description VARCHAR (100),
    budget DECIMAL (10,2),
    minutes INT,
    film_id INT,  -- Se añade la columna film_id
    PRIMARY KEY (id),
    FOREIGN KEY (film_id) REFERENCES film(id)
    );

CREATE TABLE IF NOT EXISTS character(
    id SERIAL,
    description VARCHAR (100),
    cost DECIMAL (10,3),
    stock INT,
    scene_id INT,  -- Se añade la columna scene_id
    PRIMARY KEY(id),
    FOREIGN KEY (scene_id) REFERENCES scene(id)
    );
