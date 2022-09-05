CREATE SEQUENCE fc_stadium_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE fc_stadium (
                             id int8 NOT NULL DEFAULT nextval('fc_stadium_id_seq'),
                             name VARCHAR(255),
                             capacity int4,
                             PRIMARY KEY (id));

CREATE TABLE fc_tournament_stadium (
    id_stadium int8 NOT NULL ,
    id_tournament int8 NOT NULL ,
    PRIMARY KEY (id_tournament, id_stadium));

ALTER TABLE if EXISTS fc_tournament_stadium
    ADD CONSTRAINT fc_stadium_fk
    FOREIGN KEY (id_stadium) REFERENCES fc_stadium;

ALTER TABLE if EXISTS fc_tournament_stadium
    ADD CONSTRAINT fc_tournament_fk
    FOREIGN KEY (id_tournament) REFERENCES fc_tournament;

INSERT INTO fc_stadium (name, capacity)
VALUES
    ('EmiratesStadium', 60361),
    ('WEB_Arena', 30400),
    ('Bernabeu', 80000);

INSERT INTO fc_tournament_stadium (id_tournament, id_stadium)
VALUES
    (1, 2),
    (1, 3),
    (2, 1);

ALTER TABLE fc_match ADD id_stadium int8;

UPDATE fc_match
SET id_stadium = 1
WHERE id = 3;

UPDATE fc_match
SET id_stadium = 2
WHERE id = 1;

UPDATE fc_match
SET id_stadium = 3
WHERE id = 2;