CREATE TABLE public.plans(
    pln_id serial NOT NULL,
    pln_fxdprc varchar NOT NULL,
    pln_devprc varchar NOT NULL,
    pln_bndleprc varchar NOT NULL,
    pln_bndlerequest varchar NOT NULL,
    pln_name varchar NOT NULL,
    pln_start timestamp NOT NULL,
    pln_end timestamp,
    CONSTRAINT pln_pk PRIMARY KEY (pln_id)
);
ALTER TABLE public.companies ADD pln_id int4;
ALTER table public.companies ADD CONSTRAINT companies_plans_fk FOREIGN KEY (pln_id) REFERENCES public.plans(pln_id);