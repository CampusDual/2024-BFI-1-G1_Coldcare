UPDATE public.usr_role
SET rol_json_client_permission='{"menu": [{ "attr": "contenedores", "visible": false, "enabled": false },{ "attr": "devices", "visible": false, "enabled": false },{ "attr": "lots", "visible": false, "enabled": false },{ "attr": "masters", "visible": false, "enabled": false },{ "attr": "transports", "visible": false, "enabled": false },{ "attr": "alerts", "visible": false, "enabled": false }]}'
WHERE rol_id=1;

UPDATE public.usr_role
SET rol_json_client_permission='{"menu": [{ "attr": "users", "visible": false, "enabled": false },{ "attr": "companies", "visible": false, "enabled": false },{ "attr": "devices-without-users", "visible": false, "enabled": false },{ "attr": "medidas", "visible": false, "enabled": false },{ "attr": "bills", "visible": false, "enabled": false }]}'
WHERE rol_id=2;