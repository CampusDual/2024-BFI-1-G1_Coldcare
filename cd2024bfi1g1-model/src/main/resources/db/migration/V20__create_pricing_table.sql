CREATE TABLE public.pricing(
    prc_id serial NOT NULL,
    prc_fprc varchar NOT NULL,
    prc_dprc varchar NOT NULL,
    prc_bprc varchar NOT NULL,
    prc_breq varchar NOT NULL,
    prc_start timestamp NOT NULL,
    prc_end timestamp,
    pln_id integer NOT NULL,
    CONSTRAINT prc_pk PRIMARY KEY (prc_id)
);
ALTER table public.pricing ADD CONSTRAINT prices_plans_fk FOREIGN KEY (pln_id) REFERENCES public.plans(pln_id);