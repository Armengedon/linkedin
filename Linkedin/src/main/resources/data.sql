/*insert into app_user (USER_ID, USER_NAME, ENCRYTED_PASSWORD, User_Email) values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'email'); 
insert into app_user (USER_ID, USER_NAME, ENCRYTED_PASSWORD, User_Email)values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'email');
*/
insert into app_role (ROLE_ID, ROLE_NAME) values (1, 'ROLE_ADMIN');
insert into app_role (ROLE_ID, ROLE_NAME) values (2, 'ROLE_USER');
/*
insert into user_role (ID, USER_ID, ROLE_ID) values (1, 1, 1);
insert into user_role (ID, USER_ID, ROLE_ID) values (2, 1, 2);
insert into user_role (ID, USER_ID, ROLE_ID) values (3, 2, 2);
*/