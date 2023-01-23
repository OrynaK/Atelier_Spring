
CREATE DATABASE IF NOT EXISTS atelier;

USE atelier;

DROP TABLE IF EXISTS receipt;
DROP TABLE IF EXISTS model;
DROP TABLE IF EXISTS fabric;
DROP TABLE IF EXISTS client ;
DROP TABLE IF EXISTS cutter ;

CREATE TABLE IF NOT EXISTS fabric (
    f_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(300) NOT NULL,
    width DECIMAL(9 , 2 ) UNSIGNED NOT NULL,
    price_per_meter DECIMAL(9, 2) UNSIGNED NOT NULL,
    PRIMARY KEY (f_id)
    );


CREATE TABLE IF NOT EXISTS model (
    m_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(300) NOT NULL,
    proposed_fabric INT UNSIGNED NOT NULL,
    tailoring_price DECIMAL(9,2) UNSIGNED NOT NULL,
    consumption DECIMAL(9,2) UNSIGNED NOT NULL,
    PRIMARY KEY (m_id),
    CONSTRAINT fk_proposed_fabric
    FOREIGN KEY (proposed_fabric)
    REFERENCES fabric (f_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS client (
    cl_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(500) NOT NULL,
    surname VARCHAR(500) NOT NULL,
    PRIMARY KEY (cl_id));



CREATE TABLE IF NOT EXISTS cutter (
    c_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(500) NOT NULL,
    surname VARCHAR(500) NOT NULL,
    PRIMARY KEY (c_id));


CREATE TABLE IF NOT EXISTS receipt (
    o_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    client_id INT UNSIGNED NOT NULL,
    model_id INT UNSIGNED NOT NULL,
    fabric_id INT UNSIGNED NOT NULL,
    cutter_id INT UNSIGNED NOT NULL,
    date_acceptance DATE NOT NULL,
    date_fitting DATE NOT NULL,
    due_date DATE NOT NULL,
    `status` boolean NOT NULL,
    PRIMARY KEY (o_id),
    CONSTRAINT fk_client_id
    FOREIGN KEY (client_id)
    REFERENCES client (cl_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    CONSTRAINT fk_model_id
    FOREIGN KEY (model_id)
    REFERENCES model (m_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    CONSTRAINT fk_fabric_id
    FOREIGN KEY (fabric_id)
    REFERENCES fabric (f_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    CONSTRAINT fk_cutter_id
    FOREIGN KEY (cutter_id)
    REFERENCES cutter (c_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

INSERT INTO fabric
VALUES (NULL,'cashmere','3','240'),
       (NULL,'angora','2.5','260'),
       (NULL,'velveteen','2.7','80'),
       (NULL,'gabardine','1.2','125'),
       (NULL,'eco leather','2.5','320');

INSERT INTO model
VALUES ( NULL,'should dress','1','900','309.5'),
       ( NULL,'bandeau','2','800','410'),
       ( NULL,'parachute jumpsuit','2','1500','1090.5'),
       ( NULL,'bodysuit','5','850','350'),
       ( NULL,'tracksuits','5','600','1040'),
       ( NULL,'bodycon','1','500','500'),
       ( NULL,'midi dress','4','550','1290.6'),
       ( NULL,'maxi dress','3','800','1300');

INSERT INTO cutter
VALUES (NULL, 'Mary','Smith'),
       (NULL, 'Patricia','Jones'),
       (NULL, 'David','Taylor');

INSERT INTO `client`
VALUES (NULL, 'Elizabeth','Brown'),
       (NULL, 'Susan','Davies'),
       (NULL, 'Karen','Thomas'),
       (NULL, 'Ashley','Davies'),
       (NULL, 'Joshua','Dunn'),
       (NULL, 'Jessica','Evans');

INSERT INTO receipt
VALUES (NULL, '1', '8', '1','3','21.11.15','21.12.02', '21.12.10',false),
       (NULL, '2', '3', '4','2','21.11.10','21.12.5', '21.12.12',false),
       (NULL, '3', '4', '3','2','21.10.30','21.11.15', '21.11.25',true),
       (NULL, '4', '3', '4','3','21.11.01','21.11.17', '21.11.30',false),
       (NULL, '5', '1', '2','3','21.12.01','21.12.13', '21.12.18',false),
       (NULL, '6', '6', '1','2','21.11.26','21.12.04', '21.12.09',false);
