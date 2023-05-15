INSERT INTO roles(ID, NAME) VALUES (1, 'SITE_ADMIN');
INSERT INTO roles(ID, NAME) VALUES (2, 'ADMIN');
INSERT INTO roles(ID, NAME) VALUES (3, 'USER');


insert into users (account_expired, account_locked, credentials_expired, enabled, is_deleted, is_terms_policy_agreed, login_name, password, phone,is_owner) values 
(false, false, false, true, false, false, 'admin', /*test123*/'$2a$08$KPvA7WTJ3.7JgWu31coZ8eVAIPB4Dd96R/Uo2bgqZ5HxJrgvc.A.S','1234567890',true );
INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);

CREATE TABLE oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),	
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

INSERT INTO oauth_client_details(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('gmc-client', 'api',
 /*password1234*/'$2a$04$n/Vt.4gKkscgBV9UYHlq5uw.qkp5Ah.a593gYNpItL0g3Lrg.PMiS',
 'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 2592000, 2592000);

INSERT INTO authorities(ID, NAME) VALUES (1, 'CREATE_USER');
INSERT INTO authorities(ID, NAME) VALUES (2, 'DELETE_USER');
INSERT INTO authorities(ID, NAME) VALUES (3, 'READ_USER');
INSERT INTO authorities(ID, NAME) VALUES (4, 'UPDATE_USER');
-- INSERT INTO authorities(ID, NAME) VALUES (1, 'CREATE_USER');

INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (1, 3);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (1, 4);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (2, 3);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (3, 1);