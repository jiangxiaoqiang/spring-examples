CREATE USER hldev
  NOSUPERUSER
  REPLICATION
  ENCRYPTED PASSWORD 'hldev';

CREATE DATABASE springstarter OWNER = hldev TEMPLATE = template0 ENCODING = 'UTF-8' LC_COLLATE = 'zh_CN.UTF-8' LC_CTYPE = 'zh_CN.UTF-8';

CREATE TABLE credential (
  id           BIGSERIAL PRIMARY KEY,
  email        VARCHAR(255) NULL,
  username     VARCHAR(255) NULL,
  phone        VARCHAR(255) NULL,
  salt         BYTEA        NOT NULL,
  saltpassword BYTEA        NOT NULL,
  createdat    TIMESTAMP    NOT NULL
);

CREATE UNIQUE INDEX credential_email_idx
  ON credential (email);

CREATE UNIQUE INDEX credential_username_idx
  ON credential (username);

CREATE UNIQUE INDEX credential_phone_idx
  ON credential (phone);

CREATE TABLE "user" (
  id        BIGINT PRIMARY KEY,
  nickname  VARCHAR(255),
  age       INT,
  createdat TIMESTAMP NOT NULL,
  updatedat TIMESTAMP NOT NULL
);
