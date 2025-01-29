INSERT INTO spring_users(username, password, enabled) VALUES ('john', '12345', '1');
INSERT INTO spring_users(username, password, enabled) VALUES ('alice', '123', '1');
INSERT INTO spring_authorities(username, authority) VALUES ('john', 'write');
INSERT INTO spring_authorities(username, authority) VALUES ('alice', 'write');