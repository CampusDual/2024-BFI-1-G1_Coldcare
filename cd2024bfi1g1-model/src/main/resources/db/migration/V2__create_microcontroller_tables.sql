CREATE TABLE measurements (
	me_id serial NOT NULL,
	me_temp float NOT NULL,
	me_humidity float NOT NULL,
	me_date timestamp NOT NULL,
	CONSTRAINT measurements_pk PRIMARY KEY (me_id)
);

CREATE TABLE devices (
	dev_id serial NOT NULL,
	dev_mac varchar NOT NULL,
	CONSTRAINT devices_pk PRIMARY KEY (dev_id)
);

ALTER TABLE measurements ADD dev_id integer NOT NULL;
ALTER TABLE measurements ADD CONSTRAINT measurements_devices_fk FOREIGN KEY (dev_id) REFERENCES devices(dev_id);
