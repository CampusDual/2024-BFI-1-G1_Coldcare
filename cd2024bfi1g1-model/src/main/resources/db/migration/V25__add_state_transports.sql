CREATE TABLE public.transport_states (
    trp_state_id int PRIMARY KEY,
    trp_state varchar NOT NULL
);

INSERT INTO public.transport_states (trp_state_id, trp_state) VALUES
(1, 'PENDING'),
(2, 'INITIATED'),
(3, 'FINISHED');

ALTER TABLE public.transports ADD COLUMN trp_state_id int NOT NULL DEFAULT 1;

ALTER TABLE public.transports ADD CONSTRAINT transports_states_fk FOREIGN KEY (trp_state_id) REFERENCES public.transport_states(trp_state_id);

ALTER TABLE public.transports ADD CONSTRAINT transports_users_fk FOREIGN KEY (usr_id) REFERENCES public.usr_user(usr_id);
