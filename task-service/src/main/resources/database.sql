DROP TABLE tasks;

CREATE TABLE tasks (
    task_id     NUMBER(20) NOT NULL PRIMARY KEY,
    task        VARCHAR2(1024) NOT NULL,
    completed   NUMBER(1) DEFAULT 0 NOT NULL,
    deleted     NUMBER(1) DEFAULT 0 NOT NULL,
    email       VARCHAR2(250) NOT NULL
);

DROP SEQUENCE task_id_sequence;

CREATE SEQUENCE task_id_sequence;

CREATE OR REPLACE TRIGGER set_task_id BEFORE
    INSERT ON tasks
    FOR EACH ROW
BEGIN
    :new.task_id := task_id_sequence.nextval;
END;
/

COMMIT;