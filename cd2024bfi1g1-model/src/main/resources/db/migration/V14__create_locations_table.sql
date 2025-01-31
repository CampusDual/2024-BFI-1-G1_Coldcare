CREATE TABLE public.locations (
	loc_id serial NOT NULL,
	loc_name varchar NOT NULL,
	loc_address varchar NOT NULL,
	CONSTRAINT locations_pk PRIMARY KEY (loc_id)
);

ALTER TABLE public.locations ADD cmp_id int4 NULL;
ALTER TABLE public.locations ADD CONSTRAINT locations_companies_fk FOREIGN KEY (cmp_id) REFERENCES public.companies(cmp_id);