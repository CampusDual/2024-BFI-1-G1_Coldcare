CREATE TABLE public.transfers (
	tra_id serial NOT NULL,
	tra_origin_cl int4 NOT NULL,
    tra_destiny_cl int4 NOT NULL,
	CONSTRAINT transfers_pk PRIMARY KEY (tra_id),
    CONSTRAINT tranfers_origin_containers_lots_fk FOREIGN KEY (tra_origin_cl) REFERENCES public.containers_lots(cl_id),
    CONSTRAINT tranfers_destiny_containers_lots_fk FOREIGN KEY (tra_destiny_cl) REFERENCES public.containers_lots(cl_id)
);