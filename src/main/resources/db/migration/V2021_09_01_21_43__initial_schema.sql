CREATE TABLE users (
    id BIGINT(20) NOT NULL,
    name VARCHAR(120) NOT NULL,
    email VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE hibernate_sequences (
  sequence_name VARCHAR(255) NOT NULL,
  next_val BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (sequence_name)
);
