CREATE TABLE users (
    user_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name varchar(50) NOT NULL,
    middle_name varchar(50),
    last_name varchar(50),
    PRIMARY KEY (user_id)
)