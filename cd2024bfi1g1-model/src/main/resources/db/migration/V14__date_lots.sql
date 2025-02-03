CREATE TABLE public.containers_lots (
	cl_id serial NOT NULL,
	cnt_id int4 NOT NULL,
	lot_id int4 NOT NULL,
	cl_start_date timestamp NOT NULL,
	cl_end_date timestamp NULL
);

ALTER TABLE public.containers_lots ADD CONSTRAINT containers_lots_pk PRIMARY KEY (cl_id);
ALTER TABLE public.containers_lots ADD CONSTRAINT containers_lots_containers_fk FOREIGN KEY (cnt_id) REFERENCES public.containers(cnt_id);
ALTER TABLE public.containers_lots ADD CONSTRAINT containers_lots_lots_fk FOREIGN KEY (lot_id) REFERENCES public.lots(lot_id);

ALTER TABLE measurements ADD COLUMN cl_id int4 null;

ALTER TABLE public.measurements ADD CONSTRAINT measurements_containers_lots_fk FOREIGN KEY (cl_id) REFERENCES public.containers_lots(cl_id);

INSERT into containers_lots (lot_id,cnt_id,cl_start_date,cl_end_date)
SELECT  lot_id,cnt_id, MIN(me_date), MAX(me_date) FROM measurements m WHERE cnt_id IS NOT NULL AND lot_id IS NOT NULL
group by lot_id,cnt_id;

UPDATE measurements m
	SET cl_id = cl.cl_id
FROM containers_lots cl
WHERE m.lot_id = cl.lot_id
	AND m.cnt_id = cl.cnt_id
	AND m.cnt_id IS NOT NULL
	AND m.lot_id IS NOT NULL;

ALTER TABLE measurements DROP COLUMN cnt_id;
ALTER TABLE measurements DROP COLUMN lot_id;