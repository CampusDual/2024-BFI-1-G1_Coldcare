ALTER TABLE public.devices ADD usr_id integer UNIQUE;
ALTER TABLE public.devices ADD CONSTRAINT devices_usr_user_fk FOREIGN KEY (usr_id) REFERENCES usr_user(usr_id);