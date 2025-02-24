CREATE TABLE public.transports_coordinates (
	tc_id serial NOT NULL,
	tc_latitude float8 NOT NULL,
	tc_longitude float8 NOT NULL,
	tc_date timestamp DEFAULT now() NOT NULL,
	trp_id int4 NOT NULL,
	CONSTRAINT transports_coordinates_pk PRIMARY KEY (tc_id),
	CONSTRAINT transports_coordinates_transports_fk FOREIGN KEY (trp_id) REFERENCES public.transports(trp_id)
);