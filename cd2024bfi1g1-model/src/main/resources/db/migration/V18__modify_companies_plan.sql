INSERT INTO plans (pln_id, pln_fxdprc, pln_devprc, pln_bndleprc, pln_bndlerequest, pln_name, pln_start)
VALUES(1, 5, 10, 10, 1000, 'BASIC', now());
ALTER TABLE public.companies ALTER COLUMN pln_id SET DEFAULT 1;
UPDATE companies
SET pln_id = 1
WHERE pln_id IS NULL;