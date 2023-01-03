Truncate Table station, company;
INSERT INTO company(id,name) VALUES (1,'A');
INSERT INTO company(id,name,parent_id) VALUES (2,'B',1);
INSERT INTO company(id,name,parent_id) VALUES (3,'C',2);
INSERT INTO company(id,name,parent_id) VALUES (4,'D',3);
INSERT INTO company(id,name) VALUES (5,'E');