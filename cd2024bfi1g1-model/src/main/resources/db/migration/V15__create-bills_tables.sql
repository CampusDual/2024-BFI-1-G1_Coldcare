CREATE TABLE public.bills (
                              bil_id serial NOT NULL,
                              bil_date timestamp NOT NULL,
                              bil_measurements float NOT NULL,
                              bil_devices float NOT NULL,
                              bil_expense float NOT NULL,
                              CONSTRAINT bills_pk PRIMARY KEY (bil_id)
);


ALTER TABLE public.bills ADD cmp_id integer NOT NULL;
ALTER TABLE public.bills ADD CONSTRAINT bills_companies_fk FOREIGN KEY (cmp_id) REFERENCES companies(cmp_id);
