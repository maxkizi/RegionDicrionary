INSERT INTO application_user (first_name, last_name, login, password, is_deleted, date_created, date_updated,
                              is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES ('Максим', 'Кизилов', 'admin', '$2a$10$2uJHvM24tDixLOxAcdRt4efThKORJjYusyeO7l3fw9lAtdbv8TR22', false,
        current_timestamp, current_timestamp, true, true, true, true),
       ('Иван', 'Иванов', 'guest', '$2a$10$9xx3IBozItjAX2KnovNfLeFEFCWhRc8Tv21Kv7GDK4zgc6WeL2ZA6', false,
        current_timestamp, current_timestamp, true, true, true, true);

INSERT INTO application_user_role_binding(role_code, application_user_id)
VALUES ('ROLE_ADMIN', 1),
       ('ROLE_GUEST', 2);