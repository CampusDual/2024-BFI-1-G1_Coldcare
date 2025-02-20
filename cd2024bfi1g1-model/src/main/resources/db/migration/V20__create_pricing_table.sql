CREATE TABLE public.planprices(
    planprices_id serial NOT NULL,
    planprices_fixedprice int NOT NULL,
    planprices_devprice int NOT NULL,
    planprices_bundleprice int NOT NULL,
    planprices_bundlerequests int NOT NULL,
    planprices_start timestamp NOT NULL,
    planprices_end timestamp,
    pln_id integer NOT NULL,
    CONSTRAINT prc_pk PRIMARY KEY (prc_id)
);
ALTER table public.pricing ADD CONSTRAINT prices_plans_fk FOREIGN KEY (pln_id) REFERENCES public.plans(pln_id);

INSERT INTO plans (pln_id, pln_name)
VALUES(1,'BASIC');

INSERT INTO public.pricing (prc_id,prc_fprc, prc_dprc, prc_bprc, prc_breq, prc_start, pln_id)
VALUES (1,4,5,20,1000,now(), 1);

ALTER TABLE public.companies ADD pln_id INTEGER;
ALTER table public.companies ADD CONSTRAINT companies_plans_fk FOREIGN KEY (pln_id) REFERENCES public.plans(pln_id);
ALTER TABLE public.companies ALTER COLUMN pln_id SET DEFAULT 1;
UPDATE companies
SET pln_id = 1
WHERE pln_id IS NULL;

ALTER TABLE public.companies ADD pln_id INTEGER;
ALTER table public.companies ADD CONSTRAINT companies_plans_fk FOREIGN KEY (pln_id) REFERENCES public.plans(pln_id);
ALTER TABLE public.companies ALTER COLUMN pln_id SET DEFAULT 1;
UPDATE companies
SET pln_id = 1
WHERE pln_id IS NULL;

ALTER TABLE public.bills ADD planprices_id INTEGER;
ALTER table public.bills ADD CONSTRAINT bills_planprices_fk FOREIGN KEY (planprices_id) REFERENCES public.planprices(planprices_id);
ALTER TABLE public.bills ALTER COLUMN planprices_id SET DEFAULT 1;
UPDATE bills
SET planprices_id = 1
WHERE planprices_id IS NULL;