CREATE TABLE public.transports(
    trp_id serial PRIMARY KEY,
    trp_origin varchar NOT NULL,
    trp_destination varchar NOT NULL,
    cnt_id int4 NOT NULL,
    vhc_id int4 NOT NULL,
    loc_id int4,
    trp_date timestamp NOT NULL
);

ALTER table public.transports ADD CONSTRAINT transports_containers_fk FOREIGN KEY (cnt_id) REFERENCES public.containers(cnt_id);
ALTER table public.transports ADD CONSTRAINT transports_vehicles_fk FOREIGN KEY (vhc_id) REFERENCES public.vehicles(vhc_id);
ALTER table public.transports ADD CONSTRAINT transports_locations_fk FOREIGN KEY (loc_id) REFERENCES public.locations(loc_id);
