CREATE TABLE public.bills (
                              bil_id serial NOT NULL,
                              bil_month int NOT NULL,
                              bil_year int NOT NULL,
                              bil_measurements int NOT NULL,
                              bil_devices int NOT NULL,
                              bil_expense decimal(10,2) NOT NULL,
                              CONSTRAINT bills_pk PRIMARY KEY (bil_id)
);

ALTER TABLE public.bills ADD cmp_id integer NOT NULL;
ALTER TABLE public.bills ADD CONSTRAINT bills_companies_fk FOREIGN KEY (cmp_id) REFERENCES companies(cmp_id);
