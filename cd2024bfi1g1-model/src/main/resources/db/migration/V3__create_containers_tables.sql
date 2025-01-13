CREATE TABLE public.containers (
	cnt_id serial NOT NULL,
	cnt_name varchar NOT NULL,
	usr_id int4 NOT NULL,
	CONSTRAINT containers_pk PRIMARY KEY (cnt_id),
	CONSTRAINT containers_usr_user_fk FOREIGN KEY (usr_id) REFERENCES public.usr_user(usr_id)
);

ALTER TABLE public.devices ADD cnt_id int4;
ALTER TABLE public.devices ADD CONSTRAINT devices_containers_fk FOREIGN KEY (cnt_id) REFERENCES public.containers(cnt_id);