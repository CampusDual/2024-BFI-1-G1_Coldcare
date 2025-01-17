CREATE TABLE public.clients (
	cli_id serial NOT NULL,
	cli_name varchar NOT NULL,
	CONSTRAINT clients_pk PRIMARY KEY (cli_id)
);

INSERT INTO public.clients
(cli_id, cli_name)
VALUES(1, 'no_client');

ALTER TABLE public.usr_user ADD cli_id int4 DEFAULT 1 NOT NULL;
ALTER TABLE public.usr_user ADD CONSTRAINT usr_user_clients_fk FOREIGN KEY (cli_id) REFERENCES public.clients(cli_id);