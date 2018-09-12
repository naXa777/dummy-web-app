create table if not exists `Faculty`
(
	`id` bigint not null AUTO_INCREMENT
		primary key,
	`Name` varchar(746) not null
);

create table if not exists `Rate`
(
	`id` bigint not null AUTO_INCREMENT
		primary key,
	`Student_id` bigint not null,
	`Value` int null
);

create index `FK_Rate_StudentId`
	on `Rate` (`Student_id`);

create table if not exists `Student`
(
	`id` bigint not null AUTO_INCREMENT
		primary key,
	`Name` varchar(746) not null,
	`Photo` longblob null,
	`Faculty_id` bigint not null
);

create index `FK_Student_FacultyId`
	on `Student` (`Faculty_id`);
