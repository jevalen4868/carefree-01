--	FAMILY_ID identity,
--	FAMILY_NAME varchar(512) not null,
--	CREATED_AT timestamp not null,
--	THUMBNAIL_PHOTO_URL varchar(512) not null,
--	STANDARD_PHOTO_URL varchar(512) not null,
--	DESCRIPTION varchar(512) not null,
--	LEAVES_BEHIND int not null,
--	INCARCERATED_DATE date not null

insert into FAMILY (FAMILY_NAME, THUMBNAIL_PHOTO_URL, STANDARD_PHOTO_URL, DESCRIPTION, LEAVES_BEHIND, INCARCERATED_DATE)
values ('Valenzuela', 'http://sampleurl.com/thumbnail1', 'http://sampleurl.com/standard1', 'Triple Homicide1',
    1, '1989-01-01');

insert into FAMILY (FAMILY_NAME, THUMBNAIL_PHOTO_URL, STANDARD_PHOTO_URL, DESCRIPTION, LEAVES_BEHIND, INCARCERATED_DATE)
values ('Garcia', 'http://sampleurl.com/thumbnail2', 'http://sampleurl.com/standard2', 'Triple Homicide2',
    2, '1989-01-02');

insert into FAMILY (FAMILY_NAME, THUMBNAIL_PHOTO_URL, STANDARD_PHOTO_URL, DESCRIPTION, LEAVES_BEHIND, INCARCERATED_DATE)
values ('Tupac', 'http://sampleurl.com/thumbnail3', 'http://sampleurl.com/standard3', 'Triple Homicide3',
    3, '1989-01-03');

insert into FAMILY (FAMILY_NAME, THUMBNAIL_PHOTO_URL, STANDARD_PHOTO_URL, DESCRIPTION, LEAVES_BEHIND, INCARCERATED_DATE)
values ('Biggie', 'http://sampleurl.com/thumbnail4', 'http://sampleurl.com/standard4', 'Triple Homicide4',
    4, '1989-01-04');

