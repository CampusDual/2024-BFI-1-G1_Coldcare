ALTER TABLE public.transports ADD trp_state varchar NOT NULL DEFAULT 'Pendiente';
UPDATE public.transports SET trp_state = 'Pendiente' WHERE trp_state IS NULL;

ALTER TABLE public.transports ADD usr_id int;