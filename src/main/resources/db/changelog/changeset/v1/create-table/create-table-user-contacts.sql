CREATE TABLE user_contacts (
    contact_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id bigint,
    email varchar(50) UNIQUE NOT NULL,
    phone varchar(10),
    PRIMARY KEY (contact_id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE
)