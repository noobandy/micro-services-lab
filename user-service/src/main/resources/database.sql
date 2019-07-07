drop table user_details;

CREATE TABLE user_details (
    user_id               NUMBER(20) NOT NULL PRIMARY KEY,
    first_name varchar2(250),
    last_name varchar2(250),
    email                 VARCHAR2(250) not null,
    password              VARCHAR2(250),
    enabled               NUMBER(1) DEFAULT 1 not null,
    password_expires_at   TIMESTAMP,
    last_updated TIMESTAMP default CURRENT_TIMESTAMP not null
);

drop SEQUENCE user_id_sequence;

create sequence user_id_sequence;

create  or replace trigger set_user_id before INSERT on user_details
FOR EACH ROW
BEGIN
    :new.user_id := user_id_sequence.nextval;
end;
/

commit;