ALTER TABLE public.plan_prices ALTER COLUMN pp_fixed_price TYPE numeric(10, 2) USING pp_fixed_price::numeric(10, 2);
ALTER TABLE public.plan_prices ALTER COLUMN pp_dev_price TYPE numeric(10, 2) USING pp_dev_price::numeric(10, 2);
ALTER TABLE public.plan_prices ALTER COLUMN pp_bundle_price TYPE numeric(10, 2) USING pp_bundle_price::numeric(10, 2);
