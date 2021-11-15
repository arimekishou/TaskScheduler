CREATE SEQUENCE IF NOT EXISTS confirmation_token_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS task_groups_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS task_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS user_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE app_user
(
    id            BIGINT NOT NULL,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    email         VARCHAR(255),
    password      VARCHAR(255),
    app_user_role VARCHAR(255),
    locked        BOOLEAN,
    enabled       BOOLEAN,
    CONSTRAINT pk_appuser PRIMARY KEY (id)
);

CREATE TABLE confirmation_token
(
    id           BIGINT       NOT NULL,
    token        VARCHAR(255) NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    expires_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    confirmed_at TIMESTAMP WITHOUT TIME ZONE,
    app_user_id  BIGINT       NOT NULL,
    CONSTRAINT pk_confirmationtoken PRIMARY KEY (id)
);

CREATE TABLE task
(
    id               BIGINT NOT NULL,
    task_groups_id   BIGINT NOT NULL,
    title            VARCHAR(255),
    task_description VARCHAR(255),
    start_date       TIMESTAMP WITHOUT TIME ZONE,
    end_date         TIMESTAMP WITHOUT TIME ZONE,
    status           VARCHAR(255),
    CONSTRAINT pk_task PRIMARY KEY (id)
);

CREATE TABLE task_groups
(
    id          BIGINT       NOT NULL,
    title       VARCHAR(255) NOT NULL,
    app_user_id BIGINT       NOT NULL,
    CONSTRAINT pk_taskgroups PRIMARY KEY (id)
);

ALTER TABLE confirmation_token
    ADD CONSTRAINT FK_CONFIRMATIONTOKEN_ON_APP_USER FOREIGN KEY (app_user_id) REFERENCES app_user (id);

ALTER TABLE task_groups
    ADD CONSTRAINT FK_TASKGROUPS_ON_APP_USER FOREIGN KEY (app_user_id) REFERENCES app_user (id);

ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ON_TASK_GROUPS FOREIGN KEY (task_groups_id) REFERENCES task_groups (id);