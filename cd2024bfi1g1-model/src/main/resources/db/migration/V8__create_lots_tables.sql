CREATE TABLE public.lots (
       lot_id serial NOT NULL,
       lot_name varchar NOT NULL,
       usr_id int4 NOT NULL,
       CONSTRAINT lot_pk PRIMARY KEY (lot_id),
       CONSTRAINT lot_usr_user_fk FOREIGN KEY (usr_id) REFERENCES public.usr_user(usr_id)
);

ALTER TABLE public.devices ADD lot_id int4;
ALTER TABLE public.devices ADD CONSTRAINT devices_lots_fk FOREIGN KEY (lot_id) REFERENCES public.lots(lot_id);