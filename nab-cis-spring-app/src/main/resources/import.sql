INSERT INTO country (name, created_by, created_date, last_modified_by, last_modified_date) VALUES ('India', 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO country (name, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Australia', 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());

INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Karnataka', (select id from country where name='India'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Tamilnadu', (select id from country where name='India'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Kerala', (select id from country where name='India'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('AndhraPradesh', (select id from country where name='India'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());

INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Victoria', (select id from country where name='Australia'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('NSW', (select id from country where name='Australia'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('QLD', (select id from country where name='Australia'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());

INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Bangalore', (select id from state where name='Karnataka'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Chennai', (select id from state where name='Tamilnadu'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Tiruvandram', (select id from state where name='Kerala'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Hyderabad', (select id from state where name='AndhraPradesh'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());

INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Melbourne', (select id from state where name='Victoria'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Sydney', (select id from state where name='NSW'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Queensland', (select id from state where name='QLD'), 'TestUser', CURRENT_TIMESTAMP(), 'TestUser', CURRENT_TIMESTAMP());
