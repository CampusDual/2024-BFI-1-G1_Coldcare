CREATE TABLE public.lots (
       lots_id serial NOT NULL,
       lots_name varchar NOT NULL,
       usr_id int4 NOT NULL,
       CONSTRAINT lots_pk PRIMARY KEY (lots_id),
       CONSTRAINT lots_usr_user_fk FOREIGN KEY (usr_id) REFERENCES public.usr_user(usr_id)
);

ALTER TABLE public.devices ADD lots_id int4;
ALTER TABLE public.devices ADD CONSTRAINT devices_lots_fk FOREIGN KEY (lots_id) REFERENCES public.lots(lots_id);