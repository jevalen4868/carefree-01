create table Family (
	FAMILY_ID identity,
	FAMILY_NAME varchar(512) not null,
	CREATED_AT timestamp not null default CURRENT_TIMESTAMP,
	THUMBNAIL_PHOTO_URL varchar(512) not null,
	STANDARD_PHOTO_URL varchar(512) not null,
	DESCRIPTION varchar(512) not null,
	LEAVES_BEHIND int not null,
	INCARCERATED_DATE date not null
);