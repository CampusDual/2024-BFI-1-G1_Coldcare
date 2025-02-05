CREATE TABLE public.products (
                                  pro_id serial NOT NULL,
                                  pro_name varchar NOT NULL,
                                  pro_min_temp float8 NOT NULL,
                                  pro_max_temp float8 NOT NULL,
                                  CONSTRAINT products_pk PRIMARY KEY (pro_id)
);
ALTER TABLE public.lots ADD pro_id int4 NULL;
ALTER TABLE public.lots ADD CONSTRAINT usr_lots_fk FOREIGN KEY (pro_id) REFERENCES public.products(pro_id);