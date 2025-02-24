ALTER TABLE public.vehicles ADD usr_id int;
ALTER TABLE public.vehicles ADD CONSTRAINT vehicles_users_fk FOREIGN KEY (usr_id) REFERENCES public.usr_user(usr_id);

CREATE TABLE public.transport_states (
    tst_id int PRIMARY KEY,
    tst_name varchar NOT NULL
);

INSERT INTO public.transport_states (tst_id, tst_name) VALUES
    (1, 'PENDING'),
    (2, 'INITIATED'),
    (3, 'FINISHED');

ALTER TABLE public.transports ADD COLUMN tst_id int NOT NULL DEFAULT 1;
ALTER TABLE public.transports ADD CONSTRAINT transports_states_fk FOREIGN KEY (tst_id) REFERENCES public.transport_states(tst_id);

ALTER TABLE public.transports ADD usr_id int;
ALTER TABLE public.transports ADD CONSTRAINT transports_users_fk FOREIGN KEY (usr_id) REFERENCES public.usr_user(usr_id);