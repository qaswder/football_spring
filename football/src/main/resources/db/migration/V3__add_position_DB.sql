CREATE SEQUENCE fc_position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE fc_position (
    id int8 NOT NULL DEFAULT nextval('fc_position_id_seq'),
    position VARCHAR(255),
    PRIMARY KEY (id));

INSERT INTO fc_position (position)
VALUES
    ('Goalkeeper'),
    ('CentreBack'),
    ('Midfielder'),
    ('Forward');

ALTER TABLE if EXISTS fc_player
    ADD CONSTRAINT  fc_player_fk
    FOREIGN KEY (id_position) REFERENCES fc_position;

UPDATE fc_player
SET id_position = 1
WHERE id = 1;

UPDATE fc_player
SET id_position = 4
WHERE id = 2;

UPDATE fc_player
SET id_position = 3
WHERE id = 3;