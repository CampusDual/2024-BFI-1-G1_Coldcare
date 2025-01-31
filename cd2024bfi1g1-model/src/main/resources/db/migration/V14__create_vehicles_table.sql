CREATE TABLE public.vehicles(
    vhc_id serial NOT NULL,
    vhc_plate varchar NOT NULL,
    cmp_id int4,
    CONSTRAINT vhc_pk PRIMARY KEY (vhc_id)

);

ALTER table public.vehicles ADD CONSTRAINT companies_vehicles_fk FOREIGN KEY (cmp_id) REFERENCES public.companies(cmp_id);
