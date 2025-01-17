CREATE TABLE public.companies (
	cmp_id serial NOT NULL,
	cmp_name varchar NOT NULL,
	CONSTRAINT companies_pk PRIMARY KEY (cmp_id)
);

INSERT INTO public.companies
(cmp_id, cmp_name)
VALUES(1, 'ColdCare');

ALTER TABLE public.usr_user ADD cmp_id int4 NULL;
ALTER TABLE public.usr_user ADD CONSTRAINT usr_user_companies_fk FOREIGN KEY (cmp_id) REFERENCES public.companies(cmp_id);

ALTER TABLE public.devices DROP CONSTRAINT devices_usr_user_fk;
ALTER TABLE public.devices DROP COLUMN usr_id;

ALTER TABLE public.devices ADD cmp_id int4 NULL;
ALTER TABLE public.devices ADD CONSTRAINT devices_companies_fk FOREIGN KEY (cmp_id) REFERENCES public.companies(cmp_id);