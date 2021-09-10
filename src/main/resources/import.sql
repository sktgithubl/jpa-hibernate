INSERT into COURSE(id, name, last_updated_date, created_date) VALUES (10001, 'Physics', sysdate(), sysdate());
INSERT into COURSE(id, name, last_updated_date, created_date) VALUES (10002, 'Math', sysdate(), sysdate());
INSERT into COURSE(id, name, last_updated_date, created_date) VALUES (10003, 'Computer Science', sysdate(), sysdate());

INSERT INTO PASSPORT(id, number) VALUES(40001, 'E123456');
INSERT INTO PASSPORT(id, number) VALUES(40002, 'N123457');
INSERT INTO PASSPORT(id, number) VALUES(40003, 'L123890');

INSERT into STUDENT(id, name, passport_id) VALUES(20001, 'Sourav', 40001);
INSERT into STUDENT(id, name, passport_id) VALUES(20002, 'Chirantan', 40002);
INSERT into STUDENT(id, name, passport_id) VALUES(20003, 'Radha', 40003);

INSERT INTO REVIEW(id, rating, description, course_id) VALUES(50001, '5', 'Great Course', 10001);
INSERT INTO REVIEW(id, rating, description, course_id) VALUES(50002, '4', 'Wonderful Course', 10001);
INSERT INTO REVIEW(id, rating, description, course_id) VALUES(50003, '5', 'Awesome Course', 10003);

INSERT INTO STUDENT_COURSE(course_id, student_id) VALUES(10001,20001);
INSERT INTO STUDENT_COURSE(course_id, student_id) VALUES(10001,20002);
INSERT INTO STUDENT_COURSE(course_id, student_id) VALUES(10001,20003);
INSERT INTO STUDENT_COURSE(course_id, student_id) VALUES(10003,20001);