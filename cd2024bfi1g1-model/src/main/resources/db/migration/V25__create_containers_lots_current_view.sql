CREATE OR REPLACE VIEW public.containers_lots_current_view
AS SELECT cl_id,
    cnt_id,
    lot_id,
    cl_start_date,
    cl_end_date,
    CURRENT_DATE >= cl_start_date AND CURRENT_DATE <= COALESCE(cl_end_date, CURRENT_DATE::timestamp without time zone) AS cl_is_current
    FROM containers_lots cl;