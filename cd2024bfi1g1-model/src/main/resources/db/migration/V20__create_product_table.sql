CREATE TABLE public.products (
                                  pro_id serial NOT NULL,
                                  pro_name varchar NOT NULL,
                                  pro_min_temp float8 NOT NULL,
                                  pro_max_temp float8 NOT NULL,
                                  CONSTRAINT products_pk PRIMARY KEY (pro_id)
);
ALTER TABLE public.lots ADD pro_id int4 NULL;
ALTER TABLE public.lots ADD CONSTRAINT usr_lots_fk FOREIGN KEY (pro_id) REFERENCES public.products(pro_id);

UPDATE public.usr_role
SET rol_json_client_permission='{ "menu": [{ "attr": "contenedores", "visible": false, "enabled": false }, { "attr": "devices", "visible": false, "enabled": false }, { "attr": "lots", "visible": false, "enabled": false },{ "attr": "locations", "visible": false, "enabled": false }, { "attr": "vehicles", "visible": false, "enabled": false }, { "attr": "transports", "visible": false, "enabled": false },{ "attr": "products", "visible": false, "enabled": false } ] }'
WHERE rol_id=1;
