CREATE TABLE public.plan_prices(
    pp_id serial NOT NULL,
    pp_fixed_price decimal(10,3) NOT NULL,
    pp_dev_price decimal(10,3) NOT NULL,
    pp_bundle_price decimal(10,3) NOT NULL,
    pp_bundle_requests int NOT NULL,
    pp_start timestamp NOT NULL,
    pp_end timestamp,
    pln_id integer NOT NULL,
    CONSTRAINT plan_prices_pk PRIMARY KEY (pp_id)
);
ALTER table public.plan_prices ADD CONSTRAINT prices_plans_fk FOREIGN KEY (pln_id) REFERENCES public.plans(pln_id);

INSERT INTO plans (pln_name)
VALUES('BASIC');

INSERT INTO public.plan_prices (pp_fixed_price, pp_dev_price, pp_bundle_price, pp_bundle_requests, pp_start, pln_id)
VALUES (4.99,5.99,20.67,1000,now(), 1);

ALTER TABLE public.companies ADD pln_id INTEGER;
ALTER table public.companies ADD CONSTRAINT companies_plans_fk FOREIGN KEY (pln_id) REFERENCES public.plans(pln_id);
ALTER TABLE public.companies ALTER COLUMN pln_id SET DEFAULT 1;
UPDATE companies
SET pln_id = 1
WHERE pln_id IS NULL;

ALTER TABLE public.bills ADD pp_id INTEGER;
ALTER table public.bills ADD CONSTRAINT bills_plan_prices_fk FOREIGN KEY (pp_id) REFERENCES public.plan_prices(pp_id);
ALTER TABLE public.bills ALTER COLUMN pp_id SET DEFAULT 1;
UPDATE bills
SET pp_id = 1
WHERE pp_id IS NULL;

UPDATE public.usr_role
SET rol_json_client_permission='{"menu": [{ "attr": "users", "visible": false, "enabled": false },{ "attr": "companies", "visible": false, "enabled": false },{ "attr": "devices-without-users", "visible": false, "enabled": false },{ "attr": "medidas", "visible": false, "enabled": false },{ "attr": "consumptions", "visible": false, "enabled": false },{ "attr": "plans", "visible": false, "enabled": false }]}'
WHERE rol_id=2;