INSERT INTO sbnz.user 
VALUES (1,'bronza', '123', '2020-06-10', 'stefan');
INSERT INTO sbnz.user 
VALUES (2,'bronza', '123', '2020-06-10', 'cinc');
INSERT INTO sbnz.user 
VALUES (3,'bronza', '123', '2020-06-10', 'miki');
INSERT INTO sbnz.user 
VALUES (4,'bronza', '123', '2020-06-10', 'mile');

INSERT INTO sbnz.car  
VALUES (1,'prva','2020-06-10',1500,'Golf', 'VW',0,2000,0);
INSERT INTO sbnz.car  
VALUES (2,'prva','2020-06-10',10,'3', 'BMW',0,3000,0);
INSERT INTO sbnz.car  
VALUES (3,'prva','2020-06-10',12000,'Astra', 'OPEL',0,1000,0);
INSERT INTO sbnz.car  
VALUES (4,'prva','2020-06-10',12000,'Korsa', 'OPEL',0,1000,0);
INSERT INTO sbnz.car  
VALUES (5,'prva','2020-06-10',10,'5', 'BMW',0,3000,0);

INSERT INTO sbnz.reservation --dzabe
VALUES (1,20,'2020-06-24',0, 3000, 'REZERVISAN','2020-06-25', 1,1);

INSERT INTO sbnz.reservation --30%
VALUES (2,20,'2020-05-18',0, 3000, 'REZERVISAN','2020-05-19', 2,2);

INSERT INTO sbnz.reservation --50%
VALUES (3,20,'2020-05-15',0, 3000, 'REZERVISAN','2020-05-16', 3,3);
INSERT INTO sbnz.reservation 
VALUES (4,20,'2020-06-11', 0,3000, 'REZERVISAN','2020-06-12', 4,2);
INSERT INTO sbnz.reservation 
VALUES (5,20,'2020-05-13', 0,3000, 'REZERVISAN','2020-05-14', 4,2);
INSERT INTO sbnz.reservation --55%
VALUES (6,20,'2020-05-15',0, 3000, 'REZERVISAN','2020-05-16', 4,3);
INSERT INTO sbnz.reservation --60%
VALUES (7,20,'2020-05-15',0, 3000, 'REZERVISAN','2020-05-16', 5,3);
INSERT INTO sbnz.reservation 
VALUES (8,20,'2013-05-15',0, 3000, 'ZAVRSEN','2013-05-16', 5,3);
INSERT INTO sbnz.reservation 
VALUES (9,20,'2014-05-15',0, 3000, 'ZAVRSEN','2014-05-16', 5,3);
INSERT INTO sbnz.reservation 
VALUES (10,20,'2015-05-15',0, 3000, 'ZAVRSEN','2015-05-16', 5,3);
INSERT INTO sbnz.reservation 
VALUES (11,20,'2016-05-15',0, 3000, 'ZAVRSEN','2016-05-16', 5,3);
INSERT INTO sbnz.reservation 
VALUES (12,20,'2017-05-15',0, 3000, 'ZAVRSEN','2017-05-16', 5,3);
INSERT INTO sbnz.reservation 
VALUES (13,20,'2018-05-15',0, 3000, 'ZAVRSEN','2018-05-16', 5,3);