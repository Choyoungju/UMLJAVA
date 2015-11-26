--drop table employee;
--drop table department;
--drop table dept_locations;
--drop table project;
--drop table works_on;
--drop table dependent;
drop table concert;



--CREATE TABLE employee (
--  fname    varchar(15) not null, 
--  minit    varchar(1),
--  lname    varchar(15) not null,
--  ssn      char(9),
--  bdate    date,
--  address  varchar(30),
--  sex      char,
--  salary   decimal(10,2),
--  superssn char(9),
--  dno      int(4),
--  email    varchar(50),
--  primary key (ssn) );


  CREATE TABLE concert (
  title    varchar(15) not null, 
  artist    varchar(15),
  concertdate    date,
  place  varchar(30),
  genre varchar(30),
  qualityType    varchar(50),
  primary key (title) );
  
--  
--CREATE TABLE department (
--  dname        varchar(15) not null,
--  dnumber      int(4),
--  mgrssn       char(9) not null, 
--  mgrstartdate date,
--  primary key (dnumber),
--  unique (dname),
--  foreign key (mgrssn) references employee(ssn)
--);
--
--alter table employee add (
--  foreign key (dno) references department(dnumber),
--  foreign key (superssn) references employee(ssn)
--);
--
--CREATE TABLE dept_locations (
--  dnumber   int(4),
--  dlocation varchar(15), 
--  primary key (dnumber,dlocation),
--  foreign key (dnumber) references department(dnumber)
--);
--
--CREATE TABLE project (
--  pname      varchar(15) not null,
--  pnumber    int(4),
--  plocation  varchar(15),
--  dnum       int(4) not null,
--  primary key (pnumber),
--  unique (pname),
--  foreign key (dnum) references department(dnumber)
--);
--
--CREATE TABLE works_on (
--  essn   char(9),
--  pno    int(4),
--  hours  decimal(4,1),
--  primary key (essn,pno),
--  foreign key (essn) references employee(ssn),
--  foreign key (pno) references project(pnumber)
--);
--
--CREATE TABLE dependent (
--  essn           char(9),
--  dependent_name varchar(15),
--  sex            char,
--  bdate          date,
--  relationship   varchar(8),
--  primary key (essn,dependent_name),
--  foreign key (essn) references employee(ssn)
--);
--
--INSERT INTO employee VALUES ('James', 'E', 'Borg', '888665555', '1937-11-10', '���', 'M', 55000, null, null, null);
--INSERT INTO employee VALUES ('Franklin', 'T', 'Wong', '333445555', '1955-12-08', 'Houston,TX', 'M', 40000, '888665555', null, null);
--INSERT INTO employee VALUES ('Jennifer', 'S', 'Wallace', '987654321', '1941-06-20', 'Bellaire,TX', 'F', 43000, '888665555', null, null);
--

INSERT INTO concert VALUES ('�Ź���', '��������', '2015-11-10', '��Ͽ����� �����', 'hiphop', 'HD');
INSERT INTO concert VALUES ('Live wire', '������', '2015-11-23', 'COEX', 'rock', 'HD');
INSERT INTO concert VALUES ('���׶��', '�̼���', '2015-11-30', '��Ʈ��������', 'ballad', 'normal');

--
--INSERT INTO department VALUES ('Research', 5, '333445555', '1988-05-22');
--INSERT INTO department VALUES ('Administration', 4, '987654321', '1995-01-01');
--INSERT INTO department VALUES ('Headquarters', 1, '888665555', '1981-06-19');
--
--UPDATE employee SET DNO = 5 WHERE ssn = '333445555';
--UPDATE employee SET DNO = 4 WHERE ssn = '987654321';
--UPDATE employee SET DNO = 1 WHERE ssn = '888665555';
--
--INSERT INTO employee VALUES ('John', 'B', 'Smith', '123456789', '1965-01-09', 'Houston,TX', 'M', 30000, '333445555', 5, null);
--INSERT INTO employee VALUES ('Alicia', 'J', 'Zelaya', '999887777', '1968-01-19', 'Spring,TX', 'F', 25000, '987654321', 4, null);
--INSERT INTO employee VALUES ('Ramesh', 'K', 'Narayan', '666884444', '1962-09-15', 'Humble,TX', 'M', 38000, '333445555', 5, null);
--INSERT INTO employee VALUES ('Joyce', 'A', 'English', '453453453', '1972-07-31', 'Houston, TX', 'F', 25000, '333445555', 5, null);
--INSERT INTO employee VALUES ('Ahmad', 'V', 'Jabbar', '987987987', '1969-03-29', 'Houston,TX', 'M', 25000, '987654321', 4, null);
--
--INSERT INTO project VALUES ('ProductX', 1, 'Bellaire',  5);
--INSERT INTO project VALUES ('ProductY', 2, 'Sugarland', 5);
--INSERT INTO project VALUES ('ProductZ', 3, 'Houston', 5);
--INSERT INTO project VALUES ('Computerization', 10, 'Stafford', 4);
--INSERT INTO project VALUES ('Reorganization', 20, 'Houston', 1);
--INSERT INTO project VALUES ('Newbenefits', 30,  'Stafford', 4);
--
--INSERT INTO dept_locations VALUES (1, 'Houston');
--INSERT INTO dept_locations VALUES (4, 'Stafford');
--INSERT INTO dept_locations VALUES (5, 'Bellaire');
--INSERT INTO dept_locations VALUES (5, 'Sugarland');
--INSERT INTO dept_locations VALUES (5, 'Houston');
--
--INSERT INTO dependent VALUES ('333445555','Alice','F','1986-04-05','Daughter');
--INSERT INTO dependent VALUES ('333445555','Theodore','M','1983-10-25','Son');
--INSERT INTO dependent VALUES ('333445555','Joy','F','1958-05-03','Spouse');
--INSERT INTO dependent VALUES ('987654321','Abner','M','1942-02-28','Spouse');
--INSERT INTO dependent VALUES ('123456789','Michael','M','1988-01-04','Son');
--INSERT INTO dependent VALUES ('123456789','Alice','F', '1988-12-30','Daughter');
--INSERT INTO dependent VALUES ('123456789','Elizabeth','F','1967-05-05','Spouse');
--
--INSERT INTO works_on VALUES ('123456789', 1,  32.5);
--INSERT INTO works_on VALUES ('123456789', 2,  7.5);
--INSERT INTO works_on VALUES ('666884444', 3,  40.0);
--INSERT INTO works_on VALUES ('453453453', 1,  20.0);
--INSERT INTO works_on VALUES ('453453453', 2,  20.0);
--INSERT INTO works_on VALUES ('333445555', 2,  10.0);
--INSERT INTO works_on VALUES ('333445555', 3,  10.0);
--INSERT INTO works_on VALUES ('333445555', 10, 10.0);
--INSERT INTO works_on VALUES ('333445555', 20, 10.0);
--INSERT INTO works_on VALUES ('999887777', 30, 30.0);
--INSERT INTO works_on VALUES ('999887777', 10, 10.0);
--INSERT INTO works_on VALUES ('987987987', 10, 35.0);
--INSERT INTO works_on VALUES ('987987987', 30, 5.0);
--INSERT INTO works_on VALUES ('987654321', 30, 20.0);
--INSERT INTO works_on VALUES ('987654321', 20, 15.0);
--INSERT INTO works_on VALUES ('888665555', 20, null);
