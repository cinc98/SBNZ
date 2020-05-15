INSERT INTO sbnz.user 
VALUES (100,'bronza', '123', '2020-06-10', 'stefan');
INSERT INTO sbnz.user 
VALUES (200,'bronza', '123', '2020-06-10', 'cinc');
INSERT INTO sbnz.user 
VALUES (300,'bronza', '123', '2020-06-10', 'miki');
INSERT INTO sbnz.user 
VALUES (400,'bronza', '123', '2020-06-10', 'mile');

INSERT INTO sbnz.car  
VALUES (1,'prva','2019-06-10','https://autorepublika.com/wp-content/uploads/2019/10/20.jpg',27000,'Golf', 'VW',0,20,0);
INSERT INTO sbnz.car  
VALUES (2,'prva','2020-01-20','https://autorepublika.com/wp-content/uploads/2019/01/1-299.jpg',19000,'3', 'BMW',0,35,0);
INSERT INTO sbnz.car  
VALUES (3,'prva','2017-08-9','https://autodata24.com/i/opel/astra/astra-k/large/d208939963fee74fa5fd1608eff0aa0e.jpg',120000,'Astra', 'OPEL',0,15,0);
INSERT INTO sbnz.car  
VALUES (4,'prva','2018-02-17','https://i.ytimg.com/vi/_fokJ36y8wM/maxresdefault.jpg',30000,'Insignia', 'OPEL',0,25,0);
INSERT INTO sbnz.car  
VALUES (5,'prva','2020-01-9','https://autorepublika.com/wp-content/uploads/2019/08/bmw-x5-protection2.jpg',5000,'X5', 'BMW',0,50,0);

INSERT INTO sbnz.reservation --dzabe
VALUES (100,20,'2020-06-24',0, 3000, 'REZERVISAN','2020-06-25', 1,100);

INSERT INTO sbnz.reservation --30%
VALUES (200,20,'2020-05-18',0, 3000, 'REZERVISAN','2020-05-19', 2,200);

INSERT INTO sbnz.reservation --50%
VALUES (300,20,'2020-05-15',0, 3000, 'REZERVISAN','2020-05-16', 3,300);
INSERT INTO sbnz.reservation 
VALUES (400,20,'2020-06-11', 0,3000, 'REZERVISAN','2020-06-12', 4,200);
INSERT INTO sbnz.reservation 
VALUES (500,20,'2020-05-13', 0,3000, 'REZERVISAN','2020-05-14', 4,200);
INSERT INTO sbnz.reservation --55%
VALUES (600,20,'2020-05-15',0, 3000, 'REZERVISAN','2020-05-16', 4,300);
INSERT INTO sbnz.reservation --60%
VALUES (700,20,'2020-05-15',0, 3000, 'REZERVISAN','2020-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (800,20,'2013-05-15',0, 3000, 'ZAVRSEN','2013-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (900,20,'2014-05-15',0, 3000, 'ZAVRSEN','2014-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (1000,20,'2015-05-15',0, 3000, 'ZAVRSEN','2015-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (1100,20,'2016-05-15',0, 3000, 'ZAVRSEN','2016-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (1200,20,'2017-05-15',0, 3000, 'ZAVRSEN','2017-05-16', 5,300);
INSERT INTO sbnz.reservation 
VALUES (1300,20,'2018-05-15',0, 3000, 'ZAVRSEN','2018-05-16', 5,300);
