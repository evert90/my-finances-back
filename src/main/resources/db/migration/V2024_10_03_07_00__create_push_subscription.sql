CREATE TABLE push_subscription (
  id BIGINT(20) NOT NULL,
  endpoint varchar(1000) NOT NULL,
  auth_key varchar(1000) NOT NULL,
  p256dh_key varchar(1000) NOT NULL,
  user_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);