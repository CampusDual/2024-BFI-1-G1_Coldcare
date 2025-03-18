CREATE TABLE public.transports(
    trp_id serial PRIMARY KEY,
    trp_origin int4 NOT NULL,
    trp_destination int4 NOT NULL,
    cnt_id int4 NOT NULL,
    vhc_id int4 NOT NULL,
    cmp_id int4 NOT NULL,
    trp_date timestamp NOT NULL
);

ALTER table public.transports ADD CONSTRAINT transports_containers_fk FOREIGN KEY (cnt_id) REFERENCES public.containers(cnt_id);
ALTER table public.transports ADD CONSTRAINT transports_vehicles_fk FOREIGN KEY (vhc_id) REFERENCES public.vehicles(vhc_id);
ALTER table public.transports ADD CONSTRAINT transports_locations_origin_fk FOREIGN KEY (trp_origin) REFERENCES public.locations(loc_id);
ALTER table public.transports ADD CONSTRAINT transports_locations_destination_fk FOREIGN KEY (trp_destination) REFERENCES public.locations(loc_id);
ALTER TABLE public.transports ADD CONSTRAINT transports_companies_fk FOREIGN KEY (cmp_id) REFERENCES public.companies(cmp_id);

UPDATE public.usr_role
SET rol_json_client_permission='{ "menu": [{ "attr": "contenedores", "visible": false, "enabled": false }, { "attr": "devices", "visible": false, "enabled": false }, { "attr": "lots", "visible": false, "enabled": false },{ "attr": "locations", "visible": false, "enabled": false }, { "attr": "vehicles", "visible": false, "enabled": false }, { "attr": "transports", "visible": false, "enabled": false } ] }'
WHERE rol_id=1;
