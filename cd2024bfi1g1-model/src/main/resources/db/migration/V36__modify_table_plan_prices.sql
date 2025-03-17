ALTER TABLE public.plan_prices  DROP COLUMN pp_fixed_price;
ALTER TABLE public.plan_prices  DROP COLUMN pp_dev_price;
ALTER TABLE public.plan_prices  DROP COLUMN pp_bundle_price;

ALTER TABLE public.plan_prices  ADD COLUMN pp_fixed_price decimal(10,2) NOT NULL;
ALTER TABLE public.plan_prices  ADD COLUMN pp_dev_price decimal(10,2) NOT NULL;
ALTER TABLE public.plan_prices  ADD COLUMN pp_bundle_price decimal(10,2) NOT NULL;
