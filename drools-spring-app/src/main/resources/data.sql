INSERT INTO sbnz.user 
VALUES (100,'bronza', '123',false, '2020-06-10', 'stefan');
INSERT INTO sbnz.user 
VALUES (200,'bronza', '123',false, '2020-06-10', 'cinc');
INSERT INTO sbnz.user 
VALUES (300,'bronza', '123',false, '2019-06-10', 'miki');
INSERT INTO sbnz.user 
VALUES (400,'bronza', '123',false, '2020-06-10', 'mile');
INSERT INTO sbnz.user 
VALUES (500,'bronza', 'admin',false, '2020-06-10', 'admin');

INSERT INTO sbnz.recommendation VALUES (100,'stefan', 'miki');
INSERT INTO sbnz.recommendation VALUES (101,'miki', 'cinc');

INSERT INTO sbnz.car  
VALUES (1,'prva',0,'2019-06-10','https://autorepublika.com/wp-content/uploads/2019/10/20.jpg',27000,'Golf', 'VW',0,20,0);
INSERT INTO sbnz.car  
VALUES (2,'prva',0,'2020-01-20','https://autorepublika.com/wp-content/uploads/2019/01/1-299.jpg',19000,'3', 'BMW',0,35,0);
INSERT INTO sbnz.car  
VALUES (3,'prva',0,'2017-08-9','https://www.b92.net/news/pics/2017/11/18/10269290085a0ff900403a8689518380_orig.jpg',120000,'Astra', 'OPEL',0,15,0);
INSERT INTO sbnz.car  
VALUES (4,'prva',0,'2018-02-17','https://i.ytimg.com/vi/_fokJ36y8wM/maxresdefault.jpg',30000,'Insignia', 'OPEL',0,25,0);
INSERT INTO sbnz.car  
VALUES (5,'prva',0,'2020-01-9','https://autorepublika.com/wp-content/uploads/2019/08/bmw-x5-protection2.jpg',5000,'X5', 'BMW',0,50,0);
INSERT INTO sbnz.car  
VALUES (6,'prva',0,'2020-02-17','https://41cef548pyo610dh50468nkk-wpengine.netdna-ssl.com/wp-content/uploads/2020/02/2020-C5-Concept_front..jpg',200000,'C5', 'CITROEN',0,40,0);
INSERT INTO sbnz.car  
VALUES (7,'prva',0,'2017-01-9','https://rentacarbelgrade.rs/wordpress/wp-content/uploads/2018/01/Citroen-C5.jpg',47000,'C5', 'CITROEN',0,40,0);
INSERT INTO sbnz.car  
VALUES (8,'prva',0,'2011-06-17','https://img.aaaauto.eu/thumb/900251879_2_1024x768x75.jpg',150000,'Kuga', 'FORD',0,30,0);
INSERT INTO sbnz.car  
VALUES (9,'prva',0,'2019-01-9','https://fordcms.blob.core.windows.net/media/Serbia/_Profiles/24613dd5/a6ac7cdd/1-1.jpg?v=636728649790000000',47000,'Kuga', 'FORD',0,50,0);
INSERT INTO sbnz.car  
VALUES (10,'prva',0,'2020-01-19','https://autorepublika.com/wp-content/uploads/2019/05/Ferrari.jpg',1000,'812 Superfast', 'FERARI',0,200,0);
INSERT INTO sbnz.car  
VALUES (11,'prva',0,'2018-10-19','https://autorepublika.com/wp-content/uploads/2019/06/xc90_armoured_-_exterior_1.jpg',47000,'XC90', 'VOLVO',0,70,0);
INSERT INTO sbnz.car  
VALUES (12,'prva',0,'2018-01-9','https://autorepublika.com/wp-content/uploads/2017/09/1-223.jpg',9000,'S63 AMG', 'MERCEDES',0,100,0);
INSERT INTO sbnz.car  
VALUES (13,'prva',0,'2017-07-9','https://img.drivemag.net/media/default/0001/96/bentley-continental-gt-convertible-7337-default-large.jpeg',170000,'Continental GT', 'BENTLI',0,200,0);
INSERT INTO sbnz.car  
VALUES (14,'prva',0,'2019-08-18','https://autorepublika.com/wp-content/uploads/2019/09/2-4.jpg',29000,'Chiron Sport', 'BUGATI',0,300,0);



INSERT INTO sbnz.reservation --dzabe
VALUES (100,'',20,'2020-06-24',0, 3000, 'REZERVISAN','2020-06-25', 1,100);

INSERT INTO sbnz.reservation --30%
VALUES (200,'',20,'2020-05-18',0, 3000, 'REZERVISAN','2020-05-19', 2,200);

INSERT INTO sbnz.reservation --50%
VALUES (300,'',20,'2020-05-15',0, 3000, 'REZERVISAN','2020-05-16', 3,300);
INSERT INTO sbnz.reservation 
VALUES (400,'',20,'2020-06-11', 0,3000, 'REZERVISAN','2020-06-12', 4,200);
INSERT INTO sbnz.reservation 
VALUES (500,'',20,'2020-05-13', 0,3000, 'REZERVISAN','2020-05-14', 4,200);
INSERT INTO sbnz.reservation --55%
VALUES (600,'',20,'2020-05-15',0, 3000, 'REZERVISAN','2020-05-16', 4,300);
INSERT INTO sbnz.reservation --60%
VALUES (700,'',20,'2020-05-15',0, 3000, 'REZERVISAN','2020-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (800,'',20,'2020-05-15',0, 3000, 'ZAVRSEN','2013-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (900,'',20,'2020-05-15',0, 3000, 'ZAVRSEN','2014-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (1000,'',20,'2015-05-15',0, 3000, 'ZAVRSEN','2015-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (1100,'',20,'2016-05-15',0, 3000, 'ZAVRSEN','2016-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (1200,'',20,'2017-05-15',0, 3000, 'ZAVRSEN','2017-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (1300,'',20,'2018-05-15',0, 3000, 'ZAVRSEN','2018-05-16', 5,300);
